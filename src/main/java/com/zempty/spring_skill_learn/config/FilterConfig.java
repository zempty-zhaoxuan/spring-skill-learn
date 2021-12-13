package com.zempty.spring_skill_learn.config;

import com.zempty.spring_skill_learn.filter.ThirdFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ThirdFilter> registerThirdFilter() {
        FilterRegistrationBean<ThirdFilter> bean = new FilterRegistrationBean<>();
        bean.setOrder(2);
        bean.setFilter(new ThirdFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }

    //可以一直注入FilterRegistrationBean,定义多个 filter..
}
