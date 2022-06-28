package com.telcel.gsrh.solicitudcurso.repository;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Fase;

public interface FaseRepository extends GenericOperation<Fase> {

    /**
     * Obtiene la lista de las fases que le corresponde a cada area
     * @param Integer clave de area
     * @return Lista de fases que le corresponde a cada area
     */
    public List<Fase> searchFasePorArea(Integer areaClave);
}
