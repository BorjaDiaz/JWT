package com.spring.jwt.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jwt.entity.Rol;
import com.spring.jwt.enums.RolNombre;
import com.spring.jwt.repository.RolRepository;
import com.spring.jwt.service.RolService;

@Service
@Transactional
public class RolServiceImpl implements RolService{
	
	
	 	@Autowired
	    RolRepository rolRepository;

	    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
	        return rolRepository.findByRolNombre(rolNombre);
	    }

	    public void save(Rol rol){
	        rolRepository.save(rol);
	    }
}
