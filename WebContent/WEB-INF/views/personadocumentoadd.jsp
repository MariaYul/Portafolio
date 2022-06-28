<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Carga de Archivos</title>
    
    <script src="<c:url value="/js/personadetail.js"/>"/></script>
</head>
<body>

     <div class="row">
        <div class="col-md-12">
            <div class="well well-sm">
                  *&nbsp;Campo requerido
            </div>
        </div>
    </div>
    
    <form action="<c:url value="/documento/add.htm"/>" method="post" id="frmRegDoc" enctype="multipart/form-data" >
    
    <div class="row">
        <div class="col-md-4">
              <label for="numeroModal">Clave de persona</label>
        </div>
        <div class="col-md-8">
            <input type="text" class="form-control" placeholder="Clave de Persona" name="persona.clave" id="clavePersonaModal"
                data-toggle="tooltip" data-placement="bottom" title="Clave de persona" value="${clavePersona}" readonly="readonly"/>
        </div>
    </div>
    
    <div class="shortbreakline"></div>
    
    <c:if test="${not empty numeroEmpleado}">
        <div class="row">
            <div class="col-md-4">
                  <label for="numeroModal">N&uacute;mero de empleado</label>
            </div>
            <div class="col-md-8">
                <input type="text" class="form-control" placeholder="N&uacute;mero de empleado" name="persona.clave" id="numeroEmpleado"
                    data-toggle="tooltip" data-placement="bottom" title="N&uacute;mero de empleado" value="${numeroEmpleado}" readonly="readonly"/>
            </div>
        </div>
        
        <div class="shortbreakline"></div>
    </c:if>
    
    <div class="row">
        <div class="col-md-4">
              <label for="numeroModal">*&nbsp;Etapa Laboral</label>
        </div>
        <div class="col-md-8">
            <select class="form-control" name="claveFase" id="fase" onchange="mostrarTiposDocumento(${clavePersona},${usuarioRegistrado.area.clave});">
               <option value="-1">-- Seleccione Etapa --</option>
               <c:forEach items="${fases}" var="listafase">
                   <option value="${listafase.clave}">${listafase.nombre}</option>
               </c:forEach>
            </select>
        </div>
    </div>

    <div class="shortbreakline"></div>
    
    <div class="row">
        <div class="col-md-4">
              <label for="numeroModal">*&nbsp;Tipo de Documento</label>
        </div>
        <div class="col-md-8">
            <select class="form-control" name="tipoDocumento.clave" id="docto">
                <option value="-1">-- Seleccione Documento --</option>
            </select>
        </div>
    </div>

    <div class="shortbreakline"></div>
    
    <input type="hidden" name="usuario.numero" id="usuarioReg" value="${usuarioRegistrado.numero}" />
    
    <div class="row">
        <div class="col-md-4">
              <label for="numeroModal">*&nbsp;Archivo</label>
        </div>
        <div class="col-md-8">
            <input type="file" class="form-control" id="archivoDoc" name="archivo" accept="application/pdf" />
        </div>
    </div>
    
    
     <div class="shortbreakline"></div>
      
      <div id="validationmessage">
        <div class="row">
          <div class="col-md-12">
            <div class="alert alert-danger" role="alert">
              <strong>Error de validaci&oacute;n: </strong><span class="validationerror"></span>
            </div>
          </div>
        </div>
      </div>
    
    </form>
</body>
</html>