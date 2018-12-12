package com.sailing.yjydupload.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 设备信息实体类
 * create by en
 * at 2018/12/11 10:19
 **/
@Table(name = "DEVICE_INFO")
@Entity
@Data
public class DeviceInfo {

    /**
     * 序号（数字或字母）
     */
    @Id
    private String id;

    /**
     * 设备ID（默认20位数字）
     */
    private String deviceId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备厂商
     */
    private String deviceFirm;

    /**
     * 行政区划
     */
    private String deviceArea;

    /**
     * 监控点类型
     */
    private String monitorType;

    /**
     * 设备型号
     */
    private String deviceModel;

    /**
     * 点位俗称
     */
    private String pointName;

    /**
     * IPV4地址
     */
    private String ipv4;

    /**
     * IPV6地址
     */
    private String ipv6;

    /**
     * MAC地址
     */
    private String mac;

    /**
     * 摄像机类型
     */
    private String cameraType;

    /**
     * 摄像机功能类型
     */
    private String cameraFuncType;

    /**
     * 补光属性
     */
    private String fillLightAttr;

    /**
     * 摄像机编码格式类型
     */
    private String cameraNumFormat;

    /**
     * 对于存储IP
     */
    private String storageDeviceIp;

    /**
     * 对应存储设备通道
     */
    private String storageDeviceChan;

    /**
     * 安装地址
     */
    private String installAddress;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 摄像机位置类型
     */
    private String cameraPositionType;

    /**
     * 监视方向
     */
    private String monitoringDirection;

    /**
     * 摄像机场景预设照片URL
     */
    private String cameraImageUrl;

    /**
     * 联网属性
     */
    private String networkAttr;

    /**
     * 所属辖区公安机关
     */
    private String deviceAreaNum;

    /**
     * 安装时间
     */
    private Timestamp installTime;

    /**
     * 管理单位
     */
    private String deviceOrg;

    /**
     * 管理单位联系方式
     */
    private String information;

    /**
     * 录像保存天数
     */
    private Integer saveDays;

    /**
     * 设备状态
     */
    private String deviceStatus;

    /**
     * 设备所属部门
     */
    private String subDepartment;

    /**
     * 视频分辨率
     */
    private String videoResolutionRatio;

    /**
     * 视频信号类型
     */
    private String videoSignalType;

    /**
     * 是否对外共享
     */
    private String isExternal;

    /**
     * 是否接入三级平台
     */
    private String isJoinPlat;

    /**
     * 键盘编号
     */
    private String keyNum;

    /**
     * 建设应用类别
     */
    private String useType;

    /**
     * 安装高度（单位米）
     */
    private Double installHeight;

    /**
     * 可视距离（单位米）
     */
    private Double visualDistance;

    /**
     * 扩展字段1(进沪、出沪标识)（进沪为1、出沪为 2）
     */
    private String ext1;

    /**
     * 扩展字段2(检查站标识符号)
     */
    private String ext2;

    /**
     * 扩展字段3(设备车道编号)
     */
    private String ext3;

    /**
     * 设备登录名称
     */
    private String deviceUsername;

    /**
     * 设备登录密码
     */
    private String devicePassword;

}
