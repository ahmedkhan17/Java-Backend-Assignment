package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.model.SuccessResponse;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {
    private UserService userService;

    @PostMapping
    public SuccessResponse addUser(@RequestBody final User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public SuccessResponse updateUser(@PathVariable("id") final String id,
                                      @RequestBody final User user) {
        return userService.updateUser(id,user);
    }
}
