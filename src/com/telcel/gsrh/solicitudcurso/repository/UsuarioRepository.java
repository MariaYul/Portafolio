package com.telcel.gsrh.solicitudcurso.repository;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.UsuarioBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Usuario;

public interface UsuarioRepository extends GenericOperation<Usuario> {

    /**
     * Consulta dinamicamente los usuarios de acuerdo a un rango
     * @param parametro Par�metros de b�squeda
     * @return List<Usuario> Usuarios de acuerdo a un rango
     */
    public List<Usuario> searchUsuarios(UsuarioBusqueda parametro);
    
    /**
     * Obtiene la cantidad de usuarios de acuerdo a los par�metros de b�squeda
     * @param parametro Par�metros de b�squeda
     * @return Cantidad de usuarios de acuerdo a los par�metros de b�squeda
     */
    public Long getCantidadSearchUsuarios(UsuarioBusqueda parametro);
    /**
     * Consulta dinamicamente los usuarios de acuerdo a un rango
     * @param parametro Par�metros de b�squeda
     * @return List<Usuario> Usuarios de acuerdo a un rango
     */
    public List<Usuario> getDatosEmpleado(Integer numEmpl);
}
