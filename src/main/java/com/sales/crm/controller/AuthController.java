package com.sales.crm.controller;

import com.sales.crm.dtos.AuthRequestDTO;
import com.sales.crm.model.User;
import com.sales.crm.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authenticate", description = "Authenticate with user2, pass and email@email.com ")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate (@RequestBody AuthRequestDTO authRequestDTO){
        return ResponseEntity.ok(userService.authenticate(authRequestDTO));
    }



}
