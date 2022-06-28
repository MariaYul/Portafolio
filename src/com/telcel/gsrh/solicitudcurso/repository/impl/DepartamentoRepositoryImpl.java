package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.form.DepartamentoBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Departamento;
import com.telcel.gsrh.solicitudcurso.repository.DepartamentoRepository;

@Repository
public class DepartamentoRepositoryImpl extends GenericHibernateRepositoryImpl<Departamento> implements DepartamentoRepository {

    public DepartamentoRepositoryImpl() {
        setClase(Departamento.class);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Departamento> search(DepartamentoBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.addOrder(Property.forName("nombre").asc());
        
        return consulta.list();
    }
    
    @Override
    public Long getCantidadSearch(DepartamentoBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setProjection(Projections.rowCount());
        
        return (Long)consulta.uniqueResult();
    }
    
    private Criteria getSearchCriteria(DepartamentoBusqueda parametro) {
        Criteria consulta = getSession().createCriteria(Departamento.class);
        
        if(!parametro.getNombre().isEmpty()) {
            consulta.add(Restrictions.like("nombre", "%" + parametro.getNombre() + "%"));
        }
        
        return consulta;
    }
}
