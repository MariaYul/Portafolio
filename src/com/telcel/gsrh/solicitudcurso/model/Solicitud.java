package com.telcel.gsrh.solicitudcurso.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CAP_SOLICITUDES")
@AttributeOverride(name="clave", column=@Column(name="CLAVE"))
@GenericGenerator(name="generator"
, strategy = "sequence"
, parameters = @Parameter(name="sequence", value="ARCHDIG_SEQ_FOLIO"))
public class Solicitud extends AbstractIntegerIdEntityModel implements Serializable {

    private static final long serialVersionUID = 5352190401436109869L;
    
    @Column(name="FECHA_INICIO")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date fecha_inicio;
    
    @Column(name="FECHA_FIN")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date fecha_fin;
    
    @Column(name="DURACION")
    private Integer duracion;
    
    @Column(name="HORARIO")
    private String horario;
    
    @Column(name="CONTACTO")
    private String contacto;
    
    @Column(name="DATOS_CONTACTO")
    private String datos_contacto;
    
    @Column(name="RESPONSABLE")
    private String responsable;
    
    @Column(name="DATOS_RESPONBLE")
    private String datos_responble;
    
    @Column(name="INVER_PERSON")
    private Integer inver_person;
    
    @Column(name="INVER_GRUPO")
    private Integer inver_grupo;
    
    @Column(name="NUM_PART")
    private Integer num_part;    
    
    @Column(name="TOTAL")
    private Integer total;
    
    @Column(name="COSTO_ASO")
    private Integer costo_aso;
    
    @Column(name="FUNCIONES")
    private Integer funciones;
    
    @Column(name="PROYECTO")
    private Integer proyecto;
    
    @Column(name="APLICABILIDAD")
    private Integer aplicabilidad;    
    
    @Column(name="ACTUALIZACION")
    private Integer actualizacion;
    
    @Column(name="OTRO")
    private String otro;
    
    @Column(name="DETALLE")
    private String detalle;
    
    @Column(name="PRESUPUESTO")
    private String presupuesto;
    
    @Column(name="HISTORICO")
    private String historico;
    
    @Column(name="CONTEMATICO")
    private String contematico;
    
    @Column(name="TRAYECTORIA")
    private String trayectoria;
    
    @Column(name="OBSERVACIONES")
    private String observaciones;
    
    @Column(name="FECHA_CREACION")
    @DateTimeFormat(pattern="dd/MM/yyyy'T'HH:mm:ss")
    private Date fecha_creacion;

    @Column(name="FECHA_ACTUALIZACION")
    @DateTimeFormat(pattern="dd/MM/yyyy'T'HH:mm:ss")
    private Date fecha_actualizacion;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="CLAVE_EMPLEADO")
    private Persona persona;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="TIPO_MONEDA")
    private Moneda tipo_moneda;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="ID_EDOSOL")
    private EstadoSolicitud edoSolicitud;
    
    @Column(name="ID_STATUS")
    private Integer id_status;
    
    @Column(name="CVE_DEPTO")
    private String cve_depto;
    

    @OneToMany(targetEntity=Participantes.class,cascade=CascadeType.ALL, mappedBy="participantes")
   // @OneToMany(mappedBy = "participantes", fetch=FetchType.EAGER)
    private Set<Participantes> participantes=new  HashSet<Participantes>();
    
    //, fetch=FetchType.EAGER
    @JsonIgnore 
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity=Historico.class,cascade=CascadeType.ALL, mappedBy="historicos",fetch=FetchType.LAZY)
    @ElementCollection
    @OrderBy("fecha_registro desc")
    private Set<Historico> historicos=new  HashSet<Historico>();
    
    @Transient
    private Serializador serializador = new Serializador();

	public Date getFecha_inicio() {
		return fecha_inicio == null ? getFechaDefault() : fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getDatos_contacto() {
		return datos_contacto;
	}

	public void setDatos_contacto(String datos_contacto) {
		this.datos_contacto = datos_contacto;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getDatos_responble() {
		return datos_responble;
	}

	public void setDatos_responble(String datos_responble) {
		this.datos_responble = datos_responble;
	}

	public Integer getInver_person() {
		return inver_person;
	}

	public void setInver_person(Integer inver_person) {
		this.inver_person = inver_person;
	}

	public Integer getInver_grupo() {
		return inver_grupo;
	}

	public void setInver_grupo(Integer inver_grupo) {
		this.inver_grupo = inver_grupo;
	}

	public Integer getNum_part() {
		return num_part;
	}

	public void setNum_part(Integer num_part) {
		this.num_part = num_part;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getCosto_aso() {
		return costo_aso;
	}

	public void setCosto_aso(Integer costo_aso) {
		this.costo_aso = costo_aso;
	}

	public Integer getFunciones() {
		return funciones;
	}

	public void setFunciones(Integer funciones) {
		this.funciones = funciones;
	}

	public Integer getProyecto() {
		return proyecto;
	}

	public void setProyecto(Integer proyecto) {
		this.proyecto = proyecto;
	}

	public Integer getAplicabilidad() {
		return aplicabilidad;
	}

	public void setAplicabilidad(Integer aplicabilidad) {
		this.aplicabilidad = aplicabilidad;
	}

	public Integer getActualizacion() {
		return actualizacion;
	}

	public void setActualizacion(Integer actualizacion) {
		this.actualizacion = actualizacion;
	}

	public String getOtro() {
		return otro;
	}

	public void setOtro(String otro) {
		this.otro = otro;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(String presupuesto) {
		this.presupuesto = presupuesto;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getContematico() {
		return contematico;
	}

	public void setContematico(String contematico) {
		this.contematico = contematico;
	}

	public String getTrayectoria() {
		return trayectoria;
	}

	public void setTrayectoria(String trayectoria) {
		this.trayectoria = trayectoria;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Date getFecha_actualizacion() {
		return fecha_actualizacion;
	}

	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Moneda getTipo_moneda() {
		return tipo_moneda;
	}

	public void setTipo_moneda(Moneda tipo_moneda) {
		this.tipo_moneda = tipo_moneda;
	}

	public EstadoSolicitud getEdoSolicitud() {
		return edoSolicitud;
	}

	public void setEdoSolicitud(EstadoSolicitud edoSolicitud) {
		this.edoSolicitud = edoSolicitud;
	}

	public Integer getId_status() {
		return id_status;
	}

	public void setId_status(Integer id_status) {
		this.id_status = id_status;
	}

	public String getCve_depto() {
		return cve_depto;
	}

	public void setCve_depto(String cve_depto) {
		this.cve_depto = cve_depto;
	}

	public Set<Participantes> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<Participantes> participantes) {
		this.participantes = participantes;
	}

	public Set<Historico> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(Set<Historico> historicos) {
		this.historicos = historicos;
	}
	

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		   serializador.deserializar(in, this);
		 }
		    
		 private void writeObject(ObjectOutputStream out) throws IOException {
		   serializador.serializar(out, this);
		 }

		 private Date getFechaDefault() {
		    Calendar calendario = Calendar.getInstance();
		    calendario.add(Calendar.DATE, -1);
		        
		    return calendario.getTime();
		  }

}