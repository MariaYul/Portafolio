package com.telcel.gsrh.solicitudcurso.service;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.telcel.gsrh.solicitudcurso.form.DocumentoBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Documento;
import com.telcel.gsrh.solicitudcurso.model.Usuario;

public interface DocumentoService extends GenericOperation<Documento> {

    /**
     * Metodo que actualiza documento en repositorio
     * @param Documento documento
     * @param Usuario usuario
     */
    public void actualizarDocumento(Documento documento, Usuario usuario);
    
    /**
     * metodo que se encarga de descargar del repositorio documento indicado
     * @param Documento documento
     * @param OutputStream outputstream
     */
    public void descargarDocumento(Documento documento,OutputStream outputstream);
    
    /**
     * Metodo que elimina documento en repositorio y base de datos
     * @param Documento documento
     */
    public void eliminarDocumento(Documento documento);
    
    /**
     * Consulta la cantidad total de documentos de acuerdo a los parámetros de búsqueda
     * @param parametro Parámetros de búsqueda
     * @return List<Area> Cantidad total de documentos de acuerdo a los parámetros de búsqueda
     */
    public Long getCantidadSearch(DocumentoBusqueda parametro);
    
    /**
     * Obtiene el documento para editar siempre y cuando el area del usuario en sesion sea permitida para
     * realizar la actualización
     * @param claveDocumento Clave de documento
     * @param usuario Detalle del usuario en sesión
     * @return Documento detalle de documento si es un área permitida, <b>null</b> en caso contrario.
     */
    public Documento getDocumentoEditar(Integer claveDocumento, Usuario usuario);
    
    /**
     * metodo que sirve para formar nombre al archivo que se descarga desde la aplicacion
     * @param documento
     * @return nombre de archivo para descargar
     */
    public String obtenerNombreArchivo(Documento documento);
    
    /**
     * Metodo que registra en base de datos el detalle de un documento
     * <br /><b>Nota: </b> Es p&uacute;blico porque también lo utiliza el web service que se le proporciona a Lexmark
     * @param documento Detalle del documento a persirtir
     * @return Detalle del documento persistido
     */
    public Documento persistirDocumento(Documento documento);
    
    /**
     * Metodo que registra en base de datos el detalle del documento y tambien lo transfiere al repositorio
     * @param documento Detalle del documento
     * @param usuario usuario en sesi&oacute;n
     */
    public void registrarDocumento(Documento documento, Usuario usuario);
    
    /**
     * Metodo que realiza la busqueda de los documentos registrados a una persona en una fase
     * @param parametro Par&aacute;metros de filtrado
     * @return Lista de los documentos registrados a una persona en una fase
     */
    public List<Documento> search(DocumentoBusqueda parametro);
    
    /**
     * Consulta un documento por candidato, tipo de documento y nombre de documento
     * @param documento Parámetros de filtrado
     * @return Documento por candidato, tipo de documento y nombre de documento
     */
    public Documento searchByDelete(Documento documento);
    
    /**
     * Metodo que realiza la busqueda de todos los documentos registrados a una persona
     * @param parametro Par&aacute;metros de busqueda
     * @return Lista de todos los documentos registrados a una persona
     */
    public List<Documento> searchFlipping(DocumentoBusqueda parametro);
    
    /**
     * Metodo que transfiere los archivos del repositorio al servidor de aplicaciones
     * @param request Peticion http
     * @param listaDocumento Lista de documentos
     * @return Lista de documentos con referencia al servidor de apliaciones
     */
    public List<String> transferirArchivos(HttpServletRequest request, List<Documento> listaDocumento);    
}
