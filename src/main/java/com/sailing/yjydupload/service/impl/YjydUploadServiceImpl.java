package com.sailing.yjydupload.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sailing.yjydupload.config.DataFilter;
import com.sailing.yjydupload.config.UploadInfoConfig;
import com.sailing.yjydupload.converter.DeviceInfoConverter;
import com.sailing.yjydupload.dto.CameraDto;
import com.sailing.yjydupload.dto.ResponseStatusDto;
import com.sailing.yjydupload.dto.UploadRequestDto;
import com.sailing.yjydupload.dto.UploadResponseDto;
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
    public void uploadDevice(List<DeviceInfo> deviceInfoList) {
        // 1. 判断数据是否为空
        if (deviceInfoList == null || deviceInfoList.size() == 0) {
            log.info("【数据上传】无数据，不用上传！");
            return;
        }
        log.info("【数据上传】开始上传数据，size：{}", deviceInfoList.size());
        // 2. 构建请求接口的参数 UploadRequestDto
        UploadRequestDto uploadRequestDto = new UploadRequestDto();
        List<CameraDto> cameraDtoList = deviceInfoList.stream().map(deviceInfo ->
            DeviceInfoConverter.converter(deviceInfo)).collect(Collectors.toList());
        uploadRequestDto.setUSERNAME(uploadInfoConfig.getUsername());
        uploadRequestDto.setPASSWORD(uploadInfoConfig.getPassword());
        uploadRequestDto.setOPREATEUSER(uploadInfoConfig.getOpreateUser());
        uploadRequestDto.setLOCALSERVERNAME(uploadInfoConfig.getLocalServerName());

        // 打印上传参数
        log.info("【数据上传】请求参数：{}", JSON.toJSONString(uploadRequestDto));
        uploadRequestDto.setCameraList(cameraDtoList);
        // 3. 数据上传
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
        // 4. 上传结果打印日志
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
            uploadDevice(secondUploadDeviceInfoList);
        }
    }

    @PostConstruct
    @Scheduled(cron = "0 0 0 ? * 1")    // 每个星期一的0点执行一次
    public void uploadTask() {
        log.info("【数据上传任务】任务开始！");
        uploadDevice(queryDevice());
        log.info("【数据上传任务】任务结束！");
    }

}
