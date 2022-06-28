package com.telcel.gsrh.solicitudcurso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.form.PuestoBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Puesto;
import com.telcel.gsrh.solicitudcurso.repository.PuestoRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.PuestoService;

@Service
public class PuestoServiceimpl extends AbstractService<Puesto> implements PuestoService {

    @Autowired
    private PuestoRepository repository;
    
    @Override
    protected GenericOperation<Puesto> getRepository() {
        return repository;
    }
    
    @Override
    public List<Puesto> search(PuestoBusqueda parametro) {
        return repository.search(parametro);
    }

    @Override
    public Long getCantidadSearch(PuestoBusqueda parametro) {
        return repository.getCantidadSearch(parametro);
    }
}
