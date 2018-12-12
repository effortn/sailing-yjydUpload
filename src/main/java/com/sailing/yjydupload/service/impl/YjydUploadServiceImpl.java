package com.sailing.yjydupload.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sailing.yjydupload.config.YjydUploadConfig;
import com.sailing.yjydupload.converter.DeviceInfoConverter;
import com.sailing.yjydupload.dto.UploadRequestDto;
import com.sailing.yjydupload.dto.UploadResponseDto;
import com.sailing.yjydupload.entity.DeviceInfo;
import com.sailing.yjydupload.repository.DeviceInfoRepository;
import com.sailing.yjydupload.service.YjydUploadService;

@Service
public class YjydUploadServiceImpl implements YjydUploadService {

	@Autowired
	private YjydUploadConfig yjydUploadConfig;
	
    @Autowired
    private DeviceInfoRepository deviceInfoRepository;
    
    @Override
    public List<DeviceInfo> queryDevice() {
        return deviceInfoRepository.findAll();
    }

    @Override
    public void uploadDevice(List<DeviceInfo> deviceInfoList) {
        RestTemplate restTemplate = new RestTemplate();
        UploadRequestDto uploadRequestDto = new UploadRequestDto();
        deviceInfoList.stream().forEach(deviceInfo -> {
        	DeviceInfoConverter.converter(deviceInfo);
        });
        UploadResponseDto responseDto = restTemplate.postForObject(yjydUploadConfig.getUrl(),uploadRequestDto,UploadResponseDto.class);
    }



}
