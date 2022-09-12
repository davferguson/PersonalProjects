package com.davinferguson.dao;

import com.davinferguson.model.User;

import java.util.List;

public interface UserDao {

    User findUserByUsername(String username);
    List<User> allUsers();
    boolean create(String username, String password, String role);
}
