package com.telcel.gsrh.solicitudcurso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Region;
import com.telcel.gsrh.solicitudcurso.repository.RegionRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.RegionService;

@Service
public class RegionServiceImpl extends AbstractService<Region> implements RegionService {

    @Autowired
    private RegionRepository repository;
    
    @Override
    protected GenericOperation<Region> getRepository() {
        return repository;
    }
}
