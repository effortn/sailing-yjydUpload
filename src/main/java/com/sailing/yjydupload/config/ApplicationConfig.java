package com.sailing.yjydupload.config;

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
        String username = choiceNotNull(envConfig.getUsername(), yjydUploadConfig.getUsername());
        String password = choiceNotNull(envConfig.getPassword(), yjydUploadConfig.getPassword());
        String url = choiceNotNull(envConfig.getUrl(), yjydUploadConfig.getUrl());
        String driver = choiceNotNull(envConfig.getDriverClass(), yjydUploadConfig.getDriver());
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
        // 开启驼峰命名
        vendorAdapter.setGenerateDdl(true);
        factory.setPackagesToScan("com.sailing.yjydupload.entity");
        factory.setJpaVendorAdapter(vendorAdapter);
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {    // 配置transactionManager
        EntityManagerFactory factory = entityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    /**
     * 选择非空的字符（优先选择第一个，都为空抛出异常）
     * @param v1    字符串1
     * @param v2    字符串2
     * @return  非空字符串
     */
    private String choiceNotNull(String v1, String v2) {
        if (!StringUtils.isEmpty(v1))
            return v1;
        else if (!StringUtils.isEmpty(v2))
            return v2;
        else
            throw new RuntimeException("【配置加载】配置出错，数据为空，请检查是否有未配置的选项！");
    }

}
