package com.telcel.gsrh.solicitudcurso.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.telcel.gsrh.solicitudcurso.form.DocumentoBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Area;
import com.telcel.gsrh.solicitudcurso.model.Documento;
import com.telcel.gsrh.solicitudcurso.model.Fase;
import com.telcel.gsrh.solicitudcurso.model.Usuario;
import com.telcel.gsrh.solicitudcurso.service.DocumentoService;
import com.telcel.gsrh.solicitudcurso.service.FaseService;

@Controller
@RequestMapping("/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;
    
    @Autowired 
    private FaseService fasesService;
    
    private static final String PAGINA_DOCUMENTO_FASE = "documentofase";
    private static final String PAGINA_RS_DOCUMENTO = "documentors";
    private static final String PAGINA_DOCUMENTO_NUEVO = "personadocumentoadd";
    private static final String PAGINA_DOCUMENTO_EDICION = "personadocumentoedit";
    private static final String PAGINA_REDIRECT_DETALLE_PERSONA = "redirect:/persona/detalle/${clavePersona}.htm";
    
    private static final String MSG_DOCUMENTO_REGISTRADO = "Documento cargado correctamente.";
    private static final String MSG_DOCUMENTO_ACTUALIZADO = "Documento actualizado correctamente.";
    private static final String MSG_ERROR_SINPERMISOS = "No puedes actualizar/eliminar documento porque no pertences al &aacute;rea adecuada.";
    private static final String MSG_PERSONA_ELIMINAR = "Documento eliminado correctamente.";
    
    private static final String HEADER_CONTENIDO_PDF = "application/pdf";
    private static final String HEADER_DISPOSICION_CONTENIDO = "Content-Disposition";
    private static final String HEADER_ARCHIVO_ADJUNTO = "attachment; filename=\"%s\"";
    
    private static final String USUARIO_SISTEMA = "usuarioRegistrado";
    private static final String CLAVE_PERSONA = "clavePersona";
    
    @RequestMapping(value="/add.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView add(Documento documento, HttpServletRequest request) {
        ModelAndView modelo = new ModelAndView();
        HttpSession session = request.getSession(); 
        Usuario usuario = (Usuario)session.getAttribute(USUARIO_SISTEMA);
        
        documentoService.registrarDocumento(documento, usuario);
        
        modelo.addObject("info", MSG_DOCUMENTO_REGISTRADO);
       // modelo.setViewName(getUrlDetallePersona(documento));
    
        return modelo;
    }
    
    @RequestMapping(value="/delete.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView delete(@RequestParam Integer claveDocumento) {
        ModelAndView modelo = new ModelAndView();
        
        Documento documento = documentoService.findById(claveDocumento);
        documentoService.eliminarDocumento(documento);
        
        modelo.addObject("info", MSG_PERSONA_ELIMINAR);
       // modelo.setViewName(getUrlDetallePersona(documento));
        
        return modelo;
    }
    
    @RequestMapping(value="/download.htm", method={RequestMethod.GET, RequestMethod.POST})
    public void download(@RequestParam Integer claveDocumento, HttpServletResponse response) throws IOException {
        OutputStream ostream = response.getOutputStream();
        Documento documento =  documentoService.findById(claveDocumento);
        String nombreArchivo = String.format(HEADER_ARCHIVO_ADJUNTO, documentoService.obtenerNombreArchivo(documento));
    
        response.setContentType(HEADER_CONTENIDO_PDF);
        response.setHeader(HEADER_DISPOSICION_CONTENIDO, nombreArchivo);
        
        documentoService.descargarDocumento(documento, ostream);
    }
    
    @RequestMapping(value="/edit.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView edit(Documento documento, HttpServletRequest request) {
        ModelAndView modelo = new ModelAndView();
        HttpSession session = request.getSession(); 
        Usuario usuario = (Usuario)session.getAttribute(USUARIO_SISTEMA);
        
        documentoService.actualizarDocumento(documento, usuario);
        
        modelo.addObject("info", MSG_DOCUMENTO_ACTUALIZADO);
       // modelo.setViewName(getUrlDetallePersona(documento));
        
        return modelo;
    }
    
    @RequestMapping(value="/undecorated/bypersona.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mainDocumentacionByPersona(@RequestParam(required=false, defaultValue="-1") Integer clavePersona
                                                , @RequestParam(required=false, defaultValue="-1") Integer claveFase) {
    
        ModelAndView modelo = getModeloPersonaFase(clavePersona, claveFase);
        modelo.setViewName(PAGINA_DOCUMENTO_FASE);
        
        return modelo;
    }
    
    @RequestMapping(value="/undecorated/bypersonabyfase.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView documentacionByPersonaByFase(@RequestParam(required=false, defaultValue="-1") Integer clavePersona
                                                , @RequestParam(required=false, defaultValue="-1") Integer claveFase) {
        
        ModelAndView modelo = new ModelAndView();
        
        DocumentoBusqueda getDocumentoBusqueda = getDocumentoBusqueda(clavePersona, claveFase);
        List<Documento> documentos = documentoService.search(getDocumentoBusqueda);
        
        modelo.addObject("documentos", documentos);
        modelo.addObject(CLAVE_PERSONA, clavePersona);
        modelo.setViewName(PAGINA_RS_DOCUMENTO);
        
        return modelo;
    }
    
    @RequestMapping(value="/undecorated/loadEdit.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadEdit(@RequestParam Integer claveDocumento, HttpServletRequest request) {
        ModelAndView modelo = new ModelAndView();
        HttpSession session = request.getSession(); 
        Usuario usuario = (Usuario)session.getAttribute(USUARIO_SISTEMA);
        Documento documento =  documentoService.getDocumentoEditar(claveDocumento, usuario);
        
        if(documento == null) {
            modelo.addObject("error", MSG_ERROR_SINPERMISOS);
            modelo.setViewName(PAGINA_DOCUMENTO_EDICION);
        } else {
            modelo.addObject("documento", documento);
            modelo.setViewName(PAGINA_DOCUMENTO_EDICION);
        }
        return modelo;
    }
    
    @RequestMapping(value="/undecorated/loadNew.htm", method={RequestMethod.POST})
    public ModelAndView loadNew(@RequestParam(defaultValue="0", required=true, value="clavePersona") int clavePersona, HttpServletRequest request) {
        Usuario usuario = (Usuario)request.getSession().getAttribute(USUARIO_SISTEMA);
        ModelAndView modelo = new ModelAndView();
        
        List<Fase> fases = null;
        
        if(usuario.getArea().getClave() == Area.CLAVE_AREA_PERSONAL) {
        	fases = fasesService.findAll();
        } else {
        	fases = fasesService.searchFasePorArea(usuario.getArea().getClave());
        }
        
        modelo.addObject(CLAVE_PERSONA, clavePersona);
        modelo.addObject("fases", fases);
        modelo.setViewName(PAGINA_DOCUMENTO_NUEVO);
        
        return modelo;
    }
    
    private DocumentoBusqueda getDocumentoBusqueda(Integer clavePersona, Integer claveFase) {
        DocumentoBusqueda documentoBusqueda = new DocumentoBusqueda();
        documentoBusqueda.setClavePersona(clavePersona);
        documentoBusqueda.setClaveFase(claveFase);
        
        return documentoBusqueda;
    }
    
    private ModelAndView getModeloPersonaFase(Integer clavePersona, Integer claveFase) {
        ModelAndView modelo = new ModelAndView();
        
        DocumentoBusqueda getDocumentoBusqueda = getDocumentoBusqueda(clavePersona, claveFase);
        
        List<Documento> documentos = documentoService.search(getDocumentoBusqueda);
        
        modelo.addObject("documentos", documentos);
        modelo.addObject(CLAVE_PERSONA, clavePersona);
        
        return modelo;
    }
    
  /*  private String getUrlDetallePersona(Documento documento) {
        String clavePersona = String.valueOf(documento.getPersona().getClave());
        
        return PAGINA_REDIRECT_DETALLE_PERSONA.replace("${clavePersona}", clavePersona);
    }*/
}
