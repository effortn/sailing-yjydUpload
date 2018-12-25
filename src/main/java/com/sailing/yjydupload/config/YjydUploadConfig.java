package com.sailing.yjydupload.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 项目配置类
 * create by en
 * at 2018/12/11 14:34
 **/
@Getter
@Configuration
public class YjydUploadConfig {

    @Value("${yjyd.datasource.username}")
    private String username;

    @Value("${yjyd.datasource.password}")
    private String password;

    @Value("${yjyd.datasource.url}")
    private String url;

    @Value("${yjyd.datasource.driver-class}")
    private String driver;

    @Value("${yjyd.upload.url}")
    private String uploadUrl;

    @Value("${yjyd.upload.username}")
    private String uploadUsername;

    @Value("${yjyd.upload.password}")
    private String uploadPassword;

    @Value("${yjyd.upload.opreateuser}")
    private String uploadOpreateuser;

    @Value("${yjyd.upload.localservername}")
    private String uploadLocalservername;

    @Value("${yjyd.upload.filter}")
    private String filter;

}
