package com.zempty.spring_skill_learn.service.slave;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zempty.spring_skill_learn.entity.slave.Desk;
import com.zempty.spring_skill_learn.dao.slave.DeskMapper;

import java.util.List;

@Service
public class DeskService{

    @Resource
    private DeskMapper deskMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return deskMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Desk record) {
        return deskMapper.insert(record);
    }

    
    public int insertSelective(Desk record) {
        return deskMapper.insertSelective(record);
    }

    
    public Desk selectByPrimaryKey(Integer id) {
        return deskMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Desk record) {
        return deskMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Desk record) {
        return deskMapper.updateByPrimaryKey(record);
    }

    public List<Desk> findByAll() {
        return deskMapper.findByAll();
    }
}
