package com.telcel.gsrh.solicitudcurso.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ARCHDIG_TIPODOCUMENTO")
@AttributeOverride(name="clave", column=@Column(name="CLAVE"))
@GenericGenerator(name="generator"
    , strategy = "sequence"
    , parameters = @Parameter(name="sequence", value="ARCHDIG_SEQ_TP_DOCUMENTO"))
public class TipoDocumento extends AbstractIntegerIdEntityModel implements Serializable {

    private static final long serialVersionUID = 8896511292405075561L;
    
    @Column(name="ACTIVO")
    private Integer activo;
    
    @Column(name="MULTIREGISTRO")
    private Integer multiregistro;
    
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="FASE_CLAVE")
    private Fase fase;
    
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="AREA_CLAVE")
    private Area area;

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

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
