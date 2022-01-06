package com.zempty.spring_skill_learn.dao.slave;

import com.zempty.spring_skill_learn.entity.slave.Desk;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DeskMapperTest {

    private static DeskMapper mapper;

    @BeforeAll
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(DeskMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/DeskMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(DeskMapper.class, builder.openSession(true));
    }

    @Test
    public void testFindByAll() {
        List<Desk> desks = mapper.findByAll();
        System.out.println(desks);
    }
}
