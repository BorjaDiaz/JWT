package com.spring.jwt.dto;

import javax.validation.constraints.NotBlank;

public class Login {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.userName = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
