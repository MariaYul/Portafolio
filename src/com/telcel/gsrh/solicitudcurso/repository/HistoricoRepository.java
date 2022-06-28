package com.telcel.gsrh.solicitudcurso.repository;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.HistoricoBusqueda;
import com.telcel.gsrh.solicitudcurso.form.SolicitudBusqueda;
import com.telcel.gsrh.solicitudcurso.form.TipoDocumentoPorCargarCandidatoBusqueda;
import com.telcel.gsrh.solicitudcurso.form.UsuarioBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Fase;
import com.telcel.gsrh.solicitudcurso.model.Historico;
import com.telcel.gsrh.solicitudcurso.model.Participantes;
import com.telcel.gsrh.solicitudcurso.model.Solicitud;
import com.telcel.gsrh.solicitudcurso.model.TipoDocumento;
import com.telcel.gsrh.solicitudcurso.model.Usuario;

public interface HistoricoRepository extends GenericOperation<Historico> {

    /**
     * Consulta dinamicamente los usuarios de acuerdo a un rango
     * @param parametro Parámetros de búsqueda
     * @return List<Usuario> Usuarios de acuerdo a un rango
     */
    public List<Historico> search(HistoricoBusqueda parametro);
    
    /**
     * Obtiene la cantidad de usuarios de acuerdo a los parámetros de búsqueda
     * @param parametro Parámetros de búsqueda
     * @return Cantidad de usuarios de acuerdo a los parámetros de búsqueda
     */
    public Long getCantidadSearch(HistoricoBusqueda parametro);
    
    /**
     * Obtiene la cantidad de usuarios de acuerdo a los parámetros de búsqueda
     * @param parametro Parámetros de búsqueda
     * @return Cantidad de usuarios de acuerdo a los parámetros de búsqueda
     */
    public int eliminarSolicitud(Integer cve);
    
  
    
    

}
