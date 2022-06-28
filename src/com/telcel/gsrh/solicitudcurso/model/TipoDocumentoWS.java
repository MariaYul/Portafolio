package com.telcel.gsrh.solicitudcurso.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ARCHDIG_TIPODOCUMENTO")
@AttributeOverride(name="clave", column=@Column(name="CLAVE"))
@GenericGenerator(name="generator", strategy = "assigned")
public class TipoDocumentoWS extends AbstractIntegerIdEntityModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name="ACTIVO")
    private Integer activo;
    
    @Column(name="MULTIREGISTRO")
    private Integer multiregistro;

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getMultiregistro() {
        return multiregistro;
    }

    public void setMultiregistro(Integer multiregistro) {
        this.multiregistro = multiregistro;
    }
}
