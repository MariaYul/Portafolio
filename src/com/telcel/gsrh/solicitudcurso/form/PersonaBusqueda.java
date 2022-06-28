package com.telcel.gsrh.solicitudcurso.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PersonaBusqueda extends Busqueda implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer clave;
    private String nombre;
    private Integer numeroEmpleado;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String curp;
    private String rfc;
    private Integer region;
    private String departamento;
    private String localidad;
    private String puesto;
    private String numero;
    private String costo;  
    private Map<String, PersonaBusqueda> empleadoMap = new HashMap<String,PersonaBusqueda>();
    private Map<String, String> participantesMap = new HashMap<String,String>();
    
    public PersonaBusqueda(){
    	
    }
    
    public PersonaBusqueda(Integer clave,String nombre,String strPuesto, String costo){
    	this.clave=clave;
    	this.nombre=nombre;
    	this.puesto=strPuesto;
    	this.costo = costo;
    	
    }


    public Integer getClave() {
		return clave;
	}
	public void setClave(Integer clave) {
		this.clave = clave;
	}
	public String toString (){
    	return this.clave+""+this.nombre;
    }    
    public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getNumeroEmpleado() {
        return numeroEmpleado;
    }
    public void setNumeroEmpleado(Integer numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getCurp() {
        return curp;
    }
    public void setCurp(String curp) {
        this.curp = curp;
    }
    public String getRfc() {
        return rfc;
    }
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    public Integer getRegion() {
        return region;
    }
    public void setRegion(Integer region) {
        this.region = region;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public String getPuesto() {
        return puesto;
    }
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Map<String, PersonaBusqueda> getEmpleadoMap() {
		return empleadoMap;
	}
	public Map<String, String> getParticipantesMap() {
		return participantesMap;
	}
	public void setParticipantesMap(Map<String, String> participantesMap) {
		this.participantesMap = participantesMap;
	}
	public void setEmpleadoMap(Map<String, PersonaBusqueda> empleadoMap) {
		this.empleadoMap = empleadoMap;
	}

	public String getCosto() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
	}	
}
