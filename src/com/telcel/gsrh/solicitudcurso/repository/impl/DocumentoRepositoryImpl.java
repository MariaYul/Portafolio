package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.form.DocumentoBusqueda;
import com.telcel.gsrh.solicitudcurso.model.Documento;
import com.telcel.gsrh.solicitudcurso.repository.DocumentoRepository;

@Repository
public class DocumentoRepositoryImpl extends GenericHibernateRepositoryImpl<Documento> implements DocumentoRepository {

    private static final String PARAMETRO_PERSONA_CLAVE = "persona.clave";
    private static final String PARAMETRO_TIPO_DOCUMENTO = "tipoDocumento";
    private static final String PARAMETRO_TIPO_DOCUMENTO_NOMBRE = PARAMETRO_TIPO_DOCUMENTO.concat(".nombre");
    
    public DocumentoRepositoryImpl() {
        setClase(Documento.class);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Documento> search(DocumentoBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.addOrder(Property.forName(PARAMETRO_TIPO_DOCUMENTO_NOMBRE).asc());
        
        return consulta.list();
    }
    
    @Override
    public Documento searchByDelete(Documento documento) {
        Criteria consulta = getSearchCriteriaNuevo(documento);
        
        return (Documento)consulta.uniqueResult();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Documento> searchFlipping(DocumentoBusqueda parametro) {
        Criteria consulta = getSearchCriteriaFlipping(parametro);
        consulta.addOrder(Property.forName("tipoDocumento.fase.clave").asc());
        consulta.addOrder(Property.forName(PARAMETRO_TIPO_DOCUMENTO_NOMBRE).asc());
        
        return consulta.list();
    }
    
    @Override
    public Long getCantidadSearch(DocumentoBusqueda parametro) {
        Criteria consulta = getSearchCriteria(parametro);
        consulta.setProjection(Projections.rowCount());
        
        return (Long)consulta.uniqueResult();
    }
    
    @Override
    public Integer getCantidadSearchNuevo(Documento parametro) {
        Criteria consulta = getSearchCriteriaNuevo(parametro);
        consulta.setProjection(Projections.max("contador"));
        
        Integer resultado = (Integer)consulta.uniqueResult();
        
        return resultado == null ? 0 : resultado;
    }
    
    private Criteria getSearchCriteria(DocumentoBusqueda parametro) {
        Criteria consulta = getSession().createCriteria(Documento.class);
        
        if(parametro.getClavePersona() != DEFAULT_INT_VALUE) {
            consulta.add(Restrictions.eq(PARAMETRO_PERSONA_CLAVE, parametro.getClavePersona()));
        }
        
        if(parametro.getClaveFase() != DEFAULT_INT_VALUE) {
            consulta.createCriteria(PARAMETRO_TIPO_DOCUMENTO, PARAMETRO_TIPO_DOCUMENTO).createCriteria("fase", "fase");
            consulta.add(Restrictions.eq("fase.clave", parametro.getClaveFase()));
        }
        
        return consulta;
    }
    
    private Criteria getSearchCriteriaNuevo(Documento parametro) {
        Criteria consulta = getSession().createCriteria(Documento.class);
        
       /* if(parametro.getPersona().getClave() != DEFAULT_INT_VALUE) {
            consulta.add(Restrictions.eq(PARAMETRO_PERSONA_CLAVE, parametro.getPersona().getClave()));
        }*/
        
        if(parametro.getTipoDocumento().getClave() != DEFAULT_INT_VALUE) {
            consulta.add(Restrictions.eq("tipoDocumento.clave", parametro.getTipoDocumento().getClave()));
        }
        
        if(STRING_NULL_VALUE != parametro.getNombre()) {
            if((!parametro.getNombre().isEmpty())) {
                consulta.add(Restrictions.eq("nombre", parametro.getNombre()));
            }
        }
        
        return consulta;
    }
    
    private Criteria getSearchCriteriaFlipping(DocumentoBusqueda parametro) {
        Criteria consulta = getSession().createCriteria(Documento.class);
        
        if(parametro.getClavePersona() != DEFAULT_INT_VALUE) {
            consulta.add(Restrictions.eq(PARAMETRO_PERSONA_CLAVE, parametro.getClavePersona()));
            consulta.createCriteria(PARAMETRO_TIPO_DOCUMENTO, PARAMETRO_TIPO_DOCUMENTO).createCriteria("fase", "fase");
        }
        
        return consulta;
    }
}


