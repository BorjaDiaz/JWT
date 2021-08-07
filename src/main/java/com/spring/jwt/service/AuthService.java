package com.spring.jwt.service;

import org.springframework.http.ResponseEntity;

import com.spring.jwt.dto.LoginUsuario;
import com.spring.jwt.dto.NuevoUsuario;


public interface AuthService {
	
	public ResponseEntity<?> registrarUsuario(NuevoUsuario nuevoUsuario);
	
	public ResponseEntity<?> LogearUsuario(LoginUsuario loginUsuario);
}
