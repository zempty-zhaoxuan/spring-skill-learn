package com.zempty.spring_skill_learn.config.datasource.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.zempty.spring_skill_learn.dao.slave",sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSourceConfig {

    @Autowired
    @Qualifier("slave")
    private DataSource dataSource;

    @Bean("slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory () throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:/slave/*.xml"));
        bean.setTypeAliasesPackage("com.zempty.spring_skill_learn.entity.slave");
        return bean.getObject();// 设置mybatis的xml所在位置
    }


    @Bean("slaveSqlSessionTemplate")
    public SqlSessionTemplate slaveSqlSessionTemplate() throws Exception  {
        return new SqlSessionTemplate(slaveSqlSessionFactory());
    }

    @Bean("slaveDataSourceTransactionManager")
    public DataSourceTransactionManager slaveDataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

}
