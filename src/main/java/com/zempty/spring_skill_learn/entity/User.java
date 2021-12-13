package com.zempty.spring_skill_learn.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class User {

    private Integer id;

    private String userName;

    private LocalDateTime birthday;


}
