package com.spring.jwt.service;


import com.spring.jwt.entity.Rol;
import com.spring.jwt.enums.RolNombre;

import java.util.Optional;


public interface RolService {

  
    public Optional<Rol> getByRolNombre(RolNombre rolNombre);

    public void save(Rol rol);
}
