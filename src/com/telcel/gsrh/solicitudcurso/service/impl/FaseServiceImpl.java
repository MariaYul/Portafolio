package com.telcel.gsrh.solicitudcurso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Area;
import com.telcel.gsrh.solicitudcurso.model.Fase;
import com.telcel.gsrh.solicitudcurso.repository.FaseRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.FaseService;

@Service
public class FaseServiceImpl extends AbstractService<Fase> implements FaseService {

    @Autowired
    private FaseRepository repository;
    
    @Override
    protected GenericOperation<Fase> getRepository() {
        return repository;
    }
    
    @Override
    public List<Fase> searchFasePorArea(Integer areaClave){
    	//Regla solicitada por el área de Personal
    	if(areaClave == Area.CLAVE_AREA_PERSONAL) {
        	return repository.findAll();
        }
    	
    	return repository.searchFasePorArea(areaClave);
    }
}
