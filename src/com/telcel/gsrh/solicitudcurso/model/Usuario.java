package com.telcel.gsrh.solicitudcurso.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ARCHDIG_USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 5352190401436109869L;

    @Id
    @Column(name="NUMERO")
    private Integer numero;
    
    @Column(name="NOMBRE")
    private String nombre;
    
    @Embedded
    private Apellidos apellidos;
    
    @Column(name="ACTIVO")
    private Integer activo;
    
    @Column(name="CONTRASENNIA")
    private String contrasennia;
    
    @Column(name="BLOQUEO")
    private Integer bloqueo;
    
    @Column(name="FECHA_EXPIRA_CONTRASENNIA")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date fechaExpiraContrasennia;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="AREA_CLAVE")
    private Area area;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="REGION_CLAVE")
    private Region region;
    
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name="CAP_USUARIO_ROL",
      joinColumns = {@JoinColumn(name="USUARIO_NUMERO")},
      inverseJoinColumns={@JoinColumn(name="ROL_CLAVE")}
    )
    private Set<Rol> roles = new HashSet<Rol>();
    
    @Transient
    private Serializador serializador = new Serializador();
    
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Apellidos getApellidos() {
        return apellidos;
    }

    public void setApellidos(Apellidos apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
    
    public String getContrasennia() {
        return contrasennia;
    }

    public void setContrasennia(String contrasennia) {
        this.contrasennia = contrasennia;
    }

    public Integer getBloqueo() {
        return bloqueo == null ? new Integer(0) : bloqueo;
    }

    public void setBloqueo(Integer bloqueo) {
        this.bloqueo = bloqueo;
    }

    public Date getFechaExpiraContrasennia() {
        return fechaExpiraContrasennia == null ? getFechaDefault() : fechaExpiraContrasennia;
    }

    public void setFechaExpiraContrasennia(Date fechaExpiraContrasennia) {
        this.fechaExpiraContrasennia = fechaExpiraContrasennia;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
    
    private Date getFechaDefault() {
        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.DATE, -1);
        
        return calendario.getTime();
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        serializador.deserializar(in, this);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        serializador.serializar(out, this);
    }
}
