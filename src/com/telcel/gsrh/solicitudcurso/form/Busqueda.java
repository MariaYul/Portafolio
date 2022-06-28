package com.telcel.gsrh.solicitudcurso.form;

import com.telcel.gsrh.solicitudcurso.taglib.PaginationTagLib;

public abstract class Busqueda {
    
    private String nombre;
    private Integer offset;
    private Integer max;
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getOffset() {
        return offset == null ? 0 : offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
    public int getMax() {
        return max == null ? PaginationTagLib.STEPS : max;
    }
    public void setMax(int max) {
        this.max = max;
    }
}
