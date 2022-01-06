package com.zempty.spring_skill_learn.service.master;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zempty.spring_skill_learn.entity.master.UserDemo;
import com.zempty.spring_skill_learn.dao.master.UserDemoMapper;

import java.util.List;

@Service
public class UserDemoService{

    @Resource
    private UserDemoMapper userDemoMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return userDemoMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(UserDemo record) {
        return userDemoMapper.insert(record);
    }

    
    public int insertSelective(UserDemo record) {
        return userDemoMapper.insertSelective(record);
    }

    
    public UserDemo selectByPrimaryKey(Long id) {
        return userDemoMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(UserDemo record) {
        return userDemoMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(UserDemo record) {
        return userDemoMapper.updateByPrimaryKey(record);
    }

    public List<UserDemo> findByAll() {
        return userDemoMapper.findByAll();
    }
}
