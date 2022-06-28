package com.telcel.gsrh.solicitudcurso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Rol;
import com.telcel.gsrh.solicitudcurso.repository.RolRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.RolService;

@Service
public class RolServiceImpl extends AbstractService<Rol> implements RolService {

    @Autowired
    private RolRepository repository;
    
    @Override
    protected GenericOperation<Rol> getRepository() {
        return repository;
    }
}
