package com.telcel.gsrh.solicitudcurso.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Apellidos implements Serializable {

    private static final long serialVersionUID = -7444742524688205985L;

    @Column(name="APELLIDOPATERNO")
    private String apellidoPaterno;
    
    @Column(name="APELLIDOMATERNO")
    private String apellidoMaterno;

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
    
}
