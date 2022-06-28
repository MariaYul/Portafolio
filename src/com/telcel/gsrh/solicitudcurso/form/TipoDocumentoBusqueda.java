package com.telcel.gsrh.solicitudcurso.form;

import java.io.Serializable;

public class TipoDocumentoBusqueda extends Busqueda implements Serializable {

    private static final long serialVersionUID = 1L;
    private int area;
    private int fase;
    private int estatus;
    private int multiregistro;
    
    public int getArea() {
        return area;
    }
    public void setArea(int area) {
        this.area = area;
    }
    public int getFase() {
        return fase;
    }
    public void setFase(int fase) {
        this.fase = fase;
    }
    public int getEstatus() {
        return estatus;
    }
    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
    public int getMultiregistro() {
        return multiregistro;
    }
    public void setMultiregistro(int multiregistro) {
        this.multiregistro = multiregistro;
    }
}
