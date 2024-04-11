package com.sales.crm.controller;

import com.sales.crm.dtos.AuthRequestDTO;
import com.sales.crm.dtos.UserResponseDTO;
import com.sales.crm.model.Category;
import com.sales.crm.model.Role;
import com.sales.crm.model.RoleType;
import com.sales.crm.model.User;
import com.sales.crm.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Endpoints for users ")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register (@RequestBody AuthRequestDTO authRequestDTO){
         return ResponseEntity.ok(userService.register(authRequestDTO));
    }
    @PostMapping("/{userId}/{roleType}")
    public ResponseEntity<Role> addRoleToUser(@PathVariable Long userId, @PathVariable RoleType roleType){
        return ResponseEntity.ok(userService.addRoleToUser(userId, roleType));
    }
    @PostMapping("/{userId}/")
    public ResponseEntity<String> changeStatusOfUserById(@PathVariable Long userId, @RequestParam Boolean activeStatus){
        return ResponseEntity.ok(userService.changeActiveStatus(userId,activeStatus));

    }
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        return ResponseEntity.ok(userService.findAllUsers());

    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }





}
