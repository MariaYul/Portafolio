package com.telcel.gsrh.solicitudcurso.repository.impl;

import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.model.Modulo;
import com.telcel.gsrh.solicitudcurso.repository.ModuloRepository;

@Repository
public class ModuloRepositoryImpl extends GenericHibernateRepositoryImpl<Modulo> implements ModuloRepository {

    public ModuloRepositoryImpl() {
        setClase(Modulo.class);
    }
}
