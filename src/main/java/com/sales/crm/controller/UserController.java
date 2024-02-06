package com.sales.crm.controller;

import com.sales.crm.dtos.AuthRequestDTO;
import com.sales.crm.model.Role;
import com.sales.crm.model.RoleType;
import com.sales.crm.model.User;
import com.sales.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register (@RequestBody AuthRequestDTO authRequestDTO){
        return ResponseEntity.ok(userService.register(authRequestDTO));
    }
    @PostMapping("/register/{userId}/{roleType}")
    public ResponseEntity<Role> addRoleToUser(@PathVariable Long userId, @PathVariable RoleType roleType){
        return ResponseEntity.ok(userService.addRoleToUser(userId, roleType));
    }



}
