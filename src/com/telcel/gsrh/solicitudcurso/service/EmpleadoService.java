package com.telcel.gsrh.solicitudcurso.service;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.UsuarioBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Persona;

public interface EmpleadoService extends GenericOperation<Persona> {

    /**
     * Consulta dinamicamente los usuarios de acuerdo a un rango
     * @param parametro Parámetros de búsqueda
     * @return List<Usuario> Usuarios de acuerdo a un rango
     */
    public List<Persona> searchUsuarios(UsuarioBusqueda parametro);
    
    /**
     * Obtiene la cantidad de usuarios de acuerdo a los parámetros de búsqueda
     * @param parametro Parámetros de búsqueda
     * @return Cantidad de usuarios de acuerdo a los parámetros de búsqueda
     */
    public Long getCantidadSearchUsuarios(UsuarioBusqueda parametro);
    
    /**
     * Consulta dinamicamente los usuarios de acuerdo a un rango
     * @param parametro Parámetros de búsqueda
     * @return List<Usuario> Usuarios de acuerdo a un rango
     */
    public List<Persona> getDatosEmpleado(Integer numEmpl);
}
