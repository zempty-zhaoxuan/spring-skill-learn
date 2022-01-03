package com.zempty.spring_skill_learn.controller;

import com.zempty.spring_skill_learn.dao.master.UserDemoRepository;
import com.zempty.spring_skill_learn.dao.slave.DeskRepository;
import com.zempty.spring_skill_learn.entity.User;
import com.zempty.spring_skill_learn.entity.master.UserDemo;
import com.zempty.spring_skill_learn.entity.slave.Desk;
import com.zempty.spring_skill_learn.service.master.UserDemoService;
import com.zempty.spring_skill_learn.service.slave.DeskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {


    private MessageSource messageSource;

    @Autowired
    private UserDemoService userDemoService;

    private DeskService deskService;


    @Autowired
    @Qualifier("masterJdbcTemplate")
    private JdbcTemplate masterJdbcTemplate;

    @Autowired
    @Qualifier("slaveJdbcTemplate")
    private JdbcTemplate slaveJdbcTemplate;


    @Autowired
    private UserDemoRepository userDemoRepository;

    @Autowired
    private DeskRepository deskRepository;


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


    @ResponseBody
    @GetMapping("/master_list_user_demos")
    public List<UserDemo> getUserDemos() {
        List<UserDemo> userDemos1 = userDemoService.findByAll();
        System.out.println("======================================userDemos1================================");
        System.out.println(userDemos1);
        String sql = "select * from user_demo";
        List<UserDemo> userDemos2 = masterJdbcTemplate.query(sql, new BeanPropertyRowMapper(UserDemo.class));
        System.out.println("======================================userDemos2================================");
        System.out.println(userDemos2);
        List<UserDemo> userDemos3 = userDemoRepository.findAll();
        System.out.println("======================================userDemos3================================");
        System.out.println(userDemos3);
        return userDemos1;
    }


    @ResponseBody
    @GetMapping("slave_list_desks")
    public List<Desk> getDesks() {
        List<Desk> desks1 = deskService.findByAll();
        System.out.println("======================================desks1================================");
        System.out.println(desks1);
        String sql = "select * from desk";
        List<Desk> desks2 = slaveJdbcTemplate.query(sql, new BeanPropertyRowMapper(Desk.class));
        System.out.println("======================================desks2================================");
        System.out.println(desks2);
        List<Desk> desks3 = deskRepository.findAll();
        System.out.println("======================================desks3================================");
        System.out.println(desks3);
        return desks1;
    }







}
