package com.telcel.gsrh.solicitudcurso.listener;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.telcel.gsrh.solicitudcurso.utils.Server;

public class ArchivoSessionListener implements HttpSessionListener {
    
    private static final int MIL = 1000;
    private static final int SESENTA = 60;
    private static final Logger LOGGER = Logger.getLogger(ArchivoSessionListener.class);
    private static final String[] PROTECTED_FILES = {
        "Read_me.txt", "expediente.pdf", "acta.pdf"
        , "curp.pdf", "rfc.pdf", "cv.pdf"
    };
    
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        /**
         * No se codifica porque no se debe realizar acción personalizada alguna cuando se inicia una
         * nueva sesión
         */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        String sesionEliminada = sessionEvent.getSession().getId();
        ServletContext servletContext = sessionEvent.getSession().getServletContext();
        
        //Elimina archivos creados
        processSessionFiles(sesionEliminada, servletContext, Server.DIRECTORIO_PDF);
        
        //Elimina archivos 
        processSessionFiles(sesionEliminada, servletContext, Server.DIRECTORIO_IMAGES);
    }
    
    public void processSessionFiles(String sessionId, ServletContext servletContext, String folder) {
        StringBuilder sbPathRaiz = new StringBuilder();
        String pathRaiz = "";
        
        sbPathRaiz.append(Server.DIRECTORIO_DOCUMENTOS);
        sbPathRaiz.append(Server.SEPARADOR_ARCHIVOS);
        sbPathRaiz.append(folder);
        pathRaiz = servletContext.getRealPath(sbPathRaiz.toString());
        
        File dirRaiz = new File(pathRaiz);
        String[] lstArchivos = dirRaiz.list();
        
        if(lstArchivos != null) {
            for(String nbArchivo : lstArchivos) {
                depurateSessionFile(nbArchivo, sessionId, servletContext, folder);
            }
        }
    }
    
    private void depurateSessionFile(String nbArchivo, String sessionId, ServletContext servletContext, String folder) {
        StringBuilder sbPathArchivo = new StringBuilder();
        String pathArchivo = "";
        File archivoAux = null;
        
        if(nbArchivo.contains("_" + sessionId + ".")) {
            sbPathArchivo.append(Server.DIRECTORIO_DOCUMENTOS);
            sbPathArchivo.append(Server.SEPARADOR_ARCHIVOS);
            sbPathArchivo.append(folder);
            sbPathArchivo.append(Server.SEPARADOR_ARCHIVOS);
            sbPathArchivo.append(nbArchivo);
            
            pathArchivo = servletContext.getRealPath(sbPathArchivo.toString());
            archivoAux = new File(pathArchivo);
            archivoAux.setWritable(true);
            
            deleteFile(archivoAux);
        } else {
            if(!isProtectedFile(nbArchivo)) {
                sbPathArchivo.append(Server.DIRECTORIO_DOCUMENTOS);
                sbPathArchivo.append(Server.SEPARADOR_ARCHIVOS);
                sbPathArchivo.append(folder);
                sbPathArchivo.append(Server.SEPARADOR_ARCHIVOS);
                sbPathArchivo.append(nbArchivo);
                
                pathArchivo = servletContext.getRealPath(sbPathArchivo.toString());
                archivoAux = new File(pathArchivo);
                
                //Elimina archivos creados 1 hora antes o más
                if(createdOneHourBefore(archivoAux.lastModified())) {
                    deleteFile(archivoAux);
                }
            }
        }
    }
    
    private void deleteFile(File archivo) {
        if(archivo.isFile()) {
            if(archivo.delete()) {
                LOGGER.info("Se eliminó el archivo : " + archivo);
            } else {
                LOGGER.info("Imposible eliminar el archivo : " + archivo);
            }
        }
    }
    
    private boolean createdOneHourBefore(long fechaCreacion) {
        Date fechaCreado = new Date(fechaCreacion);
        Date fechaActual = new Date(System.currentTimeMillis());
        int dividendoHoras = MIL * SESENTA * SESENTA;
        long diferenciaFechas = fechaActual.getTime() - fechaCreado.getTime();
        int diferenciaEnHoras = (int)( diferenciaFechas/dividendoHoras);
        
        if(diferenciaEnHoras >= 1){
            return true;
        }
        
        return false;
    }
    
    private boolean isProtectedFile(String nbArchivo) {
        boolean fileProtected = false;
        
        for(String protectedFile : PROTECTED_FILES) {
            if(nbArchivo.equals(protectedFile)) {
                fileProtected = true;
                break;
            }
        }
        
        return fileProtected;
    }
}
