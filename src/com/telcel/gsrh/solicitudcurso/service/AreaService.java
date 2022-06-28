package com.telcel.gsrh.solicitudcurso.service;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.AreaBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Area;

public interface AreaService extends GenericOperation<Area> {

    /**
     * Obtiene la cantidad de �reas de acuerdo a los par�metros de b�squeda
     * @param parametro Par�metros de b�squeda
     * @return Cantidad de �reas de acuerdo a los par�metros de b�squeda
     */
    public Long getCantidadSearch(AreaBusqueda parametro);
    
    /**
     * Consulta dinamicamente las �reas de acuerdo a un rango
     * @param parametro Par�metros de b�squeda
     * @return List<TipoDocumento> �reas de acuerdo a un rango
     */
    public List<Area> search(AreaBusqueda parametro);
}
