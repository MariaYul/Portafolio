package com.telcel.gsrh.solicitudcurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.telcel.gsrh.solicitudcurso.form.UsuarioBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Area;
import com.telcel.gsrh.solicitudcurso.model.Region;
import com.telcel.gsrh.solicitudcurso.model.Rol;
import com.telcel.gsrh.solicitudcurso.model.Usuario;
import com.telcel.gsrh.solicitudcurso.service.AreaService;
import com.telcel.gsrh.solicitudcurso.service.RegionService;
import com.telcel.gsrh.solicitudcurso.service.RolService;
import com.telcel.gsrh.solicitudcurso.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private AreaService areaService;
    
    @Autowired
    private RegionService regionService;
    
    @Autowired
    private RolService rolService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    private static final String ATRIBUTO_MODELO_AREAS = "areas";
    private static final String ATRIBUTO_MODELO_REGIONES = "regiones";
    private static final String ATRIBUTO_MODELO_USUARIO = "usuario";
    
    private static final String PAGINA_ADD_USUARIO = "usuarioadd";
    private static final String PAGINA_EDIT_USUARIO = "usuarioedit";
    private static final String PAGINA_MAIN_USUARIO = "usuariomain";
    private static final String PAGINA_ROLES_USUARIO = "usuarioroles";
    private static final String PAGINA_REDIRECT_RS_USUARIOS = "redirect:/usuario/main.htm";
    private static final String PAGINA_RS_USUARIO = "usuariors";
    
    private static final String MSG_USUARIO_REGISTRADO = "Usuario registrado correctamente";
    private static final String MSG_USUARIO_ACTUALIZADO = "Usuario actualizado correctamente";
    private static final String OPCION_ACTIVA_NAVBAR = "liusuario";
    
    @RequestMapping(value="/main.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView main(@RequestParam(required=false) String info, @RequestParam(required=false) String error) {
        ModelAndView modelo = new ModelAndView();
        List<Area> areas = areaService.findAll();
        List<Region> regiones = regionService.findAll();
        
        modelo.addObject(ATRIBUTO_MODELO_AREAS, areas);
        modelo.addObject(ATRIBUTO_MODELO_REGIONES, regiones);
        modelo.addObject("activeitemmenu", OPCION_ACTIVA_NAVBAR);
        
        if(info != null) {
            modelo.addObject("info", info);
        }
        
        if(error != null) {
            modelo.addObject("error", error);
        }
        
        modelo.setViewName(PAGINA_MAIN_USUARIO);
        
        return modelo;
    }
    
    @RequestMapping(value="/add.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView add(Usuario usuario) {
        ModelAndView modelo = new ModelAndView();
        
        usuarioService.register(usuario);
        
        modelo.addObject("info", MSG_USUARIO_REGISTRADO);
        modelo.setViewName(PAGINA_REDIRECT_RS_USUARIOS);
        
        return modelo;
    }
    
    @RequestMapping(value="/edit.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView edit(Usuario usuario) {
        ModelAndView modelo = new ModelAndView();
        
        usuarioService.update(usuario);
        
        modelo.addObject("info", MSG_USUARIO_ACTUALIZADO);
        modelo.setViewName(PAGINA_REDIRECT_RS_USUARIOS);
        
        return modelo;
    }
    
    @RequestMapping(value="/undecorated/loadEdit.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadEdit(@RequestParam(defaultValue="0", required=false) Integer numeroEmpleado) {
        ModelAndView modelo = new ModelAndView();
        Usuario usuario = usuarioService.findById(numeroEmpleado);
        List<Area> areas = areaService.findAll();
        List<Region> regiones = regionService.findAll();
        List<Rol> roles = rolService.findAll();
        
        modelo.addObject(ATRIBUTO_MODELO_USUARIO, usuario);
        modelo.addObject(ATRIBUTO_MODELO_AREAS, areas);
        modelo.addObject(ATRIBUTO_MODELO_REGIONES, regiones);
        modelo.addObject("roles", roles);
        
        modelo.setViewName(PAGINA_EDIT_USUARIO);
        
        return modelo;
    }
    
    @RequestMapping(value="/undecorated/loadNew.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadNew() {
        ModelAndView modelo = new ModelAndView();
        List<Area> areas = areaService.findAll();
        List<Region> regiones = regionService.findAll();
        List<Rol> roles = rolService.findAll();
        
        modelo.addObject(ATRIBUTO_MODELO_USUARIO, new Usuario());
        modelo.addObject(ATRIBUTO_MODELO_AREAS, areas);
        modelo.addObject(ATRIBUTO_MODELO_REGIONES, regiones);
        modelo.addObject("roles", roles);
        
        modelo.setViewName(PAGINA_ADD_USUARIO);
        
        return modelo;
    }
    
    @RequestMapping(value="/undecorated/loadRoles.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadRoles(@RequestParam(defaultValue="0", required=false) Integer numeroEmpleado) {
        ModelAndView modelo = new ModelAndView();
        Usuario usuario = usuarioService.findById(numeroEmpleado);
        
        modelo.addObject(ATRIBUTO_MODELO_USUARIO, usuario);
        
        modelo.setViewName(PAGINA_ROLES_USUARIO);
        
        return modelo;
    }
    
    @RequestMapping(value="/undecorated/search.htm", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView search(UsuarioBusqueda busqueda) {
        ModelAndView modelo = new ModelAndView();
        List<Usuario> usuarios = usuarioService.searchUsuarios(busqueda);
        Long cantidadTotalRegistros = usuarioService.getCantidadSearchUsuarios(busqueda);
        
        modelo.addObject("usuarios", usuarios);
        modelo.addObject("count", cantidadTotalRegistros);
        modelo.addObject("offset", busqueda.getOffset());
        
        modelo.setViewName(PAGINA_RS_USUARIO);
        
        return modelo;
    }
    

  
}
