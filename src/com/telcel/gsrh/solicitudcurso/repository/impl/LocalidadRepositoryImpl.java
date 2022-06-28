package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.form.LocalidadBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Localidad;
import com.telcel.gsrh.solicitudcurso.repository.LocalidadRepository;

@Repository
public class LocalidadRepositoryImpl extends GenericHibernateRepositoryImpl<Localidad> implements LocalidadRepository {

    public LocalidadRepositoryImpl() {
        setClase(Localidad.class);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Localidad> search(LocalidadBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.addOrder(Property.forName("nombre").asc());
        
        return consulta.list();
    }
    
    @Override
    public Long getCantidadSearch(LocalidadBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setProjection(Projections.rowCount());
        
        return (Long)consulta.uniqueResult();
    }
    
    private Criteria getSearchCriteria(LocalidadBusqueda parametro) {
        Criteria consulta = getSession().createCriteria(Localidad.class);
        
        if(!parametro.getNombre().isEmpty()) {
            consulta.add(Restrictions.like("nombre", "%" + parametro.getNombre() + "%"));
        }
        
        return consulta;
    }
}
