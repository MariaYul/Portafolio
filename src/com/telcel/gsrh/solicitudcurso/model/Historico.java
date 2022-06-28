package com.telcel.gsrh.solicitudcurso.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CAP_HISSOLICITUDES")
@AttributeOverride(name="id", column=@Column(name="ID"))
@GenericGenerator(name="generator"
    , strategy = "sequence"
    , parameters = @Parameter(name="sequence", value="ARCHDIG_SEQ_HISTORICO"))
public class Historico extends AbstractIdEntityModel implements Serializable {
    private static final long serialVersionUID = 2937129444708842223L;
 
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
  	@JoinColumn(name="CLAVE",nullable=false)
    private Solicitud historicos;
      
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
  	@JoinColumn(name="CLAVE_EMPLEADO")
    private Persona persona;
  
  
    @Column(name="COMENTARIO")
    private String comentario;  
    
    @Column(name="FECHA_REGISTRO")
	@DateTimeFormat(pattern="dd/MM/yyyy'T'HH:mm:ss")
	private Date fecha_registro;
    
   
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
  	@JoinColumn(name="ID_EDOSOL")
    private EstadoSolicitud edoSolicitud;
    
 /*   @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ID_EDOSOL")
    private EstadoSolicitud edoSolicitud;*/
    
   
    @Column(name="MOTIVO")
    private String motivo;  
	

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Solicitud getHistoricos() {
		return historicos;
	}

	public void setHistoricos(Solicitud historicos) {
		this.historicos = historicos;
	}

	/*public EstadoSolicitud getEdoSolicitud() {
		return edoSolicitud;
	}

	public void setEdoSolicitud(EstadoSolicitud edoSolicitud) {
		this.edoSolicitud = edoSolicitud;
	}*/

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public EstadoSolicitud getEdoSolicitud() {
		return edoSolicitud;
	}

	public void setEdoSolicitud(EstadoSolicitud edoSolicitud) {
		this.edoSolicitud = edoSolicitud;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	
	  
}
