package com.telcel.gsrh.solicitudcurso.generic;

import java.util.List;

public interface GenericOperation<T> {
    
    /**
     * Encuentra un registro por su identificador
     * @param id Identificador del registro
     * @return Detalle del registro
     */
    public T findById(final Integer id);
    
    
    /**
     * Encuentra todos los registros de una entidad
     * @return Todos los registros de una entidad
     */
    public List<T> findAll();
    
    /**
     * Registra un nuevo registro en la entidad
     * @param item Nuevo elemento
     */
    public void register(T item);
    
    /**
     * Actualiza un registro en una entidad
     * @param item Elemento a actualizar
     */
    public void update(T item);
    
    /**
     * Elimina un registro en una entidad
     * @param item Elemento a eliminar
     */
    public void delete(T item);
}
