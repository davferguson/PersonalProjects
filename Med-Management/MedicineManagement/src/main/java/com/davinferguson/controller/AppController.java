package com.davinferguson.controller;

import com.davinferguson.exception.UserAlreadyExistsException;
import com.davinferguson.exception.UserNotFoundException;
import com.davinferguson.model.RegisterUser;
import com.davinferguson.model.User;
import com.davinferguson.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class AppController {
    private UserService userService;

    public AppController(UserService userService){
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@Valid @RequestBody RegisterUser newUser) {
        try{
            User user = userService.findUserByUsername(newUser.getUsername());
            System.out.println("username: " + "\"" + newUser.getUsername() + "\""+ " already exists.");
            throw new UserAlreadyExistsException();
        } catch(UserNotFoundException e){
            userService.createUser(newUser.getUsername(),newUser.getPassword(), newUser.getRole());
        }
    }
}
