package com.telcel.gsrh.solicitudcurso.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="CAP_TIPOMONEDA")
@AttributeOverride(name="clave", column=@Column(name="CLAVE"))
@GenericGenerator(name="generator", strategy = "assigned")
public class Moneda extends AbstractIntegerIdEntityModel implements Serializable {
    private static final long serialVersionUID = 2937129444708842223L;
    
    @Column(name="ACTIVO")
    private Integer activo;
    
    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}
