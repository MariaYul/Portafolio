package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.form.SolicitudBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Solicitud;
import com.telcel.gsrh.solicitudcurso.repository.SolicitudRepository;


@Repository
public class SolicitudRepositoryImpl extends GenericHibernateRepositoryImpl<Solicitud> implements SolicitudRepository {
    
    public SolicitudRepositoryImpl() {
        setClase(Solicitud.class);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Solicitud> searchSolicitudes(SolicitudBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setFirstResult(parametro.getOffset());
        consulta.setMaxResults(parametro.getMax());
        consulta.addOrder(Property.forName("clave").desc());        
        return consulta.list();
    }
    
    @Override
    public Long getCantidadSearchSolicitudes(SolicitudBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setProjection(Projections.rowCount());
        return (Long)consulta.uniqueResult();
    }
    
    private Criteria getSearchCriteria(SolicitudBusqueda parametro) {
    	Criteria consulta = getSession().createCriteria(Solicitud.class);
    	consulta = addIntegerCriteria("id_status", 1, consulta);//Todas las solicitudes Activas
    	
    	if(parametro.getClave()!=null){
    		consulta = addIntegerCriteria("clave", parametro.getClave(), consulta);
    	}
        
        if(parametro.getStrDepartamento()!=null && parametro.getStrDepartamento().equals("DEPTO CAPACITACION TECNICA E INFORMATICA") || parametro.getStrDepartamento().equals("GCIA CAPACITACION Y DES")){
        	//definir regla
        }else{
        	if(parametro.getStrDescPuesto().startsWith("GERE") || parametro.getStrDescPuesto().startsWith("LIDER")  ){
        		consulta = addStringLikeCriteria("cve_depto", parametro.getStrCveDepto().substring(0, parametro.getStrCveDepto().length()-2).concat("%"), consulta);
        	}else if(parametro.getStrDescPuesto().startsWith("SUBDIR") || parametro.getStrDescPuesto().startsWith("COORDI")  ){
        		consulta = addStringLikeCriteria("cve_depto", parametro.getStrCveDepto().substring(0, parametro.getStrCveDepto().length()-4), consulta);
        	}else if(parametro.getStrDescPuesto().startsWith("DIR")){
        		consulta = addStringLikeCriteria("cve_depto", parametro.getStrCveDepto().substring(0, parametro.getStrCveDepto().length()-5).concat("%"), consulta);
        	}else{
        		  consulta = addIntegerCriteria("persona.clave", parametro.getClaveEmpleado(), consulta);
        		  //Visualiza sus propias solicitudes
        	}
        }
        
        System.out.println("consulta :"+consulta);
        return consulta;
    }
    
    
    @Override
    public int eliminarSolicitud(Integer clave){
    	Query query  = getSession().createSQLQuery("UPDATE CAP_SOLICITUDES  set id_status=0 where clave=:cve")
    			.addEntity(Solicitud.class).
    			setParameter("cve", clave);
    	return query.executeUpdate();
    	}
    
    
    @Override
    public int updateEdoSolicitud(Integer claveSolicitud,Integer id_edoSol){
    	Query query  = getSession().createSQLQuery("UPDATE CAP_SOLICITUDES  set id_edosol=:idEdoSol where clave=:cve")
    			.addEntity(Solicitud.class).
                setParameter("cve", claveSolicitud).
                setParameter("idEdoSol", id_edoSol);
    	return query.executeUpdate();
    	}

	
	
    }
