package com.telcel.gsrh.solicitudcurso.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.telcel.dsrh.EnviaMail.mail.EnviaMailDSRH;
import com.telcel.gsrh.solicitudcurso.form.HistoricoBusqueda;
import com.telcel.gsrh.solicitudcurso.form.Items;
import com.telcel.gsrh.solicitudcurso.form.PersonaBusqueda;
import com.telcel.gsrh.solicitudcurso.form.SolicitudBusqueda;
import com.telcel.gsrh.solicitudcurso.model.EstadoSolicitud;
import com.telcel.gsrh.solicitudcurso.model.Historico;
import com.telcel.gsrh.solicitudcurso.model.Moneda;
import com.telcel.gsrh.solicitudcurso.model.Participantes;
import com.telcel.gsrh.solicitudcurso.model.Persona;
import com.telcel.gsrh.solicitudcurso.model.Solicitud;
import com.telcel.gsrh.solicitudcurso.service.CorreoService;
import com.telcel.gsrh.solicitudcurso.service.EstadoService;
import com.telcel.gsrh.solicitudcurso.service.HistoricoService;
import com.telcel.gsrh.solicitudcurso.service.MonedaService;
import com.telcel.gsrh.solicitudcurso.service.PersonaService;
import com.telcel.gsrh.solicitudcurso.service.SolicitudService;
import com.telcel.gsrh.solicitudcurso.service.impl.CorreoServiceImpl;
import com.telcel.gsrh.solicitudcurso.utils.UtilMail;

@Controller
@RequestMapping("/solicitud")
public class SolicitudController {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private SolicitudService solicitudService;

	@Autowired
	private HistoricoService historicoService;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private MonedaService monedaservice;

	private static final String PAGINA_MAIN_USUARIO = "solicitudmain";
	private static final String PAGINA_RS_SOLICITUDES = "solicitudes";
	private static final String PAGINA_REDIRECT_RS_SOLICITUD = "redirect:/solicitud/main.htm";
	private static final String PAGINA_EDIT_SOLICITUD = "solicitudedit";
	private static final String PAGINA_CONSULTA_SOLICITUD = "solicitudconsulta";
	private static final String PAGINA_ADD_SOLICITUD = "solicitudadd";
	private static final String PAGINA_RS_HISTORICO = "historicors";
	private static final String PAGINA_MAIN_PARTICIPANTES = "newListadoAdiParticipantes";
	private static final String PAGINA_CONSULTA_PARTICIPANTES_SESION = "participatesAddSesion";
	private static final String PAGINA_INICIO_PARTICIPANTES_EDIT = "listadoAdiParticiEdit";

	private static final String PAGINA_ADD_MOTIVO_RECHAZO = "motivoRechazo";

	private static final String MSG_SOLICITUD_ELIMINADO = "Solicitud eliminada correctamente";
	private static final String MSG_SOLICITUD_REGISTRADO = "Solicitud registrada correctamente";
	private static final String MSG_SOLICITUD_ACTUALIZADO = "Solicitud actualizada correctamente";

	private static final String OPCION_ACTIVA_NAVBAR = "lisolicitud";
	private static final String USUARIO_SISTEMA = "usuarioRegistrado";

	@RequestMapping(value = "/main.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView main(@RequestParam(required = false) String info,
			@RequestParam(required = false) String error) {
		ModelAndView modelo = new ModelAndView();

		modelo.addObject("activeitemmenu", OPCION_ACTIVA_NAVBAR);

		if (info != null) {
			modelo.addObject("info", info);
		}

		if (error != null) {
			modelo.addObject("error", error);
		}

		modelo.setViewName(PAGINA_MAIN_USUARIO);

		return modelo;
	}

	@RequestMapping(value = "/undecorated/search.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView search(@RequestParam(required = false) Integer clave, SolicitudBusqueda busqueda,
			HttpSession session) {
		ModelAndView modelo = new ModelAndView();
		Persona persona = (Persona) session.getAttribute(USUARIO_SISTEMA);

		busqueda.setStrDepartamento(persona.getDepartamento().getNombre());
		busqueda.setStrCveDepto(persona.getDepartamento().getClave());
		busqueda.setClaveEmpleado(persona.getClave());
		busqueda.setStrDescPuesto(persona.getPuesto().getNombre());
		busqueda.setClave(clave);

		List<Solicitud> solicitudes = solicitudService.searchSolicitudes(busqueda);
		Long cantidadTotalRegistros = solicitudService.getCantidadSearchSolicitudes(busqueda);

		System.out.println("tamaño search:" + solicitudes.size());

		modelo.addObject("solicitudes", solicitudes);
		modelo.addObject("count", cantidadTotalRegistros);
		modelo.addObject("offset", busqueda.getOffset());

		modelo.setViewName(PAGINA_RS_SOLICITUDES);

		return modelo;
	}

	/************ ADD ***********/
	@RequestMapping(value = "/loadNew.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView loadNew() {
		ModelAndView modelo = new ModelAndView();
		SolicitudBusqueda soliForm = new SolicitudBusqueda();
		Map<String, String> empleadosMap = new HashMap<String, String>();
		List<Moneda> tipos = monedaservice.findAll();

		empleadosMap.put("1", "1");
		empleadosMap.put("2", "2");
		empleadosMap.put("3", "3");
//        empleadosMap.put("4","4");

		soliForm.setEmpleadosMap(empleadosMap);
		modelo.addObject("empMap", soliForm);
		modelo.addObject("tipos", tipos);
		modelo.setViewName(PAGINA_ADD_SOLICITUD);

		return modelo;
	}

	@RequestMapping(value = "/add.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView add(Solicitud solicitud, SolicitudBusqueda bus, HttpServletRequest request,
			HttpSession session) {
		int intPos = 1;
		Set<Participantes> participantes = new HashSet<Participantes>();
		Set<Historico> historicos = new HashSet<Historico>();
		ModelAndView modelo = new ModelAndView();
		Historico historico = new Historico();
		Iterator it = bus.getEmpleadosMap().keySet().iterator();
		Persona usuario = (Persona) session.getAttribute(USUARIO_SISTEMA);

		while (it.hasNext()) {
			String key = (String) it.next();
			Participantes participante = new Participantes();
			participante.setParticipantes(solicitud);
			participante.setClave_EMPLEADO(Integer.valueOf(bus.getEmpleadosMap().get(key)));
			participante.setId_status(1);
			participante.setOrden(intPos++);
			participante.setFecha_actualizacion(new Date());
			participantes.add(participante);

		}

		Persona persona = personaService.findById(usuario.getClave());
		EstadoSolicitud estado = estadoService.findById(1);

		historico.setHistoricos(solicitud);
		historico.setPersona(persona);
		historico.setComentario(estado.getNombre());
		historico.setFecha_registro(new Date());
		historico.setEdoSolicitud(estado);
		historicos.add(historico);

		solicitud.setParticipantes(participantes);
		solicitud.setHistoricos(historicos);

		solicitud.setFecha_creacion(new Date());// FECHA Y HORA DE REGISTRO
		solicitud.setFecha_actualizacion(new Date());
		solicitud.setPersona(persona);
		solicitud.setEdoSolicitud(estado);
		solicitud.setCve_depto(persona.getDepartamento().getClave());

		solicitudService.register(solicitud);

		CorreoService correoService = new CorreoServiceImpl();
		String to = correoService.getMail(usuario.getClave());
		System.out.println("TO " + to);
		// CORREO CREACION SOLICITUD
		try {
			if(to != null) {
				EnviaMailDSRH enviaUser = new EnviaMailDSRH();
				enviaUser.setFrom(UtilMail.MAIL_REMITENTE);
				enviaUser.setTo(to);
				enviaUser.setSubject("NUEVA " + UtilMail.SUBJECT);
				enviaUser.setContent(UtilMail.solicitudCreada(solicitud));
				//enviaUser.sendMail();
				System.out.println("entro al la condicion no null");
			}
			EnviaMailDSRH envia = new EnviaMailDSRH();
			envia.setFrom(UtilMail.MAIL_REMITENTE);
			envia.setTo(UtilMail.MAIL_CAPACITACION);
			envia.setSubject("NUEVA " + UtilMail.SUBJECT);
			envia.setContent(UtilMail.solicitudCreada(solicitud));
			//envia.sendMail();
		} catch (Exception e) {
			System.err.println("MAIL" + e.getMessage());
		}

		modelo.addObject("info", MSG_SOLICITUD_REGISTRADO);
		modelo.setViewName(PAGINA_REDIRECT_RS_SOLICITUD);

		return modelo;
	}

	@RequestMapping(value = "/loadConsulta.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView loadConsulta(@RequestParam(defaultValue = "0", required = false) Integer clave) {
		ModelAndView modelo = new ModelAndView();
		List<Persona> lpersonas;
		PersonaBusqueda soliForm = new PersonaBusqueda();
		Map<String, PersonaBusqueda> contactMap = new TreeMap<String, PersonaBusqueda>();
		Historico historico = new Historico();
		List<Historico> lhistorico = new ArrayList<Historico>();
		Solicitud solicitud = solicitudService.findById(clave);
		Iterator it = solicitud.getHistoricos().iterator();
		List<Moneda> tipos = monedaservice.findAll();

		while (it.hasNext()) {
			historico = (Historico) it.next();
			lhistorico.add(historico);
		}

		lpersonas = personaService.getDatosParticipantes(solicitud.getClave(), 4);

		Object[] fila;
		int pos = 0;
		for (Object item : lpersonas) {
			pos++;
			fila = (Object[]) item;
			Persona per = new Persona();
			per = (Persona) fila[0];
			contactMap.put(String.valueOf(pos),
					new PersonaBusqueda(per.getClave(),
							per.getNombre().concat(" ")
									.concat(per.getApellidos().getApellidoPaterno().concat(" ")
											.concat(per.getApellidos().getApellidoMaterno())),
							per.getPuesto().getNombre(), per.getCostos().getClave()));
			soliForm.setEmpleadoMap(contactMap);
		}

		modelo.addObject("creatorSolicitud", solicitud.getPersona().getClave());
		modelo.addObject("tipos", tipos);
		modelo.addObject("empMap", soliForm);
		modelo.addObject("solicitud", solicitud);
		modelo.setViewName(PAGINA_CONSULTA_SOLICITUD);

		return modelo;
	}

	@RequestMapping(value = "/loadEdit.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView loadEdit(@RequestParam(defaultValue = "0", required = false) Integer clave) {
		ModelAndView modelo = new ModelAndView();
		List<Persona> lpersonas;
		PersonaBusqueda soliForm = new PersonaBusqueda();
		Map<String, PersonaBusqueda> contactMap = new TreeMap<String, PersonaBusqueda>();

		Solicitud solicitud = solicitudService.findById(clave);
		lpersonas = personaService.getDatosParticipantes(solicitud.getClave(), 4);
		System.out.println("tamaño personas:" + lpersonas.size());
		List<Moneda> tipos = monedaservice.findAll();

		Object[] fila;
		int pos = 0;
		for (Object item : lpersonas) {
			pos++;
			fila = (Object[]) item;
			Persona per = new Persona();
			per = (Persona) fila[0];
			contactMap.put(String.valueOf(pos),
					new PersonaBusqueda(per.getClave(),
							per.getNombre().concat(" ")
									.concat(per.getApellidos().getApellidoPaterno().concat(" ")
											.concat(per.getApellidos().getApellidoMaterno())),
							per.getPuesto().getNombre(), per.getCostos().getClave()));
			soliForm.setEmpleadoMap(contactMap);
		}

		modelo.addObject("tipos", tipos);
		modelo.addObject("empMap", soliForm);
		modelo.addObject("solicitud", solicitud);
		modelo.setViewName(PAGINA_EDIT_SOLICITUD);

		return modelo;
	}

	@RequestMapping(value = "/edit.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView edit(Solicitud solicitud, PersonaBusqueda bus, HttpServletRequest request,
			HttpSession session) {
		int intPos = 1;
		ModelAndView modelo = new ModelAndView();
		Set<Participantes> participantes = new HashSet<Participantes>();
		Set<Historico> historicos = new HashSet<Historico>();
		Historico historico = new Historico();
		Iterator it = bus.getParticipantesMap().keySet().iterator();
		Persona usuario = (Persona) session.getAttribute(USUARIO_SISTEMA);

		System.out.println("ingresa a actualizar");
		personaService.eliminarParticipante(solicitud.getClave(), 4);

		while (it.hasNext()) {
			String key = (String) it.next();
			Participantes participante = new Participantes();
			participante.setParticipantes(solicitud);
			participante.setClave_EMPLEADO(Integer.valueOf(bus.getParticipantesMap().get(key)));
			participante.setId_status(1);
			participante.setOrden(intPos++);
			participante.setFecha_actualizacion(new Date());
			participantes.add(participante);
		}

		Persona persona = personaService.findById(usuario.getClave());
		EstadoSolicitud estado = estadoService.findById(21);

		historico.setHistoricos(solicitud);
		historico.setPersona(persona);
		historico.setComentario(estado.getNombre());
		historico.setFecha_registro(new Date());
		historico.setEdoSolicitud(estado);
		historicos.add(historico);

		solicitud.setParticipantes(participantes);
		solicitud.setHistoricos(historicos);

		Solicitud solicitudAux = solicitudService.findById(solicitud.getClave());
		solicitud.setFecha_creacion(solicitudAux.getFecha_creacion());
		solicitud.setFecha_actualizacion(new Date());
		solicitud.setPersona(solicitudAux.getPersona());//
		solicitud.setEdoSolicitud(estado);
		solicitud.setCve_depto(persona.getDepartamento().getClave());

		solicitudService.update(solicitud);

		// CORREO SOLICITUD ACTUALIZADA
		Integer cveEmpleado = solicitudAux.getPersona().getClave();
		System.out.println("CVEEMPLEADO = " + cveEmpleado);
		CorreoService correoService = new CorreoServiceImpl();
		String to = correoService.getMail(cveEmpleado);
		System.err.println("TO " + to);
		System.out.println("TO " + to);
		// CORREO CREACION SOLICITUD
		try {
			if(to != null) {
				EnviaMailDSRH enviaUser = new EnviaMailDSRH();
				enviaUser.setFrom(UtilMail.MAIL_REMITENTE);
				enviaUser.setTo(to);
				enviaUser.setSubject(UtilMail.SUBJECT + " ACTUALIZADA");
				enviaUser.setContent(UtilMail.solicitudCreada(solicitud));
				enviaUser.sendMail();
				System.out.println("entro al la condicion no null");
			}
			EnviaMailDSRH envia = new EnviaMailDSRH();
			// AREA USUARIA
			envia.setFrom(UtilMail.MAIL_REMITENTE);
			envia.setTo(UtilMail.MAIL_CAPACITACION);
			envia.setSubject(UtilMail.SUBJECT + " ACTUALIZADA");
			envia.setContent(UtilMail.solicitudActualizada(solicitud));
			//envia.sendMail();
			
		} catch (Exception e) {
			System.err.println("MAIL" + e.getMessage());
			e.printStackTrace();
		}
		modelo.addObject("info", MSG_SOLICITUD_ACTUALIZADO);
		modelo.setViewName(PAGINA_REDIRECT_RS_SOLICITUD);

		return modelo;
	}

	@RequestMapping(value = "/eliminar.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView eliminar(@RequestParam(defaultValue = "0", required = false) Integer clave) {
		ModelAndView modelo = new ModelAndView();

		solicitudService.eliminarSolicitud(clave);

		modelo.addObject("info", MSG_SOLICITUD_ELIMINADO);
		modelo.setViewName(PAGINA_REDIRECT_RS_SOLICITUD);

		return modelo;
	}

	@RequestMapping(value = "/undecorated/loadHistorico.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView loadHistorico(@RequestParam(defaultValue = "0", required = false) Integer claveSolicitud) {
		ModelAndView modelo = new ModelAndView();
		System.out.println("calve:" + claveSolicitud);
		Solicitud solicitud = solicitudService.findById(claveSolicitud);
		System.out.println("soliciud histoi:" + solicitud.getHistoricos());
		Historico historico = new Historico();
		List<Historico> lhistorico = new ArrayList<Historico>();
		System.out.println("histor:" + historico);
		Iterator it = solicitud.getHistoricos().iterator();
		while (it.hasNext()) {
			historico = (Historico) it.next();
			lhistorico.add(historico);
		}

		modelo.addObject("historico", lhistorico);
		modelo.addObject("solicitud", solicitud);
		modelo.setViewName(PAGINA_RS_HISTORICO);

		return modelo;
	}

	/*
	 * @RequestMapping(value="/inicioListaAdicional.htm", method=RequestMethod.GET)
	 * public ModelAndView inicioListaAdicional(@RequestParam(required=false) String
	 * clave,@RequestParam(required=false) String
	 * info, @RequestParam(required=false) String error,HttpSession session)
	 * {//addListAdicional ModelAndView modelo = new ModelAndView(); String[]
	 * elementoEmpleados = clave.split(",");
	 * 
	 * if(info != null) { modelo.addObject("info", info); } if(error != null) {
	 * modelo.addObject("error", error); }
	 * modelo.setViewName(PAGINA_MAIN_PARTICIPANTES);
	 * 
	 * return modelo; }
	 */

	@RequestMapping(value = "/undecorated/inicioListaPartites.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView inicioListaPartites(@RequestParam(required = false) String info,
			@RequestParam(required = false) String error, HttpSession session) {
		ModelAndView modelo = new ModelAndView();

		if (info != null) {
			modelo.addObject("info", info);
		}

		if (error != null) {
			modelo.addObject("error", error);
		}

		modelo.setViewName(PAGINA_CONSULTA_PARTICIPANTES_SESION);
		return modelo;
	}

	@RequestMapping(value = "/inicioListaAdicionalEdit.htm", method = RequestMethod.GET)
	public ModelAndView inicioListaAdicionalEdit(@RequestParam(required = false) String info,
			@RequestParam(required = false) String error, HttpSession session,
			@RequestParam(defaultValue = "0", required = false) Integer clave) {
		ModelAndView modelo = new ModelAndView();
		List<Persona> lpersonas = new ArrayList<Persona>();
		List<Persona> lpersonal = null;

		lpersonal = personaService.getDatosParticipantes(clave, 4);

		Object[] fila;
		int pos = 0;
		for (Object item : lpersonal) {
			pos++;
			fila = (Object[]) item;
			Persona per = new Persona();
			per = (Persona) fila[0];
			lpersonas.add(new Persona(per.getClave(),
					per.getNombre().concat("")
							.concat(per.getApellidos().getApellidoPaterno().concat(" ")
									.concat(per.getApellidos().getApellidoMaterno())),
					per.getPuesto().getNombre(), per.getCostos().getNombre()));
		}

		session.setAttribute("lpersonas", lpersonas);
		modelo.addObject("lpersonas", lpersonas);

		if (info != null) {
			modelo.addObject("info", info);
		}

		if (error != null) {
			modelo.addObject("error", error);
		}

		modelo.setViewName(PAGINA_INICIO_PARTICIPANTES_EDIT);

		return modelo;
	}

	@RequestMapping(value = "/undecorated/addParticipanteListaSesion.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String addParticipanteListaSesion(@RequestParam(defaultValue = "0", required = false) Integer claveEmp,
			HttpSession session, @RequestParam(required = false) String info,
			@RequestParam(required = false) String error) {
		ModelAndView modelo = new ModelAndView();
		int indexAdici = -1;
		List<Items> listAdicionales = new ArrayList<Items>();
		String strResult = "0";

		if (session.getAttribute("listAdicionales") != null) {
			List<Items> listAdicional = (List<Items>) session.getAttribute("listAdicionales");

			indexAdici = isExistingAdicional(claveEmp, listAdicional);

			if ((indexAdici == -1)) {// no existe en la lista precargada, agregas uno a la lista adicional
				listAdicional.add(new Items(personaService.findById(claveEmp)));
				strResult = "1";
			} else {
				strResult = "0";
			}
			session.setAttribute("listAdicionales", listAdicional);
		} else {
			listAdicionales.add(new Items(personaService.findById(claveEmp)));
			session.setAttribute("listAdicionales", listAdicionales);
			strResult = "1";
		}

		if (info != null) {
			modelo.addObject("info", info);
		}

		if (error != null) {
			modelo.addObject("error", error);
		}

		modelo.setViewName(PAGINA_ADD_SOLICITUD);

		return strResult;
	}

	/*
	 * @RequestMapping(value="/undecorated/displayParticipanteListaSesion.htm",
	 * method={RequestMethod.GET, RequestMethod.POST})
	 * 
	 * @SuppressWarnings("unchecked") public ModelAndView
	 * displeParticipanteListaSesion(SolicitudBusqueda bus,HttpSession
	 * session,@RequestParam(required=false) String
	 * info, @RequestParam(required=false) String error) { ModelAndView modelo = new
	 * ModelAndView();
	 * 
	 * List <Items> listAdicionales= new ArrayList<Items>();
	 * 
	 * if(session.getAttribute("listAdicionales")!=null){ List <Items>
	 * listAdicional= (List<Items>)session.getAttribute("listAdicionales");
	 * session.setAttribute("listAdicionales", listAdicional); }
	 * 
	 * if(info != null) { modelo.addObject("info", info); }
	 * 
	 * if(error != null) { modelo.addObject("error", error); }
	 * 
	 * System.out.println("tamaño de la solici:"+listAdicionales.size());
	 * modelo.addObject("solicitudes", listAdicionales.size());
	 * 
	 * modelo.setViewName(PAGINA_CONSULTA_PARTICIPANTES_SESION);
	 * 
	 * return modelo; }
	 */

	@RequestMapping(value = "/undecorated/deleteParticipanteListaSesion.htm", method = { RequestMethod.GET,
			RequestMethod.POST }) // deleteEmplistaSesion
	@SuppressWarnings("unchecked")
	public ModelAndView deleteParticipanteListaSesion(@RequestParam(defaultValue = "0", required = false) Integer clave,
			HttpSession session, @RequestParam(required = false) String info,
			@RequestParam(required = false) String error) {
		ModelAndView modelo = new ModelAndView();
		int index = -1;
		List<Items> listAdicional = (List<Items>) session.getAttribute("listAdicionales");
		index = exists(clave, listAdicional);

		if (index != -1) {
			listAdicional.remove(index);
		}

		session.setAttribute("listAdicionales", listAdicional);

		if (info != null) {
			modelo.addObject("info", info);
		}

		if (error != null) {
			modelo.addObject("error", error);
		}

		modelo.setViewName(PAGINA_CONSULTA_PARTICIPANTES_SESION);
		return modelo;
	}

	//AUTORIZACION POR EL AREA DE CAPACITACION
	@RequestMapping(value = { "/undecorated/validarSolicitud.ajax" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ModelAndView validarSolicitud(@RequestParam(defaultValue = "0", required = false) Integer claveSolicitud,
			HttpServletRequest request) {
		List<Persona> lpersonas;
		PersonaBusqueda soliForm = new PersonaBusqueda();
		Map<String, PersonaBusqueda> contactMap = new TreeMap<String, PersonaBusqueda>();
		Integer intEdo = 0;
		ModelAndView modelo = new ModelAndView();
		intEdo = solicitudService.updateEdoSolicitud(claveSolicitud, 2);

		HttpSession session = request.getSession();
		Persona usuario = (Persona) session.getAttribute(USUARIO_SISTEMA);

		Historico histori = new Historico();

		Persona persona = personaService.findById(usuario.getClave());

		Solicitud solicitud = solicitudService.findById(claveSolicitud);

		EstadoSolicitud estado = estadoService.findById(2);

		histori.setHistoricos(solicitud);
		histori.setPersona(persona);
//	    histori.setComentario("Validación Realizada por el área de Capacitación");
		histori.setComentario(estado.getNombre());
		histori.setFecha_registro(new Date());
		histori.setEdoSolicitud(estado);

		lpersonas = personaService.getDatosParticipantes(solicitud.getClave(), 4);

		Object[] fila;
		int pos = 0;
		for (Object item : lpersonas) {
			pos++;
			fila = (Object[]) item;
			Persona per = new Persona();
			per = (Persona) fila[0];
			contactMap.put(String.valueOf(pos),
					new PersonaBusqueda(per.getClave(),
							per.getNombre().concat("")
									.concat(per.getApellidos().getApellidoPaterno().concat(" ")
											.concat(per.getApellidos().getApellidoMaterno())),
							per.getPuesto().getNombre(), per.getCostos().getNombre()));
			soliForm.setEmpleadoMap(contactMap);
		}
		modelo.addObject("empMap", soliForm);
		modelo.addObject("solicitud", solicitud);
		modelo.addObject("creatorSolicitud", solicitud.getPersona().getClave());
		historicoService.register(histori);

		// CORREO SOLICITUD VALIDADA
		System.out.println("solicitud.getPersona().getClave()" + solicitud.getPersona().getClave());
		
		CorreoService correoService = new CorreoServiceImpl();
		String to = correoService.getMail(solicitud.getPersona().getClave());
		
		System.out.println("TO " + to);
		// CORREO CREACION SOLICITUD
		try {//********-------
			if(to != null) {
			EnviaMailDSRH envia = new EnviaMailDSRH();
			// AREA USUARIA
			envia.setFrom(UtilMail.MAIL_REMITENTE);
			envia.setTo(to);//CAMBIAR A TO
			envia.setSubject(UtilMail.SUBJECT + " REVISADA");
			String nombre = persona.getNombre() + " " + persona.getApellidos().getApellidoPaterno() + " "
					+ persona.getApellidos().getApellidoMaterno();
			envia.setContent(UtilMail.solicitudRevisada(solicitud, nombre));
			//envia.sendMail();
			}
		} catch (Exception e) {
			System.err.println("MAIL" + e.getMessage());
			e.printStackTrace();
		}

		modelo.setViewName(PAGINA_CONSULTA_SOLICITUD);

		return modelo;
	}

	/// RECHAZO
	@RequestMapping(value = { "/undecorated/rechazarSolicitud.ajax" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ModelAndView rechazarSolicitud(@RequestParam(defaultValue = "0", required = false) Integer claveSolicitud,
			String motivo, HttpServletRequest request) {
		
		System.out.println("motivo" + motivo);
		System.out.println("claveSolicitud" + claveSolicitud);
		List<Persona> lpersonas;
		PersonaBusqueda soliForm = new PersonaBusqueda();
		Map<String, PersonaBusqueda> contactMap = new TreeMap<String, PersonaBusqueda>();

		ModelAndView modelo = new ModelAndView();

		HttpSession session = request.getSession();
		Persona usuario = (Persona) session.getAttribute(USUARIO_SISTEMA);
		Historico histori = new Historico();

		Persona persona = personaService.findById(usuario.getClave());

		/****/
		int nivel = 0;
		String revisor = "";
		EstadoSolicitud estado = new EstadoSolicitud();

		HistoricoBusqueda buscar = new HistoricoBusqueda();
		buscar.setClave(claveSolicitud);
		List<Historico> historial = historicoService.search(buscar);
		for (Historico hist : historial) {
			if (hist.getEdoSolicitud().getClave() == 2) {
				revisor = hist.getPersona().getNombre() + " " + hist.getPersona().getApellidos().getApellidoPaterno()
						+ " " + hist.getPersona().getApellidos().getApellidoMaterno();
			}
		}
//			if(usuario.getPuesto().getNombre().startsWith("JEFE") ){
//				usuario = personaService.findById(usuario.getNumeroJefe());
//		  	  	estado=estadoService.findById(10);
//				histori.setComentario(estado.getNombre());
//				nivel  = 1;
//		  	}else 
		if (usuario.getPuesto().getNombre().startsWith("GERE")) {
			estado = estadoService.findById(10);
			nivel = 1;
		} else if (usuario.getPuesto().getNombre().startsWith("SUBDIR")) {
			estado = estadoService.findById(14);
			nivel = 2;
		} else if (usuario.getPuesto().getNombre().startsWith("DIR")) {
			estado = estadoService.findById(17);
			nivel = 3;
		}

		histori.setComentario(estado.getNombre());
		solicitudService.updateEdoSolicitud(claveSolicitud, estado.getClave());
		Solicitud solicitud = solicitudService.findById(claveSolicitud);
		System.out.println("claveSolicitud" + claveSolicitud);
		/****/
		histori.setHistoricos(solicitud);
		histori.setPersona(persona);
		histori.setFecha_registro(new Date());
		histori.setEdoSolicitud(estado);
		histori.setMotivo(motivo);

//		modelo.addObject("creatorSolicitud", solicitud.getPersona().getClave());
//		System.err.println("creatorSolicitud="+solicitud.getPersona().getClave());
//		System.out.println("creatorSolicitud="+solicitud.getPersona().getClave());
		lpersonas = personaService.getDatosParticipantes(solicitud.getClave(), 4);

		Object[] fila;
		int pos = 0;
		for (Object item : lpersonas) {
			pos++;
			fila = (Object[]) item;
			Persona per = new Persona();
			per = (Persona) fila[0];
			contactMap.put(String.valueOf(pos),
					new PersonaBusqueda(per.getClave(),
							per.getNombre().concat("")
									.concat(per.getApellidos().getApellidoPaterno().concat(" ")
											.concat(per.getApellidos().getApellidoMaterno())),
							per.getPuesto().getNombre(), per.getCostos().getNombre()));
			soliForm.setEmpleadoMap(contactMap);
		}
		modelo.addObject("empMap", soliForm);
		modelo.addObject("solicitud", solicitud);
		modelo.addObject("creatorSolicitud", solicitud.getPersona().getClave());
		
		historicoService.register(histori);

		System.out.println("creatorSolicitud"+ solicitud.getPersona().getClave());
		CorreoService correoService = new CorreoServiceImpl();
		String to = correoService.getMail(solicitud.getPersona().getClave());
		System.out.println("TO " + to);
		
		
		// CORREO RECHAZO
		try {
			EnviaMailDSRH envia = new EnviaMailDSRH();
			// AREA USUARIA
			envia.setFrom(UtilMail.MAIL_REMITENTE);
			envia.setTo(to + "," + UtilMail.MAIL_CAPACITACION);//CORREGIR CORREOS DESTINATARIOS
			envia.setSubject(UtilMail.SUBJECT + " RECHAZADA");
			String autoriza = persona.getNombre() + " " + persona.getApellidos().getApellidoPaterno() + " "
					+ persona.getApellidos().getApellidoMaterno();
			if (nivel == 1) {
				envia.setContent(UtilMail.primeraAutorizacionRechazo(solicitud, revisor, autoriza));
			} else if (nivel == 2) {
				envia.setContent(UtilMail.segundaAutorizacionRechazo(solicitud, revisor, autoriza));
			} else if (nivel == 3) {
				envia.setContent(UtilMail.terceraAutorizacionRechazo(solicitud, revisor, autoriza));
			}
			//envia.sendMail();
		} catch (Exception e) {
			System.err.println("MAIL" + e.getMessage());
			e.printStackTrace();
		}

		modelo.setViewName(PAGINA_CONSULTA_SOLICITUD);

		return modelo;
	}

	@RequestMapping(value = { "/undecorated/autorizarSolicitud.ajax" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ModelAndView autorizarSolicitud(@RequestParam(defaultValue = "0", required = false) Integer claveSolicitud,
			HttpServletRequest request) {
//        Integer intEdo=0,intEstatusSolicitu=0;
		ModelAndView modelo = new ModelAndView();
//  	    String strMensaje="";
		HttpSession session = request.getSession();
		Persona persona = (Persona) session.getAttribute(USUARIO_SISTEMA);
		EstadoSolicitud estado = null;
		Historico histori = new Historico();
		List<Moneda> tipos = monedaservice.findAll();
		List<Persona> lpersonas;
		Map<String, PersonaBusqueda> contactMap = new TreeMap<String, PersonaBusqueda>();
		PersonaBusqueda soliForm = new PersonaBusqueda();

		System.out.println("jefe es:" + persona.getClave());

		Persona personaJefe = personaService.findById(persona.getClave());

//	  	Solicitud solicitud=solicitudService.findById(claveSolicitud);

		System.out.println("puesot:" + personaJefe.getPuesto());

		int nivel = 0;
		String revisor = "";
		HistoricoBusqueda buscar = new HistoricoBusqueda();
		buscar.setClave(claveSolicitud);
		List<Historico> historial = historicoService.search(buscar);
		for (Historico hist : historial) {
			if (hist.getEdoSolicitud().getClave() == 2) {
				revisor = hist.getPersona().getNombre() + " " + hist.getPersona().getApellidos().getApellidoPaterno()
						+ " " + hist.getPersona().getApellidos().getApellidoMaterno();
			}
		}

		if (personaJefe.getPuesto().getNombre().startsWith("GERE")) {
			estado = estadoService.findById(9);
//	  		strMensaje="Autorización Realizada por Gerente";
//	  		intEdo=9;
//	  		histori.setComentario(strMensaje);
			nivel = 1;
		} else if (personaJefe.getPuesto().getNombre().startsWith("SUBDIR")) {
			estado = estadoService.findById(12);
//	  		strMensaje="Autorización Realizada por Subdirector";
//	  		histori.setComentario(strMensaje);
//	  		intEdo=12;
			nivel = 2;
		} else if (personaJefe.getPuesto().getNombre().startsWith("DIR")) {
			estado = estadoService.findById(16);
//	  		strMensaje="Autorización Realizada por Director";
//	  		histori.setComentario(strMensaje);
//	  		intEdo=16;
			nivel = 3;
		}

//	  	intEdo=solicitudService.updateEdoSolicitud(claveSolicitud,intEdo);
		solicitudService.updateEdoSolicitud(claveSolicitud, estado.getClave());
		Solicitud solicitud = solicitudService.findById(claveSolicitud);
		modelo.addObject("tipos", tipos);
		histori.setHistoricos(solicitud);
		histori.setPersona(personaJefe);
		histori.setComentario(estado.getNombre());
		histori.setFecha_registro(new Date());
		histori.setEdoSolicitud(estado);

		lpersonas = personaService.getDatosParticipantes(solicitud.getClave(), 4);

		Object[] fila;
		int pos = 0;
		for (Object item : lpersonas) {
			pos++;
			fila = (Object[]) item;
			Persona per = new Persona();
			per = (Persona) fila[0];
			contactMap.put(String.valueOf(pos),
					new PersonaBusqueda(per.getClave(),
							per.getNombre().concat("")
									.concat(per.getApellidos().getApellidoPaterno().concat(" ")
											.concat(per.getApellidos().getApellidoMaterno())),
							per.getPuesto().getNombre(), per.getCostos().getNombre()));
			soliForm.setEmpleadoMap(contactMap);
		}
		modelo.addObject("empMap", soliForm);
		modelo.addObject("solicitud", solicitud);
		modelo.addObject("creatorSolicitud", solicitud.getPersona().getClave());

		historicoService.register(histori);

		CorreoService correoService = new CorreoServiceImpl();
		String to = correoService.getMail(solicitud.getPersona().getClave());
		System.out.println("TO " + to);
		// CORREO AUTORIZACIONES
		try {

			EnviaMailDSRH envia = new EnviaMailDSRH();
			// AREA USUARIA
			envia.setFrom(UtilMail.MAIL_REMITENTE);
			envia.setTo(to);
			envia.setSubject(UtilMail.SUBJECT + " " + nivel + "ra. AUTORIZACIÓN");
			String autoriza = personaJefe.getNombre() + " " + personaJefe.getApellidos().getApellidoPaterno() + " "
					+ personaJefe.getApellidos().getApellidoMaterno();
			if (nivel == 1) {
				envia.setContent(UtilMail.primeraAutorizacionAutorizada(solicitud, revisor, autoriza));
			} else if (nivel == 2) {
				envia.setContent(UtilMail.segundaAutorizacionAutorizada(solicitud, revisor, autoriza));
			} else if (nivel == 3) {
				envia.setContent(UtilMail.terceraAutorizacionAutorizada(solicitud, revisor, autoriza));
			}
			// envia.setFormat(1);
			//envia.sendMail();

		} catch (Exception e) {
			System.err.println("MAIL" + e.getMessage());
			e.printStackTrace();
		}

		modelo.setViewName(PAGINA_CONSULTA_SOLICITUD);

		return modelo;
	}

	// ***************
	/// RECHAZO POR EL AREA DE CAPACITACION
	@RequestMapping(value = { "/undecorated/rechazarAutoSolicitud.ajax" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ModelAndView rechazarAutoSolicitud(
			@RequestParam(defaultValue = "0", required = false) Integer claveSolicitud, String motivo,
			HttpServletRequest request) {
		
		System.out.println("======motivo:" + motivo);
		System.out.println("======motivo:" + motivo);
		List<Persona> lpersonas;
		PersonaBusqueda soliForm = new PersonaBusqueda();
		Map<String, PersonaBusqueda> contactMap = new TreeMap<String, PersonaBusqueda>();
		Integer intEdo = 0;
		ModelAndView modelo = new ModelAndView();
		intEdo = solicitudService.updateEdoSolicitud(claveSolicitud, 3);

		HttpSession session = request.getSession();
		Persona usuario = (Persona) session.getAttribute(USUARIO_SISTEMA);

		Historico histori = new Historico();

		Persona persona = personaService.findById(usuario.getClave());

		Solicitud solicitud = solicitudService.findById(claveSolicitud);

		EstadoSolicitud estado = estadoService.findById(3);

		histori.setHistoricos(solicitud);
		histori.setPersona(persona);
//	    histori.setComentario("Validación Rechazada por el área de Capacitación");
		histori.setComentario(estado.getNombre());
		histori.setFecha_registro(new Date());
		histori.setEdoSolicitud(estado);
		histori.setMotivo(motivo);

		lpersonas = personaService.getDatosParticipantes(solicitud.getClave(), 4);

		Object[] fila;
		int pos = 0;
		for (Object item : lpersonas) {
			pos++;
			fila = (Object[]) item;
			Persona per = new Persona();
			per = (Persona) fila[0];
			contactMap.put(String.valueOf(pos),
					new PersonaBusqueda(per.getClave(),
							per.getNombre().concat("")
									.concat(per.getApellidos().getApellidoPaterno().concat(" ")
											.concat(per.getApellidos().getApellidoMaterno())),
							per.getPuesto().getNombre(), per.getCostos().getNombre()));
			soliForm.setEmpleadoMap(contactMap);
		}
		modelo.addObject("empMap", soliForm);
		modelo.addObject("solicitud", solicitud);
		modelo.addObject("creatorSolicitud", solicitud.getPersona().getClave());
		historicoService.register(histori);

		
		CorreoService correoService = new CorreoServiceImpl();
		String to = correoService.getMail(solicitud.getPersona().getClave());
		System.out.println("TO " + to);
	
		// CORREO rechazo
		try {
			EnviaMailDSRH envia = new EnviaMailDSRH();
			// AREA USUARIA
			envia.setFrom(UtilMail.MAIL_REMITENTE);
			envia.setTo(to);//TO
			envia.setSubject(UtilMail.SUBJECT + " RECHAZADA");
			String nombre = persona.getNombre() + " " + persona.getApellidos().getApellidoPaterno() + " "
					+ persona.getApellidos().getApellidoMaterno();
			envia.setContent(UtilMail.solicitudRechazada(solicitud, nombre));
			// envia.setFormat(1);
			//envia.sendMail();
		} catch (Exception e) {
			System.err.println("MAIL" + e.getMessage());
			e.printStackTrace();
		}

		modelo.setViewName(PAGINA_CONSULTA_SOLICITUD);

		return modelo;
	}

	@RequestMapping(value = { "/undecorated/buscarAdscriptor.ajax" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ModelAndView buscarAdscriptor(@RequestParam(defaultValue = "0", required = false) Integer claveSolicitud,
			@RequestParam(defaultValue = "0", required = false) Integer creatorSolicitud, HttpServletRequest request,
			HttpSession session) {
		System.out.println("ingresando");
		System.out.println("claveSolicitud" + claveSolicitud);
		System.out.println("creatorSolicitud:" + creatorSolicitud);

		Persona usuario = (Persona) session.getAttribute(USUARIO_SISTEMA);
		ModelAndView modelo = new ModelAndView();
		Historico histori = new Historico();
		EstadoSolicitud estado = null;
		// List<Moneda> tipos = monedaservice.findAll();
		// reciba el numero de empleado de quien creo la solciitud
		Persona creadorSolicitud = personaService.findById(creatorSolicitud);
		// reciba el numero de empleado Jefe de quien creo la solciitud
		Persona personaJefe = personaService.findById(creadorSolicitud.getNumeroJefe());

		Persona capacitacion = personaService.findById(usuario.getClave());
		String revisor = capacitacion.getNombre() + " " + capacitacion.getApellidos().getApellidoPaterno() + " "
				+ capacitacion.getApellidos().getApellidoMaterno();

		Solicitud solicitud = solicitudService.findById(claveSolicitud);

		System.out.println("personaJefe.getPuesto().getNombre():" + personaJefe.getPuesto().getNombre());

		// CORREO AUTORIZACIONES PENDIENTES
		int nivel = 0;

		if (personaJefe.getPuesto().getNombre().startsWith("JEFE")) {
			personaJefe = personaService.findById(personaJefe.getNumeroJefe());
			estado = estadoService.findById(8);
			histori.setComentario(estado.getNombre());
			nivel = 1;
		} else
			if (personaJefe.getPuesto().getNombre().startsWith("GERE")) {
			estado = estadoService.findById(8);
//  	  		histori.setComentario("Autorización Pendiente por Gerente");
			histori.setComentario(estado.getNombre());
			nivel = 1;
		} else if (personaJefe.getPuesto().getNombre().startsWith("SUBDIR")) {
			estado = estadoService.findById(11);
//  	  		histori.setComentario("Autorización Pendiente por Subdirector");
			histori.setComentario(estado.getNombre());
			nivel = 2;
		} else if (personaJefe.getPuesto().getNombre().startsWith("DIR")) {
			estado = estadoService.findById(15);
//  	  		histori.setComentario("Autorización Pendiente por Director");
			histori.setComentario(estado.getNombre());
			nivel = 3;
		}
		
		CorreoService correoService = new CorreoServiceImpl();
		String to = correoService.getMail(personaJefe.getClave());
		System.out.println("TO " + to);
		// obtener su puesto y en base a eso se definira el coemnatio y id de la
		// autorizacion
		histori.setHistoricos(solicitud);
		histori.setPersona(personaJefe);
		histori.setFecha_registro(new Date());
		histori.setEdoSolicitud(estado);
		historicoService.register(histori);

		List<Persona> lpersonas;
		PersonaBusqueda soliForm = new PersonaBusqueda();
		Map<String, PersonaBusqueda> contactMap = new TreeMap<String, PersonaBusqueda>();

		// Participantes participantes=participanteService.findById(144);
		lpersonas = personaService.getDatosParticipantes(solicitud.getClave(), 4);

		Object[] fila;
		int pos = 0;
		for (Object item : lpersonas) {
			pos++;
			fila = (Object[]) item;
			Persona per = new Persona();
			per = (Persona) fila[0];
			contactMap.put(String.valueOf(pos),
					new PersonaBusqueda(per.getClave(),
							per.getNombre().concat("")
									.concat(per.getApellidos().getApellidoPaterno().concat(" ")
											.concat(per.getApellidos().getApellidoMaterno())),
							per.getPuesto().getNombre(), per.getCostos().getNombre()));
			soliForm.setEmpleadoMap(contactMap);
		}

		HistoricoBusqueda buscar = new HistoricoBusqueda();
		buscar.setClave(claveSolicitud);
		List<Historico> historial = historicoService.search(buscar);
		for (Historico hist : historial) {
			if (hist.getEdoSolicitud().getClave() == 2) {
				revisor = hist.getPersona().getNombre() + " " + hist.getPersona().getApellidos().getApellidoPaterno()
						+ " " + hist.getPersona().getApellidos().getApellidoMaterno();
			}
		}
		
		try {
			EnviaMailDSRH envia = new EnviaMailDSRH();
			// AREA USUARIA
			envia.setFrom(UtilMail.MAIL_REMITENTE);
			envia.setTo(to); //CAMBIAR A TO
			envia.setSubject(UtilMail.SUBJECT + " PENDIENTE POR AUTORIZAR");
//			String revisor = "";
//			String gerente = "";
//			String director = "";
//			Persona usuario =(Persona)session.getAttribute(USUARIO_SISTEMA);
//			Persona persona = personaService.findById(usuario.getClave());
			if (nivel == 1) {
//				revisor = persona.getNombre() + " " + persona.getApellidos().getApellidoPaterno() + " " + persona.getApellidos().getApellidoMaterno();
				envia.setContent(UtilMail.primeraAutorizacion(solicitud, revisor));
			} else if (nivel == 2) {
//				HistoricoBusqueda buscar = new HistoricoBusqueda();
//		  		buscar.setClave(claveSolicitud);
//		  		List<Historico> historial = historicoService.search(buscar);
//				for(Historico hist : historial) {
//					if(hist.getEdoSolicitud().getClave() == 2) {
//						revisor=hist.getPersona().getNombre() + " " + hist.getPersona().getApellidos().getApellidoPaterno() + " " + hist.getPersona().getApellidos().getApellidoMaterno();
//					}
//				}
//				gerente = persona.getNombre() + " " + persona.getApellidos().getApellidoPaterno() + " " + persona.getApellidos().getApellidoMaterno();
				envia.setContent(UtilMail.segundaAutorizacion(solicitud, revisor));
			} else if (nivel == 3) {
//				HistoricoBusqueda buscar = new HistoricoBusqueda();
//		  		buscar.setClave(claveSolicitud);
//		  		List<Historico> historial = historicoService.search(buscar);
//				for(Historico hist : historial) {
//					if(hist.getEdoSolicitud().getClave() == 2) {
//						revisor=hist.getPersona().getNombre() + " " + hist.getPersona().getApellidos().getApellidoPaterno() + " " + hist.getPersona().getApellidos().getApellidoMaterno();
//					}
//				}
				envia.setContent(UtilMail.terceraAutorizacion(solicitud, revisor));
			}
			//envia.sendMail();
		} catch (Exception e) {
			System.err.println("MAIL" + e.getMessage());
			e.printStackTrace();
		}
		// modelo.addObject("tipos", tipos);
		modelo.addObject("empMap", soliForm);
		modelo.addObject("solicitud", solicitud);
		modelo.setViewName(PAGINA_CONSULTA_SOLICITUD);

		return modelo;

	}

	@RequestMapping(value = { "/undecorated/buscarAdscriptorSub.ajax" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ModelAndView buscarAdscriptorSub(@RequestParam(defaultValue = "0", required = false) Integer claveSolicitud,
			HttpServletRequest request) {
		String strMessage = "";
		Integer intEdo = 0;
		ModelAndView modelo = new ModelAndView();
		HttpSession session = request.getSession();
		Persona persona = (Persona) session.getAttribute(USUARIO_SISTEMA);
		Historico histori = new Historico();
		EstadoSolicitud estado = null;
		List<Moneda> tipos = monedaservice.findAll();

		System.out.println("persona.getClave():" + persona.getClave());
		// reciba el numero de empleado de quien creo la solciitud
		Persona autorizadorSolicitud = personaService.findById(persona.getClave());// 2667
		// reciba el numero de empleado Jefe de quien creo la solciitud
		Persona personaJefe = personaService.findById(autorizadorSolicitud.getNumeroJefe());

		Solicitud solicitud = solicitudService.findById(claveSolicitud);

		// CORREO AUTORIZACIONES PENDIENTES
		int nivel = 0;
		if (personaJefe.getPuesto().getNombre().startsWith("JEFE")) {
			personaJefe = personaService.findById(personaJefe.getNumeroJefe());
			estado = estadoService.findById(8);
			histori.setComentario(estado.getNombre());
			nivel = 1;
		} else if (personaJefe.getPuesto().getNombre().startsWith("GERE")) {
			estado = estadoService.findById(8);
//  	  		histori.setComentario("Autorización Pendiente por Gerente");
			histori.setComentario(estado.getNombre());
			nivel = 1;
		} else if (personaJefe.getPuesto().getNombre().startsWith("SUBDIR")) {
			estado = estadoService.findById(11);
//  	  		histori.setComentario("Autorización Pendiente por Subdirector");
			histori.setComentario(estado.getNombre());
			nivel = 2;
		} else if (personaJefe.getPuesto().getNombre().startsWith("DIR")) {
			estado = estadoService.findById(15);
//  	  		histori.setComentario("Autorización Pendiente por Director");
			histori.setComentario(estado.getNombre());
			nivel = 3;
		}

		// obtener su puesto y en base a eso se definira el coemnatio y id de la
		// autorizacion
		histori.setHistoricos(solicitud);
		histori.setPersona(personaJefe);// persona
		histori.setFecha_registro(new Date());
		histori.setEdoSolicitud(estado);
		historicoService.register(histori);

		List<Persona> lpersonas;
		PersonaBusqueda soliForm = new PersonaBusqueda();
		Map<String, PersonaBusqueda> contactMap = new TreeMap<String, PersonaBusqueda>();

		lpersonas = personaService.getDatosParticipantes(solicitud.getClave(), 4);

		Object[] fila;
		int pos = 0;
		for (Object item : lpersonas) {
			pos++;
			System.out.println("ingresna al objeto" + item);
			fila = (Object[]) item;
			Persona per = new Persona();
			per = (Persona) fila[0];
			contactMap.put(String.valueOf(pos),
					new PersonaBusqueda(per.getClave(),
							per.getNombre().concat("")
									.concat(per.getApellidos().getApellidoPaterno().concat(" ")
											.concat(per.getApellidos().getApellidoMaterno())),
							per.getPuesto().getNombre(), per.getCostos().getNombre()));
			soliForm.setEmpleadoMap(contactMap);
		}

		CorreoService correoService = new CorreoServiceImpl();
		String to = correoService.getMail(personaJefe.getClave());
		System.out.println("TO " + to);
		// CORREO
		try {
			solicitud.setEdoSolicitud(estado);
			EnviaMailDSRH envia = new EnviaMailDSRH();
			// AREA USUARIA
			envia.setFrom(UtilMail.MAIL_REMITENTE);
			envia.setTo(to);//CAMBIAR A TO
			envia.setSubject(UtilMail.SUBJECT + " PENDIENTE POR AUTORIZAR");

			String autoriza = autorizadorSolicitud.getNombre() + " "
					+ autorizadorSolicitud.getApellidos().getApellidoPaterno() + " "
					+ autorizadorSolicitud.getApellidos().getApellidoMaterno();
			if (nivel == 1) {
				envia.setContent(UtilMail.primeraAutorizacion(solicitud, autoriza));/// *******
			} else if (nivel == 2) {
//				envia.setContent(UtilMail.segundaAutorizacion(solicitud, autoriza));////////ERROR
			} else if (nivel == 3) {
				envia.setContent(UtilMail.terceraAutorizacion(solicitud, autoriza));
			}
			//envia.sendMail();
		} catch (Exception e) {
			System.err.println("MAIL" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("tipo son_tipos" + tipos);
		System.out.println("tipo son_tipos" + soliForm);
		modelo.addObject("tipos", tipos);
		modelo.addObject("empMap", soliForm);
		modelo.addObject("solicitud", solicitud);
		modelo.setViewName(PAGINA_CONSULTA_SOLICITUD);

		return modelo;

	}

	private int isExisting(String clave, List<Persona> lpersonas) {
		int exis = 0;
		System.out.println("clase" + clave);

		for (int i = 0; i < lpersonas.size(); i++) {
			System.out.println("Lista Actual de empleados" + lpersonas.get(i).getClave());
			if (lpersonas.get(i).getClave().equals(clave)) {
				exis = i;
			} else {
				exis = -1;
			}
		}
		return exis;

	}

	private int isExistingAdicional(Integer clave, List<Items> lpersonas) {
		int exis = -1;

		for (int i = 0; i < lpersonas.size(); i++) {
			int encontrado = 0;

			if (String.valueOf(lpersonas.get(i).getPersona().getClave()).equals(String.valueOf(clave))) {
				exis = i;
				return exis;
			} else {
				exis = -1;
			}
		}
		return exis;

	}

	private int exists(Integer clave, List<Items> lpersonas) {
		for (int i = 0; i < lpersonas.size(); i++) {
			if (String.valueOf(lpersonas.get(i).getPersona().getClave()).equalsIgnoreCase(String.valueOf(clave))) {
				return i;
			}
		}
		return -1;
	}

	@RequestMapping(value = "/undecorated/loadRechazo.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView loadRechazo(@RequestParam(defaultValue = "0", required = false) Integer claveSolicitud) {
		ModelAndView modelo = new ModelAndView();
		System.out.println("calve:" + claveSolicitud);
		Solicitud solicitud = solicitudService.findById(claveSolicitud);
		System.out.println("soliciud histoi:" + solicitud.getHistoricos());
		Historico historico = new Historico();
		List<Historico> lhistorico = new ArrayList<Historico>();
		System.out.println("histor:" + historico);
		Iterator it = solicitud.getHistoricos().iterator();
		while (it.equals(1)) {
			historico = (Historico) it.next();
			lhistorico.add(historico);
		}

		modelo.addObject("historico", lhistorico);

		modelo.addObject("solicitud", solicitud);
		modelo.setViewName(PAGINA_ADD_MOTIVO_RECHAZO);

		return modelo;
	}

}
