package com.eden.agent.service;

import com.eden.agent.dao.UserDao;
import com.eden.agent.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> selectUser() {
        return userDao.selectUser();
    }
}
