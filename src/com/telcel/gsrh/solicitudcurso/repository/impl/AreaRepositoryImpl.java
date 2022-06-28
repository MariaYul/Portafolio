package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.form.AreaBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Area;
import com.telcel.gsrh.solicitudcurso.repository.AreaRepository;

@Repository
public class AreaRepositoryImpl extends GenericHibernateRepositoryImpl<Area> implements AreaRepository {

    public AreaRepositoryImpl() {
        setClase(Area.class);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Area> search(AreaBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setFirstResult(parametro.getOffset());
        consulta.setMaxResults(parametro.getMax());
        consulta.addOrder(Property.forName("nombre").asc());
        
        return consulta.list();
    }
    
    @Override
    public Long getCantidadSearch(AreaBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setProjection(Projections.rowCount());
        
        return (Long)consulta.uniqueResult();
    }
    
    private Criteria getSearchCriteria(AreaBusqueda parametro) {
        Criteria consulta = getSession().createCriteria(Area.class);
        
        if(!parametro.getNombre().isEmpty()) {
            consulta.add(Restrictions.like("nombre", "%" + parametro.getNombre() + "%"));
        }
        
        return consulta;
    }
}
