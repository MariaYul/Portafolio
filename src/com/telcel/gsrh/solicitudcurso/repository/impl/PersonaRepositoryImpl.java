package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.form.PersonaBusqueda;
import com.telcel.gsrh.solicitudcurso.model.CentroCostos;
import com.telcel.gsrh.solicitudcurso.model.Departamento;
import com.telcel.gsrh.solicitudcurso.model.Estados;
import com.telcel.gsrh.solicitudcurso.model.Estatus;
import com.telcel.gsrh.solicitudcurso.model.Localidad;
import com.telcel.gsrh.solicitudcurso.model.Moneda;
import com.telcel.gsrh.solicitudcurso.model.Participantes;
import com.telcel.gsrh.solicitudcurso.model.Persona;
import com.telcel.gsrh.solicitudcurso.model.Puesto;
import com.telcel.gsrh.solicitudcurso.model.Region;
import com.telcel.gsrh.solicitudcurso.model.Solicitud;
import com.telcel.gsrh.solicitudcurso.repository.PersonaRepository;

@Repository
public class PersonaRepositoryImpl extends GenericHibernateRepositoryImpl<Persona> implements PersonaRepository {

    public PersonaRepositoryImpl() {
        setClase(Persona.class);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Persona> search(PersonaBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setFirstResult(parametro.getOffset());
        consulta.setMaxResults(parametro.getMax());
        consulta.addOrder(Property.forName("nombre").asc());
        consulta.addOrder(Property.forName("apellidos.apellidoPaterno").asc());
        consulta.addOrder(Property.forName("apellidos.apellidoMaterno").asc());
        
        return consulta.list();
    }
    
    private Criteria getSearchCriteria(PersonaBusqueda parametro) {
        Criteria consulta = getSession().createCriteria(Persona.class);
        consulta = addIntegerCriteria("clave", Integer.valueOf(parametro.getClave()), consulta);
        return consulta;
        }
    

    @Override
    public Long getCantidadSearch(PersonaBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setProjection(Projections.rowCount());
        
        return (Long)consulta.uniqueResult();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Persona> getDatosEmpleado(PersonaBusqueda parametro) {
    	System.out.println("vloar:"+parametro.getClave()); 
    	SQLQuery query  = getSession().createSQLQuery(
    			"SELECT pr.curp AS curp, {pr.*},{re.*},{dept.*},{lo.*},{pu.*},{edo.*},{cc.*} " +
    					 " FROM CAP_EMPLEADO pr, CAP_REGION re,CAP_DEPARTAMENTO dept, CAP_LOCALIDADPAGO lo, CAP_PUESTO pu, CAP_ESTATUS edo, CAP_CCOSTOS cc " +
    					 " WHERE pr.REGION_CLAVE = re.clave "+
    					 " AND pr.DEPARTAMENTO_CLAVE=dept.clave "+
    					 " AND pr.LOCALIDADPAGO_CLAVE=lo.clave"+
    					 " AND pr.PUESTO_CLAVE=pu.clave"+
    					 " AND pr.COSTOS_CLAVE=cc.clave"+
    					 " AND pr.ESTATUS_CLAVE=edo.clave AND  pr.clave = :claveNumero");
    					query.addEntity( "pr", Persona.class);
    					query.addEntity( "re", Region.class);
    					query.addEntity( "dept", Departamento.class);
    					query.addEntity( "lo", Localidad.class);
    					query.addEntity( "pu", Puesto.class);
    					query.addEntity( "edo", Estatus.class);
    					query.addEntity( "cc", CentroCostos.class);
    					query.setParameter("claveNumero", parametro.getClave());
    					System.out.println("consilta"+query.list());    	 
    					return query.list();
    					}
    
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Persona> getDatosParticipantes(Integer  cve,Integer numRegis) {
    	System.out.println("vloar:"+cve); 
    	String strValor="";

		if(numRegis==4){
			strValor="<=";
		}else{
			strValor=">=";
		}
		
    	SQLQuery query  = getSession().createSQLQuery(
    			"SELECT pr.curp AS curp, {pr.*},{re.*},{dept.*},{lo.*},{pu.*},{edo.*},{soli.*},{tipo.*},{par.*},{estdo.*}" +
    					 " FROM CAP_EMPLEADO pr, CAP_REGION re,CAP_DEPARTAMENTO dept, CAP_LOCALIDADPAGO lo, CAP_PUESTO pu, CAP_ESTATUS edo,CAP_SOLICITUDES soli,CAP_TIPOMONEDA tipo,CAP_PARTIPNTES  par,CAP_ESTADO estdo " +
    					 " WHERE pr.REGION_CLAVE = re.clave "+
    					 " AND pr.DEPARTAMENTO_CLAVE=dept.clave "+
    					 " AND pr.LOCALIDADPAGO_CLAVE=lo.clave"+
    					 " AND pr.PUESTO_CLAVE=pu.clave"+
    					 " AND pr.ESTATUS_CLAVE=edo.clave " +    					 
    					 " AND soli.clave=par.clave " +
    					 " AND pr.clave=par.CLAVE_EMPLEADO " +//cambiar
    					 " AND soli.TIPO_MONEDA=tipo.CLAVE " +
    					 " AND soli.ID_STATUS=estdo.CLAVE " +
    					 " AND par.clave=:claveSoli AND par.orden"+strValor+":numRegis"+
    					 " AND par.id_status=1");    	
    					query.addEntity( "pr", Persona.class);
    					query.addEntity( "re", Region.class);
    					query.addEntity( "dept", Departamento.class);
    					query.addEntity( "lo", Localidad.class);
    					query.addEntity( "pu", Puesto.class);
    					query.addEntity( "edo", Estatus.class);
    					query.addEntity( "soli", Solicitud.class);
    					query.addEntity( "tipo", Moneda.class);
    					query.addEntity( "par", Participantes.class);
    					query.addEntity( "estdo", Estados.class);    					    					
    					query.setParameter("claveSoli", cve);
    					query.setParameter("numRegis", numRegis);
    					System.out.println("consilta"+query.list());
    					return query.list();
    					}
    
    
    @Override
    public int eliminarParticipante(Integer clave,Integer numRegis){
    	 String strValor="";
    		if(numRegis==4){
    			strValor="<="+numRegis;
    		}else{
    			strValor=">="+numRegis;
    		}
    		Query query  = getSession().createSQLQuery("UPDATE CAP_PARTIPNTES  set id_status=0 where clave=:cve and id_status=1 and orden"+strValor)
    				.addEntity(Solicitud.class);
    		query.setParameter("cve", clave);
        	//query.setParameter("numRegis", numRegis);
        	return query.executeUpdate();
        	}
    }
