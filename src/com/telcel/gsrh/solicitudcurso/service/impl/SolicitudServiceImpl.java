package com.telcel.gsrh.solicitudcurso.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.form.SolicitudBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Solicitud;
import com.telcel.gsrh.solicitudcurso.repository.SolicitudRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.SolicitudService;


@Service
public class SolicitudServiceImpl extends AbstractService<Solicitud> implements SolicitudService {

    @Autowired
    private SolicitudRepository repository;
    
    @Override
    protected GenericOperation<Solicitud> getRepository() {
        return repository;
    }
    
    @Override
    public List<Solicitud> searchSolicitudes(SolicitudBusqueda parametro) {
        return repository.searchSolicitudes(parametro);
    }
    
    @Override
    public Long getCantidadSearchSolicitudes(SolicitudBusqueda parametro) {
        return repository.getCantidadSearchSolicitudes(parametro);
    }
    
    @Override
    public Integer eliminarSolicitud(Integer cve){    	
    	return repository.eliminarSolicitud(cve);
    }
    
    @Override
    public Integer updateEdoSolicitud(Integer cveSolicitud,Integer id_edoSol){    	
    	return repository.updateEdoSolicitud(cveSolicitud,id_edoSol);
    }

  
}
