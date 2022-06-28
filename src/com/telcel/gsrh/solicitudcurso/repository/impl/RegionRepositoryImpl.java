package com.telcel.gsrh.solicitudcurso.repository.impl;

import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.model.Region;
import com.telcel.gsrh.solicitudcurso.repository.RegionRepository;

@Repository
public class RegionRepositoryImpl extends GenericHibernateRepositoryImpl<Region> implements RegionRepository {

    public RegionRepositoryImpl() {
        setClase(Region.class);
    }
}
