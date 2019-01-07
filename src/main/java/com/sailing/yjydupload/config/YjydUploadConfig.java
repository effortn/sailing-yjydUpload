package com.sailing.yjydupload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 项目配置类
 * create by en
 * at 2018/12/11 14:34
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "yjyd.upload")
public class YjydUploadConfig {

    private String username;

    private String password;

    private String url;

    private String driverClass;

    private String uploadUrl;

    private String uploadUsername;

    private String uploadPassword;

    private String uploadOpreateuser;

    private String uploadLocalservername;

    private Map<String, String> filter;

}
