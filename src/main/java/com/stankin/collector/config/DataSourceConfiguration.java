package com.stankin.collector.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean("dataSource")
    public DataSource dataSource() {
        return createDataSource(username, url, password);
    }

    private DataSource createDataSource(String username, String url, String password) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername(username);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setPassword(password);
        return new HikariDataSource(hikariConfig);
    }

}
