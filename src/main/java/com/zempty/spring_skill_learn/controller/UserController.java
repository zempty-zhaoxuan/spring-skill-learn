package com.zempty.spring_skill_learn.controller;

import com.zempty.spring_skill_learn.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class UserController {


    private MessageSource messageSource;


    @Autowired
    private User user;


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


    @GetMapping("/freemarker")
    public ModelAndView freemarker() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "hello world");
        modelAndView.setViewName("helloworld");
        return modelAndView;
    }


    @GetMapping("/index")
    public String changeTheme() {
        return "index";
    }


    @GetMapping("/xml")
    public User getUser() {
        System.out.println(user);
        return user;
    }



}
