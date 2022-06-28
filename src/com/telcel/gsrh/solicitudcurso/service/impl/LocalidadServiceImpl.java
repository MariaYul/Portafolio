package com.telcel.gsrh.solicitudcurso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.form.LocalidadBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Localidad;
import com.telcel.gsrh.solicitudcurso.repository.LocalidadRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.LocalidadService;

@Service
public class LocalidadServiceImpl extends AbstractService<Localidad> implements LocalidadService {

    @Autowired
    private LocalidadRepository repository;
    
    @Override
    protected GenericOperation<Localidad> getRepository() {
        return repository;
    }
    
    @Override
    public List<Localidad> search(LocalidadBusqueda parametro) {
        return repository.search(parametro);
    }
    
    @Override
    public Long getCantidadSearch(LocalidadBusqueda parametro) {
        return repository.getCantidadSearch(parametro);
    }
}
