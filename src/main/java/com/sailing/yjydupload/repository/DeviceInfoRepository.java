package com.sailing.yjydupload.repository;

import com.sailing.yjydupload.entity.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 设备信息数据持久化层
 * create by en
 * at 2018/12/11 10:20
 **/
@Repository
public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, String> {

//    @Query(nativeQuery = true, value = "SELECT T.* FROM DEVICE_INFO VERSIONS BETWEEN TIMESTAMP MINVALUE AND MAXVALUE T")
//    public List<DeviceInfo> queryDeviceInfoLast();

    List<DeviceInfo> findByDeviceId(String deviceId);

}
