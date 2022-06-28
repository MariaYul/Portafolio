package com.telcel.gsrh.solicitudcurso.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SiteMeshFilter extends ConfigurableSiteMeshFilter {
    
    private static final String LOGGEDIN_DECORATOR = "/WEB-INF/decorators/loggedinDecorator.jsp";
    
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/WEB-INF/decorators/indexDecorator.jsp")
               .addDecoratorPath("/area*", LOGGEDIN_DECORATOR)
               .addDecoratorPath("/documento*", LOGGEDIN_DECORATOR)
               .addDecoratorPath("/home*", LOGGEDIN_DECORATOR)
               .addDecoratorPath("/persona*", LOGGEDIN_DECORATOR)
               .addDecoratorPath("/tipodocumento*", LOGGEDIN_DECORATOR)
               .addDecoratorPath("/usuario*", LOGGEDIN_DECORATOR)
               .addDecoratorPath("/solicitud*", LOGGEDIN_DECORATOR)
               .addDecoratorPath("/inicio**", LOGGEDIN_DECORATOR)
               .addDecoratorPath("/accessdenied*", "/WEB-INF/decorators/indexDecorator.jsp")
               .addExcludedPath("/area/undecorated/*")
               .addExcludedPath("/departamento/undecorated/*")
               .addExcludedPath("/documento/undecorated/*")
               .addExcludedPath("/localidad/undecorated/*")
               .addExcludedPath("/persona/undecorated/*")
               .addExcludedPath("/puesto/undecorated/*")
               .addExcludedPath("/tipodocumento/undecorated/*")
               .addExcludedPath("/usuario/undecorated/*")
               .addExcludedPath("/historico/undecorated/*")
               .addExcludedPath("/solicitud/undecorated/*")
               .addExcludedPath("/error*")
               .addExcludedPath("/404**")
               .addExcludedPath("/expedienteflipbook**");
               
    }
}
