package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.form.AreaBusqueda;
import com.telcel.gsrh.solicitudcurso.form.DocumentoBusqueda;
import com.telcel.gsrh.solicitudcurso.form.EstadoSolicitudBusqueda;
import com.telcel.gsrh.solicitudcurso.form.HistoricoBusqueda;
import com.telcel.gsrh.solicitudcurso.form.SolicitudBusqueda;
import com.telcel.gsrh.solicitudcurso.form.TipoDocumentoPorCargarCandidatoBusqueda;
import com.telcel.gsrh.solicitudcurso.form.UsuarioBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Area;
import com.telcel.gsrh.solicitudcurso.model.Departamento;
import com.telcel.gsrh.solicitudcurso.model.Documento;
import com.telcel.gsrh.solicitudcurso.model.EstadoSolicitud;
import com.telcel.gsrh.solicitudcurso.model.Estatus;
import com.telcel.gsrh.solicitudcurso.model.Fase;
import com.telcel.gsrh.solicitudcurso.model.Historico;
import com.telcel.gsrh.solicitudcurso.model.Localidad;
import com.telcel.gsrh.solicitudcurso.model.Participantes;
import com.telcel.gsrh.solicitudcurso.model.Persona;
import com.telcel.gsrh.solicitudcurso.model.Puesto;
import com.telcel.gsrh.solicitudcurso.model.Region;
import com.telcel.gsrh.solicitudcurso.model.Solicitud;
import com.telcel.gsrh.solicitudcurso.model.TipoDocumento;
import com.telcel.gsrh.solicitudcurso.model.Usuario;
import com.telcel.gsrh.solicitudcurso.repository.EstadoRepository;
import com.telcel.gsrh.solicitudcurso.repository.HistoricoRepository;
import com.telcel.gsrh.solicitudcurso.repository.SolicitudRepository;
import com.telcel.gsrh.solicitudcurso.repository.UsuarioRepository;

@Repository
public class EstadoRepositoryImpl extends GenericHibernateRepositoryImpl<EstadoSolicitud> implements EstadoRepository {
    
    public EstadoRepositoryImpl() {
        setClase(EstadoSolicitud.class);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<EstadoSolicitud> search(EstadoSolicitudBusqueda parametro) {
    	Criteria consulta = getSearchCriteria(parametro);
    	consulta.setFirstResult(parametro.getOffset());
        consulta.setMaxResults(parametro.getMax());
        consulta.addOrder(Property.forName("fecha_registro").asc());
    
         return consulta.list();
    }
    
    
    @Override
    public Long getCantidadSearch(EstadoSolicitudBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setProjection(Projections.rowCount());
        
        return (Long)consulta.uniqueResult();
    }
    
    private Criteria getSearchCriteria(EstadoSolicitudBusqueda parametro) {
        Criteria consulta = getSession().createCriteria(Historico.class);
        
        if(parametro.getClave()!=null) {
            consulta = addIntegerCriteria("historicos.clave", parametro.getClave(), consulta);
        }
        
        return consulta;
    }

	@Override
	public int eliminarSolicitud(Integer cve) {
		// TODO Auto-generated method stub
		return 0;
	}

    
   
  
   
   }
