package com.telcel.gsrh.solicitudcurso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.form.SolicitudBusqueda;
import com.telcel.gsrh.solicitudcurso.form.TipoDocumentoPorCargarCandidatoBusqueda;
import com.telcel.gsrh.solicitudcurso.form.UsuarioBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Area;
import com.telcel.gsrh.solicitudcurso.model.Fase;
import com.telcel.gsrh.solicitudcurso.model.Participantes;
import com.telcel.gsrh.solicitudcurso.model.Solicitud;
import com.telcel.gsrh.solicitudcurso.model.TipoDocumento;
import com.telcel.gsrh.solicitudcurso.model.Usuario;
import com.telcel.gsrh.solicitudcurso.repository.ParticipanteRepository;
import com.telcel.gsrh.solicitudcurso.repository.SolicitudRepository;
import com.telcel.gsrh.solicitudcurso.repository.UsuarioRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.ParticipanteService;
import com.telcel.gsrh.solicitudcurso.service.SolicitudService;
import com.telcel.gsrh.solicitudcurso.service.UsuarioService;

@Service
public class ParticipanteServiceImpl extends AbstractService<Participantes> implements ParticipanteService {

	 @Autowired
	 private ParticipanteRepository repository;
	 
	@Override
	protected GenericOperation<Participantes> getRepository() {
		return repository;
	}

	
  
   
}
