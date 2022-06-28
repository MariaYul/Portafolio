package com.telcel.gsrh.solicitudcurso.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.telcel.dsrh.EnviaMail.mail.EnviaMailDSRH;
import com.telcel.gsrh.solicitudcurso.model.EstadoSolicitud;
import com.telcel.gsrh.solicitudcurso.model.Persona;
import com.telcel.gsrh.solicitudcurso.model.Solicitud;
import com.telcel.gsrh.solicitudcurso.repository.impl.RegionRepositoryImpl;
import com.telcel.gsrh.solicitudcurso.service.impl.SolicitudService;
import com.telcel.gsrh.solicitudcurso.service.impl.SolicitudServiceImpl;


public class app {

	 @Autowired
		private static SolicitudService solicitudService;
	 
	public static void main(String[] args)  {
//      System.out.println( "Hello World!" );
      try {
    	  try {
//    		  sendMailRegitro();
//    		  SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
//
////    		  String date = simpleDateFormat.format("26/07/2021 11:07:52");
//    		  Date date = simpleDateFormat.parse("26/07/2021 11:07:52");
//    		  System.out.println (simpleDateFormat.format(date));
//  			EnviaMailDSRH envia = new EnviaMailDSRH();
//  			envia.setFrom(UtilMail.MAIL_REMITENTE);
//  			envia.setTo(UtilMail.MAIL_PARA);
//  			envia.setSubject(UtilMail.SUBJECT + " ACEPTADA POR JEFE/GERENTE DE CAPACITACION");
//  			Solicitud solicitud = new Solicitud();
//  			solicitud.setFecha_creacion(date);
//  			solicitud.setClave(515);
//  			solicitud.setNombre("METODOLOGIAS AGILES");
//  			Persona persona = new Persona();
//  			persona.setNombre("JOSE ARMANDO");
//  			solicitud.setPersona(persona);
//  			solicitud.setContacto("Maricela Campos");
//  			EstadoSolicitud edo = new EstadoSolicitud();
//  			edo.setClave(18);
//  			edo.setNombre("Autorización Pendiente");
//  			solicitud.setEdoSolicitud(edo);
//  			envia.setContent(UtilMail.cuartaAutorizacion(solicitud));
//  			envia.sendMail();
    		 
    		  
    		  Long cveEmpleado = solicitudService.getUsusarioSolicitud(734);
    			System.out.println("CVEEMPLEADO = " + cveEmpleado);
  		} catch (Exception e) {
  			System.err.println("MAIL" + e.getMessage());
  			e.printStackTrace();
  		}
      }catch(Exception e) {
      	System.err.println(e.getMessage());
      }
	}
	
      
      public static void sendMailRegitro() {
    	//CORREO CREACION SOLICITUD
    	 
    	 
			
  		try {
  			 SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
  			  Date date = simpleDateFormat.parse("26/07/2021 11:07:52");
  			 Solicitud solicitud = new Solicitud();
 			solicitud.setFecha_creacion(date);
 			solicitud.setClave(515);
 			solicitud.setNombre("METODOLOGIAS AGILES");
 			Persona persona = new Persona();
 			persona.setNombre("JOSE ARMANDO");
 			solicitud.setPersona(persona);
 			solicitud.setContacto("Maricela Campos");
 			EstadoSolicitud edo = new EstadoSolicitud();
 			edo.setClave(18);
 			edo.setNombre("Autorización Pendiente");
 			solicitud.setEdoSolicitud(edo);
 			
  			EnviaMailDSRH envia = new EnviaMailDSRH();
  			//AREA USUARIA
  			envia.setFrom(UtilMail.MAIL_REMITENTE);
//  			envia.setTo(UtilMail.MAIL_PARA + "," + UtilMail.MAIL_PARA);
  			envia.setSubject("NUEVA " + UtilMail.SUBJECT);
  			envia.setContent("Le informamos que ha sido creada una Solicitud de curso el día <b>23/12/2021 18:14:37</b> se registró con el número de folio: <b>649</b>. Con la siguiente descripción:<br><br><b>Elaboró:</b> JOSE ARMANDO DURAN GARCIA<br><b>Nombre de curso: </b>INTRODUCCIÓN A LA METODOLOGÍA AGIL SCRUM<br><b>Proveedor: </b>DAVID PAZARAN ROMERO - ITEAM MÉXICO<br><b>Estatus:</b> PENDIENTE DE REVISION <br><br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: <a href=\"http://10.188.82.33:8080/solicituddecursos/index.htm\">http://intranet.telcel.com:8020/solicituddecursos/index.htm</a><br><br><br><b>Atentamente</b><br><br><span style=\"color:rgb(31,73,125)\"><b>Departamento De Capacitación Técnica E Informática</b><br><a href=\"mailto:\" capacitaciontecnica@telcel.com?=\"\">capacitaciontecnica@telcel.com</a></span>");//UtilMail.solicitudCreada(solicitud));
  			//envia.sendMail();
  			//CAPACITACION
//  			EnviaMailDSRH envio = new EnviaMailDSRH();
//  			envio.setFrom(UtilMail.MAIL_REMITENTE);
//  			envio.setTo(UtilMail.MAIL_PARA);
//  			envio.setSubject(UtilMail.SUBJECT + " CREADA PARA SU REVISIÓN");
//  			envio.setContent(UtilMail.solicitudCreada(solicitud));
//  			envio.sendMail();
  		} catch (Exception e) {
  			System.err.println("MAIL" + e.getMessage());
  			e.printStackTrace();
  		}
      }

	

}
