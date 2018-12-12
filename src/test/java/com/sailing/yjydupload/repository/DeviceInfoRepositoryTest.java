package com.sailing.yjydupload.repository;

import com.sailing.yjydupload.YjydUploadApplicationTests;
import com.sailing.yjydupload.entity.DeviceInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

public class DeviceInfoRepositoryTest extends YjydUploadApplicationTests {

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Test
    public void findAll() {
        List<DeviceInfo> deviceInfoList = deviceInfoRepository.findAll();
        Assert.assertTrue(deviceInfoList.size() != 0);
    }

}