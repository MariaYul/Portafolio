package com.telcel.gsrh.solicitudcurso.exception;

import org.springframework.security.core.AuthenticationException;

public class AsociacionLdapException extends AuthenticationException{

    private static final long serialVersionUID = 1L;
     
    /**
  * Constructs a <code>AsociacionLdapException</code> with the specified
  * message.
  *
  * @param msg the detail message
  */
    public AsociacionLdapException(String msg) {
        super(msg);
    }

 /**
  * Constructs a <code>AsociacionLdapException</code> with the specified
  * message and root cause.
  *
  * @param msg the detail message
  * @param t root cause
  */
    public AsociacionLdapException(String msg, Throwable t) {
        super(msg, t);
    }
}
