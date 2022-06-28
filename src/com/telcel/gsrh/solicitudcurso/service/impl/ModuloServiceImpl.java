package com.telcel.gsrh.solicitudcurso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Modulo;
import com.telcel.gsrh.solicitudcurso.repository.ModuloRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.ModuloService;

@Service
public class ModuloServiceImpl extends AbstractService<Modulo> implements ModuloService {

    @Autowired
    private ModuloRepository repository;
    
    @Override
    protected GenericOperation<Modulo> getRepository() {
        return repository;
    }
}
