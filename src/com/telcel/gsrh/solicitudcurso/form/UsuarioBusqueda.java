package com.telcel.gsrh.solicitudcurso.form;

import java.io.Serializable;

public class UsuarioBusqueda extends Busqueda implements Serializable {

    private static final long serialVersionUID = 1L;
    private String numeroEmpleado;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int claveArea;
    private int claveRegion;
    private int estatus;
    
    
    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }
    public void setNumeroEmpleado(String numeroEmpleado) {
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
    public int getClaveArea() {
        return claveArea;
    }
    public void setClaveArea(int claveArea) {
        this.claveArea = claveArea;
    }
    public int getClaveRegion() {
        return claveRegion;
    }
    public void setClaveRegion(int claveRegion) {
        this.claveRegion = claveRegion;
    }
    public int getEstatus() {
        return estatus;
    }
    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

}
