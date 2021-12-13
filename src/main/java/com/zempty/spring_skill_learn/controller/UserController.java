package com.zempty.spring_skill_learn.controller;

import com.zempty.spring_skill_learn.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class UserController {


    private MessageSource messageSource;


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

    @ResponseBody
    @GetMapping("/user")
    public User useUser(User user) {
        System.out.println(user);
        return user;
    }


    @ResponseBody
    @GetMapping("/i18n")
    public String i18n() {
        String job = messageSource.getMessage("user.job", null, LocaleContextHolder.getLocale());
        String name = messageSource.getMessage("user.username", null, LocaleContextHolder.getLocale());
        return name +"'s job is " + job ;
    }



}
