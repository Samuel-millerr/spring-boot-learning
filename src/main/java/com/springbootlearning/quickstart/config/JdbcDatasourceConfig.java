package com.springbootlearning.quickstart.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcDatasourceConfig {

    @Bean(name = "jdbcDataSource")
    @ConfigurationProperties("spring.datasource.jdbc")
    public DataSource jdbcDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(
            @Qualifier("jdbcDataSource") final DataSource dataSource
    ) {
        return new JdbcTemplate(dataSource);
    }

}
