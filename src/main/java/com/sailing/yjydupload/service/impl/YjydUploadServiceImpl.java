package com.sailing.yjydupload.service.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.sailing.yjydupload.config.UploadInfoConfig;
import com.sailing.yjydupload.dto.CameraDto;
import com.sailing.yjydupload.dto.ResponseStatusDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sailing.yjydupload.config.YjydUploadConfig;
import com.sailing.yjydupload.converter.DeviceInfoConverter;
import com.sailing.yjydupload.dto.UploadRequestDto;
import com.sailing.yjydupload.dto.UploadResponseDto;
import com.sailing.yjydupload.entity.DeviceInfo;
import com.sailing.yjydupload.repository.DeviceInfoRepository;
import com.sailing.yjydupload.service.YjydUploadService;

@Slf4j
@Service
public class YjydUploadServiceImpl implements YjydUploadService {

	@Autowired
	private UploadInfoConfig uploadInfoConfig;
	
    @Autowired
    private DeviceInfoRepository deviceInfoRepository;
    
    @Override
    public List<DeviceInfo> queryDevice() {
        return deviceInfoRepository.findAll();
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
        List<CameraDto> cameraDtoList = deviceInfoList.stream().map(deviceInfo -> {
            return DeviceInfoConverter.converter(deviceInfo);
        }).collect(Collectors.toList());
        uploadRequestDto.setCameraList(cameraDtoList);
        uploadRequestDto.setUSERNAME(uploadInfoConfig.getUsername());
        uploadRequestDto.setPASSWORD(uploadInfoConfig.getPassword());
        uploadRequestDto.setOPREATEUSER(uploadInfoConfig.getOpreateUser());
        uploadRequestDto.setLOCALSERVERNAME(uploadInfoConfig.getLocalServerName());

        // 模拟上传接口
        log.info("【数据上传】请求参数：{}", JSON.toJSONString(uploadRequestDto));
        // 3. 数据上传
        /*RestTemplate restTemplate = new RestTemplate();
        UploadResponseDto responseDto = restTemplate.postForObject(uploadInfoConfig.getUrl(),
                JSON.toJSONString(uploadRequestDto), UploadResponseDto.class);

        // 4. 上传结果打印日志
        List<ResponseStatusDto> responseStatusList = responseDto.getResponseStatusList();
        // 定义变量记录失败条数
        AtomicInteger failedInt = new AtomicInteger(0);
        responseStatusList.stream().forEach(responseStatusDto -> {
            if (!"0".equals(responseStatusDto.getStatusCode())) {
                failedInt.getAndIncrement();
                log.error("【数据上传】部分数据上传失败，信息：{}", responseStatusDto);
            }
        });

        log.info("【数据上传】上传结束，成功上传{}条数据！", (deviceInfoList.size() - failedInt.get()));*/
    }

    @Scheduled(cron = "0 0 0 ? * 1")    // 每个星期一的0点执行一次
    public void uploadTask() {
        log.info("【数据上传任务】任务开始！");
        uploadDevice(queryDevice());
        log.info("【数据上传任务】任务结束！");
    }

}
