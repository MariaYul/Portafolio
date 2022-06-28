package com.telcel.gsrh.solicitudcurso.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.telcel.gsrh.solicitudcurso.form.PersonaBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Documento;
import com.telcel.gsrh.solicitudcurso.model.Persona;
import com.telcel.gsrh.solicitudcurso.model.Region;
import com.telcel.gsrh.solicitudcurso.service.PersonaService;
import com.telcel.gsrh.solicitudcurso.service.RegionService;

@Controller
@RequestMapping("/persona")
public class PersonaController {

    private static final String PAGINA_ADD_DOCUMENTO = "documentoadd";
    private static final String PAGINA_DETAIL_PERSONA = "personadetail";
    private static final String PAGINA_MAIN_PERSONA = "personamain";
    private static final String PAGINA_RS_PERSONA = "personars";
    private static final String OPCION_ACTIVA_NAVBAR = "lipersona";
    private static final String PAGINA_REDIRECT_MAIN = "redirect:/persona/main.htm";
    
    private static final Logger LOGGER = Logger.getLogger(PersonaController.class);
    
    @Autowired
    private PersonaService personaService;
    
    @Autowired
    private RegionService regionService;
    
    @RequestMapping(value="/detalle/{clavePersona}.htm", method=RequestMethod.GET)
    public ModelAndView personaExpediente(@PathVariable Integer clavePersona) {
        ModelAndView modelo = new ModelAndView();
        Persona persona = personaService.findById(clavePersona);
        
        modelo.addObject("persona", persona);
        modelo.addObject("activeitemmenu", OPCION_ACTIVA_NAVBAR);
        
        modelo.setViewName(PAGINA_DETAIL_PERSONA);
        
        return modelo;
    }
    
    @RequestMapping(value="/main.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView main(@RequestParam(required=false) String info, @RequestParam(required=false) String error) {
        ModelAndView modelo = new ModelAndView();
        List<Region> regiones = regionService.findAll();
        
        modelo.addObject("regiones", regiones);
        modelo.addObject("activeitemmenu", OPCION_ACTIVA_NAVBAR);
        
        if(info != null) {
            modelo.addObject("info", info);
        }
        
        if(error != null) {
            modelo.addObject("error", error);
        }
        
        modelo.setViewName(PAGINA_MAIN_PERSONA);
        
        return modelo;
    }
    
    @RequestMapping(value="/undecorated/search.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView search(PersonaBusqueda busqueda) {
        ModelAndView modelo = new ModelAndView();
        
        List<Persona> personas = personaService.search(busqueda);
        Long cantidadTotalRegistros = personaService.getCantidadSearch(busqueda);
        
        modelo.addObject("personas", personas);
        modelo.addObject("count", cantidadTotalRegistros);
        modelo.addObject("offset", busqueda.getOffset());
        
        modelo.setViewName(PAGINA_RS_PERSONA);
        
        return modelo;
    }
    
    @RequestMapping(value="/undecorated/loadNew.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadNew(@RequestParam(required=false) Integer clavePersona) {
        ModelAndView modelo = new ModelAndView();
        
        modelo.addObject("documento", new Documento());
        modelo.addObject("clavePersona", clavePersona);
        modelo.setViewName(PAGINA_ADD_DOCUMENTO);
        
        return modelo;
    }
  
    
    @RequestMapping(value="/undecorated/loadDatosEmpleado.ajax", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Persona> loadDatosEmpleado(@RequestParam(defaultValue="0", required=false, value="clave") Integer clave,HttpSession session) {
    	System.out.println("ingresa"+clave);
    	PersonaBusqueda parametro = new PersonaBusqueda();
        parametro.setClave(clave);
        LOGGER.info("Documento en controlador: " + parametro);
        
        return personaService.getDatosEmpleado(parametro);
        
    }
}
