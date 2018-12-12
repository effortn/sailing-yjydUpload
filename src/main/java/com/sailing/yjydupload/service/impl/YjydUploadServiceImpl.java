package com.sailing.yjydupload.service.impl;

import com.sailing.yjydupload.entity.DeviceInfo;
import com.sailing.yjydupload.repository.DeviceInfoRepository;
import com.sailing.yjydupload.service.YjydUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class YjydUploadServiceImpl implements YjydUploadService {

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Override
    public List<DeviceInfo> queryDevice() {
        return deviceInfoRepository.findAll();
    }

    @Override
    public void uploadDevice(List<DeviceInfo> deviceInfoList) {
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.postForObject();
    }



}
