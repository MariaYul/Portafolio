package com.telcel.gsrh.solicitudcurso.repository;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.AreaBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Area;

public interface AreaRepository extends GenericOperation<Area> {
    
    /**
     * Consulta dinamicamente las áreas de acuerdo a un rango
     * @param parametro Parámetros de búsqueda
     * @return List<Area> Áreas de acuerdo a un rango
     */
    public List<Area> search(AreaBusqueda parametro);
    
    /**
     * Obtiene la cantidad de Áreas de acuerdo a los parámetros de búsqueda
     * @param parametro Parámetros de búsqueda
     * @return Cantidad de Áreas de acuerdo a los parámetros de búsqueda
     */
    public Long getCantidadSearch(AreaBusqueda parametro);

}
