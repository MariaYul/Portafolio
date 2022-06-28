package com.telcel.gsrh.solicitudcurso.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private static final String PAGINA_PROHIBIDA = "accessdenied";
    private static final String PAGINA_INDEX = "index";
    private static final String LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";
    
    @RequestMapping(value="/index.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        Exception exception = (Exception) request.getSession().getAttribute(LAST_EXCEPTION);
        
        if (exception != null) {
            model.addObject("msgerror", exception.getMessage());
        }

        model.setViewName(PAGINA_INDEX);
        
        return model;
    }
    
    @RequestMapping(value = "/accessdenied.htm",method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        
        model.addObject("msgdenied", "USTED NO CUENTA CON PERMISOS PARA ACCESAR AL RECURSO SOLICITADO");
        model.setViewName(PAGINA_PROHIBIDA);
        
        return model;
    }
}
