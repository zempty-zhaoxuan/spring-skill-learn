package com.zempty.spring_skill_learn.dao.slave;

import com.zempty.spring_skill_learn.entity.slave.Desk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeskRepository extends JpaRepository<Desk,Integer> {
}
