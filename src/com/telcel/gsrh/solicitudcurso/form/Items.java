package com.telcel.gsrh.solicitudcurso.form;

import java.io.Serializable;

import com.telcel.gsrh.solicitudcurso.model.Persona;

public class Items {

    private static final long serialVersionUID = 1L;
    
    private Persona persona;
    

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Items(){
		
	}
		
	public Items(Persona persona){
		this.persona=persona;
	}
    
  }
