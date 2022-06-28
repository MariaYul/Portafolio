<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edicion de Archivo</title>
     <script src="<c:url value="/js/personadocumento.js"/>"></script>
</head>
<body>
    <c:choose>
      <c:when test="${not empty documento}">
        <div class="row">
          <div class="col-md-12">
            <div class="well well-sm">
              *&nbsp;Campo requerido
            </div>
          </div>
        </div>
        
        <form:form action="../../documento/edit.htm" method="post" id="frmEditDocumento" commandName="documento" enctype="multipart/form-data">
          <div class="row">
            <div class="col-md-4">
              <label for="numeroModal">Clave de persona</label>
            </div>
            <div class="col-md-8">
              <form:input type="text" cssClass="form-control" placeholder="Clave de Persona" path="persona.clave" id="clavePersonaModal"
                data-toggle="tooltip" data-placement="bottom" title="Clave de persona" value="${documento.persona.clave}" readonly="true"/>
            </div>
          </div>
          
          <div class="shortbreakline"></div>
          
          <div class="row">
            <div class="col-md-4">
              <label for="numeroModal">N&uacute;mero de empleado</label>
            </div>
            <div class="col-md-8">
              <form:input  cssClass="form-control" placeholder="N&uacute;mero de empleado" path="persona.numero" id="numeroEmpleado"
                    data-toggle="tooltip" data-placement="bottom" title="N&uacute;mero de empleado" value="${documento.persona.numero}" readonly="true"/>
            </div>
          </div>
          
          <div class="shortbreakline"></div>
          
          <div class="row">
            <div class="col-md-4">
              <label for="numeroModal">Tipo de Documento</label>
            </div>
            <div class="col-md-8">
              <form:input type="text" cssClass="form-control" placeholder="Tipo Documento" path="tipoDocumento.nombre" id="docto"
                data-toggle="tooltip" data-placement="bottom" title="Tipo Documento" value="${documento.tipoDocumento.nombre}" readonly="true"/>
            </div>
          </div>
          
          <div class="shortbreakline"></div>
          
          <div class="row">
            <div class="col-md-4">
              <label for="numeroModal">*&nbsp;Archivo</label>
            </div>
            <div class="col-md-8">
              <form:input type="file" class="form-control" id="archivoDoc" path="archivo" />
            </div>
          </div>
          
          <form:hidden path="clave" value="${documento.clave}" />
          <form:hidden path="tipoDocumento.clave" value="${documento.tipoDocumento.clave}" />
          <form:hidden path="nombre" value="${documento.nombre}" />
          <form:hidden path="ruta" value="${documento.ruta}" />
          <form:hidden path="contador" value="${documento.contador}" />
          
          <div id="validationmessage">
            <div class="row">
              <div class="col-md-12">
                <div class="alert alert-danger" role="alert">
                 <strong>Error de validaci&oacute;n: </strong><span class="validationerror"></span>
                </div>
              </div>
            </div>
          </div>
          
          <div class="shortbreakline"></div>
          
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
            <button type="button" class="btn btn-primary" id="btnNuevoDocumentoEditar" onclick="editarDocumento();">Actualizar</button>
          </div>
        </form:form>
      </c:when>
      <c:otherwise>
        <c:if test="${not empty error}">
          <div class="row">
            <div class="col-md-12">
              <div class="alert alert-danger" role="alert" id="msgerror">
                <span class="glyphicon glyphicon-info-sign"></span>&nbsp;<strong>Error: </strong><c:out value="${error}" escapeXml="false"/>
              </div>
            </div>
          </div>
        </c:if>
      </c:otherwise>
    </c:choose>
</body>
</html>