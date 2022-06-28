package com.telcel.gsrh.solicitudcurso.service.impl;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.SolicitudBusqueda;
import com.telcel.gsrh.solicitudcurso.form.TipoDocumentoPorCargarCandidatoBusqueda;
import com.telcel.gsrh.solicitudcurso.form.UsuarioBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Solicitud;
import com.telcel.gsrh.solicitudcurso.model.TipoDocumento;
import com.telcel.gsrh.solicitudcurso.model.Usuario;

public interface SolicitudService extends GenericOperation<Solicitud> {

    /**
     * Consulta dinamicamente los usuarios de acuerdo a un rango
     * @param parametro Parámetros de búsqueda
     * @return List<Usuario> Usuarios de acuerdo a un rango
     */
    public List<Solicitud> searchSolicitudes(SolicitudBusqueda parametro);
    
    /**
     * Obtiene la cantidad de usuarios de acuerdo a los parámetros de búsqueda
     * @param parametro Parámetros de búsqueda
     * @return Cantidad de usuarios de acuerdo a los parámetros de búsqueda
     */
    public Long getCantidadSearchSolicitudes(SolicitudBusqueda parametro);
    
    /**
     * Obtiene la lista de las fases que le corresponde a cada area
     * @param Integer clave de area
     * @return Lista de fases que le corresponde a cada area
     */
    public Integer eliminarSolicitud(Integer cve);
    
    
    /**
     * Obtiene la lista de las fases que le corresponde a cada area
     * @param Integer clave de area
     * @return Lista de fases que le corresponde a cada area
     */
    public Integer eliminarEmpleado(Integer cve,Integer numRegis);
    
    public Long getUsusarioSolicitud(Integer cve);
    
    
}
