package com.zempty.spring_skill_learn.dao.master;

import com.zempty.spring_skill_learn.entity.master.UserDemo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserDemoMapperTest {
    private static UserDemoMapper mapper;


    @BeforeAll
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(UserDemoMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/UserDemoMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(UserDemoMapper.class, builder.openSession(true));
    }


    @Test
    public void testFindByAll() {
        List<UserDemo> userDemos = mapper.findByAll();
        System.out.println(userDemos);
    }
}
