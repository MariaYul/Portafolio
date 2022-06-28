package com.telcel.gsrh.solicitudcurso.security.provider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.telcel.gsrh.solicitudcurso.model.Persona;
import com.telcel.gsrh.solicitudcurso.model.Rol;
import com.telcel.gsrh.solicitudcurso.model.Usuario;
import com.telcel.gsrh.solicitudcurso.service.PersonaService;
import com.telcel.gsrh.solicitudcurso.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component("autenticationBaseDatos")
public class ArchivoAuthenticationProvider implements AuthenticationProvider {
    
    @Autowired 
   // private UsuarioService usuarioService;
    private PersonaService personaService;
    
    private static final String MSG_USARIO_NOEXISTE = "El usuario no existe dentro de la aplicacion favor de contactar al administrador del sistema";
    private static final String MSG_USARIO_NOACTIVO = "El usuario no se encuentra activo por lo que no puede ingresar al sistema, favor de contactar al administrador";
    private static final String MSG_USARIO_BLOQUEADO = "El acceso del usuario está bloqueado, favor de contactar al administrador";
    private static final String MSG_CONTRASENNIA_INCORRECTA = "Contraseña incorrecta, verifique informacion proporcionada";
    private static final int TRES = 3;
    private static final int ZERO = 0;
    
     
    @Override
    public Authentication authenticate(Authentication authentication) {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        Integer numeroEmpleado = Integer.parseInt(name);
       //Usuario usrDatos =  usuarioService.findById(numeroEmpleado);
        Persona usrDatos =  personaService.findById(numeroEmpleado);
        
        if(usrDatos == null){
            throw new AuthenticationCredentialsNotFoundException(MSG_USARIO_NOEXISTE);
        }
        
        if (!usrDatos.getContrasennia().equals(password)) {
            throw new BadCredentialsException(MSG_CONTRASENNIA_INCORRECTA);
          }else 
          if (usrDatos.getBloqueo() == TRES){
              throw new LockedException(MSG_USARIO_BLOQUEADO);
          }else if (usrDatos.getActivo() == ZERO){
              throw new DisabledException(MSG_USARIO_NOACTIVO);
          }else{
              List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
              grantedAuths = buildUserAuthority(usrDatos.getRoles());
              return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
        }
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    public List<GrantedAuthority> buildUserAuthority(Set<Rol> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
 
        for (Rol userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getNombre()));
        }
 
        return new ArrayList<GrantedAuthority>(setAuths);
    }
}
    
