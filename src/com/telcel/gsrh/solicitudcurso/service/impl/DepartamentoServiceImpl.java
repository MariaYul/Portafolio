package com.telcel.gsrh.solicitudcurso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.form.DepartamentoBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Departamento;
import com.telcel.gsrh.solicitudcurso.repository.DepartamentoRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.DepartamentoService;

@Service
public class DepartamentoServiceImpl extends AbstractService<Departamento> implements DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;
    
    @Override
    protected GenericOperation<Departamento> getRepository() {
        return repository;
    }
    
    @Override
    public List<Departamento> search(DepartamentoBusqueda parametro) {
        return repository.search(parametro);
    }
    
    @Override
    public Long getCantidadSearch(DepartamentoBusqueda parametro) {
        return repository.getCantidadSearch(parametro);
    }
}
