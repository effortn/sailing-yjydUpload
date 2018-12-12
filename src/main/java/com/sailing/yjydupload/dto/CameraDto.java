package com.sailing.yjydupload.dto;

import lombok.Setter;

import java.sql.Timestamp;

/**
 * 接收接口对应的CameraList参数的dto类
 * create by en
 * at 2018/12/11 17:01
 **/
@Setter
public class CameraDto {

    /**
     * 设备编码（必填）
     */
    public String SBBM;

    /**
     * 设备名称（必填）
     **/
    public String SBMC;

    /**
     * 设备厂商（必填）
     **/
    public String SBCS;

    /**
     * 行政区域（必填）
     **/
    public String XZQY;
    
    /**
     * 监控点位类型（必填）
     **/
    public String JKDWLX;

    /**
     * 设备型号
     **/
    public String SBXH;

    /**
     * 点位俗称
     **/
    public String DWSC;

    /**
     * IPV4地址
     **/
    public String IPV4;

    /**
     * IPV6地址
     **/
//    public String IPV6;

    /**
     * MAC地址
     **/
//    public String MACDZ;

    /**
     * 摄像机类型
     **/
    public String SXJLX;

    /**
     * 摄像机功能类型
     **/
    public String SXJGNLX;

    /**
     * 补光属性
     **/
    public String BGSX;

    /**
     * 摄像机编码格式
     **/
    public String SXJBMGS;

    /**
     * 安装地址（必填）
     **/
    public String AZDZ;

    /**
     * 安装高度
     **/
    public String AZGD;

    /**
     * 经度（必填）
     **/
    public String JD;

    /**
     * 纬度（必填）
     **/
    public String WD;

    /**
     * 可视距离
     **/
    public String KSJL;

    /**
     * 摄像机位置类型（必填）
     **/
    public String SXJWZLX;

    /**
     * 监视方位
     **/
    public String JSFW;

    /**
     * 摄像机场景预设照片URL
     **/
//    public String SXJCJYSZP;
    
    /**
     * 联网属性（必填）
     **/
    public String LWSX;

    /**
     * 所属辖区公安机关（必填）
     **/
    public String SSXQGAJG;

    /**
     * 安装时间（必填）
     **/
    public Timestamp AZSJ;

    /**
     * 管理单位（必填）
     **/
    public String GLDW;

    /**
     * 管理单位联系方式（必填）
     **/
    public String GLDWLXFS;
    
    /**
     * 录像保存天数（必填）
     **/
    public String LXBCTS;

    /**
     * 设备状态（必填）
     **/
    public String SBZT;

    /**
     * 所属部门/行业
     **/
    public String SSBMHY;

    /**
     * 视频分辨率
     **/
    public String SPFBL;

    /**
     * 对应存储通道IP
     **/
    public String DYCCSBIP;

    /**
     * 键盘编号
     **/
    public String JPBH;

    /**
     * 对应存储通道
     **/
    public String DYCCSBTD;

    /**
     * 视频信号类型
     **/
    public String SPXHLX;

    /**
     * 建设应用类别(1.公安出资建设2.社会单位出资建设公安租赁3.区县街镇出资建设公安使用4.复接公安其他图像
     * 			5.社会单位复接6.社会单位联网7.移动监控8.指挥室、会议室、监控室内部9.实验用)
     **/
    public String JSYYLB;
    
    /**
     * 是否对外共享
     *  是	1
     * 	否	2
     **/
    public String SFDWGX;
    
    /**
     * 是否接入三级平台
     * 	是	1
     * 	否	2
     **/
    public String SFJRSJPT;
    
    
}
