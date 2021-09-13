package com.spring.jwt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.spring.jwt.dto.Login;
import com.spring.jwt.dto.Mensaje;
import com.spring.jwt.dto.Signup;
import com.spring.jwt.service.AuthService;
import com.spring.jwt.service.UsuarioService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
	AuthService authService;
    
    @PostMapping("/signup")
    public ResponseEntity<?> nuevo(@Valid @RequestBody Signup signup, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(signup.getUserName()))
            return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(signup.getEmail()))
            return new ResponseEntity<>(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        return authService.registrarUsuario(signup);
        
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Login loginUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        return authService.LogearUsuario(loginUser);       
    }
    
}
