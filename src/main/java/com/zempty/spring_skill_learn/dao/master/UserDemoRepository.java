package com.zempty.spring_skill_learn.dao.master;

import com.zempty.spring_skill_learn.entity.master.UserDemo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDemoRepository extends JpaRepository<UserDemo,Long> {
}
