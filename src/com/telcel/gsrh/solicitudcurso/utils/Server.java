package com.telcel.gsrh.solicitudcurso.utils;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class Server {

    public static final String DIRECTORIO_DOCUMENTOS = "documents";
    public static final String DIRECTORIO_PDF = "pdf";
    public static final String DIRECTORIO_IMAGES = "images";
    public static final String SEPARADOR_ARCHIVOS = File.separator;
    private static final String SEPARADOR_NOMBRE_ARCHIVO_GENERADO = "_";
    
    public String getDeploymentFolderPath(HttpServletRequest request, String folder) {
        return request.getServletContext().getRealPath(folder);
    }
    
    public String getResourceInDocumentsFolderPath(HttpServletRequest request, String folder, String resource) {
        StringBuilder sbPathServer = new StringBuilder();
        
        sbPathServer.append(DIRECTORIO_DOCUMENTOS);
        sbPathServer.append(SEPARADOR_ARCHIVOS);
        sbPathServer.append(folder);
        sbPathServer.append(SEPARADOR_ARCHIVOS);
        sbPathServer.append(resource);
        return request.getServletContext().getRealPath(sbPathServer.toString());
    }
    
    public String getResourceInFolderPath(HttpServletRequest request, String folder, String resource) {
        StringBuilder sbPathServer = new StringBuilder();
        
        sbPathServer.append(folder);
        sbPathServer.append(SEPARADOR_ARCHIVOS);
        sbPathServer.append(resource);
        return request.getServletContext().getRealPath(sbPathServer.toString());
    }
    
    /**
     * Genera el nombre del archivo a generar en el servidor de aplicaciones
     * @param request
     * @param format Expresado en .pdf o .jpg
     * @return Nombre del archivo a generar en el servidor de aplicaciones
     */
    public String getArchivoGenerado(HttpServletRequest request, String format) {
        String horaActual = String.valueOf(System.currentTimeMillis());
        StringBuilder nbArchivoGenerado = new StringBuilder();
        nbArchivoGenerado.append(horaActual);
        nbArchivoGenerado.append(SEPARADOR_NOMBRE_ARCHIVO_GENERADO);
        nbArchivoGenerado.append(request.getSession().getId());
        nbArchivoGenerado.append(format);
        
        return nbArchivoGenerado.toString();
    }
}
