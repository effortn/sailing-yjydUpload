package com.sailing.yjydupload.service;

import com.sailing.yjydupload.entity.DeviceInfo;

import java.util.List;

/**
 * 一机一档数据上传 Service
 * create by en
 * at 2018/12/11 10:19
 **/
public interface YjydUploadService {

    /**
     * 查询设备信息
     * @return List<DeviceInfo>
     */
    List<DeviceInfo> queryDevice();

    /**
     * 上传设备信息
     * @param deviceInfoList    设备信息
     */
    void uploadDevice(List<DeviceInfo> deviceInfoList);

}
