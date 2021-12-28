package com.zempty.spring_skill_learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ServletComponentScan("com.zempty.spring_skill_learn")
//该注解用来导入 xml bean 的注入
@ImportResource("classpath*:spring*.xml")
public class SpringSkillLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSkillLearnApplication.class, args);
    }

}
