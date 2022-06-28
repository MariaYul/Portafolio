package com.telcel.gsrh.solicitudcurso.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractStringIdEntityModel implements Serializable {

    private static final long serialVersionUID = 7234566651531563064L;

    @Id
    @GeneratedValue(generator="generator")
    @Column(name="CLAVE")
    protected String clave;
    
    @Column(name="NOMBRE")
    protected String nombre;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
