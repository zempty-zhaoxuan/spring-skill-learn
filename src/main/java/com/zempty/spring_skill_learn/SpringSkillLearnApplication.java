package com.zempty.spring_skill_learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.zempty.spring_skill_learn")
public class SpringSkillLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSkillLearnApplication.class, args);
    }

}
