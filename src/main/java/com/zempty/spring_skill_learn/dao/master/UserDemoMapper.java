package com.zempty.spring_skill_learn.dao.master;

import com.zempty.spring_skill_learn.entity.master.UserDemo;

import java.util.List;

public interface UserDemoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserDemo record);

    int insertSelective(UserDemo record);

    UserDemo selectByPrimaryKey(Long id);


    List<UserDemo> findByAll();

    int updateByPrimaryKeySelective(UserDemo record);

    int updateByPrimaryKey(UserDemo record);
}