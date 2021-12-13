package com.zempty.spring_skill_learn.entity;

import java.time.LocalDateTime;

import com.zempty.spring_skill_learn.annotation.UserName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySources;

@Setter
@Getter
@Accessors(chain = true)
public class User {

    private Integer id;

    @UserName
    private String userName;

    private LocalDateTime birthday;

    private String job;


}
