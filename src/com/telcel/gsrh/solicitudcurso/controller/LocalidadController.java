package com.telcel.gsrh.solicitudcurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.telcel.gsrh.solicitudcurso.form.LocalidadBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Localidad;
import com.telcel.gsrh.solicitudcurso.service.LocalidadService;

@Controller
@RequestMapping("/localidad")
public class LocalidadController {

    private static final String PAGINA_MAIN_LOCALIDAD = "localidadmain";
    private static final String PAGINA_RS_LOCALIDAD = "localidadrs";
    
    @Autowired
    private LocalidadService localidadService;

    @RequestMapping(value="/undecorated/loadBusqueda.htm", method={RequestMethod.GET, RequestMethod.POST})
    public String loadSearch() {
        return PAGINA_MAIN_LOCALIDAD;
    }
    
    @RequestMapping(value="/undecorated/search.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView search(LocalidadBusqueda busqueda) {
        ModelAndView modelo = new ModelAndView();
        
        List<Localidad> localidades = localidadService.search(busqueda);
        Long cantidadTotalRegistros = localidadService.getCantidadSearch(busqueda);
        
        modelo.addObject("localidades", localidades);
        modelo.addObject("count", cantidadTotalRegistros);
        modelo.addObject("offset", busqueda.getOffset());
        
        modelo.setViewName(PAGINA_RS_LOCALIDAD);
        
        return modelo;
    }
}
