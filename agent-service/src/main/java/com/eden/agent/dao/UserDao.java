package com.eden.agent.dao;

import com.eden.agent.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<User> selectUser();
}
