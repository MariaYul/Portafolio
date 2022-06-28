package com.telcel.gsrh.solicitudcurso.service;

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

public interface HistoricoService extends GenericOperation<Historico> {

    /**
     * Consulta dinamicamente los usuarios de acuerdo a un rango
     * @param parametro Par�metros de b�squeda
     * @return List<Usuario> Usuarios de acuerdo a un rango
     */
    public List<Historico> search(HistoricoBusqueda parametro);
    
    /**
     * Obtiene la cantidad de usuarios de acuerdo a los par�metros de b�squeda
     * @param parametro Par�metros de b�squeda
     * @return Cantidad de usuarios de acuerdo a los par�metros de b�squeda
     */
    public Long getCantidadSearch(HistoricoBusqueda parametro);
    
    /**
     * Obtiene la lista de las fases que le corresponde a cada area
     * @param Integer clave de area
     * @return Lista de fases que le corresponde a cada area
     */
    public Integer eliminarSolicitud(Integer cve);
    
    
    
}
