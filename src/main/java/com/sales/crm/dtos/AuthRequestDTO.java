package com.sales.crm.dtos;

import jakarta.validation.constraints.NotNull;

public class AuthRequestDTO {

    @NotNull(message = "Name cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;

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

    public AuthRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthRequestDTO() {
    }
}
