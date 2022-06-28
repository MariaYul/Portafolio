package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.form.TipoDocumentoPorCargarCandidatoBusqueda;
import com.telcel.gsrh.solicitudcurso.form.UsuarioBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Area;
import com.telcel.gsrh.solicitudcurso.model.TipoDocumento;
import com.telcel.gsrh.solicitudcurso.model.Usuario;
import com.telcel.gsrh.solicitudcurso.repository.UsuarioRepository;

@Repository
public class UsuarioRepositoryImpl extends GenericHibernateRepositoryImpl<Usuario> implements UsuarioRepository {
    
    public UsuarioRepositoryImpl() {
        setClase(Usuario.class);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Usuario> searchUsuarios(UsuarioBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setFirstResult(parametro.getOffset());
        consulta.setMaxResults(parametro.getMax());
        consulta.addOrder(Property.forName("nombre").asc());
        
        return consulta.list();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Usuario> getDatosEmpleado(Integer numEmpl) {
    	 SQLQuery query = getDatosEmpleadoConsu(numEmpl);
         return query.list();
    }

    
    @Override
    public Long getCantidadSearchUsuarios(UsuarioBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setProjection(Projections.rowCount());
        
        return (Long)consulta.uniqueResult();
    }
    
    private Criteria getSearchCriteria(UsuarioBusqueda parametro) {
        Criteria consulta = getSession().createCriteria(Usuario.class);
        
        if(!parametro.getNumeroEmpleado().isEmpty()) {
            consulta.add(Restrictions.eq("numero", new Integer(parametro.getNumeroEmpleado())));
        }
        
        consulta = addStringLikeCriteria("nombre", parametro.getNombre(), consulta);
        consulta = addStringLikeCriteria("apellidos.apellidoPaterno", parametro.getApellidoPaterno(), consulta);
        consulta = addStringLikeCriteria("apellidos.apellidoMaterno", parametro.getApellidoMaterno(), consulta);
        
        consulta = addIntegerCriteria("activo", parametro.getEstatus(), consulta);
        consulta = addIntegerCriteria("area.clave", parametro.getClaveArea(), consulta);
        consulta = addIntegerCriteria("region.clave", parametro.getClaveRegion(), consulta);
        
        return consulta;
    }
    
    private SQLQuery getDatosEmpleadoConsu(Integer numero) {
        StringBuilder stringQuery = new StringBuilder();
        stringQuery.append("SELECT NOMBRE, ,, ");
        stringQuery.append("FROM ");
        
        
         
        SQLQuery query = getSession().createSQLQuery(stringQuery.toString());
        query.addEntity(Usuario.class);
                
        query.setParameter("fase",numero);
        
        
        return query;
    }
    
   

}
