package com.telcel.gsrh.solicitudcurso.form;

import java.io.Serializable;

public class DocumentoBusqueda extends Busqueda implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer clavePersona;
    private Integer claveFase;
    
    public Integer getClavePersona() {
        return clavePersona;
    }
    public void setClavePersona(Integer clavePersona) {
        this.clavePersona = clavePersona;
    }
    public Integer getClaveFase() {
        return claveFase;
    }
    public void setClaveFase(Integer claveFase) {
        this.claveFase = claveFase;
    }
}
