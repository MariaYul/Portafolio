package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.form.PuestoBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Puesto;
import com.telcel.gsrh.solicitudcurso.repository.PuestoRepository;

@Repository
public class PuestoRepositoryImpl extends GenericHibernateRepositoryImpl<Puesto> implements PuestoRepository {

    public PuestoRepositoryImpl() {
        setClase(Puesto.class);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Puesto> search(PuestoBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.addOrder(Property.forName("nombre").asc());
        
        return consulta.list();
    }
    
    @Override
    public Long getCantidadSearch(PuestoBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setProjection(Projections.rowCount());
        
        return (Long)consulta.uniqueResult();
    }
    
    private Criteria getSearchCriteria(PuestoBusqueda parametro) {
        Criteria consulta = getSession().createCriteria(Puesto.class);
        
        if(!parametro.getNombre().isEmpty()) {
            consulta.add(Restrictions.like("nombre", "%" + parametro.getNombre() + "%"));
        }
        
        return consulta;
    }
}
