package com.sales.crm.dtos;

import com.sales.crm.model.RoleType;

import java.util.List;

public class UserResponseDTO {
    private String name;
    private String email;
    private List<RoleType> roles;

    public UserResponseDTO() {
    }

    public UserResponseDTO(String name, String email, List<RoleType> roles) {
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleType> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleType> roles) {
        this.roles = roles;
    }
}
