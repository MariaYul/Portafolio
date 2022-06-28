package com.telcel.gsrh.solicitudcurso.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
 
@Component
public class ArchivoAccessDeniedHandler implements AccessDeniedHandler {
 
    private String errorPage;
 
    public ArchivoAccessDeniedHandler() {
    
    }
 
    public ArchivoAccessDeniedHandler(String errorPage) {
        this.errorPage = errorPage;
    }
 
    public String getErrorPage() {
        return errorPage;
    }
 
    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
 
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendRedirect(request.getContextPath()+"/accessdenied.htm");
    }
}