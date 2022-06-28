package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.form.TipoDocumentoBusqueda;
import com.telcel.gsrh.solicitudcurso.form.TipoDocumentoPorCargarCandidatoBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Area;
import com.telcel.gsrh.solicitudcurso.model.Moneda;
import com.telcel.gsrh.solicitudcurso.model.TipoDocumento;
import com.telcel.gsrh.solicitudcurso.repository.MonedaRepository;

@Repository
@SuppressWarnings("unchecked")
public class MonedaRepositoryImpl extends GenericHibernateRepositoryImpl<Moneda> implements MonedaRepository {
    
    public MonedaRepositoryImpl() {
        setClase(Moneda.class);
    }
    
    @Override
    public List<Moneda> searchTiposDocumento(TipoDocumentoBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setFirstResult(parametro.getOffset());
        consulta.setMaxResults(parametro.getMax());
        consulta.addOrder(Property.forName("nombre").asc());
        return consulta.list();
    }
    
    @Override
    public List<Moneda> getTiposDocumentoByLoadToCandidate(TipoDocumentoPorCargarCandidatoBusqueda parametro) {
        SQLQuery query = getTiposDocumentoByLoadToCandidateQuery(parametro);
        return query.list();
    }
    
    @Override
    public Long getCantidadSearchTiposDocumento(TipoDocumentoBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setProjection(Projections.rowCount());
        
        return (Long)consulta.uniqueResult();
    }
    
    private Criteria getSearchCriteria(TipoDocumentoBusqueda parametro) {
        Criteria consulta = getSession().createCriteria(TipoDocumento.class);

        if(!parametro.getNombre().isEmpty()) {
            consulta.add(Restrictions.like("nombre", "%" + parametro.getNombre() + "%"));
        }
        
        if(parametro.getMultiregistro() != DEFAULT_INT_VALUE) {
            consulta.add(Restrictions.eq("multiregistro", parametro.getMultiregistro()));
        }
        
        if(parametro.getEstatus() != DEFAULT_INT_VALUE) {
            consulta.add(Restrictions.eq("activo", parametro.getEstatus()));
        }
        
        if(parametro.getArea() != DEFAULT_INT_VALUE) {
            consulta.add(Restrictions.eq("area.clave", parametro.getArea()));
        }
        
        if(parametro.getFase() != DEFAULT_INT_VALUE) {
            consulta.add(Restrictions.eq("fase.clave", parametro.getFase()));
        }
        
        return consulta;
    }
    
    private SQLQuery getTiposDocumentoByLoadToCandidateQuery(TipoDocumentoPorCargarCandidatoBusqueda parametro) {
        StringBuilder stringQuery = new StringBuilder();
        stringQuery.append("SELECT CLAVE, NOMBRE, ACTIVO, ");
        stringQuery.append("  MULTIREGISTRO, AREA_CLAVE, FASE_CLAVE ");
        stringQuery.append("FROM ( ");
        stringQuery.append("    (SELECT TPDOC.CLAVE, TPDOC.NOMBRE, TPDOC.ACTIVO, ");
        stringQuery.append("      TPDOC.MULTIREGISTRO, TPDOC.AREA_CLAVE, TPDOC.FASE_CLAVE ");
        stringQuery.append("    FROM ");
        stringQuery.append("      ARCHDIG_TIPODOCUMENTO TPDOC ");
        stringQuery.append("    WHERE 1 = 1 ");
        stringQuery.append("      AND TPDOC.ACTIVO = 1 ");
        stringQuery.append("      AND TPDOC.MULTIREGISTRO = 1 ");
        
        //Regla solicitada por el área de Personal
        if(parametro.getArea() != Area.CLAVE_AREA_PERSONAL) {
        	stringQuery.append("      AND TPDOC.AREA_CLAVE = :area ");
        }
        
        stringQuery.append("      AND TPDOC.FASE_CLAVE = :fase ");
        stringQuery.append("    ) ");
        stringQuery.append("  UNION ALL ");
        stringQuery.append("    (SELECT TPDOC.CLAVE, TPDOC.NOMBRE, TPDOC.ACTIVO, ");
        stringQuery.append("      TPDOC.MULTIREGISTRO, TPDOC.AREA_CLAVE, TPDOC.FASE_CLAVE ");
        stringQuery.append("    FROM ");
        stringQuery.append("      ARCHDIG_TIPODOCUMENTO TPDOC ");
        stringQuery.append("    WHERE 1 = 1 ");
        stringQuery.append("      AND TPDOC.ACTIVO = 1 ");
        stringQuery.append("      AND TPDOC.MULTIREGISTRO = 0 ");
        
        //Regla solicitada por el área de Personal
        if(parametro.getArea() != Area.CLAVE_AREA_PERSONAL) {
        	stringQuery.append("      AND TPDOC.AREA_CLAVE = :area ");
        }
        
        stringQuery.append("      AND TPDOC.FASE_CLAVE = NVL(:fase, TPDOC.FASE_CLAVE) ");
        stringQuery.append("      AND TPDOC.CLAVE NOT IN( ");
        stringQuery.append("        SELECT DOC.TIPODOCUMENTO_CLAVE ");
        stringQuery.append("        FROM ARCHDIG_DOCUMENTO DOC ");
        stringQuery.append("        WHERE 1 = 1 ");
        stringQuery.append("          AND DOC.EMPLEADO_CLAVE = :candidato ");
        stringQuery.append("      ) ");
        stringQuery.append("    ) ");
        stringQuery.append(") ORDER BY NOMBRE ASC");
        
        SQLQuery query = getSession().createSQLQuery(stringQuery.toString());
        query.addEntity(TipoDocumento.class);
        
        //Regla solicitada por el área de Personal
        if(parametro.getArea() != Area.CLAVE_AREA_PERSONAL) {
        	query.setParameter("area", parametro.getArea());
        }
        
        query.setParameter("fase", parametro.getFase());
        query.setParameter("candidato", parametro.getCandidato());
        
        return query;
    }
}
