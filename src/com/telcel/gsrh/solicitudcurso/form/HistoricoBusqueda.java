package com.telcel.gsrh.solicitudcurso.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class HistoricoBusqueda extends Busqueda implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer clave;
    private Map<String, String> empleadosMap = new HashMap<String,String>(); 
    private Map<String, PersonaBusqueda> empleadoMap = new HashMap<String,PersonaBusqueda>();
    
    
	public Integer getClave() {
		return clave;
	}
	public void setClave(Integer clave) {
		this.clave = clave;
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
	
	
}
