package com.davinferguson.service;

import com.davinferguson.dao.UserDao;
import com.davinferguson.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public boolean createUser(String username, String password, String role){
        return userDao.create(username, password, role);
    }

    public User findUserByUsername(String username){
        return userDao.findUserByUsername(username);
    }
}
