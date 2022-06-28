package com.telcel.gsrh.solicitudcurso.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CAP_PARTIPNTES")
@AttributeOverride(name="id", column=@Column(name="ID"))
@GenericGenerator(name="generator"
    , strategy = "sequence"
    , parameters = @Parameter(name="sequence", value="ARCHDIG_SEQ_PARTICIPANTE"))
public class Participantes extends AbstractIdEntityModel implements Serializable {

    private static final long serialVersionUID = 5352190401436109869L;
      
     
    @Column(name="CLAVE_EMPLEADO")
    private Integer  clave_EMPLEADO;  
    
    @Column(name="ID_STATUS")
    private Integer  id_status;
  
    @JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CLAVE")
    private Solicitud participantes;
	
	@Column(name="FECHA_ACTUALIZACION")
	@DateTimeFormat(pattern="dd/MM/yyyy'T'HH:mm:ss")
	private Date fecha_actualizacion;
	  
	 @Column(name="ORDEN")
	 private Integer orden;
	 
    @Transient
    private Serializador serializador = new Serializador();

	public Serializador getSerializador() {
		return serializador;
	}

	public void setSerializador(Serializador serializador) {
		this.serializador = serializador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getClave_EMPLEADO() {
		return clave_EMPLEADO;
	}

	public void setClave_EMPLEADO(Integer clave_EMPLEADO) {
		this.clave_EMPLEADO = clave_EMPLEADO;
	}

	public Integer getId_status() {
		return id_status;
	}

	public void setId_status(Integer id_status) {
		this.id_status = id_status;
	}

	public Solicitud getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Solicitud participantes) {
		this.participantes = participantes;
	}

	public Date getFecha_actualizacion() {
		return fecha_actualizacion;
	}

	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
}