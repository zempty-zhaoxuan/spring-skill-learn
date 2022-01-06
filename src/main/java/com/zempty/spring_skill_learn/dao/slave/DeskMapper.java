package com.zempty.spring_skill_learn.dao.slave;

import com.zempty.spring_skill_learn.entity.slave.Desk;

import java.util.List;

public interface DeskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Desk record);

    int insertSelective(Desk record);

    Desk selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Desk record);

    int updateByPrimaryKey(Desk record);

    List<Desk> findByAll();
}