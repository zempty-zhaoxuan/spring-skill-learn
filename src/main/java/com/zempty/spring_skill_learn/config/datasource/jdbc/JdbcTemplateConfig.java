package com.zempty.spring_skill_learn.config.datasource.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

    @Autowired
    @Qualifier("master")
    private DataSource masterDataSource;

    @Autowired
    @Qualifier("slave")
    private DataSource slaveDataSource;

    @Primary
    @Bean("masterJdbcTemplate")
    public JdbcTemplate masterJdbcTemplate() {
        return new JdbcTemplate(masterDataSource);
    }

    @Bean
    public JdbcTemplate slaveJdbcTemplate() {
        return new JdbcTemplate(slaveDataSource);
    }


}
