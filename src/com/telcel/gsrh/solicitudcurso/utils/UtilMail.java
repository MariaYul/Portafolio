package com.telcel.gsrh.solicitudcurso.utils;

import java.text.SimpleDateFormat;

import com.telcel.gsrh.solicitudcurso.model.Solicitud;

public class UtilMail {
	
	public static final String MAIL_CAPACITACION = "capacitaciontecnica@telcel.com";
//	public static final String MAIL_CAPACITACION ="guadalupe.mertinez@telcel.com";
	public static final String MAIL_REMITENTE = "capacitaciontecnica@mail.telcel.com";
//	public static final String MAIL_PARA ="guadalupe.mertinez@telcel.com";
//	public static final String MAIL_PARA ="erick.adache@telcel.com";
	public static final String URL = "http://intranet.telcel.com:8020/Solicitudes/index.htm";
	public static final String URL_TXT = "http://intranet.telcel.com:8020/Solicitudes/index.htm";
	public static final String SUBJECT = "SOLICITUD DE CURSO";
	public static SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static final String FIRMA = "<br><br><br>"
			+ "<b>Atentamente</b>"
			+ "<br><br>"
			+ "<span style='color:rgb(31,73,125)'><b>Departamento De Capacitaci&oacute;n T&eacute;cnica E Inform&aacute;tica</b>"
			+ "<br><a href='mailto:'"+MAIL_CAPACITACION+"'>capacitaciontecnica@telcel.com</a></span>";
	/**SOLICITUD CREADA AREA USUARIA Y CAPACITACION
	 * MENSAJE:
		Le informamos que ha sido creada una Solicitud de curso el día 01/22/2019 10:53:24 se registró con el número de folio: 5222953. Con la siguiente descripción: 
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus: Pendiente de revisión
		Para ver el detalle de la Solicitud haga clic en la siguiente liga:  http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp
	 * */
	public static String solicitudCreada(Solicitud solicitud) {
		return "Le informamos que ha sido creada una Solicitud de curso el d&iacute;a <b>" + formateador.format(solicitud.getFecha_creacion()) +
				"</b> se registr&oacute; con el n&uacute;mero de folio: <b>" + solicitud.getClave() + "</b>."+
				" Con la siguiente descripci&oacute;n:" + 
				"<br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno().toUpperCase() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre().toUpperCase() +
				"<br><b>Proveedor: </b>" + solicitud.getContacto().toUpperCase() +
				"<br><b>Estatus:</b> PENDIENTE DE REVISI&Oacute;N " + 
				"<br><br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
				"<a href='"+URL+"'>"+URL_TXT+"</a>"+FIRMA;
	}
	
	
	public static String solicitudActualizada(Solicitud solicitud) {
		return "Le informamos que ha sido actualizada una Solicitud de curso registrada el d&iacute;a <b>" + formateador.format(solicitud.getFecha_creacion()) +
				"</b> con el n&uacute;mero de folio: <b>" + solicitud.getClave() + "</b>."+
				" Con la siguiente descripci&oacute;n:" + 
				"<br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno().toUpperCase() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre().toUpperCase() +
				"<br><b>Proveedor: </b>" + solicitud.getContacto().toUpperCase() +
				"<br><b>Estatus:</b> " + solicitud.getEdoSolicitud().getNombre().toUpperCase() + 
				"<br><br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
				"<a href='"+URL+"'>"+URL_TXT+"</a>"+FIRMA;
	}
	
	/**SOLICITUD CREADA REVISADA
	 * MENSAJE:
		Le informamos que fue revisada la Solicitud de curso con el número de folio 5222953, del día 01/22/2019 10:53:24, Con la siguiente descripción: 
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus:
		Revisada por : Erick Adache
		
		La solicitud de curso iniciara el proceso de  autorizaciones correspondientes
		 Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp
		
		Nota: El departamento de Capacitación Técnica e Informática iniciara la logística del curso una vez que se cuente con la autorización de Gerente, Subdirector y Director según sea el caso.
	 * 
	 * */
	public static String solicitudRevisada(Solicitud solicitud, String usuario) {
		return "Le informamos que fue revisada la Solicitud de curso con el n&uacute;mero de folio <b>" + solicitud.getClave() + "</b>, del d&iacute;a <b>" + formateador.format(solicitud.getFecha_creacion()) +
				"</b>, Con la siguiente descripci&oacute;n:" + 
				"<br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno().toUpperCase() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre().toUpperCase() +
				"<br><b>Proveedor: </b>" + solicitud.getContacto().toUpperCase() +
				"<br><b>Estatus: </b>" + solicitud.getEdoSolicitud().getNombre().toUpperCase() +
				"<br><b>Revisada por:</b> " + usuario.toUpperCase()+ 
				"<br><br>La solicitud de curso iniciar&aacute; el proceso de  autorizaciones correspondientes."+
				"<br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
				"<a href='"+URL+"'>"+URL_TXT+"</a>"+
				"<br><br><br><b>Nota:</b> El departamento de Capacitaci&oacute;n T&eacute;cnica e Inform&aacute;tica iniciar&aacute; la log&iacute;stica del curso una vez que se cuente con la autorizaci&oacute;n de Gerente, Subdirector y Director seg&uacute;n sea el caso."+
				""+FIRMA;
	}
	
	
	/**
	 * SOLICITUD CREADA RECHAZADA
	 * MENSAJE:
		Le informamos que fue rechazada la Solicitud con el número de folio 5222953, del día 01/22/2019 10:53:24 Con la siguiente descripción: 
		
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus: Rechaza por Erick Adache
		
		Favor de revisar que los datos hallan sido capturados correctamente.
		
		Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp
	 * */
	public static String solicitudRechazada(Solicitud solicitud, String usuario) {
		return "Le informamos que fue rechazada la Solicitud con el n&uacute;mero de folio <b>" + solicitud.getClave() + "</b>, del d&iacute;a <b>" + formateador.format(solicitud.getFecha_creacion()) +
				"</b>, Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;: </b>" + solicitud.getPersona().getNombre().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno().toUpperCase() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre().toUpperCase() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto().toUpperCase() +
				"<br><b>Estatus: </b>" + solicitud.getEdoSolicitud().getNombre().toUpperCase() + 
				"<br><b>Rechazada por:</b> " + usuario.toUpperCase() + 
				"<br><br><br>Favor de revisar que los datos se encuentren capturados correctamente."+
				"<br><br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
				"<a href='"+URL+"'>"+URL_TXT+"</a>"+FIRMA;
	}
	
	
	/**PRIMERA AUTORIZACION GERENTE
	 * MENSAJE:
		Le informamos que usted cuenta con una Solicitud de Curso pendiente por autorizar con el número de folio 5222953, Con la siguiente descripción: 
		Elaboro: Nombre de curso:
		Proveedor:
		Reviso: Erick Adache
		Estatus: pendiente por autorizar
		Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp
	 * */
	public static String primeraAutorizacion(Solicitud solicitud, String revisor) {
		return "Le informamos que usted cuenta con una Solicitud de Curso pendiente por autorizar con el n&uacute;mero de folio <b>" + solicitud.getClave() +
				"</b>, Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno().toUpperCase() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre().toUpperCase() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto().toUpperCase() +
				"<br><b>Revis&oacute;:</b> " + revisor.toUpperCase() +
				"<br><b>Estatus:</b> PENDIENTE POR AUTORIZAR" +
				"<br><br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
				"<a href='"+URL+"'>"+URL_TXT+"</a>"+FIRMA;
	}
	
	
	/**PRIMERA AUTORIZACION RECHAZO
	 * MENSAJE:
		Le informamos que fue rechazada la Solicitud con el número de folio 5222953, del día 01/22/2019 10:53:24 Con la siguiente descripción: 
		
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus: Rechaza por Erick Adache
		
		Favor de revisar que los datos hallan sido capturados correctamente.
		
		Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp
	 * */
	public static String primeraAutorizacionRechazo(Solicitud solicitud, String revisor, String autoriza) {
		return "Le informamos que fue rechazada la Solicitud con el n&uacute;mero de folio <b>" + solicitud.getClave() + 
				"</b> del d&iacute;a <b>" + formateador.format(solicitud.getFecha_creacion()) + "</b> Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno().toUpperCase() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre().toUpperCase() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto().toUpperCase() +
				"<br><b>Estatus:</b> " + solicitud.getEdoSolicitud().getNombre().toUpperCase() + 
				"<br><b>Revis&oacute;:</b> " + revisor.toUpperCase() + 
//				"<br><b>Rechaz&oacute;: </b>Adscriptor (Gerente)" +
				"<br><b>Rechaz&oacute;: </b> GERENTE " + autoriza.toUpperCase() + 
				"<br><br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
//				"<a href='http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp'>http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp</a>";
				"<a href='"+URL+"'>"+URL_TXT+"</a>"+FIRMA;
	}
	
	
	/**PRIMERA AUTORIZACION AUTORIZADA
	 * MENSAJE:
		Le informamos que fue autorizada la Solicitud de Curso con el número de folio 5222953, del día 01/22/2019 10:53:24, Con la siguiente descripción: 
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus:
		Reviso: Erick Adache
		Autorizo 1: Adscriptor (Gerente) 
		
		Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp
		
		Nota: El departamento de Capacitación Técnica e Informática iniciara la logística del curso una vez que se cuente con la autorización de Gerente, Subdirector y Director según sea el caso.
	 * */
	public static String primeraAutorizacionAutorizada(Solicitud solicitud, String revisor, String autoriza) {
		return "Le informamos que fue autorizada la Solicitud de Curso con el n&uacute;mero de folio <b>" + solicitud.getClave() + 
				"</b> del d&iacute;a <b>" + formateador.format(solicitud.getFecha_creacion()) + "</b> Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno().toUpperCase() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre().toUpperCase() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto().toUpperCase() +
				"<br><b>Estatus:</b> " + solicitud.getEdoSolicitud().getNombre().toUpperCase() +
				"<br><b>Revis&oacute;:</b> " + revisor.toUpperCase() + 
				"<br><b>Autoriz&oacute; 1:</b> GERENTE " + autoriza.toUpperCase() +
				"<br><br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
				"<a href='"+URL+"'>"+URL_TXT+"</a>" +
				"<br><br><br><b>Nota:</b> El departamento de Capacitaci&oacute;n T&eacute;cnica e Inform&aacute;tica iniciar&aacute; la log&iacute;stica del curso una vez que se cuente con la autorizaci&oacute;n de Gerente, Subdirector y Director seg&uacute;n sea el caso."+
				FIRMA;
	}
	
	/**SEGUNDA AUTORIZACION SUBDIRECTOR
	 * Le informamos que usted cuenta con una Solicitud de Curso pendiente por autorizar con el número de folio 5222953, Con la siguiente descripción: 
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus
		Reviso: Erick Adache
		Autorizo 1: Adscriptor (Gerente) 
		Estatus: pendiente por autorizar
		
		Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp
	 * */
	public static String segundaAutorizacion(Solicitud solicitud, String revisor) {
		return "Le informamos que usted cuenta con una Solicitud de Curso pendiente por autorizar con el n&uacute;mero de folio <b>" + solicitud.getClave() +
				"</b>, Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno().toUpperCase() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre().toUpperCase() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto().toUpperCase() +
				"<br><b>Revis&oacute;:</b> " + revisor.toUpperCase() +
				"<br><b>Autoriz&oacute; 1:</b> GERENTE " + 
				"<br><b>Estatus:</b> PENDIENTE POR AUTORIZAR" +
				"<br><br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
//				"<a href='http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp'>http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp</a>";
"<a href='"+URL+"'>"+URL_TXT+"</a>"+FIRMA;
	}
	
	
	/**
	 * SEGUNDA AUTORIZACION SUBDIRECTOR RECHAZADA
	 * MENSAJE:
		Le informamos que fue rechazada la Solicitud de Curso con el número de folio 5222953, del día 01/22/2019 10:53:24 Con la siguiente descripción: 
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus:
		Reviso: Erick Adache
		Rechazo: Adscriptor (Gerente) 
		
		Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp
	 * */
	public static String segundaAutorizacionRechazo(Solicitud solicitud, String revisor, String autoriza) {
		return "Le informamos que fue rechazada la Solicitud de Curso con el n&uacute;mero de folio <b>" + solicitud.getClave() + 
				"</b> del d&iacute;a <b>" + formateador.format(solicitud.getFecha_creacion()) + "</b> Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno().toUpperCase() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre().toUpperCase() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto().toUpperCase() +
				"<br><b>Estatus:</b> " + solicitud.getEdoSolicitud().getNombre().toUpperCase() +
				"<br><b>Revis&oacute;:</b> " + revisor.toUpperCase() + 
				"<br><b>Autoriz&oacute; 1:</b> ADSCRIPTOR (GERENTE) " +
				"<br><b>Rechaz&oacute;:</b> SUBDIRECTOR " + autoriza.toUpperCase() +  
				"<br><br><br>Favor de revisar que los datos hallan sido capturados correctamente." +
				"<br><br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
//				"<a href='http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp'>http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp</a>";
				"<a href='"+URL+"'>"+URL_TXT+"</a>"+FIRMA;
	}
	
	
	/**
	 * SEGUNDA AUTORIZACION SUBDIRECTOR AUTORIZADA
	 * MENSAJE:
		Le informamos que fue autorizada la Solicitud de Curso con el número de folio 5222953, del día 01/22/2019 10:53:24, Con la siguiente descripción: 
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus:
		Reviso: Erick Adache
		Autorizo 1: Adscriptor (Gerente) 
		Autorizo 2: Subdirector
		
		Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp
		
		Nota: El departamento de Capacitación Técnica e Informática iniciara la logística del curso una vez que se cuente con la autorización de Gerente, Subdirector y Director según sea el caso.

	 * */
	public static String segundaAutorizacionAutorizada(Solicitud solicitud, String revisor, String autoriza) {
		return "Le informamos que fue autorizada la Solicitud de Curso con el n&uacute;mero de folio <b>" + solicitud.getClave() + 
				"</b> del d&iacute;a <b>" + formateador.format(solicitud.getFecha_creacion()) + "</b> Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre().toUpperCase()  + " " + solicitud.getPersona().getApellidos().getApellidoPaterno().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno().toUpperCase() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre().toUpperCase() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto().toUpperCase() +
				"<br><b>Estatus:</b> " + solicitud.getEdoSolicitud().getNombre().toUpperCase() +
				"<br><b>Revis&oacute;:</b> " + revisor.toUpperCase() +
				"<br><b>Autoriz&oacute; 1:</b> ADSCRIPTOR (GERENTE) " +
				"<br><b>Autoriz&oacute; 2:</b> SUBDIRECTOR " + autoriza.toUpperCase() + 
				"<br><br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
				"<a href='"+URL+"'>"+URL_TXT+"</a>" +
				"<br><br><br><b>Nota:</b> El departamento de Capacitaci&oacute;n T&eacute;cnica e Inform&aacute;tica iniciar&aacute; la log&iacute;stica del curso una vez que se cuente con la autorizaci&oacute;n de Gerente, Subdirector y Director seg&uacute;n sea el caso." +
				FIRMA;
	}
	
	
	/**TERCERA AUTORIZACION DIRECTOR
	 * MENSAJE:
		Le informamos que usted cuenta con una Solicitud de Curso pendiente por autorizar con el número de folio 5222953, Con la siguiente descripción:
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus
		Reviso: Erick Adache
		Autorizo 1: Adscriptor (Gerente) 
		Autorizo 2: Subdirector
		Estatus: pendiente por autorizar
		
		Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp

	 * */
	public static String terceraAutorizacion(Solicitud solicitud, String revisor) {
		return "Le informamos que usted cuenta con una Solicitud de Curso pendiente por autorizar con el n&uacute;mero de folio <b>" + solicitud.getClave() +
				"</b>, Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno().toUpperCase() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre().toUpperCase() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto().toUpperCase() +
				"<br><b>Estatus:</b> " +
				"<br><b>Revis&oacute;:</b> " + revisor.toUpperCase() +
				"<br><b>Autoriz&oacute; 1:</b> ADSCRIPTOR (GERENTE)" +
				"<br><b>Autoriz&oacute; 2:</b> SUBDIRECTOR"+
				"<br><b>Estatus:</b> PENDIENTE POR AUTORIZAR" +
				"<br><br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
				"<a href='"+URL+"'>"+URL_TXT+"</a><br>"+FIRMA;
	}
	
	
	/**
	 * TERCERA AUTORIZACION DIRECTOR RECHAZADA
	 * MENSAJE:
		Le informamos que fue rechazada la Solicitud de Curso con el número de folio 5222953, del día 01/22/2019 10:53:24, Con la siguiente descripción: 
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus:
		Reviso: Erick Adache
		Autorizo 1: Adscriptor (Gerente) 
		Autorizo 2: Subdirector
		Rechazada: Director
		
		Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp

	 * */
	public static String terceraAutorizacionRechazo(Solicitud solicitud, String revisor, String autoriza) {
		return "Le informamos que fue rechazada la Solicitud de Curso con el n&uacute;mero de folio <b>" + solicitud.getClave() + 
				"</b> del d&iacute;a <b>" + formateador.format(solicitud.getFecha_creacion()) + "</b> Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno().toUpperCase() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre().toUpperCase() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto().toUpperCase() +
				"<br><b>Estatus:</b> " + solicitud.getEdoSolicitud().getNombre().toUpperCase() +
				"<br><b>Revis&oacute;:</b> " + revisor.toUpperCase() +
				"<br><b>Autoriz&oacute; 1:</b> ADSCRIPTOR (GERENTE)" +
				"<br><b>Autoriz&oacute; 2:</b> SUBDIRECTOR" +
				"<br><b>Rechaz&oacute;:</b> DIRECTOR " + autoriza.toUpperCase() +
				"<br><br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
//				"<a href='http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp'>http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp</a>";
				"<a href='"+URL+"'>"+URL_TXT+"</a>"+FIRMA;
	}
	
	
	/**
	 * TERCERA AUTORIZACION DIRECTOR AUTORIZADA
	 * MENSAJE:
		Le informamos que fue autorizada la Solicitud de Curso con el número de folio 5222953, del día 01/22/2019 10:53:24, Con la siguiente descripción: 
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus:
		Reviso: Erick Adache
		Autorizo 1: Adscriptor (Gerente) 
		Autorizo 2: Subdirector
		Autorizo 3: Director
		
		Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp


	 * */
	public static String terceraAutorizacionAutorizada(Solicitud solicitud, String revisor, String autoriza) {
		return "Le informamos que fue autorizada la Solicitud de Curso con el n&uacute;mero de folio <b>" + solicitud.getClave() + 
				"</b> del d&iacute;a <b>" + formateador.format(solicitud.getFecha_creacion()) + "</b> Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno().toUpperCase() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno().toUpperCase() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre().toUpperCase() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto().toUpperCase() +
				"<br><b>Estatus:</b>" + solicitud.getEdoSolicitud().getNombre().toUpperCase() + 
				"<br><b>Revis&oacute;:</b> " + revisor.toUpperCase() +  
				"<br><b>Autoriz&oacute; 1:</b> ADSCRIPTOR (GERENTE) " +
				"<br><b>Autoriz&oacute; 2:</b> SUBDIRECTOR" +
				"<br><b>Autoriz&oacute; 3:</b> DIRECTOR " + autoriza.toUpperCase() + 
				"<br><br><br>Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
				"<a href='"+URL+"'>"+URL_TXT+"</a>"+ FIRMA;
//				"<br><br><br><b>Nota:</b> El departamento de Capacitaci&oacute;n T&eacute;cnica e Inform&aacute;tica iniciar&aacute; la log&iacute;stica del curso una vez que se cuente con la autorizaci&oacute;n de Gerente, Subdirector y Director seg&uacute;n sea el caso.";
	}
	
	
	/**CONCLUYE PROCESO
	 * MENSAJE:
		Le informamos que esta pendiente por autorizar la Solicitud de Curso con el número de folio 5222953, del día 01/22/2019 10:53:24,f Con la siguiente  descripción: 
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus:
		Reviso: Erick Adache
		Autorizo 1: Adscriptor (Gerente) 
		Autorizo 2: Subdirector
		Autorizo 3: Director
		Estatus: pendiente por autorizar
		
		 ha concluido el flujo de autorizaciones de  Gerente, Subdirector y Director, según sea el caso.
		
		Se requiere autorice la solicitud, para continuar con el flujo. Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp

	 * */
	public static String concluyeProceso(Solicitud solicitud) {
		return "Le informamos que esta pendiente por autorizar la Solicitud de Curso con el n&uacute;mero de folio <b>" + solicitud.getClave() +
				"</b> del d&iacute;a <b>" + formateador.format(solicitud.getFecha_creacion()) + "</b> Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto() +
				"<br><b>Estatus:</b>" +
				"<br><b>Revis&oacute;:</b> Erick Adache" + 
				"<br><b>Autoriz&oacute; 1:</b> Adscriptor (Gerente)" +
				"<br><b>Autoriz&oacute; 2:</b> Subdirector"+
				"<br><b>Autoriz&oacute; 3:</b> Director"+
				"<br><b>Estatus:</b> Pendiente por autorizar" +
				"<br><br><br>Ha concluido el flujo de autorizaciones de  Gerente, Subdirector y Director, seg&uacute;n sea el caso." +
				"<br><br><br>Se requiere autorice la solicitud, para continuar con el flujo. Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
				"<a href='http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp'>http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp</a>";
	}
	
	
	/**
	 * CAPACITACION ACEPTA SOLICITUD
	 * MENSAJE:
		Le informamos que fue recibida la Solicitud de Curso con el número de folio 5222953, del día 01/22/2019 10:53:24, Con la siguiente  descripción: 
		Elaboro: Nombre de curso:
		Proveedor:
		Reviso: Erick Adache
		Autorizo 1: Adscriptor (Gerente) 
		Autorizo 2: Subdirector
		Autorizo 3: Director
		Estatus: Recibida
		
		Ha concluido el flujo de autorizaciones de  Gerente, Subdirector y Director, según sea el caso.
		
		Se requiere autorice la solicitud, para continuar con el flujo. Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp

	 * */
	public static String capacitacionAcepta(Solicitud solicitud) {
		return "Le informamos que fue recibida la Solicitud de Curso con el n&uacute;mero de folio <b>" + solicitud.getClave() + 
				"</b> del d&iacute;a <b>" + formateador.format(solicitud.getFecha_creacion()) + "</b> Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto() +
				"<br><b>Revis&oacute;:</b> Erick Adache" +
				"<br><b>Autoriz&oacute; 1:</b> Adscriptor (Gerente)" +
				"<br><b>Autoriz&oacute; 2:</b> Subdirector" +
				"<br><b>Autoriz&oacute; 3:</b> Director" +
				"<br><b>Estatus:</b> Recibida" +
				"<br><br><br>Ha concluido el flujo de autorizaciones de  Gerente, Subdirector y Director, seg&uacute;n sea el caso." +
				"<br><br><br>Se requiere autorice la solicitud, para continuar con el flujo. Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
				"<a href='http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp'>http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp</a>";
	}
	
	
	/**
	 * PENDIENTE AUTORIZACION
	 * MENSAJE:

		Le informamos que usted cuenta con una Solicitud de Curso pendiente por autorizar con el número de folio 5222953, Con la siguiente descripción: 
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus:
		Reviso: Erick Adache
		Autorizo 1: Adscriptor (Gerente) 
		Autorizo 2: Subdirector
		Autorizo 3: Director
		Recibida: Erick Adache
		Estatus: pendiente por autorizar
		
		
		ha concluido el flujo de autorizaciones de  Gerente, Subdirector y Director, según sea el caso.
		
		Se requiere autorice la solicitud, para continuar con el flujo. Para ver el detalle de la Solicitud haga clic en la siguiente liga: http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp

	 * */
	public static String pendienteAutorizacion(Solicitud solicitud) {
		return "Le informamos que usted cuenta con una Solicitud de Curso pendiente por autorizar con el n&uacute;mero de folio <b>" + solicitud.getClave() + 
				"</b> Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto() +
				"<br><b>Estatus:</b>" +
				"<br><b>Revis&oacute;:</b> Erick Adache" + 
				"<br><b>Autoriz&oacute; 1:</b> Adscriptor (Gerente) " +
				"<br><b>Autoriz&oacute; 2:</b> Subdirector" +
				"<br><b>Autoriz&oacute; 3:</b> Director" +
				"<br><b>Recibida:</b> Erick Adache" +
				"<br><b>Estatus:</b> Pendiente por autorizar" +
				"<br><br><br>Ha concluido el flujo de autorizaciones de  Gerente, Subdirector y Director, seg&uacute;n sea el caso." +
				"<br><br><br>Se requiere autorice la solicitud, para continuar con el flujo. Para ver el detalle de la Solicitud haga clic en la siguiente liga: " + 
				"<a href='http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp'>http://intranet.telcel.com:8020/drh/capacitaTec/inicio.jsp</a>";
	}
	
	
	/**CUARTA AUTORIZACION
	 * Le informamos que se autorizo la Solicitud de Curso con el número de folio 5222953, del día 01/22/2019 10:53:24, Con la siguiente descripción: 
		Elaboro: Nombre de curso:
		Proveedor:
		Estatus:
		Reviso: Erick Adache
		Autorizo 1: Adscriptor (Gerente) 
		Autorizo 2: Subdirector
		Autorizo 3: Director
		Recibida: Erick Adache
		Autorizo: 4 Gerente o Jefe de capacitación técnica
		
		
		
		Solicitud autorizada exitosamente por  Gerente de Capacitación o Jefe de capacitación

	 * */
	public static String cuartaAutorizacion(Solicitud solicitud) {
		return "Le informamos que se autoriz&oacute; la Solicitud de Curso con el n&uacute;mero de folio <b>" + solicitud.getClave() + 
				"</b> del d&iacute;a <b>" + formateador.format(solicitud.getFecha_creacion()) + "</b>, Con la siguiente descripci&oacute;n:" + 
				"<br><br><br><b>Elabor&oacute;:</b> " + solicitud.getPersona().getNombre() + " " + solicitud.getPersona().getApellidos().getApellidoPaterno() + " " + solicitud.getPersona().getApellidos().getApellidoMaterno() +
				"<br><b>Nombre de curso: </b>" + solicitud.getNombre() +
				"<br><b>Proveedor:</b> " + solicitud.getContacto() +
				"<br><b>Estatus:</b>" +
				"<br><b>Revis&oacute;:</b> Erick Adache" + 
				"<br><b>Autoriz&oacute; 1:</b> Adscriptor (Gerente) " +
				"<br><b>Autoriz&oacute; 2:</b> Subdirector" +
				"<br><b>Autoriz&oacute; 3:</b> Director" +
				"<br><b>Recibida:</b> Erick Adache" +
				"<br><b>Autoriz&oacute; 4:</b> Gerente o Jefe de capacitaci&oacute;n t&eacute;cnica" +
				"<br><br><br>Solicitud autorizada exit&oacute;samente por Gerente de Capacitaci&oacute;n o Jefe de capacitaci&oacute;n";
	}
	
}
