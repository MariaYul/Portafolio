package com.telcel.gsrh.solicitudcurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.telcel.gsrh.solicitudcurso.form.PuestoBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Puesto;
import com.telcel.gsrh.solicitudcurso.service.PuestoService;

@Controller
@RequestMapping("/puesto")
public class PuestoController {

    private static final String PAGINA_MAIN_PUESTO = "puestomain";
    private static final String PAGINA_RS_PUESTO = "puestors";
    
    @Autowired
    private PuestoService puestoService;

    @RequestMapping(value="/undecorated/loadBusqueda.htm", method={RequestMethod.GET, RequestMethod.POST})
    public String loadSearch() {
        return PAGINA_MAIN_PUESTO;
    }
    
    @RequestMapping(value="/undecorated/search.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView search(PuestoBusqueda busqueda) {
        ModelAndView modelo = new ModelAndView();
        
        List<Puesto> puestos = puestoService.search(busqueda);
        Long cantidadTotalRegistros = puestoService.getCantidadSearch(busqueda);
        
        modelo.addObject("puestos", puestos);
        modelo.addObject("count", cantidadTotalRegistros);
        modelo.addObject("offset", busqueda.getOffset());
        
        modelo.setViewName(PAGINA_RS_PUESTO);
        
        return modelo;
    }
}
