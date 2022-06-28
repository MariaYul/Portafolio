package com.telcel.gsrh.solicitudcurso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController {
    
    private static final String PAGINA_INICIO = "inicio";
    
    @RequestMapping(method=RequestMethod.GET, value="inicio.htm")
    public String home() {
        return PAGINA_INICIO;
    }
}
