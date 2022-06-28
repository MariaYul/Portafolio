package com.telcel.gsrh.solicitudcurso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.FaseWS;
import com.telcel.gsrh.solicitudcurso.repository.FaseWsRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.FaseWsService;

@Service("faseWsService")
public class FaseWsServiceImpl extends AbstractService<FaseWS> implements FaseWsService {

    @Autowired
    private FaseWsRepository repository;
    
    @Override
    protected GenericOperation<FaseWS> getRepository() {
        return repository;
    }
}
