package com.telcel.gsrh.solicitudcurso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.form.TipoDocumentoBusqueda;
import com.telcel.gsrh.solicitudcurso.form.TipoDocumentoPorCargarCandidatoBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Moneda;
import com.telcel.gsrh.solicitudcurso.repository.MonedaRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.MonedaService;

@Service
public class MonedaServiceImpl extends AbstractService<Moneda> implements MonedaService {

    @Autowired
    private MonedaRepository repository;
    
    @Override
    protected GenericOperation<Moneda> getRepository() {
        return repository;
    }
    
    @Override
    public List<Moneda> getTiposDocumentoByLoadToCandidate(TipoDocumentoPorCargarCandidatoBusqueda parametro) {
    	return repository.getTiposDocumentoByLoadToCandidate(parametro); 
    }
    
    @Override
    public List<Moneda> searchTiposDocumento(TipoDocumentoBusqueda parametro) {
        return repository.searchTiposDocumento(parametro);
    }
    
    @Override
    public Long getCantidadSearchTiposDocumento(TipoDocumentoBusqueda parametro) {
        return repository.getCantidadSearchTiposDocumento(parametro);
    }
}
