package com.telcel.gsrh.solicitudcurso.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.telcel.gsrh.solicitudcurso.exception.PdfException;
import com.telcel.gsrh.solicitudcurso.form.DocumentoBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Documento;
import com.telcel.gsrh.solicitudcurso.pdf.PdfUtils;
import com.telcel.gsrh.solicitudcurso.service.DocumentoService;

@Controller
public class ExpedienteController {

    @Autowired
    private PdfUtils pdfUtils;
    
    @Autowired
    private DocumentoService documentoService;
    
    private static final String PAGINA_EXPEDIENTE_FLIPPINGBOOK = "expedienteflipbook";
    
    @RequestMapping(value="/expedienteflipbook.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView generarFlippingBookPdf(HttpServletRequest request, DocumentoBusqueda parametro) throws PdfException {
        ModelAndView modelo = new ModelAndView();
        
        List<Documento> documentosEmpleado = documentoService.searchFlipping(parametro);
        List<String> archivos = documentoService.transferirArchivos(request, documentosEmpleado);
        String archivoPdf = pdfUtils.unirArchivosPdf(request, archivos);
        List<String> imagenes = pdfUtils.convertirPdfToImagen(request, archivoPdf);
        
        modelo.addObject("imagenes", imagenes);
        modelo.setViewName(PAGINA_EXPEDIENTE_FLIPPINGBOOK);
        
        return modelo;
    }
}
