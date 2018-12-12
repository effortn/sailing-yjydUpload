package com.sailing.yjydupload.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 数据上传接口请求参数DTO
 * create by en
 * at 2018/12/12 10:46
 **/
@Data
public class UploadRequestDto {

    /**
     * 数据集合
     */
    @JSONField(name = "CameraList")
    private List<CameraDto> CameraList;

    /**
     * 用户名
     **/
    @JSONField(name = "USERNAME")
    private String USERNAME;
    
    /**
     * 密码
     **/
    @JSONField(name = "PASSWORD")
    private String PASSWORD;

    /**
     * 操作人名称，一般为系统登录用户名称
     **/
    @JSONField(name = "OPREATEUSER")
    private String OPREATEUSER;
    
    /**
     * 本机名称，用于上级判断是否已开通该下级提交数据权限
     **/
    @JSONField(name = "LOCALSERVERNAME")
    private String LOCALSERVERNAME;
    
}
