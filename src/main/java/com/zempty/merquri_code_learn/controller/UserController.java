package com.zempty.merquri_code_learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {


    @ResponseBody
    @GetMapping("/interceptor")
    public String testInterceptor() {
        String helloworld = "hello world";
        return helloworld;
    }

}
