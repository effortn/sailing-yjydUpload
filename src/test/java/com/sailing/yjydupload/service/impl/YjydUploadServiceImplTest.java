package com.sailing.yjydupload.service.impl;

import com.sailing.yjydupload.YjydUploadApplicationTests;
import com.sailing.yjydupload.entity.DeviceInfo;
import com.sailing.yjydupload.service.YjydUploadService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class YjydUploadServiceImplTest extends YjydUploadApplicationTests {

    @Autowired
    private YjydUploadService yjydUploadService;

    @Test
    public void uploadDevice() {
        List<DeviceInfo> deviceInfos = yjydUploadService.queryDevice();
        log.info("【数据】:{}", deviceInfos);
        yjydUploadService.uploadDevice(deviceInfos);
    }
}