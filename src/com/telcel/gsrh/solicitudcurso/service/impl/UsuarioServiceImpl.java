package com.telcel.gsrh.solicitudcurso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.solicitudcurso.form.UsuarioBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Usuario;
import com.telcel.gsrh.solicitudcurso.repository.UsuarioRepository;
import com.telcel.gsrh.solicitudcurso.service.AbstractService;
import com.telcel.gsrh.solicitudcurso.service.UsuarioService;

@Service
public class UsuarioServiceImpl extends AbstractService<Usuario> implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    
    @Override
    protected GenericOperation<Usuario> getRepository() {
        return repository;
    }
    
    @Override
    public List<Usuario> searchUsuarios(UsuarioBusqueda parametro) {
        return repository.searchUsuarios(parametro);
    }
    
    @Override
    public Long getCantidadSearchUsuarios(UsuarioBusqueda parametro) {
        return repository.getCantidadSearchUsuarios(parametro);
    }
    
    @Override
    public List<Usuario> getDatosEmpleado(Integer numEmpl) {
        return repository.getDatosEmpleado(numEmpl);
    }

}
