package com.telcel.gsrh.solicitudcurso.repository;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.LocalidadBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Localidad;

public interface LocalidadRepository extends GenericOperation<Localidad> {

    /**
     * Consulta dinamicamente los Localidades de acuerdo a un rango
     * @param parametro Parámetros de búsqueda
     * @return List<Area> Localidades de acuerdo a un rango
     */
    public List<Localidad> search(LocalidadBusqueda parametro);
    
    /**
     * Obtiene la cantidad de Localidades de acuerdo a los parámetros de búsqueda
     * @param parametro Parámetros de búsqueda
     * @return Cantidad de Localidades de acuerdo a los parámetros de búsqueda
     */
    public Long getCantidadSearch(LocalidadBusqueda parametro);
}
