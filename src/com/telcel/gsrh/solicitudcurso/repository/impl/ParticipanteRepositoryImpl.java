package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.form.SolicitudBusqueda;
import com.telcel.gsrh.solicitudcurso.form.TipoDocumentoPorCargarCandidatoBusqueda;
import com.telcel.gsrh.solicitudcurso.form.UsuarioBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Fase;
import com.telcel.gsrh.solicitudcurso.model.Participantes;
import com.telcel.gsrh.solicitudcurso.model.Solicitud;
import com.telcel.gsrh.solicitudcurso.model.TipoDocumento;
import com.telcel.gsrh.solicitudcurso.model.Usuario;
import com.telcel.gsrh.solicitudcurso.repository.ParticipanteRepository;
import com.telcel.gsrh.solicitudcurso.repository.SolicitudRepository;
import com.telcel.gsrh.solicitudcurso.repository.UsuarioRepository;

@Repository
public class ParticipanteRepositoryImpl extends GenericHibernateRepositoryImpl<Participantes> implements ParticipanteRepository {
    
    public ParticipanteRepositoryImpl() {
        setClase(Participantes.class);
    }
    
    
   

}
