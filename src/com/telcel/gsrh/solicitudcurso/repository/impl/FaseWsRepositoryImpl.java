package com.telcel.gsrh.solicitudcurso.repository.impl;

import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.model.FaseWS;
import com.telcel.gsrh.solicitudcurso.repository.FaseWsRepository;

@Repository("faseWsRepository")
public class FaseWsRepositoryImpl extends GenericHibernateRepositoryImpl<FaseWS>implements FaseWsRepository {

    public FaseWsRepositoryImpl() {
        setClase(FaseWS.class);
    }
}
