package com.telcel.gsrh.solicitudcurso.security.provider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.telcel.gsa.dswi.test.Ldap; 
import com.telcel.gsa.dswi.test.LdapPortProxy;
import com.telcel.gsa.dswi.test.LdapPortProxy.Descriptor;
import com.telcel.gsrh.solicitudcurso.exception.AsociacionLdapException;
import com.telcel.gsrh.solicitudcurso.exception.ConexionLdapException;
import com.telcel.gsrh.solicitudcurso.exception.DataBaseException;
import com.telcel.gsrh.solicitudcurso.exception.DesconexionLdapException;
import com.telcel.gsrh.solicitudcurso.exception.GeneralException;
import com.telcel.gsrh.solicitudcurso.exception.IntentosFallidosException;
import com.telcel.gsrh.solicitudcurso.model.Persona;
import com.telcel.gsrh.solicitudcurso.model.Rol;
import com.telcel.gsrh.solicitudcurso.service.PersonaService;
//import com.telcel.gsrh.solicitudcurso.service.UsuarioService;
import com.telcel.security.Crypt;

@Component("autenticationLDAP")
public class ArchivoAuthenticationProviderLDAP implements AuthenticationProvider{

//    @Autowired 
//    private UsuarioService usuarioService;
    
    @Autowired 
     private PersonaService personaService;
//    SOLICITUD DE CURSOS
//    idapp: 00450101014
//    claveapp: Acurs022

    private static final String IDAPLICACION = "00450101014";
    private static final String CLAVEAPLICACION = "Acurs022";

    private static final String MSG_CONEXION_LDAP = "Error de conexión LDAP. Por favor, intente mas tarde";//0
    private static final String MSG_CONTRASENNIA_INCORRECTA = "Contraseña incorrecta, verifique informacion proporcionada";//2
    private static final String MSG_USUARIO_NOEXISTE = "El usuario no existe dentro de la aplicacion favor de contactar al administrador del sistema";//3
    private static final String MSG_INTENTOS_FALLIDOS = "Número de intentos excedido para el password";//4
    private static final String MSG_USUARIO_BLOQUEADO = "Usuario inactivo o bloqueado por intentos fallidos"; //5
    private static final String MSG_DESCONEXION_LDAP = "Error de desconexión LDAP. Por favor, intente mas tarde.";//6
    private static final String MSG_DATABASE = "Error Base de Datos. Por favor, intente más tarde.";//8
    private static final String MSG_NO_ASOCIACION = "No existe aplicación asociada. Por favor, intente nuevamente \n"
      + "Su aplicación no tiene permisos para invocar el Web Service o la clave de la aplicación es incorrecta"; //9
    private static final int CERO = 0;
    private static final int ONE = 1, DOS = 2, TRES = 3, CUATRO = 4, CINCO = 5;
    private static final int SEIS = 6, OCHO = 8, NUEVE = 9;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String numero = authentication.getName();
        String password = authentication.getCredentials().toString();
//        System.out.println("numero:" + numero);
//        System.err.println("numero:" + numero);
        Integer numeroEmpleado = Integer.parseInt(numero);
//        System.out.println("Busca persona");
//        System.err.println("Busca persona");
//        Usuario usrDatos =  usuarioService.findById(numeroEmpleado);
        Persona usrDatos = personaService.findById(numeroEmpleado);
       
        
        if(usrDatos == null){
//        	 System.out.println("El usuario no existe");
//        	 System.err.println("El usuario no existe");
            throw new AuthenticationCredentialsNotFoundException("El usuario no existe");
        }
        
//        System.out.println("usrDatos:"+usrDatos.getNombre()+ " " + usrDatos.getApellidos().getApellidoPaterno() + " " + usrDatos.getApellidos().getApellidoMaterno());
//        System.out.println("activo:" + usrDatos.getActivo());
//        System.err.println("usrDatos:"+usrDatos.getNombre()+ " " + usrDatos.getApellidos().getApellidoPaterno() + " " + usrDatos.getApellidos().getApellidoMaterno());
//        System.err.println("activo:" + usrDatos.getActivo());
        String validador = autenticarAppAES(numero, password, IDAPLICACION, CLAVEAPLICACION);
        System.out.println("validador:"+validador);
//        System.out.println("=====" +validador.split("|")[0]);
//        System.err.println("validador:"+validador);
//        System.err.println("=====" +validador.split("|")[0]);
//        int valida = Integer.parseInt(validador.split("|")[0]);
        int valida = 1;
        if(validador.contains("|")) {
        	valida = Integer.parseInt(validador.split("|")[0]);
        }else {
        	valida = Integer.parseInt(validador);
        }
        return  direccionarUsuario(usrDatos, numero, password, valida);
    }
    
//    private Authentication direccionarUsuario(Usuario usrDatos, String numero, String password, int validador){
    	 private Authentication direccionarUsuario(Persona usrDatos, String numero, String password, int validador){
//    	System.out.println("direccionarUsuario");
//    	System.err.println("direccionarUsuario");
        if(validador != ONE){
//        	System.out.println("validador != ONE");
//        	System.err.println("validador != ONE");
            manejarExcepecionUno(validador);
        }

        return regresarAutoridad(usrDatos, numero, password);
    }
    
    public void manejarExcepecionUno(int validador){
       switch(validador){
          case CERO:
           throw new ConexionLdapException(MSG_CONEXION_LDAP);
          case DOS:
            throw new BadCredentialsException(MSG_CONTRASENNIA_INCORRECTA);
          case TRES:
            throw new AuthenticationCredentialsNotFoundException(MSG_USUARIO_NOEXISTE);
          case CUATRO:
            throw new IntentosFallidosException(MSG_INTENTOS_FALLIDOS);
          default:
              manejarExcepecionDos(validador);
       }
    }
    
    public void manejarExcepecionDos(int validador){
        switch(validador){
        case CINCO:
          throw new LockedException(MSG_USUARIO_BLOQUEADO);
        case SEIS:
          throw new DesconexionLdapException(MSG_DESCONEXION_LDAP);
        case OCHO:
          throw new DataBaseException(MSG_DATABASE);
        case NUEVE:
          throw new AsociacionLdapException(MSG_NO_ASOCIACION);
        default:
          throw new GeneralException("ERROR");
        }
    }
    
//    private Authentication regresarAutoridad(Usuario usrDatos, String numero, String password){
    	 private Authentication regresarAutoridad(Persona usrDatos, String numero, String password){
//    		 System.out.println("regresarAutoridad");
//    		 System.err.println("regresarAutoridad");
    	List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
         grantedAuths = 
                 buildUserAuthority(usrDatos.getRoles());
    
         return new UsernamePasswordAuthenticationToken(numero, password, grantedAuths);
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Rol> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (Rol userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getNombre()));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }

     private String autenticarAppAES (String numeroempleado, String password, String idapp, String claveapp) {
        String result = "";
        Crypt miCrypt = new Crypt(idapp);
        String contrasennia = miCrypt.encrypt(password.getBytes());
        LdapPortProxy ldapProxy = new LdapPortProxy();
        Descriptor descriptor = ldapProxy._getDescriptor();
//        System.out.println("contrasennia:"+contrasennia);
//        System.err.println("contrasennia:"+contrasennia);
//        //Prod
        descriptor.setEndpoint("http://serviciosidentidad.telcel.com:8000/ldapWebBrokerDes/ldapService");
        Ldap ldap = descriptor.getProxy();
//        System.out.println("numeroempleado:"+numeroempleado);
//        System.out.println("idapp:"+idapp);
//        System.out.println("claveapp:"+claveapp);
//        System.err.println("numeroempleado:"+numeroempleado);
//        System.err.println("idapp:"+idapp);
//        System.err.println("claveapp:"+claveapp);
        result = ldap.autenticarUsuarioAppAES(numeroempleado, contrasennia, idapp, claveapp);
//        System.out.println("result:"+result);
//        System.err.println("result:"+result);
//
        return result;
//    	 String respuesta = "";
// 		try {
// 			
//
// 			LdapPortProxy ldapProxy = new LdapPortProxy();
// 		   Descriptor descriptor = ldapProxy._getDescriptor(); 
// 		    	
// 		    //Se establece Endpoint a invocar
// 		    descriptor.setEndpoint("http://serviciosidentidad.telcel.com:8000/ldapWebBrokerDes/ldapService");
// 		    
// 		  
// 		Ldap ldap =  descriptor.getProxy();
//// 		String respuestaLdap = "";
// 			
// 		//Se usa la clase Crypt instanciandola con nuestro id de aplicacion **idapp
// 		Crypt miCrypt = new Crypt(idapp);
// 	    
// 	    //se encripta la contrasea
// 	    String contrasenniaEncriptada = miCrypt.encrypt(password.getBytes());
// 	    
// 	   respuesta = ldap.autenticarUsuarioAppAES(numeroempleado, contrasenniaEncriptada, idapp, claveapp);
// 	    
//// 	    respuesta=getRespuestaLdapValidada(respuestaLdap);
// 	    
// 		} catch (Exception e) {
//// 			LOGGER.error(e);
//// 			throw new AuthenticationServiceException(MSG_ERROR_SERVICIO_AUTENTICACION,e);
// 		}
// 	    return respuesta;
     }
}
