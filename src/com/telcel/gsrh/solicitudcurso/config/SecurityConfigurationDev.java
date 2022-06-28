package com.telcel.gsrh.solicitudcurso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.telcel.gsrh.solicitudcurso.security.handler.ArchivoAccessDeniedHandler;
import com.telcel.gsrh.solicitudcurso.security.handler.ArchivoAuthenticationFailureHandler;
import com.telcel.gsrh.solicitudcurso.security.handler.ArchivoAuthenticationSuccessHandler;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {
        "com.telcel.gsrh.solicitudcurso.security.provider"
        , "com.telcel.gsrh.solicitudcurso.security.handler"    
})
@Profile("dev")
public class SecurityConfigurationDev extends WebSecurityConfigurerAdapter {
    
    @Autowired
    @Qualifier("autenticationBaseDatos")
    private AuthenticationProvider archivoAuthenticationProvider;
    
    @Autowired
    private ArchivoAccessDeniedHandler accessDeniedHandler;
    
    @Autowired
    private ArchivoAuthenticationSuccessHandler accessSuccess;
    
    @Autowired
    private ArchivoAuthenticationFailureHandler accessFailure;
    
    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder authentication){
        authentication.authenticationProvider(archivoAuthenticationProvider);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          .headers()
          .disable()
          .authorizeRequests()
          .antMatchers("/css/**", "/js/**", "/images/**", "/taglibs/**", "/login**", "/fonts/**"
                  , "/ArchivoDigitalService**", "/ArchivoDigitalService/ArchivoDigitalService?wsdl"
                  , "/ArchivoDigitalService/ArchivoDigitalService.wsdl", "/ArchivoDigitalService/ArchivoDigitalService_schema1.xsd").permitAll()
          //PARTE DE USUARIO
          .antMatchers("/usuario/main.htm").access("hasRole('ROL_USUARIO_CONSULTAR')")
          .antMatchers("/usuario/add.htm").access("hasRole('ROL_USUARIO_REGISTRAR')")
          .antMatchers("/usuario/edit.htm").access("hasRole('ROL_USUARIO_ACTUALIZAR')")
          //SOLICITUDES
          //.antMatchers("/solicitud/main.htm").access("hasRole('ROL_SOLICITUD_CONSULTAR')")
          //.antMatchers("/solicitud/add.htm").access("hasRole('ROL_SOLICITUD_REGISTRAR')")
          //.antMatchers("/solicitud/edit.htm").access("hasRole('ROL_SOLICITUD_ACTUALIZAR')")
          //GENERAL
          .anyRequest().authenticated()
          .and()
          .formLogin().loginPage("/index.htm").usernameParameter("numero").permitAll()
          .failureHandler(accessFailure)
          .successHandler(accessSuccess)
          .and()
          .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
}
    

