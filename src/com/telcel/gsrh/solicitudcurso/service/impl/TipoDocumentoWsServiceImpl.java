package com.telcel.gsrh.solicitudcurso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.TipoDocumentoWS;
import com.telcel.gsrh.solicitudcurso.repository.TipoDocumentoWsRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.TipoDocumentoWsService;

@Service("tipoDocumentoWsService")
public class TipoDocumentoWsServiceImpl extends AbstractService<TipoDocumentoWS> implements TipoDocumentoWsService {

    @Autowired
    private TipoDocumentoWsRepository repository;
    
    @Override
    protected GenericOperation<TipoDocumentoWS> getRepository() {
        return repository;
    }
    
    @Override
    public List<TipoDocumentoWS> getTiposDocumentoByLoadToCandidate(int claveFase, int claveCandidato) {
        return repository.getTiposDocumentoByLoadToCandidate(claveFase, claveCandidato);
    }
}
