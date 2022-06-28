package com.telcel.gsrh.solicitudcurso.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SolicitudBusqueda extends Busqueda implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer clave;
    private Integer claveEmpleado;
    private Map<String, String> empleadosMap = new HashMap<String,String>(); 
    private Map<String, PersonaBusqueda> empleadoMap = new HashMap<String,PersonaBusqueda>();
    private String strDepartamento;
    private String strDescPuesto;
    private String strCveDepto;
    
	public Integer getClave() {
		return clave;
	}
	public void setClave(Integer clave) {
		this.clave = clave;
	}
	public Integer getClaveEmpleado() {
		return claveEmpleado;
	}
	public void setClaveEmpleado(Integer claveEmpleado) {
		this.claveEmpleado = claveEmpleado;
	}
	public Map<String, String> getEmpleadosMap() {
		return empleadosMap;
	}
	public void setEmpleadosMap(Map<String, String> empleadosMap) {
		this.empleadosMap = empleadosMap;
	}
	public Map<String, PersonaBusqueda> getEmpleadoMap() {
		return empleadoMap;
	}
	public void setEmpleadoMap(Map<String, PersonaBusqueda> empleadoMap) {
		this.empleadoMap = empleadoMap;
	}
	public String getStrDepartamento() {
		return strDepartamento;
	}
	public void setStrDepartamento(String strDepartamento) {
		this.strDepartamento = strDepartamento;
	}
	
	
	public String getStrDescPuesto() {
		return strDescPuesto;
	}
	public void setStrDescPuesto(String strDescPuesto) {
		this.strDescPuesto = strDescPuesto;
	}
	public String getStrCveDepto() {
		return strCveDepto;
	}
	public void setStrCveDepto(String strCveDepto) {
		this.strCveDepto = strCveDepto;
	}
	
	
	
	
}
