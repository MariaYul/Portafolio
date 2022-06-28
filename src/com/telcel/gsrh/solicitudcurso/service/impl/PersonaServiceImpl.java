package com.telcel.gsrh.solicitudcurso.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.form.PersonaBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Persona;
import com.telcel.gsrh.solicitudcurso.repository.PersonaRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.PersonaService;


@Service
public class PersonaServiceImpl extends AbstractService<Persona> implements PersonaService {

    @Autowired
    private PersonaRepository repository;
    
    @Override
    protected GenericOperation<Persona> getRepository() {
        return repository;
    }
    
    @Override
    public List<Persona> search(PersonaBusqueda parametro) {
        return repository.search(parametro);
    }

    @Override
    public Long getCantidadSearch(PersonaBusqueda parametro) {
        return repository.getCantidadSearch(parametro);
    }
    
    @Override
    public List<Persona> getDatosEmpleado(PersonaBusqueda parametro) {
    	return repository.getDatosEmpleado(parametro); 
    }
    
    @Override
    public List<Persona> getDatosParticipantes(Integer cve,Integer numRegis) {
    	return repository.getDatosParticipantes(cve,numRegis); 
    }
    
    
    @Override
    public Integer eliminarParticipante(Integer cve,Integer numRegis){    	
    	return repository.eliminarParticipante(cve,numRegis);
    }
 
}
