package com.telcel.gsrh.solicitudcurso.exception;

/**
 * Excepción generada en el tratamiento de documentos PDF
 * @author vcbg00k
 */
public class PdfException extends Exception {

    private static final long serialVersionUID = 3096368969851765758L;
    
    public PdfException(String message, Throwable cause) {
        super(message, cause);
    }
}
