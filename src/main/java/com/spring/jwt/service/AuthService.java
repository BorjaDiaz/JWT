package com.spring.jwt.service;

import org.springframework.http.ResponseEntity;

import com.spring.jwt.dto.Login;
import com.spring.jwt.dto.Signup;


public interface AuthService {
	
	public ResponseEntity<?> registrarUsuario(Signup signup);
	
	public ResponseEntity<?> LogearUsuario(Login loginUser);
}
