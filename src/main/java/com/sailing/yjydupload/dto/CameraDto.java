package com.sailing.yjydupload.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 接收接口对应的CameraList参数的dto类
 * create by en
 * at 2018/12/11 17:01
 **/
@Data
public class CameraDto {

    /*"DWSC":"临江公园大门G",
            "XZQY":"31011311",
            "SBMC":"临江公园大门G",
            "AZGD":"6",
            "KSJL":"120",
            "SFDWGX":"1",
            "WD":"31.41064484530000000000",
            "AZSJ":1320940800000,
            "SFJRSJPT":"1",
            "AZDZ":"临江公园大门G",
            "LWSX":"0",
            "GLDWLXFS":"021-56608111",
            "SSXQGAJG":"310113420000",
            "GLDW":"友谊路派出所",
            "SBCS":"2",
            "SBXH":"Q1635",
            "JSYYLB":"1",
            "SPFBL":"1",
            "IPV4":"15.192.49.113",
            "JKDWLX":"2",
            "SXJLX":"3",
            "LXBCTS":30,
            "SPXHLX":"2",
            "DYCCSBIP":"15.192.49.113",
            "JPBH":"22011100",
            "SBZT":"1",
            "SSBMHY":"1",
            "DYCCSBTD":"4",
            "BGSX":"1",
            "JSFW":"90",
            "JD":"121.48853888070000000000",
            "SXJGNLX":"5",
            "SBBM":"31011311001310000100",
            "SXJBMGS":"1",
            "SXJWZLX":"9"*/

    /**
     * 设备编码（必填）
     */
    private String SBBM;

    /**
     * 设备名称（必填）
     **/
    private String SBMC;

    /**
     * 设备厂商（必填）
     **/
    private String SBCS;

    /**
     * 行政区域（必填）
     **/
    private String XZQY;
    
    /**
     * 监控点位类型（必填）
     **/
    private String JKDWLX;

    /**
     * 设备型号
     **/
    private String SBXH;

    /**
     * 点位俗称
     **/
    private String DWSC;

    /**
     * IPV4地址
     **/
    private String IPV4;

    /**
     * IPV6地址
     **/
//    private String IPV6;

    /**
     * MAC地址
     **/
//    private String MACDZ;

    /**
     * 摄像机类型
     **/
    private String SXJLX;

    /**
     * 摄像机功能类型
     **/
    private String SXJGNLX;

    /**
     * 补光属性
     **/
    private String BGSX;

    /**
     * 摄像机编码格式
     **/
    private String SXJBMGS;

    /**
     * 安装地址（必填）
     **/
    private String AZDZ;

    /**
     * 安装高度
     **/
    private String AZGD;

    /**
     * 经度（必填）
     **/
    private String JD;

    /**
     * 纬度（必填）
     **/
    private String WD;

    /**
     * 可视距离
     **/
    private String KSJL;

    /**
     * 摄像机位置类型（必填）
     **/
    private String SXJWZLX;

    /**
     * 监视方位
     **/
    private String JSFW;

    /**
     * 摄像机场景预设照片URL
     **/
//    private String SXJCJYSZP;
    
    /**
     * 联网属性（必填）
     **/
    private String LWSX;

    /**
     * 所属辖区公安机关（必填）
     **/
    private String SSXQGAJG;

    /**
     * 安装时间（必填）
     **/
    private Timestamp AZSJ;

    /**
     * 管理单位（必填）
     **/
    private String GLDW;

    /**
     * 管理单位联系方式（必填）
     **/
    private String GLDWLXFS;
    
    /**
     * 录像保存天数（必填）
     **/
    private String LXBCTS;

    /**
     * 设备状态（必填）
     **/
    private String SBZT;

    /**
     * 所属部门/行业
     **/
    private String SSBMHY;

    /**
     * 视频分辨率
     **/
    private String SPFBL;

    /**
     * 对应存储通道IP
     **/
    private String DYCCSBIP;

    /**
     * 键盘编号
     **/
    private String JPBH;

    /**
     * 对应存储通道
     **/
    private String DYCCSBTD;

    /**
     * 视频信号类型
     **/
    private String SPXHLX;

    /**
     * 建设应用类别(1.公安出资建设2.社会单位出资建设公安租赁3.区县街镇出资建设公安使用4.复接公安其他图像
     * 			5.社会单位复接6.社会单位联网7.移动监控8.指挥室、会议室、监控室内部9.实验用)
     **/
    private String JSYYLB;
    
    /**
     * 是否对外共享
     *  是	1
     * 	否	2
     **/
    private String SFDWGX;
    
    /**
     * 是否接入三级平台
     * 	是	1
     * 	否	2
     **/
    private String SFJRSJPT;
    
    
}
