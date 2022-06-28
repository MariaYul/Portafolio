package com.telcel.gsrh.solicitudcurso.service;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.DepartamentoBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Departamento;

public interface DepartamentoService extends GenericOperation<Departamento> {

    /**
     * Obtiene la cantidad de Departamentos de acuerdo a los parámetros de búsqueda
     * @param parametro Parámetros de búsqueda
     * @return Cantidad de Departamentos de acuerdo a los parámetros de búsqueda
     */
    public Long getCantidadSearch(DepartamentoBusqueda parametro);
    
    /**
     * Consulta dinamicamente los departamentos de acuerdo a un rango
     * @param parametro Parámetros de búsqueda
     * @return List<Area> Departamentos de acuerdo a un rango
     */
    public List<Departamento> search(DepartamentoBusqueda parametro);
}
