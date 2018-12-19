package com.sailing.yjydupload.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 数据上传接口返回信息DTO
 * create by en
 * at 2018/12/12 10:51
 **/
@Data
public class ResponseStatusDto {

    /**
     * ID
     **/
    @JSONField(name = "ID")
    private String ID;

    /**
     * 本地时间
     **/
    @JSONField(name = "LocalTime")
    private String LocalTime;

    /**
     * 请求地址
     **/
    @JSONField(name = "RequestURL")
    private String RequestURL;

    /**
     * 状态码
     *  10：设备同步校验失败,校验错误信息为：摄像机点位坐标(121.48853888070000000000,31.41064484530000000000)不在行政区域内
     *  0：OK
     **/
    @JSONField(name = "StatusCode")
    private String StatusCode;

    /**
     * 状态信息
     **/
    @JSONField(name = "StatusString")
    private String StatusString;

}
