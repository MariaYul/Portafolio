package com.telcel.gsrh.solicitudcurso.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CAP_EMPLEADO")
@AttributeOverride(name="clave", column=@Column(name="CLAVE"))
@GenericGenerator(name="generator", strategy = "assigned")
public class Persona extends AbstractIntegerIdEntityModel implements Serializable {

    private static final long serialVersionUID = -7619202526632560040L;
   
    @Embedded
    private Apellidos apellidos;
    
    @Column(name="CURP")
    private String curp;
 
    @Column(name="IMSS")
    private String imss;
    
    @Column(name="RFC")
    private String rfc;
    
    @Column(name="NUMEROJEFE")
    private Integer numeroJefe;
    
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="REGION_CLAVE")
    private Region region;
    
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="DEPARTAMENTO_CLAVE")
    private Departamento departamento;
    
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="LOCALIDADPAGO_CLAVE")
    private Localidad localidad;
    
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="PUESTO_CLAVE")
    private Puesto puesto;
    
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ESTATUS_CLAVE")
    private Estatus estatus;
    
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="COSTOS_CLAVE")
    private CentroCostos costos;
    
    
    @Column(name="ACTIVO")
    private Integer activo;
    
    @Column(name="CONTRASENNIA")
    private String contrasennia;
    
    @Column(name="BLOQUEO")
    private Integer bloqueo;
    
    
    
    
    
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    @JoinTable(name="CAP_USUARIO_ROL",
      joinColumns = {@JoinColumn(name="USUARIO_NUMERO")},
      inverseJoinColumns={@JoinColumn(name="ROL_CLAVE")}
    )
    private Set<Rol> roles = new HashSet<Rol>();
 
    public Persona(){
    	
    }
    
    public Persona(Integer clave,String nombre,String strPuesto,String strCc ){
    	this.clave=clave;
    	this.nombre=nombre;
    	puesto=new Puesto();
    	costos=new CentroCostos();
    	this.puesto.setNombre(strPuesto);
    	this.costos.setNombre(strCc);
    	
    	
    }
        
    @Transient
    private Serializador serializador = new Serializador();


	public Apellidos getApellidos() {
		return apellidos;
	}

	public void setApellidos(Apellidos apellidos) {
		this.apellidos = apellidos;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getImss() {
		return imss;
	}

	public void setImss(String imss) {
		this.imss = imss;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Integer getNumeroJefe() {
		return numeroJefe;
	}

	public void setNumeroJefe(Integer numeroJefe) {
		this.numeroJefe = numeroJefe;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public Estatus getEstatus() {
		return estatus;
	}

	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}

	public CentroCostos getCostos() {
		return costos;
	}

	public void setCostos(CentroCostos costos) {
		this.costos = costos;
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
	
	public Set<Rol> getRoles() {
	        return roles;
	}

	public void setRoles(Set<Rol> roles) {
	        this.roles = roles;
	}

	
}
