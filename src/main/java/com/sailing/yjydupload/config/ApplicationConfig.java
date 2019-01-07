package com.sailing.yjydupload.config;

import com.sailing.yjydupload.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * 应用Bean配置
 * create by en
 * at 2018/12/11 14:43
 **/
@Configuration
@EnableJpaRepositories("com.sailing.yjydupload.repository")
public class ApplicationConfig {

    @Autowired
    private EnvConfig envConfig;

    @Autowired
    private YjydUploadConfig yjydUploadConfig;

    @Bean
    public DataSource dataSource() {    // 配置数据源（优先使用环境变量中的数据源设置）
        String username = CommonUtil.choiceNotNull(envConfig.getUsername(), yjydUploadConfig.getUsername());
        String password = CommonUtil.choiceNotNull(envConfig.getPassword(), yjydUploadConfig.getPassword());
        String url = CommonUtil.choiceNotNull(envConfig.getUrl(), yjydUploadConfig.getUrl());
        String driver = CommonUtil.choiceNotNull(envConfig.getDriverClass(), yjydUploadConfig.getDriverClass());
        return DataSourceBuilder.create()
                .driverClassName(driver)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {  // 配置EntityManagerFactory
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        factory.setPackagesToScan("com.sailing.yjydupload.entity");
        factory.setJpaVendorAdapter(vendorAdapter);
        // 开启驼峰命名=
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        factory.setJpaProperties(jpaProperties);
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {    // 配置transactionManager
        EntityManagerFactory factory = entityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public UploadInfoConfig uploadInfoConfig() {
        UploadInfoConfig uploadInfoConfig = new UploadInfoConfig();
        uploadInfoConfig.setUrl(CommonUtil.choiceNotNull(envConfig.getUploadUrl(), yjydUploadConfig.getUploadUrl()));
        uploadInfoConfig.setUsername(CommonUtil.choiceNotNull(envConfig.getUploadUsername(), yjydUploadConfig.getUploadUsername()));
        uploadInfoConfig.setPassword(CommonUtil.choiceNotNull(envConfig.getUploadPassword(), yjydUploadConfig.getUploadPassword()));
        uploadInfoConfig.setOpreateUser(CommonUtil.choiceNotNull(envConfig.getUploadOpreateuser(), yjydUploadConfig.getUploadOpreateuser()));
        uploadInfoConfig.setLocalServerName(CommonUtil.choiceNotNull(envConfig.getUploadLocalservername(), yjydUploadConfig.getUploadLocalservername()));
        uploadInfoConfig.setFilter(yjydUploadConfig.getFilter());
        return uploadInfoConfig;
    }


}
