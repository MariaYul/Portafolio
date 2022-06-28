package com.telcel.gsrh.solicitudcurso.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="CAP_ROL")
@AttributeOverride(name="clave", column=@Column(name="CLAVE"))
@GenericGenerator(name="generator", strategy = "assigned")
public class Rol extends AbstractIntegerIdEntityModel implements Serializable {

    private static final long serialVersionUID = -1542130290343425389L;
    
    @Column(name="DESCRIPCION")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name="MODULO_CLAVE")
    private Modulo modulo;
    
    @Transient
    private Serializador serializador = new Serializador();

    public Rol() {
        
    }
    
    public Rol(String clave) {
        this.clave = new Integer(clave);
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        serializador.deserializar(in, this);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        serializador.serializar(out, this);
    }
}
