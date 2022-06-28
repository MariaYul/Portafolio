package com.telcel.gsrh.solicitudcurso.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractIdEntityModel implements Serializable {

    private static final long serialVersionUID = 2401064458111506330L;
    
    @Id
    @GeneratedValue(generator="generator")
    @Column(name="ID")
    protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
    
    }
