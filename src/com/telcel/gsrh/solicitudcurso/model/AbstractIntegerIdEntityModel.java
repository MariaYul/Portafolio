package com.telcel.gsrh.solicitudcurso.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractIntegerIdEntityModel implements Serializable {

    private static final long serialVersionUID = 2401064458111506330L;
    
    @Id
    @GeneratedValue(generator="generator")
    @Column(name="CLAVE")
    protected Integer clave;
    
    @Column(name="NOMBRE")
    protected String nombre;

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
