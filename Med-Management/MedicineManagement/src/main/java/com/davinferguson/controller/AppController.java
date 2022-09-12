package com.davinferguson.controller;

import com.davinferguson.exception.UserAlreadyExistsException;
import com.davinferguson.exception.UserNotFoundException;
import com.davinferguson.model.LoginUser;
import com.davinferguson.model.RegisterUser;
import com.davinferguson.model.User;
import com.davinferguson.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class AppController {
    private UserService userService;

    public AppController(UserService userService){
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@Valid @RequestBody @NotNull RegisterUser newUser) {
        try{
            User user = userService.findUserByUsername(newUser.getUsername());
            System.out.println("username: " + "\"" + newUser.getUsername() + "\""+ " already exists.");
            throw new UserAlreadyExistsException();
        } catch(UserNotFoundException e){
            userService.createUser(newUser.getUsername(),newUser.getPassword(), newUser.getRole());
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testMessage(){
        return "do you see this big dawg????????...";
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> users(){
        return userService.users();
    }


    static class LoginResponse {

        private String token;
        private User user;

        LoginResponse(String token, User user) {
            this.token = token;
            this.user = user;
        }

        @JsonProperty("token")
        String getToken() {
            return token;
        }

        void setToken(String token) {
            this.token = token;
        }

        @JsonProperty("user")
        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}
