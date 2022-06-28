package com.telcel.gsrh.solicitudcurso.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;

@SuppressWarnings("unchecked")
public abstract class AbstractRepository<T extends Serializable> implements GenericOperation<T> {

    @Autowired
    private SessionFactory sessionFactory;
   
    private Class<T> clase;
    
    public static final int DEFAULT_INT_VALUE = -1;
    public static final Integer NULL_VALUE = null;
    public static final String STRING_NULL_VALUE = null;
        
    protected final void setClase(final Class<T> clase) {
        this.clase = Preconditions.checkNotNull(clase);
    }
    
    protected final Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public T findById(Integer id) {
        return (T) getSession().get(clase, id); 
    }

    @Override
    public List<T> findAll() {
        return getSession().createQuery("From " + clase.getName() + " order by 2 asc").list();
    }

    @Override
    public void register(T item) {
        getSession().persist(item);
    }

    @Override
    public void update(T item) {
        getSession().update(item);
    }

    @Override
    public void delete(T item) {
        getSession().delete(item);
    }
    
    public Criteria addIntegerCriteria(String clave, Integer valor, Criteria consulta) {
        Criteria consultaModificada = consulta;
        
        if((valor != NULL_VALUE) && (valor != DEFAULT_INT_VALUE)){
            consultaModificada.add(Restrictions.eq(clave, valor));
        }
        
        return consultaModificada;
    }
    
    public Criteria addStringCriteria(String clave, String valor, Criteria consulta) {
        Criteria consultaModificada = consulta;
        
        if(!valor.isEmpty()) {
            consultaModificada.add(Restrictions.eq(clave, valor));
        }
        
        return consultaModificada;
    }
    
    public Criteria addStringLikeCriteria(String clave, String valor, Criteria consulta) {
        Criteria consultaModificada = consulta;
        
        if(!valor.isEmpty()) {
            consultaModificada.add(Restrictions.like(clave, "%" + valor + "%"));
        }
        
        return consultaModificada;
    }
}
