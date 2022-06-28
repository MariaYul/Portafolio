package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.io.Serializable;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.repository.AbstractRepository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericHibernateRepositoryImpl<T extends Serializable> extends AbstractRepository<T> implements GenericOperation<T> {

}
