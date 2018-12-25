package com.sailing.yjydupload.config;

import lombok.Data;

import java.util.Map;

/**
 * 设备信息上传接口参数及URL配置
 * create by en
 * at 2018/12/12 16:41
 **/
@Data
public class UploadInfoConfig {

    /**
     * 请求URL
     **/
    private String url;

    /**
     * 用户名
     **/
    private String username;

    /**
     * 密码
     **/
    private String password;

    /**
     * 操作人名称，一般为系统登录用户名称
     **/
    private String opreateUser;
    
    /**
     * 本机名称，用于上级判断是否已开通该下级提交数据权限
     **/
    private String localServerName;

    /**
     * 上传数据过滤器
     */
    private String filter;
    
}
