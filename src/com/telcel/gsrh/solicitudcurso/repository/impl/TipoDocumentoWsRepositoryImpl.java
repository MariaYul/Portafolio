package com.telcel.gsrh.solicitudcurso.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.telcel.gsrh.solicitudcurso.model.TipoDocumentoWS;
import com.telcel.gsrh.solicitudcurso.repository.TipoDocumentoWsRepository;

@Repository("tipoDocumentoWsRepository")
public class TipoDocumentoWsRepositoryImpl extends GenericHibernateRepositoryImpl<TipoDocumentoWS> implements TipoDocumentoWsRepository {
    
    @Override
    @SuppressWarnings("unchecked")
    public List<TipoDocumentoWS> getTiposDocumentoByLoadToCandidate(int claveFase, int claveCandidato) {
        SQLQuery query = getTiposDocumentoByLoadToCandidateQuery(claveFase, claveCandidato);
        return query.list();
    }
    
    private SQLQuery getTiposDocumentoByLoadToCandidateQuery(int claveFase, int claveCandidato) {
        StringBuilder stringQuery = new StringBuilder();
        stringQuery.append("SELECT CLAVE, NOMBRE, ACTIVO, ");
        stringQuery.append("  MULTIREGISTRO ");
        stringQuery.append("FROM ( ");
        stringQuery.append("    (SELECT TPDOC.CLAVE, TPDOC.NOMBRE, TPDOC.ACTIVO, ");
        stringQuery.append("      TPDOC.MULTIREGISTRO ");
        stringQuery.append("    FROM ");
        stringQuery.append("      ARCHDIG_TIPODOCUMENTO TPDOC ");
        stringQuery.append("    WHERE 1 = 1 ");
        stringQuery.append("      AND TPDOC.FASE_CLAVE = :fase ");
        stringQuery.append("      AND TPDOC.ACTIVO = 1 ");
        stringQuery.append("      AND TPDOC.MULTIREGISTRO = 1 ");
        stringQuery.append("    ) ");
        stringQuery.append("  UNION ALL ");
        stringQuery.append("    (SELECT TPDOC.CLAVE, TPDOC.NOMBRE, TPDOC.ACTIVO, ");
        stringQuery.append("      TPDOC.MULTIREGISTRO ");
        stringQuery.append("    FROM ");
        stringQuery.append("      ARCHDIG_TIPODOCUMENTO TPDOC ");
        stringQuery.append("    WHERE 1 = 1 ");
        stringQuery.append("      AND TPDOC.FASE_CLAVE = NVL(:fase, TPDOC.FASE_CLAVE) ");
        stringQuery.append("      AND TPDOC.ACTIVO = 1 ");
        stringQuery.append("      AND TPDOC.MULTIREGISTRO = 0 ");
        stringQuery.append("      AND TPDOC.CLAVE NOT IN( ");
        stringQuery.append("        SELECT DOC.TIPODOCUMENTO_CLAVE ");
        stringQuery.append("        FROM ARCHDIG_DOCUMENTO DOC ");
        stringQuery.append("        WHERE 1 = 1 ");
        stringQuery.append("          AND DOC.EMPLEADO_CLAVE = :candidato ");
        stringQuery.append("      ) ");
        stringQuery.append("    ) ");
        stringQuery.append(") ORDER BY NOMBRE ASC");
        
        SQLQuery query = getSession().createSQLQuery(stringQuery.toString());
        query.addEntity(TipoDocumentoWS.class);
        query.setParameter("fase", claveFase);
        query.setParameter("candidato", claveCandidato);
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
        return query;
    }
}
