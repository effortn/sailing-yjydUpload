package com.sailing.yjydupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class YjydUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(YjydUploadApplication.class, args);
    }
}
