package com.telcel.gsrh.solicitudcurso.ssh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;
import com.telcel.gsrh.solicitudcurso.exception.GeneralException;
import com.telcel.gsrh.solicitudcurso.model.Documento;
import com.telcel.gsrh.solicitudcurso.utils.SshUserInfo;

@Service
public class ManejadorSsh {

    private static final String CANAL_EXEC = "exec";
    private static final String CANAL_SFTP = "sftp";
    private static final String CLAVE_HOST = "sftp.servidor.host";
    private static final String CLAVE_PUERTO = "sftp.servidor.puerto";
    private static final String CLAVE_USUARIO = "sftp.servidor.usuario";
    private static final String CLAVE_CONTRASENNIA = "sftp.servidor.contrasennia";
    private static final String CLAVE_CHECAR_CLAVES_HOST = "sftp.servidor.stricthostkeychecking";
    public static final String CLAVE_RUTA = "sftp.servidor.repositorio.ruta";
    private static final String CLAVE_PROPIEDAD_CHECAR_CLAVES_HOST = "StrictHostKeyChecking";
    private static final String COMANDO_CAMBIAR_DIRECTORIO = "cd ";
    private static final String COMANDO_CONCATENACION = " && ";
    private static final String COMANDO_VALIDA_DIRECTORIO = "[ ! -d ${directorio} ]";
    private static final String COMANDO_CREAR_DIRECTORIO = "mkdir ${directorio}";
    private static final String MSG_EXCEPCION_CONEXION = "Error al conectar servidor sftp %s: %s";
    private static final String MSG_EXCEPCION_CANAL_SFTP = "Error al obtener canal sftp: %s";
    private static final String MSG_EXCEPCION_CANAL_EXEC = "Error al obtener canal exec: %s";
    private static final String MSG_EXCEPCION_DOCUMENTO_NO_EXISTE = "El documento %s no existe en el repositorio";
    private static final String MSG_EXCEPCION_DESCARGAR_DOCUMENTO = "Error al descargar el documento %s: %s";
    private static final String MSG_EXCEPCION_ELIMINAR_DOCUMENTO = "Error al subir el documento %s: %s";
    private static final String MSG_EXCEPCION_EJECUTAR_COMANDO = "Error al ejecutar el comando %s: %s";
    private static final String MSG_EXCEPCION_SUBIR_DOCUMENTO = "Error al subir el documento %s: %s";
    private static final String PARAMETRO_DIRECTORIO = "${directorio}";
    private static final String VACIO = "";
    private static final int MEGABYTE = 1024;
    private static final Logger LOGGER = Logger.getLogger(ManejadorSsh.class);
    
    @Autowired
    private Environment env;
    
    private JSch jsch;
    
    public ManejadorSsh() {
        jsch = new JSch();
    }
    
    private void cerrarCanalExec(ChannelExec canal) {
        if(canal != null) {
            canal.disconnect();
        }
    }
    
    private void cerrarCanalSftp(ChannelSftp canal) {
        if(canal != null) {
            canal.exit();
            canal.disconnect();
        }
    }
    
    private void cerrarSession(Session session) {
        if(session != null) {
            session.disconnect();
        }
    }
    
    public void descargarDocumento(Documento documento, OutputStream outputstream) {
        Session session = null;
        ChannelSftp canal = null;
        String directorio = documento.getRuta().replace(documento.getNombre(), VACIO);
        InputStream archivoRepositorio = null;
        
        try {
            session = getSession();
            canal = getCanalSftp(session);
            canal.cd(directorio);
            
            archivoRepositorio = canal.get(documento.getNombre());
            
            IOUtils.copy(archivoRepositorio, outputstream);
        } catch(SftpException e) {
            LOGGER.error(e);
            throw new GeneralException(String.format(MSG_EXCEPCION_DOCUMENTO_NO_EXISTE, documento.getNombre(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error(e);
            throw new GeneralException(String.format(MSG_EXCEPCION_DESCARGAR_DOCUMENTO, documento.getNombre(), e.getMessage()));
        } finally {
            cerrarCanalSftp(canal);
            cerrarSession(session);
            IOUtils.closeQuietly(outputstream);
            IOUtils.closeQuietly(archivoRepositorio);
        }
    }
    
    public void eliminarDocumento(Documento documento) {
        Session session = null;
        ChannelSftp canal = null;
        String directorio = documento.getRuta().replace(documento.getNombre(), VACIO);
        
        try {
            session = getSession();
            canal = getCanalSftp(session);
            canal.cd(directorio);
            
            canal.rm(documento.getNombre());
        } catch(SftpException e) {
            LOGGER.error(e);
            throw new GeneralException(String.format(MSG_EXCEPCION_DOCUMENTO_NO_EXISTE, documento.getNombre(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error(e);
            throw new GeneralException(String.format(MSG_EXCEPCION_ELIMINAR_DOCUMENTO, documento.getNombre(), e.getMessage()));
        } finally {
            cerrarCanalSftp(canal);
            cerrarSession(session);
        }
    }
    
    private ChannelExec getCanalExec(Session session) {
        ChannelExec canal = null;
        
        try {
            canal = (ChannelExec) session.openChannel(CANAL_EXEC);
        } catch (JSchException e) {
            LOGGER.error(e);
            throw new GeneralException(String.format(MSG_EXCEPCION_CANAL_EXEC, e.getMessage()));
        }
        
        return canal;
    }
    
    private ChannelSftp getCanalSftp(Session session) {
        ChannelSftp canal = null;
        
        try {
            canal = (ChannelSftp) session.openChannel(CANAL_SFTP);
            canal.connect();
        } catch (JSchException e) {
            LOGGER.error(e);
            throw new GeneralException(String.format(MSG_EXCEPCION_CANAL_SFTP, e.getMessage()));
        }
        
        return canal;
    }
    
    private String getComandoValidarDirectorio(String directorio) {
        StringBuilder comando = new StringBuilder();
        comando.append(COMANDO_CAMBIAR_DIRECTORIO);
        comando.append(getPropiedad(CLAVE_RUTA));
        comando.append(COMANDO_CONCATENACION);
        comando.append(COMANDO_VALIDA_DIRECTORIO.replace(PARAMETRO_DIRECTORIO, directorio));
        comando.append(COMANDO_CONCATENACION);
        comando.append(COMANDO_CREAR_DIRECTORIO.replace(PARAMETRO_DIRECTORIO, directorio));
        
        return comando.toString();
    }
    
    public String getPropiedad(String clave) {
        return env.getProperty(clave);
    }
    
    private Properties getPropiedadesSessionSftp() {
        Properties propiedades = new Properties();
        String checarClavesHost = getPropiedad(CLAVE_CHECAR_CLAVES_HOST);
        propiedades.put(CLAVE_PROPIEDAD_CHECAR_CLAVES_HOST, checarClavesHost);
                
        return propiedades;
    }
    
    private Session getSession() {
        Session session = null;
        String host = getPropiedad(CLAVE_HOST);
        String usuario = getPropiedad(CLAVE_USUARIO);
        String contrasennia = getPropiedad(CLAVE_CONTRASENNIA);
        int puerto = Integer.parseInt(getPropiedad(CLAVE_PUERTO));
        UserInfo informacionUsuario = new SshUserInfo(contrasennia, null);
        
        try { 
             session = jsch.getSession(usuario, host, puerto);
             session.setUserInfo(informacionUsuario);
             session.setPassword(contrasennia);
             session.setConfig(getPropiedadesSessionSftp());
             
             session.connect();
        } catch(Exception e) {
            LOGGER.error(e);
            throw new GeneralException(String.format(MSG_EXCEPCION_CONEXION, host, e.getMessage()));
        }
        
        return session;
    }
    
    public void subirDocumento(Documento documento) {
        Session session = null;
        ChannelSftp canal = null;
        String directorio = documento.getRuta().replace(documento.getNombre(), VACIO);
        
        try {
            session = getSession();
            canal = getCanalSftp(session);
            canal.cd(directorio);
            
            canal.put(documento.getArchivo().getInputStream(), documento.getNombre());
        } catch (Exception e) {
            LOGGER.error(e);
            throw new GeneralException(String.format(MSG_EXCEPCION_SUBIR_DOCUMENTO, documento.getNombre(), e.getMessage()));
        } finally {
            cerrarCanalSftp(canal);
            cerrarSession(session);
        }
    }
    
    public void validarDirectorio(String directorio) {
        Session session = null;
        ChannelExec canal = null;
        String comando = getComandoValidarDirectorio(directorio);
        InputStream respuesta = null;
        
        try {
            session = getSession();
            canal = getCanalExec(session);
            canal.setCommand(comando);
            canal.setInputStream(null);
            
            respuesta = canal.getInputStream();
            
            canal.connect();
                 
            imprimirMensajes(respuesta);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new GeneralException(String.format(MSG_EXCEPCION_EJECUTAR_COMANDO, comando, e.getMessage()));
        } finally {
            IOUtils.closeQuietly(respuesta);
            cerrarCanalExec(canal);
            cerrarSession(session);
        }
    }
    
    private void imprimirMensajes(InputStream in) throws IOException {
        byte[] tmp = new byte[MEGABYTE];
        String linea = VACIO;
        
        while(in.available() > 0) {
            int i = in.read(tmp, 0, MEGABYTE);
                
            if(i < 0){
                break;
            }
                
            linea = new String(tmp, 0, i);
                
            LOGGER.info(linea);
        }
    }
}
