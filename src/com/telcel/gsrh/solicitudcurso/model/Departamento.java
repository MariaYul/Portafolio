package com.telcel.gsrh.solicitudcurso.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="CAP_DEPARTAMENTO")
@AttributeOverride(name="clave", column=@Column(name="CLAVE"))
@GenericGenerator(name="generator", strategy = "assigned")
public class Departamento extends AbstractStringIdEntityModel implements Serializable {
    private static final long serialVersionUID = 5599217691851562632L;
}
