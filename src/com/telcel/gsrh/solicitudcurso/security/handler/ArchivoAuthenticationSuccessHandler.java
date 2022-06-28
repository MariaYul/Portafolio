package com.telcel.gsrh.solicitudcurso.security.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.telcel.gsrh.solicitudcurso.model.Persona;
import com.telcel.gsrh.solicitudcurso.service.PersonaService;


@Component
public class ArchivoAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {
    
    @Autowired
    private PersonaService personaService;
    
    private static final String INDEX = "index.htm";
    private static final String LOGOUT = "?logout";
    private static final String HOME = "/inicio.htm";
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        HttpSession session = request.getSession(); 
        System.out.println("onAuthenticationSuccess");
        Persona persona=preparaUsuario(authentication);
        session.setAttribute("usuarioRegistrado", persona);
        System.out.println("persona:"+ persona.getNombre());
        if (savedRequest == null) {
        	System.out.println("savedRequest == null");
            response.sendRedirect(request.getContextPath() + HOME);
        } else {
        	System.out.println("else");
            if(isIndex(savedRequest.getRedirectUrl(), request)) {
            	System.out.println("HOME");
                response.sendRedirect(request.getContextPath() + HOME);
            } else {
            	System.out.println("REDIRECT");
                response.sendRedirect(savedRequest.getRedirectUrl());
            }
        }
    }
    
    public String getUrlApplication(HttpServletRequest request) {
        StringBuilder url = new StringBuilder();
        url.append(request.getScheme()).append("://");
        url.append(request.getServerName()).append(":");
        url.append(request.getServerPort());
        url.append(request.getServletContext().getContextPath());
        return url.toString();
    }
    
    private boolean isIndex(String url, HttpServletRequest request) {
        
        boolean bandera = true;
        if(url.equals(getUrlApplication(request).concat("/"))) {
            bandera = true;
        } else if(url.equals(getUrlApplication(request).concat("/") + INDEX)) {
            bandera = true;
        } else if(url.equals(getUrlApplication(request).concat("/") + LOGOUT)) {
            bandera = true;
        } else if(url.equals(getUrlApplication(request).concat("/") + INDEX.concat(LOGOUT))) {
            bandera = true;
        } else {
            bandera = false;
        }
        return bandera;
    }
    
    private Persona preparaUsuario(Authentication authentication){
        Integer numero = Integer.parseInt(authentication.getName());
        System.out.println("clave"+authentication.getName());
        return personaService.findById(numero);
    }
}
