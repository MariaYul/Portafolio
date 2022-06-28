package com.telcel.gsrh.solicitudcurso.service;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.PuestoBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Puesto;

public interface PuestoService extends GenericOperation<Puesto> {

    /**
     * Consulta dinamicamente los puestos de acuerdo a un rango
     * @param parametro Parámetros de búsqueda
     * @return List<Area> Puestos de acuerdo a un rango
     */
    public List<Puesto> search(PuestoBusqueda parametro);
    
    /**
     * Obtiene la cantidad de Puestos de acuerdo a los parámetros de búsqueda
     * @param parametro Parámetros de búsqueda
     * @return Cantidad de Puestos de acuerdo a los parámetros de búsqueda
     */
    public Long getCantidadSearch(PuestoBusqueda parametro);
}
