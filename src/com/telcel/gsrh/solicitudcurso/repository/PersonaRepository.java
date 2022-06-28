package com.telcel.gsrh.solicitudcurso.repository;

import java.util.List;

import com.telcel.gsrh.solicitudcurso.form.PersonaBusqueda;
import com.telcel.gsrh.solicitudcurso.generic.GenericOperation;
import com.telcel.gsrh.solicitudcurso.model.Persona;

public interface PersonaRepository extends GenericOperation<Persona> {

    /**
     * Consulta dinamicamente las personas de acuerdo a un rango
     * @param parametro Parámetros de búsqueda
     * @return List<Area> personas de acuerdo a un rango
     */
    public List<Persona> search(PersonaBusqueda parametro);    
    /**
     * Obtiene la cantidad de Personas de acuerdo a los parámetros de búsqueda
     * @param parametro Parámetros de búsqueda
     * @return Cantidad de Personas de acuerdo a los parámetros de búsqueda
     */
    public Long getCantidadSearch(PersonaBusqueda parametro);
    /**
     * Consulta dinamicamente las personas de acuerdo a un rango
     * @param parametro Parámetros de búsqueda
     * @return List<Persona> personas de acuerdo a un rango
     */
    public List<Persona> getDatosEmpleado(PersonaBusqueda parametro);
    /**
     * Consulta dinamicamente las participantes de acuerdo a un rango
     * @param Parametros Clave de la Solicitud
     * @param Parametros numero de Registros consultados
     * @return List<Persona> personas de acuerdo a un rango
     */
    public List<Persona> getDatosParticipantes(Integer cve,Integer numRegis);
    /**
     * Realiza la baja de participantes al editar la solicitud
     * @param Parametros Clave de la Solicitud
     * @param Parametros numero de Registros consultados
     * @return Actualizacion de Baja de participantes
     */
    public int eliminarParticipante(Integer cve,Integer numRegi);
    
}
