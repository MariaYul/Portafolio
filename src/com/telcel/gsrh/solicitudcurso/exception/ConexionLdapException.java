package com.telcel.gsrh.solicitudcurso.exception;

import org.springframework.security.core.AuthenticationException;

public class ConexionLdapException extends AuthenticationException{
    
    private static final long serialVersionUID = 1L;

    /**
    * Constructs a <code>ConexionLdapException</code> with the specified
    * message.
    *
    * @param msg the detail message
    */
   public ConexionLdapException(String msg) {
       super(msg);
   }

   /**
    * Constructs a <code>ConexionLdapException</code> with the specified
    * message and root cause.
    *
    * @param msg the detail message
    * @param t root cause
    */
   public ConexionLdapException(String msg, Throwable t) {
       super(msg, t);
   }
   
}
