package com.telcel.gsrh.solicitudcurso.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="ARCHDIG_DOCUMENTO")
@AttributeOverride(name="clave", column=@Column(name="CLAVE"))
@GenericGenerator(name="generator"
    , strategy = "sequence"
    , parameters = @Parameter(name="sequence", value="ARCHDIG_SEQ_DOCUMENTO"))
public class Documento extends AbstractIntegerIdEntityModel implements Serializable {

    private static final long serialVersionUID = -509128655262726854L;

    @Column(name="RUTA")
    private String ruta;
    
    @Column(name="FECHAACTUALIZACION")
    @DateTimeFormat(pattern="dd/MM/yyyy'T'HH:mm:ss")
    private Date fechaActualizacion;
    
    @ManyToOne
    @JoinColumn(name="USUARIO_NUMERO")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name="TIPODOCUMENTO_CLAVE")
    private TipoDocumento tipoDocumento;
    
 /*   @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="EMPLEADO_CLAVE")
    private Persona persona;*/
    
    @Column(name="CONTADOR_TPD")
    private Integer contador;
    
   
    
    @Transient
    private Integer fase; 
    
    @Transient
    private MultipartFile archivo;
    
    @Transient
    private Serializador serializador = new Serializador();

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

  /*  public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }*/

    public Integer getFase() {
        return fase;
    }

    public void setFase(Integer fase) {
        this.fase = fase;
    }

    public MultipartFile getArchivo() {
        return archivo;
    }

    public void setArchivo(MultipartFile archivo) {
        this.archivo = archivo;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        serializador.deserializar(in, this);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        serializador.serializar(out, this);
    }
}
