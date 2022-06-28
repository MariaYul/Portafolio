package com.telcel.gsrh.solicitudcurso.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class ArchivoAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    
    private static final String PAGE_LOGIN = "/index.htm";
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        super.setDefaultFailureUrl(PAGE_LOGIN);
        super.onAuthenticationFailure(request, response, exception);
    }
}
