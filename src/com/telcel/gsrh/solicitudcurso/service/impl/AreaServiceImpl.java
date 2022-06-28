package com.telcel.gsrh.solicitudcurso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.form.AreaBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Area;
import com.telcel.gsrh.solicitudcurso.repository.AreaRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.AreaService;

@Service
public class AreaServiceImpl extends AbstractService<Area> implements AreaService {

    @Autowired
    private AreaRepository repository;

    @Override
    protected GenericOperation<Area> getRepository() {
        return repository;
    }
    
    @Override
    public List<Area> search(AreaBusqueda parametro) {
        return repository.search(parametro);
    }
    
    @Override
    public Long getCantidadSearch(AreaBusqueda parametro) {
        return repository.getCantidadSearch(parametro);
    }
}
