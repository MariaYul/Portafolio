package com.telcel.gsrh.solicitudcurso.endpoint;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.telcel.gsrh.solicitudcurso.model.Documento;
import com.telcel.gsrh.solicitudcurso.model.FaseWS;
import com.telcel.gsrh.solicitudcurso.model.Persona;
import com.telcel.gsrh.solicitudcurso.model.TipoDocumento;
import com.telcel.gsrh.solicitudcurso.model.TipoDocumentoWS;
import com.telcel.gsrh.solicitudcurso.model.Usuario;
import com.telcel.gsrh.solicitudcurso.service.DocumentoService;
import com.telcel.gsrh.solicitudcurso.service.FaseWsService;
import com.telcel.gsrh.solicitudcurso.service.MonedaService;
import com.telcel.gsrh.solicitudcurso.service.PersonaService;
import com.telcel.gsrh.solicitudcurso.service.TipoDocumentoWsService;
import com.telcel.gsrh.solicitudcurso.service.UsuarioService;

@WebService(serviceName="ArchivoDigitalService")
public class ArchivoDigitalEndPoint extends SpringBeanAutowiringSupport {

    @Autowired
    private DocumentoService documentoService;
    
    @Autowired
    private FaseWsService faseWsService;
    
    @Autowired
    private PersonaService personaService;
    
    @Autowired
    private MonedaService tipoDocumentoService;
    
    @Autowired
    private TipoDocumentoWsService tipoDocumentoWsService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    private static final Integer USUARIO_LEXMARK = 0;
    
    @WebMethod
    public void deleteDocument(Integer candidateId, Integer documentTypeId, String documentName) {
        Persona persona = personaService.findById(candidateId);
        //TipoDocumento tipoDocumento = tipoDocumentoService.findById(documentTypeId);
        
        Documento documentoBuscar = new Documento();
       // documentoBuscar.setPersona(persona);
        //documentoBuscar.setTipoDocumento(tipoDocumento);
        documentoBuscar.setNombre(documentName);
        
        Documento documentoEliminar = documentoService.searchByDelete(documentoBuscar);
        
        if(documentoBuscar != null) {
            documentoService.delete(documentoEliminar);
        }
    }
    
    @WebMethod
    public List<FaseWS> getAllPhases() {
        return faseWsService.findAll();
    }
    
    @WebMethod
    public List<TipoDocumentoWS> getDocumentTypesByPhaseByCandidate(Integer candidateId, Integer phaseId) {
        return tipoDocumentoWsService.getTiposDocumentoByLoadToCandidate(phaseId, candidateId);
    }
    
    @WebMethod
    public String registerDocument(Integer candidateId, Integer documentTypeId) {
        Documento documento = new Documento();
        Documento documentoPersistido = null;
        
        Persona persona = personaService.findById(candidateId);
        //TipoDocumento tipoDocumento = tipoDocumentoService.findById(documentTypeId);
        Usuario usuarioLexmark = usuarioService.findById(USUARIO_LEXMARK);
        
        //documento.setPersona(persona);
        //documento.setTipoDocumento(tipoDocumento);
        documento.setUsuario(usuarioLexmark);
        
        documentoPersistido = documentoService.persistirDocumento(documento);
        
        return documentoPersistido.getNombre();
    }
}
