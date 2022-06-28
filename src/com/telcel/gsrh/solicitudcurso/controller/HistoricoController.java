package com.telcel.gsrh.solicitudcurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.telcel.gsrh.solicitudcurso.form.AreaBusqueda;
import com.telcel.gsrh.solicitudcurso.form.HistoricoBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Area;
import com.telcel.gsrh.solicitudcurso.model.Historico;
import com.telcel.gsrh.solicitudcurso.model.Usuario;
import com.telcel.gsrh.solicitudcurso.service.AreaService;
import com.telcel.gsrh.solicitudcurso.service.HistoricoService;

@Controller
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;
    
    private static final String PAGINA_ADD_AREA = "areaadd";
    private static final String PAGINA_EDIT_AREA = "areaedit";
    private static final String PAGINA_MAIN_AREA = "areamain";
    private static final String PAGINA_RS_AREA = "arears"; 
    private static final String PAGINA_REDIRECT_RS_AREAS = "redirect:/area/main.htm";
    private static final String MSG_AREA_REGISTRADA = "&Aacute;rea registrada correctamente";
    private static final String MSG_AREA_ACTUALIZADA = "&Aacute;rea actualizada correctamente";
    private static final String OPCION_ACTIVA_NAVBAR = "liarea";
    
    
    
    @RequestMapping(value="/main.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView main(@RequestParam(required=false) String info, @RequestParam(required=false) String error) {
        ModelAndView modelo = new ModelAndView();
        
        modelo.addObject("activeitemmenu", OPCION_ACTIVA_NAVBAR);
        
        if(info != null) {
            modelo.addObject("info", info);
        }
        
        if(error != null) {
            modelo.addObject("error", error);
        }
        
        modelo.setViewName(PAGINA_MAIN_AREA);
        
        return modelo;
    }
    
    @RequestMapping(value="/add.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView add(Area area) {
        ModelAndView modelo = new ModelAndView();
        
        //historicoService.register(area);
        
        modelo.addObject("info", MSG_AREA_REGISTRADA);
        modelo.setViewName(PAGINA_REDIRECT_RS_AREAS);
        
        return modelo;
    }
    
    
    @RequestMapping(value="/undecorated/loadNew.htm", method={RequestMethod.GET, RequestMethod.POST})
    public String loadNew() {
        return PAGINA_ADD_AREA;
    }
    
    @RequestMapping(value="/undecorated/loadHistorico.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView search(@RequestParam(required=false) Integer claveSolicitud) {
        ModelAndView modelo = new ModelAndView();
        
     /*   Solicitud solicitud = usuarioService.findById(numeroEmpleado);
        
        modelo.addObject(ATRIBUTO_MODELO_USUARIO, usuario);*/
        
        
               
        return modelo;
    }
}
