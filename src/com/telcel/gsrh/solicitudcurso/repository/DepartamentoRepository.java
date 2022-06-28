package com.telcel.gsrh.solicitudcurso.repository;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.DepartamentoBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Departamento;

public interface DepartamentoRepository extends GenericOperation<Departamento> {

    /**
     * Consulta dinamicamente los departamentos de acuerdo a un rango
     * @param parametro Par�metros de b�squeda
     * @return List<Area> Departamentos de acuerdo a un rango
     */
    public List<Departamento> search(DepartamentoBusqueda parametro);
    
    /**
     * Obtiene la cantidad de Departamentos de acuerdo a los par�metros de b�squeda
     * @param parametro Par�metros de b�squeda
     * @return Cantidad de Departamentos de acuerdo a los par�metros de b�squeda
     */
    public Long getCantidadSearch(DepartamentoBusqueda parametro);
}
