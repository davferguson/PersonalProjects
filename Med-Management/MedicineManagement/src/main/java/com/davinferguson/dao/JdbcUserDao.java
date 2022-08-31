package com.davinferguson.dao;

import com.davinferguson.exception.UserNotFoundException;
import com.davinferguson.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(String username, String password, String role) {
        String sql = "INSERT INTO app_user (username,password_hash,role) VALUES (?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();

        return jdbcTemplate.update(sql, username, password_hash, ssRole) == 1;
    }

    @Override
    public User findUserByUsername(String username){
        String sql = "SELECT * FROM app_user WHERE username = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);

        if(rowSet.next()){
            User curUser = mapRowToUser(rowSet);
            return curUser;
        } else{
            throw new UserNotFoundException();
        }
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        return user;
    }
}
