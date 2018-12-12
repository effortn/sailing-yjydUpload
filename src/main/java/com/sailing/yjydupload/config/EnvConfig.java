package com.sailing.yjydupload.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 环境变量配置
 * create by en
 * at 2018/12/11 14:36
 **/
@Getter
@Configuration
public class EnvConfig {

    @Value("#{systemProperties['DB_USERNAME']}")
    private String username;

    @Value("#{systemProperties['DB_PASSWORD']}")
    private String password;

    @Value("#{systemEnvironment['DB_URL']}")
    private String url;

    @Value("#{systemProperties['DB_DRIVER_CLASS']}")
    private String driverClass;

    @Value("#{systemProperties['UPLOAD_URL']}")
    private String uploadUrl;

    @Value("#{systemProperties['UPLOAD_USERNAME']}")
    private String uploadUsername;

    @Value("#{systemProperties['UPLOAD_PASSWORD']}")
    private String uploadPassword;

    @Value("#{systemProperties['UPLOAD_OPREATEUSER']}")
    private String uploadOpreateuser;

    @Value("#{systemProperties['UPLOAD_LOCALSERVERNAME']}")
    private String uploadLocalservername;

}
