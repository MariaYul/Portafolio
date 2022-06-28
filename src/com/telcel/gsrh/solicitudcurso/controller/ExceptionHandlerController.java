package com.telcel.gsrh.solicitudcurso.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final String PAGINA_ERROR = "error";
    private static final String PAGINA_REDIRECT_RS_AREAS = "redirect:/area/main.htm";
    private static final String PAGINA_REDIRECT_DETALLE_PERSONA = "redirect:/persona/detalle/${clavePersona}.htm";
    private static final String PAGINA_REDIRECT_RS_TIPOS_DOCUMENTO = "redirect:/tipodocumento/main.htm";
    private static final String PAGINA_REDIRECT_RS_USUARIOS = "redirect:/usuario/main.htm";
    private static final String PAGINA_REDIRECT_RS_SOLICITUD = "redirect:/solicitud/main.htm";
    private static final String PAGINA_REDIRECT_RS_DOCUMENTO = "redirect:/persona/main.htm";
    
    private static final String[] AREA_PATRON = {"/area/add.htm", "/area/edit.htm"};
    private static final String[] TIPO_DOCUMENTO_PATRON = {"/tipodocumento/add.htm", "/tipodocumento/edit.htm"};
    private static final String[] USUARIO_PATRON = {"/usuario/add.htm", "/usuario/edit.htm"};
    private static final String[] SOLICITUD_PATRON = {"/solicitud/add.htm", "/solicitud/edit.htm"};
    private static final String[] CARGA_DOCUMENTO_PATRON = {"/documento/add.htm", "/documento/edit.htm"};
    private static final String[] CARGA_DOCUMENTO_DELETE_PATRON = {"/documento/delete.htm"};
    private static final String[] DESCARGA_DOCUMENTO_PATRON = {"/documento/download.htm"};
    private static final String[] EXPEDIENTE_PATRON = {"/expedienteflipbook.htm"};
    
    private static final String MSG_ERROR_AREA = "Ocurri&oacute; un error al registrar / actualizar el &aacute;rea.<br/>";
    private static final String MSG_ERROR_TIPO_DOCUMENTO = "Ocurri&oacute; un error al registrar / actualizar el tipo de documento.<br/>";
    private static final String MSG_ERROR_USUARIO = "Ocurri&oacute; un error al registrar / actualizar el usuario.<br/>";
    private static final String MSG_ERROR_SOLICITUD = "Ocurri&oacute; un error al registrar / actualizar la solicitud.<br/>";
    private static final String MSG_ERROR_DOCUMENTO = "Ocurri&oacute; un error al cargar / actualizar documento.";
    private static final String MSG_ERROR_DOCUMENTO_MEDIDA = "No se pueden cargar archivos mayores a 10 MB.";
    private static final String MSG_ERROR_DOCUMENTO_DELETE = "Ocurri&oacute; un error al eliminar documento";
    private static final String MSG_ERROR_DOCUMENTO_DOWNLOAD = "Ocurri&oacute; un error al descargar documento";
    private static final String MSG_ERROR_EXPEDIENTE = "Ocurri&oacute; un error al generar expediente: %s";
    private static final String MSG_ERROR_GENERAL = "Ocurri&oacute; un error general, por favor contacte al administrador del sistema.<br/>";
    
    private static final Logger LOGGER = Logger.getLogger(ExceptionHandlerController.class);
    
    @ExceptionHandler({Exception.class})
    public ModelAndView handleSqlException(HttpServletRequest request, Exception ex) {
        LOGGER.error(ex.getMessage());
        
        return getModel(request, ex);
    }
    
    private ModelAndView getModel(HttpServletRequest request, Exception ex) {
        String url = request.getRequestURL().toString();
        ModelAndView modelo = new ModelAndView();
        StringBuilder mensajeError = new StringBuilder();
        
        if(isEntityResource(url, AREA_PATRON)) {
            mensajeError.append(MSG_ERROR_AREA);
            modelo.setViewName(PAGINA_REDIRECT_RS_AREAS);
        } else if(isEntityResource(url, TIPO_DOCUMENTO_PATRON)) {
            mensajeError.append(MSG_ERROR_TIPO_DOCUMENTO);
            modelo.setViewName(PAGINA_REDIRECT_RS_TIPOS_DOCUMENTO);
        } else if(isEntityResource(url, USUARIO_PATRON)) {
            mensajeError.append(MSG_ERROR_USUARIO);
            modelo.setViewName(PAGINA_REDIRECT_RS_USUARIOS);
        }else if(isEntityResource(url, SOLICITUD_PATRON)) {
            mensajeError.append(MSG_ERROR_SOLICITUD);
            modelo.setViewName(PAGINA_REDIRECT_RS_SOLICITUD);
        } else if(isEntityResource(url, CARGA_DOCUMENTO_PATRON)) {
            if(ex instanceof MaxUploadSizeExceededException) {
                mensajeError.append(MSG_ERROR_DOCUMENTO_MEDIDA);
                modelo.setViewName(PAGINA_REDIRECT_RS_DOCUMENTO);
            } else {
                mensajeError.append(MSG_ERROR_DOCUMENTO);
                modelo.setViewName(getUrlDetallePersona(request));
            }
        } else if(isEntityResource(url, CARGA_DOCUMENTO_DELETE_PATRON)) {
            mensajeError.append(MSG_ERROR_DOCUMENTO_DELETE);
            modelo.setViewName(getUrlDetallePersona(request));
        } else if(isEntityResource(url, DESCARGA_DOCUMENTO_PATRON)) {
            mensajeError.append(MSG_ERROR_DOCUMENTO_DOWNLOAD);
            modelo.setViewName(PAGINA_REDIRECT_RS_DOCUMENTO);
        } else if(isEntityResource(url, EXPEDIENTE_PATRON)) {
            mensajeError.append(String.format(MSG_ERROR_EXPEDIENTE, ex.getMessage()));
            modelo.setViewName(PAGINA_REDIRECT_RS_DOCUMENTO);
        } else {
            mensajeError.append(MSG_ERROR_GENERAL);
            modelo.setViewName(PAGINA_ERROR);
        }
        
        modelo.addObject("error", mensajeError.toString());
        
        return modelo; 
    }
    
    private boolean isEntityResource(String url, String[] patronEntidad) {
        boolean match = false;
        
        for(String patron : patronEntidad) {
            if(url.contains(patron)) {
                match = true;
                break;
            }
        }
        
        return match;
    }
    
    private String getUrlDetallePersona(HttpServletRequest request) {
        return PAGINA_REDIRECT_DETALLE_PERSONA.replace("${clavePersona}", getClavePersonaFromRequest(request));
    }
    
    private String getClavePersonaFromRequest(HttpServletRequest request) {
        String clavePersona = request.getParameter("persona.clave");
        
        return clavePersona == null ? "-1" : clavePersona;
    }
}
