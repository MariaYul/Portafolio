package com.telcel.gsrh.solicitudcurso.service;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.TipoDocumentoBusqueda;
import com.telcel.gsrh.solicitudcurso.form.TipoDocumentoPorCargarCandidatoBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Moneda;

public interface MonedaService extends GenericOperation<Moneda> {

    /**
     * Consulta los tipos de documento que están activos, pertenecen a un área y fase y que: <br />
     * <ul>
     * <li>Son multiregistro</li>
     * <li>No son multiregistro pero no han sido asignados a un candidato</li>
     * </ul>
     * @param parametro Parámetros de filtrado
     * @return Tipos de documento que pueden ser asignados a un candidato en una fáse por un área
     */
    public List<Moneda> getTiposDocumentoByLoadToCandidate(TipoDocumentoPorCargarCandidatoBusqueda parametro);
    
    /**
     * Consulta dinamicamente los tipos de documento
     * @param parametro Parámetros de búsqueda
     * @return List<TipoDocumento> Tipos de documento
     */
    public List<Moneda> searchTiposDocumento(TipoDocumentoBusqueda parametro);
    
    /**
     * Obtiene la cantidad de Tipos de Documento de acuerdo a los parámetros de búsqueda
     * @param parametro Parámetros de búsqueda
     * @return Cantidad de Tipos de Documento de acuerdo a los parámetros de búsqueda
     */
    public Long getCantidadSearchTiposDocumento(TipoDocumentoBusqueda parametro);
}
