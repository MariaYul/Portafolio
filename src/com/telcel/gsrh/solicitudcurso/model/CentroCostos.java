package com.telcel.gsrh.solicitudcurso.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="CAP_CCOSTOS")
@AttributeOverride(name="clave", column=@Column(name="CLAVE"))
@GenericGenerator(name="generator", strategy = "assigned")
public class CentroCostos extends AbstractStringIdEntityModel implements Serializable {
    private static final long serialVersionUID = -1851818896076479685L;
}
