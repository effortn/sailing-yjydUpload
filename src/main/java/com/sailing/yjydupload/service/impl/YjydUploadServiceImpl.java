package com.sailing.yjydupload.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sailing.yjydupload.config.DataFilter;
import com.sailing.yjydupload.config.UploadInfoConfig;
import com.sailing.yjydupload.converter.DeviceInfoConverter;
import com.sailing.yjydupload.dto.*;
import com.sailing.yjydupload.entity.DeviceInfo;
import com.sailing.yjydupload.repository.DeviceInfoRepository;
import com.sailing.yjydupload.service.YjydUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class YjydUploadServiceImpl implements YjydUploadService {

	@Autowired
	private UploadInfoConfig uploadInfoConfig;
	
    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Autowired
    private DataFilter dataFilter;
    
    @Override
    public List<DeviceInfo> queryDevice() {
        // 对查找出的数据进行筛选，根据配置的摄像机编码格式类型
        return deviceInfoRepository.findAll().stream()
                .filter(deviceInfo -> dataFilter.filter(deviceInfo))
                .collect(Collectors.toList());
    }

    @Override
    public void uploadDevice(List<DeviceInfo> deviceInfoList, List<String> sbbmList) {
        // 1. 判断数据是否为空
        if (deviceInfoList == null || deviceInfoList.size() == 0) {
            log.info("【数据上传】无数据，不用上传！");
            return;
        }
        log.info("【数据上传】开始上传数据，size：{}", deviceInfoList.size());
        // 打印市局拥有的设备编号
        log.info("【数据上传】一机一档查询接口获取设备编号列表：{}", sbbmList);
        // 3. 构建请求接口的参数 UploadRequestDto
        UploadRequestDto uploadRequestDto = new UploadRequestDto();
        // 先用 filter 进行数据过滤，然后用 map 转换数据
        List<CameraDto> cameraDtoList = deviceInfoList.stream().filter(deviceInfo -> sbbmList.contains(deviceInfo.getDeviceId()))
                .map(deviceInfo -> DeviceInfoConverter.converter(deviceInfo)).collect(Collectors.toList());
        uploadRequestDto.setUSERNAME(uploadInfoConfig.getUsername());
        uploadRequestDto.setPASSWORD(uploadInfoConfig.getPassword());
        uploadRequestDto.setOPREATEUSER(uploadInfoConfig.getOpreateUser());
        uploadRequestDto.setLOCALSERVERNAME(uploadInfoConfig.getLocalServerName());

        // 打印上传参数
        log.info("【数据上传】请求参数：{}", JSON.toJSONString(uploadRequestDto));
        uploadRequestDto.setCameraList(cameraDtoList);
        // 4. 数据上传
        RestTemplate restTemplate = new RestTemplate();
        // 返回的数据不能直接用实体类接收
//        UploadResponseDto responseDto1 = restTemplate.postForObject(uploadInfoConfig.getUrl(),
//                uploadRequestDto, UploadResponseDto.class);
//        restTemplate.postForEntity()
        /*ResponseEntity<UploadResponseDto> responseDto = restTemplate.postForEntity(uploadInfoConfig.getUrl(),
                uploadRequestDto, UploadResponseDto.class);*/
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(uploadInfoConfig.getUrl(),
                uploadRequestDto, String.class);
        UploadResponseDto responseDto = JSONObject.parseObject(responseEntity.getBody(), UploadResponseDto.class);
        // 上传结果打印日志
        List<ResponseStatusDto> responseStatusList = responseDto.getResponseStatusList();
        // 5. 定义变量记录失败的ID号，如果出现错误，重新上传
        List<String> failedIdList = new CopyOnWriteArrayList<>();
        //  定义变量记录失败条数，打印日志
        AtomicInteger failedInt = new AtomicInteger(0);
        responseStatusList.stream().forEach(responseStatusDto -> {
            if (!"0".equals(responseStatusDto.getStatusCode())) {
                failedIdList.add(responseStatusDto.getID());
                failedInt.getAndIncrement();
                log.error("【数据上传】部分数据上传失败，信息：{}", responseStatusDto);
            }
        });
        if (failedInt.get() == 0) {
            log.info("【数据上传】上传结束，成功上传{}条数据！", deviceInfoList.size());
        } else {
            log.info("【数据上传】上传失败，上传失败{}条数据！过滤错误数据，准备下次上传", deviceInfoList.size());
            // 6. 对上传错误的数据进行筛除，重新上传
            List<DeviceInfo> secondUploadDeviceInfoList = deviceInfoList.stream().filter(deviceInfo ->
                    !failedIdList.contains(deviceInfo.getDeviceId())).collect(Collectors.toList());
            uploadDevice(secondUploadDeviceInfoList, sbbmList);
        }
    }

    @Override
    public List<String> queryData(QueryRequestDto requestDto) {
        // 2. 发送请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(uploadInfoConfig.getQueryUrl(),
                requestDto, String.class);
        QueryResponseDto responseDto = JSONObject.parseObject(responseEntity.getBody(), QueryResponseDto.class);
        // 3. 判断请求是否成功
        if (!"200".equals(responseDto.getResultCode())) {
            log.error("【数据拉取】获取数据失败, response:{}", responseDto);
            return null;
        }
        // 4. 数据为空，直接返回
        if (responseDto.getData() == null || responseDto.getData().size() == 0) {
            return null;
        }
        // 5. 根据返回条数判断是否需要进行再次发送
        int returnSize = responseDto.getData().size();
        if (returnSize < Integer.valueOf(requestDto.getLimit())) {
            // 5-1. 若返回数据条数小于请求条数，说明数据已经拉取完毕，返回数据
            return responseDto.getData().stream().map(localCameraDto -> {
                    return localCameraDto.SBBM;
                }).collect(Collectors.toList());
        }
        // 5-2. 若返回数据条数等于请求条数，说明可能还有数据需要拉取，继续请求
        requestDto.setOffset(String.valueOf(Integer.valueOf(requestDto.getOffset()) + 1));
        // 6. 将之后请求获取的数据和此次请求获取的数据合并后返回

        List<String> nextSbbmList = queryData(requestDto);
        List<String> sbbmList = responseDto.getData().stream().map(localCameraDto -> {
                return localCameraDto.SBBM;
            }).collect(Collectors.toList());
        sbbmList.addAll(nextSbbmList);
        return sbbmList;
    }

    @PostConstruct
    @Scheduled(cron = "0 0 0 ? * 1")    // 每个星期一的0点执行一次
    public void uploadTask() {
        log.info("【数据上传任务】任务开始！");
        // 1. 查询一机一档数据，构建查询参数
        QueryRequestDto queryRequestDto = new QueryRequestDto();
        queryRequestDto.setXzqy(uploadInfoConfig.getXzqh());
        queryRequestDto.setUserName(uploadInfoConfig.getUsername());
        queryRequestDto.setPassWord(uploadInfoConfig.getPassword());
        queryRequestDto.setHostName(uploadInfoConfig.getLocalServerName());
        queryRequestDto.setLimit("5000");
        queryRequestDto.setOffset("1");
        // todo
        // 打印查询接口请求参数
        log.info("【数据上传】一机一档查询接口参数：{}", queryRequestDto);
        List<String> sbbmList = new CopyOnWriteArrayList<>(queryData(queryRequestDto));
        if (sbbmList == null || sbbmList.size() == 0) {
            log.error("【数据上传】一机一档查询接口获取数据失败！");
            return;
        }
        uploadDevice(queryDevice(), sbbmList);
        log.info("【数据上传任务】任务结束！");
    }

}
