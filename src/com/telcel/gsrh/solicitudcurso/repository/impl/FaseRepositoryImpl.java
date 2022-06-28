package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.model.Fase;
import com.telcel.gsrh.solicitudcurso.repository.FaseRepository;

@Repository
public class FaseRepositoryImpl extends GenericHibernateRepositoryImpl<Fase> implements FaseRepository {

    public FaseRepositoryImpl() {
        setClase(Fase.class);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Fase> searchFasePorArea(Integer areaClave){
        Query query  = getSession().createSQLQuery("SELECT DISTINCT FS.CLAVE, FS.NOMBRE " +
                        " FROM ARCHDIG_TIPODOCUMENTO TP, ARCHDIG_AREA AREA, ARCHDIG_FASE FS " +
                        " WHERE 1 = 1 " + 
                        " AND TP.AREA_CLAVE = :claveArea " +  
                        " AND TP.ACTIVO = 1 " +
                        " AND AREA.CLAVE = TP.AREA_CLAVE " + 
                        " AND TP.FASE_CLAVE = FS.CLAVE ").addEntity(Fase.class).
                        setParameter("claveArea", areaClave);
        
        return query.list();
    }
}
