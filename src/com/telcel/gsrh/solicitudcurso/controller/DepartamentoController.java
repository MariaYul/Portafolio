package com.telcel.gsrh.solicitudcurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.telcel.gsrh.solicitudcurso.form.DepartamentoBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Departamento;
import com.telcel.gsrh.solicitudcurso.service.DepartamentoService;

@Controller
@RequestMapping("/departamento")
public class DepartamentoController {
    
    private static final String PAGINA_MAIN_DEPARTAMENTO = "departamentomain";
    private static final String PAGINA_RS_DEPARTAMENTO = "departamentors";
    
    @Autowired
    private DepartamentoService departamentoService;

    @RequestMapping(value="/undecorated/loadBusqueda.htm", method={RequestMethod.GET, RequestMethod.POST})
    public String loadSearch() {
        return PAGINA_MAIN_DEPARTAMENTO;
    }
    
    @RequestMapping(value="/undecorated/search.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView search(DepartamentoBusqueda busqueda) {
        ModelAndView modelo = new ModelAndView();
        
        List<Departamento> departamentos = departamentoService.search(busqueda);
        Long cantidadTotalRegistros = departamentoService.getCantidadSearch(busqueda);
        
        modelo.addObject("departamentos", departamentos);
        modelo.addObject("count", cantidadTotalRegistros);
        modelo.addObject("offset", busqueda.getOffset());
        
        modelo.setViewName(PAGINA_RS_DEPARTAMENTO);
        
        return modelo;
    }
}
