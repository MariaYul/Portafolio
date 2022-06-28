package com.telcel.gsrh.solicitudcurso.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telcel.gsrh.solicitudcurso.exception.GeneralException;
import com.telcel.gsrh.solicitudcurso.form.DocumentoBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Area;
import com.telcel.gsrh.solicitudcurso.model.Documento;
import com.telcel.gsrh.solicitudcurso.model.TipoDocumento;
import com.telcel.gsrh.solicitudcurso.model.Usuario;
import com.telcel.gsrh.solicitudcurso.pdf.PdfUtils;
import com.telcel.gsrh.solicitudcurso.repository.DocumentoRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.DocumentoService;
import com.telcel.gsrh.solicitudcurso.service.MonedaService;
import com.telcel.gsrh.solicitudcurso.ssh.ManejadorSsh;
import com.telcel.gsrh.solicitudcurso.utils.Server;

@Service
public class DocumentoServiceImpl extends AbstractService<Documento> implements DocumentoService {
    
    private static final String PUNTO = ".";
    private static final int DOS = 2;
    private static final String SEPARADOR_NOMENCLATURA_ARCHIVO = "_";
    private static final String MSG_EXCEPCION_TIPO_ARCHIVO = "El archivo proporcionado no tiene la extensión .pdf";
    private static final String MSG_EXCEPCION_TRANSFERIR_DOCUMENTOS = "Error al transferir documentos: %s";
    private static final Logger LOGGER = Logger.getLogger(DocumentoServiceImpl.class);
    
    @Autowired
    private DocumentoRepository repository;
    
    @Autowired
    private MonedaService tipoDocumentoService;
    
    @Autowired
    private Server server;
    
    @Autowired
    private ManejadorSsh manejadorSsh;
    
    @Override
    protected GenericOperation<Documento> getRepository() {
        return repository;
    }
    
    @Override
    @Transactional(readOnly=false)
    public void actualizarDocumento(Documento documento, Usuario usuario) {
        if(isArchivoValido(documento.getArchivo().getOriginalFilename())) {
            documento.setUsuario(usuario);
            documento.setFechaActualizacion(new Date());
            
            update(documento);
          //  transferirDocumentoAlRepositorio(documento);
        } else {
            throw new GeneralException(MSG_EXCEPCION_TIPO_ARCHIVO);
        }
    }
    
    @Override
    public void descargarDocumento(Documento documento, OutputStream outputstream) {
        manejadorSsh.descargarDocumento(documento, outputstream);
    }
    
    @Override
    @Transactional(readOnly=false)
    public void eliminarDocumento(Documento documento) {
        delete(documento);
        manejadorSsh.eliminarDocumento(documento);
    }
    
    @Override
    public Long getCantidadSearch(DocumentoBusqueda parametro) {
        return repository.getCantidadSearch(parametro);
    }
    
    @Override
    public Documento getDocumentoEditar(Integer claveDocumento, Usuario usuario) {
        Documento documento = findById(claveDocumento);
        
        if(!isAreaValida(documento.getTipoDocumento().getArea().getClave(), usuario.getArea().getClave())) {
            documento = null;
        }
        
        return documento;
    }
    
    private Documento getDocumentoPersistir(Documento documento) {
        Documento documentoModificado = documento;
        String nombreArchivo = "";
        String rutaArchivo = "";
        //TipoDocumento tipodocumento = tipoDocumentoService.findById(documento.getTipoDocumento().getClave());
        int contadorTipoArchivo = 0;
        
        /*if(tipodocumento.getMultiregistro() == 1) {
            contadorTipoArchivo = repository.getCantidadSearchNuevo(documento);
        }*/
        
        contadorTipoArchivo++;
       // nombreArchivo = getNombreArchivo(contadorTipoArchivo, tipodocumento.getClave(), documento.getPersona().getClave());
       // rutaArchivo = getRutaArchivo(documento.getPersona().getClave(), nombreArchivo);
        
        documentoModificado.setNombre(nombreArchivo);
        documentoModificado.setContador(contadorTipoArchivo);
        documentoModificado.setRuta(rutaArchivo);
        documentoModificado.setFechaActualizacion(new Date());
        
        return documentoModificado;
    }
    
    private String getNombreArchivo(int contadorTipoArchivo, int claveTipoArchivo, int clavePersona) {
        StringBuilder nombreArchivo = new StringBuilder();
        nombreArchivo.append(claveTipoArchivo);
        nombreArchivo.append(SEPARADOR_NOMENCLATURA_ARCHIVO);
        nombreArchivo.append(clavePersona);
        nombreArchivo.append(SEPARADOR_NOMENCLATURA_ARCHIVO);
        nombreArchivo.append(contadorTipoArchivo);
        nombreArchivo.append(PdfUtils.TERMINACION_ARCHIVO_PDF);
        
        return nombreArchivo.toString(); 
    }
    
    private String getRutaArchivo(Integer clavePersona, String nombreArchivo) {
        StringBuilder rutaArchivo = new StringBuilder();
        rutaArchivo.append(manejadorSsh.getPropiedad(ManejadorSsh.CLAVE_RUTA));
        rutaArchivo.append(clavePersona);
        rutaArchivo.append(PdfUtils.SEPARADOR_ARCHIVOS_REPOSITORIO);
        rutaArchivo.append(nombreArchivo);
        
        return rutaArchivo.toString();
    }
    
    private boolean isArchivoValido(String nombreArchivo) {
        if(nombreArchivo.toLowerCase().endsWith(PdfUtils.TERMINACION_ARCHIVO_PDF)){
            return true;
        }
        
        return false;
    }
    
    private boolean isAreaValida(Integer claveAreaDocumento, Integer claveAreaUsuario) {
        if((claveAreaDocumento == claveAreaUsuario) || (Area.CLAVE_AREA_PERSONAL == claveAreaUsuario)){
            return true;
        }
        
        return false;
    }
    
    @Override
    public String obtenerNombreArchivo(Documento documento){
        StringBuilder nombreArchivo = new StringBuilder();
        String nombrePrimero = documento.getNombre();
        
        String[] arregloNombreArchivo = nombrePrimero.split(SEPARADOR_NOMENCLATURA_ARCHIVO);
        String[] elementoNombreArchivo = arregloNombreArchivo[DOS].split("\\.");
        
        nombreArchivo.append(documento.getTipoDocumento().getNombre());
        nombreArchivo.append(SEPARADOR_NOMENCLATURA_ARCHIVO);
        //nombreArchivo.append(documento.getPersona().getClave());
        nombreArchivo.append(SEPARADOR_NOMENCLATURA_ARCHIVO);
        nombreArchivo.append(elementoNombreArchivo[0]);
        nombreArchivo.append(PUNTO);
        nombreArchivo.append(elementoNombreArchivo[1]);
        
        return nombreArchivo.toString();
    }
    
    @Override
    @Transactional(readOnly=false)
    public Documento persistirDocumento(Documento documento) {
        Documento documentoPersistir = getDocumentoPersistir(documento);
        
        register(documentoPersistir);
        
        return documentoPersistir;
    }
    
    @Override
    @Transactional(readOnly=false)
    public void registrarDocumento(Documento documento, Usuario usuario) {
        Documento documentoPersistido = null;
        
        if(isArchivoValido(documento.getArchivo().getOriginalFilename())) {
            documento.setUsuario(usuario);
            documentoPersistido = persistirDocumento(documento);
            
           // transferirDocumentoAlRepositorio(documentoPersistido);
        } else {
            throw new GeneralException(MSG_EXCEPCION_TIPO_ARCHIVO);
        }
    }
    
    @Override
    public List<Documento> search(DocumentoBusqueda parametro) {
        return repository.search(parametro);
    }
    
    @Override
    public Documento searchByDelete(Documento documento) {
        return repository.searchByDelete(documento);
    }
    
    @Override
    public List<Documento> searchFlipping(DocumentoBusqueda parametro) {
        return repository.searchFlipping(parametro);
    }
    
    @Override
    public List<String> transferirArchivos(HttpServletRequest request, List<Documento> listaDocumento) {
        List<String> listaNombres = new ArrayList<String>();
        
        try {
            for(Documento documento: listaDocumento){
                String nombre = server.getArchivoGenerado(request, PdfUtils.TERMINACION_ARCHIVO_PDF);
                String rutaDestino = server.getResourceInDocumentsFolderPath(request, Server.DIRECTORIO_PDF, nombre);
                OutputStream outputAppServer = new FileOutputStream(rutaDestino);
                
                manejadorSsh.descargarDocumento(documento, outputAppServer);
                
                listaNombres.add(nombre);
            }
        } catch(GeneralException e) {
            throw e;
        } catch(Exception e) {
            LOGGER.info(e);
            throw new GeneralException(String.format(MSG_EXCEPCION_TRANSFERIR_DOCUMENTOS, e.getMessage()));
        }
        return listaNombres;
    }
    
   /* private void transferirDocumentoAlRepositorio(Documento documento){
        manejadorSsh.validarDirectorio(String.valueOf(documento.getPersona().getClave()));
        manejadorSsh.subirDocumento(documento);
    }*/
}
