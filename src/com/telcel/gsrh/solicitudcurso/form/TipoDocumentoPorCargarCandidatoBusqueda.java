package com.telcel.gsrh.solicitudcurso.form;

import java.io.Serializable;

public class TipoDocumentoPorCargarCandidatoBusqueda implements Serializable {

    private static final long serialVersionUID = 1L;
    private int area;
    private int candidato;
    private int fase;
    
    public int getArea() {
        return area;
    }
    public void setArea(int area) {
        this.area = area;
    }
    public int getCandidato() {
        return candidato;
    }
    public void setCandidato(int candidato) {
        this.candidato = candidato;
    }
    public int getFase() {
        return fase;
    }
    public void setFase(int fase) {
        this.fase = fase;
    }
}
