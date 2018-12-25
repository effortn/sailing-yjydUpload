package com.sailing.yjydupload.converter;

import com.sailing.yjydupload.dto.CameraDto;
import com.sailing.yjydupload.entity.DeviceInfo;

/**
 * 设备信息实体类到DTO的转换器
 * create by en
 * at 2018/12/12 13:43
 **/
public class DeviceInfoConverter {

    /**
     * 设备信息实体类到DTO的转换器方法
     * @param deviceInfo    设备信息实体类
     * @return  CameraDto    设备信息DTO
     */
    public static CameraDto converter(DeviceInfo deviceInfo) {
        CameraDto cameraDto = new CameraDto();
        cameraDto.setSBBM(deviceInfo.getDeviceId());
        cameraDto.setSBMC(deviceInfo.getDeviceName());
        cameraDto.setSBCS(deviceInfo.getDeviceFirm());
        cameraDto.setSBXH(deviceInfo.getDeviceModel());
        cameraDto.setSBZT(deviceInfo.getDeviceStatus());

        cameraDto.setKSJL(String.valueOf(deviceInfo.getVisualDistance()));
        cameraDto.setLWSX(deviceInfo.getNetworkAttr());
        cameraDto.setLXBCTS(String.valueOf(deviceInfo.getSaveDays()));
        cameraDto.setSFDWGX(deviceInfo.getIsExternal());
        cameraDto.setSFJRSJPT(deviceInfo.getIsJoinPlat());
        cameraDto.setSPFBL(deviceInfo.getVideoResolutionRatio());
        cameraDto.setSPXHLX(deviceInfo.getVideoSignalType());
        cameraDto.setSSBMHY(deviceInfo.getSubDepartment());
        cameraDto.setSSXQGAJG(deviceInfo.getDeviceAreaNum());
        cameraDto.setSXJBMGS(deviceInfo.getCameraNumFormat());
        cameraDto.setSXJGNLX(deviceInfo.getCameraFuncType());
        cameraDto.setSXJLX(deviceInfo.getCameraType());
        cameraDto.setSXJWZLX(deviceInfo.getCameraPositionType());

        cameraDto.setWD(deviceInfo.getLatitude());

        // 行政区划传成6位,如果大于六位,只截取前六位
        String deviceArea = deviceInfo.getDeviceArea();
        if (deviceArea.length() > 6) {
            deviceArea = deviceArea.substring(0, 6);
        }
        cameraDto.setXZQY(deviceArea);

        cameraDto.setJSYYLB(deviceInfo.getUseType());
        cameraDto.setJSFW(deviceInfo.getMonitoringDirection());
        cameraDto.setJPBH(deviceInfo.getKeyNum());
        cameraDto.setJKDWLX(deviceInfo.getMonitorType());
        cameraDto.setJD(deviceInfo.getLongitude());
        cameraDto.setIPV4(deviceInfo.getIpv4());
        cameraDto.setGLDW(deviceInfo.getDeviceOrg());
        cameraDto.setGLDWLXFS(deviceInfo.getInformation());

        cameraDto.setDYCCSBTD(deviceInfo.getStorageDeviceChan());
        cameraDto.setDYCCSBIP(deviceInfo.getStorageDeviceIp());
        cameraDto.setDWSC(deviceInfo.getPointName());
        cameraDto.setBGSX(deviceInfo.getFillLightAttr());
        cameraDto.setAZGD(String.valueOf(deviceInfo.getInstallHeight()));
        cameraDto.setAZSJ(deviceInfo.getInstallTime());
        cameraDto.setAZDZ(deviceInfo.getInstallAddress());
        return cameraDto;
    }

}
