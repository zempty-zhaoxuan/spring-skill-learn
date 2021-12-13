package com.zempty.spring_skill_learn.controller;

import com.zempty.spring_skill_learn.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class UserController {


    @ResponseBody
    @GetMapping("/interceptor")
    public String testInterceptor() {
        String helloworld = "hello world";
        return helloworld;
    }


    @ResponseBody
    @GetMapping("/filter")
    public String testFilter() {
        String helloworld = "hello world";
        return helloworld;
    }

    @ResponseBody
    @GetMapping("/modify_response")
    public User testModifyResponse() {
        User user = new User().setUserName("zempty")
                .setId(1).setBirthday(LocalDateTime.of(2021, 12, 13, 14, 28));
        return user;
    }



}
