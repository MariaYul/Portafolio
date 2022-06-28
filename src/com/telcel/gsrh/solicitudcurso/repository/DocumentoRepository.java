package com.telcel.gsrh.solicitudcurso.repository;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.DocumentoBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Documento;

public interface DocumentoRepository extends GenericOperation<Documento> {

    /**
     * Consulta dinamicamente los Documentos de acuerdo a un rango
     * @param parametro Par�metros de b�squeda
     * @return List<Area> Documentos de acuerdo a un rango
     */
    public List<Documento> search(DocumentoBusqueda parametro);
    
    /**
     * Consulta un documento por candidato, tipo de documento y nombre de documento
     * @param documento Par�metros de filtrado
     * @return Documento por candidato, tipo de documento y nombre de documento
     */
    public Documento searchByDelete(Documento documento);
    
    /**
     * Consulta la cantidad total de documentos de acuerdo a los par�metros de b�squeda
     * @param parametro Par�metros de b�squeda
     * @return List<Area> Cantidad total de documentos de acuerdo a los par�metros de b�squeda
     */
    public Long getCantidadSearch(DocumentoBusqueda parametro);
    
    /**
     * Consulta la cantidad total de documentos de acuerdo a los nuevos par�metros de b�squeda
     * @param parametro Par�metros de b�squeda
     * @return Cantidad total de documentos de acuerdo a los nuevos par�metros de b�squeda
     */
    public Integer getCantidadSearchNuevo(Documento parametro);
    
    /**
     * metodo que regresa todos los documentos que tiene un empleado
     * @param Documento parametro
     * @return lista de documentos ordenados por fase y despues por nombre
     */
    public List<Documento> searchFlipping(DocumentoBusqueda parametro);
}
