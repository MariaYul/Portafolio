package com.telcel.gsrh.solicitudcurso.service;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.TipoDocumentoWS;

public interface TipoDocumentoWsService extends GenericOperation<TipoDocumentoWS> {

    /**
     * Consulta los tipos de documento que están activos, pertenecen a una fase y que: <br />
     * <ul>
     * <li>Son multiregistro</li>
     * <li>No son multiregistro pero no han sido asignados a un candidato</li>
     * </ul>
     * @param claveFase Clave de fase
     * @param claveCandidato Clave de candidato
     * @return Tipos de documento que pueden ser asignados a un candidato en una fáse por un área
     */
    public List<TipoDocumentoWS> getTiposDocumentoByLoadToCandidate(int claveFase, int claveCandidato);
}
