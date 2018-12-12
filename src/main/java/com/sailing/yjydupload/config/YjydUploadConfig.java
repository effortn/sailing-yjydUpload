package com.sailing.yjydupload.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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

    @Value("${yjyd.url.upload}")
    private String uploadUrl;

}
