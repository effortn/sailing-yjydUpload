package com.sailing.yjydupload.service;

import com.sailing.yjydupload.dto.QueryRequestDto;
import com.sailing.yjydupload.dto.QueryResponseDto;
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
     * @param sbbmList           市局已有设备编码列表
     */
    void uploadDevice(List<DeviceInfo> deviceInfoList, List<String> sbbmList);

    /**
     * 查询一机一档数据，返回设备编号列表
     * @param requestDto    接口参数
     * @return  设备编号列表
     */
    List<String> queryData(QueryRequestDto requestDto);

}
