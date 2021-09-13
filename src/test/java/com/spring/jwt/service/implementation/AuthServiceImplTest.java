package com.spring.jwt.service.implementation;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.jwt.dto.JwtDto;
import com.spring.jwt.dto.Login;
import com.spring.jwt.dto.Mensaje;
import com.spring.jwt.dto.Signup;
import com.spring.jwt.entity.Rol;
import com.spring.jwt.entity.Usuario;
import com.spring.jwt.enums.RolNombre;
import com.spring.jwt.security.JwtProvider;
import com.spring.jwt.service.RolService;
import com.spring.jwt.service.UsuarioService;


@DataJpaTest
public class AuthServiceImplTest {
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
    RolService rolService;
	
	@Autowired
    UsuarioService usuarioService;
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
    JwtProvider jwtProvider;

	@Test
	public void registrarUsuario() {
		
		Usuario usuario = new Usuario("Test", "Test", "test@test.com", passwordEncoder.encode("test"));
        Set<String> roles = new HashSet<>();
	    roles.add("ROLE_ADMIN");
        usuarioService.save(usuario);
        System.out.printf("Test");
	     
	}
}
