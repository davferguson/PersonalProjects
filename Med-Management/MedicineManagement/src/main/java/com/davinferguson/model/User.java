package com.davinferguson.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private int userId;
    private String username;
    private String password;
    private Set<Authority> authorities = new HashSet<>();

    public User(){};
    public User(int userId, String username, String password, String authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        if(authorities != null) {
            this.setAuthorities(authorities);
        }
    }

    public void setAuthorities(Set<Authority> authorities) {

        this.authorities = authorities;
    }
    public void setAuthorities(String authorities) {
        String[] roles = authorities.split(",");
        for(String role : roles) {
            String authority = role.contains("ROLE_") ? role : "ROLE_" + role;
            this.authorities.add(new Authority(authority));
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }
}
