package com.telcel.gsrh.solicitudcurso.repository;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.AreaBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Area;

public interface AreaRepository extends GenericOperation<Area> {
    
    /**
     * Consulta dinamicamente las �reas de acuerdo a un rango
     * @param parametro Par�metros de b�squeda
     * @return List<Area> �reas de acuerdo a un rango
     */
    public List<Area> search(AreaBusqueda parametro);
    
    /**
     * Obtiene la cantidad de �reas de acuerdo a los par�metros de b�squeda
     * @param parametro Par�metros de b�squeda
     * @return Cantidad de �reas de acuerdo a los par�metros de b�squeda
     */
    public Long getCantidadSearch(AreaBusqueda parametro);

}
