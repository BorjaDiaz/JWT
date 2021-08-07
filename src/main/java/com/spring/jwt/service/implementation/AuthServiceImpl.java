package com.spring.jwt.service.implementation;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.jwt.dto.JwtDto;
import com.spring.jwt.dto.LoginUsuario;
import com.spring.jwt.dto.Mensaje;
import com.spring.jwt.dto.NuevoUsuario;
import com.spring.jwt.entity.Rol;
import com.spring.jwt.entity.Usuario;
import com.spring.jwt.enums.RolNombre;
import com.spring.jwt.security.JwtProvider;
import com.spring.jwt.service.AuthService;
import com.spring.jwt.service.RolService;
import com.spring.jwt.service.UsuarioService;

@Service
public class AuthServiceImpl implements AuthService{
	
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

	@Override
	public ResponseEntity<?> registrarUsuario(NuevoUsuario nuevoUsuario) {
		
		 Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
	                        passwordEncoder.encode(nuevoUsuario.getPassword()));
	        Set<Rol> roles = new HashSet<>();
	        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
	        if(nuevoUsuario.getRoles().contains("admin"))
	            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
	        usuario.setRoles(roles);
	        usuarioService.save(usuario);
	        return new ResponseEntity<>(new Mensaje("usuario guardado"), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> LogearUsuario(LoginUsuario loginUsuario) {
		 Authentication authentication =
	                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtProvider.generateToken(authentication);
	        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
	        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
	        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}

}
