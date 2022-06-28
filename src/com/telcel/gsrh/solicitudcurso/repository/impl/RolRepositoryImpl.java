package com.telcel.gsrh.solicitudcurso.repository.impl;

import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.model.Rol;
import com.telcel.gsrh.solicitudcurso.repository.RolRepository;

@Repository
public class RolRepositoryImpl extends GenericHibernateRepositoryImpl<Rol> implements RolRepository {
    
    public RolRepositoryImpl() {
        setClase(Rol.class);
    }
}
