package com.telcel.gsrh.solicitudcurso.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;

@Transactional(readOnly=true)
public abstract class AbstractService<T extends Serializable> implements GenericOperation<T> {

    protected abstract GenericOperation<T> getRepository();
    
    @Override
    @Transactional(readOnly=false)
    public void delete(T item) {
        getRepository().delete(item);
    }
    
    @Override
    public T findById(Integer id) {
        return getRepository().findById(id);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    @Transactional(readOnly=false)
    public void register(T item) {
        getRepository().register(item);
    }

    @Override
    @Transactional(readOnly=false)
    public void update(T item) {
        getRepository().update(item);
    }
}
