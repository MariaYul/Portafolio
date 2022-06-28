package com.telcel.gsrh.solicitudcurso.repository;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.SolicitudBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Solicitud;

public interface SolicitudRepository extends GenericOperation<Solicitud> {

	 /**
     * Consulta dinamicamente el listado de solicitudes 
     * @param parametro Parámetros de búsqueda
     * @return List<Solicitud> Solicitudes de acuerdo a los parámetros de búsqueda
     */
    public List<Solicitud> searchSolicitudes(SolicitudBusqueda parametro);
    
    /**
     * Obtiene la cantidad de solicitudes de acuerdo a los parámetros de búsqueda
     * @param parametro Parámetros de búsqueda
     * @return Cantidad de usuarios de acuerdo a los parámetros de búsqueda
     */
    public Long getCantidadSearchSolicitudes(SolicitudBusqueda parametro);
    
    /**
     * Realiza la baja de la solicitud de acuerdo a la clave de solicitud
     * @param Integer clave de solicitud
     * @return actualizacion de la solicitud realizada
     */
    public int eliminarSolicitud(Integer cve);
    
    /**
     * Realiza la actualizacion de la solicitud de acuerdo a la clave de solicitud
     * @param Integer clave de solicitud
     * @param Integer estatus de la solicitud
     * @return Lista de fases que le corresponde a cada area
     */
    public int updateEdoSolicitud(Integer cveSolicitud,Integer id_edoSol);
    
  
}
