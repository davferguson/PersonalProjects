package com.davinferguson.dao;

import com.davinferguson.model.User;

public interface UserDao {

    User findUserByUsername(String username);
    boolean create(String username, String password, String role);
}
