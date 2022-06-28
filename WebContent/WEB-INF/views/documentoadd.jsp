<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <title>Registro de usuario</title>
  
  <script src="<c:url value="/js/usuarioutils.js"/>"/></script>
</head>
<body>
    <div class="row">
      <div class="col-md-12">
        <div class="well well-sm">
          *&nbsp;Campo requerido
        </div>
      </div>
    </div>
    
    <form:form action="../add.htm" id="frmAdd" method="POST" enctype="multipart/form-data" commandName="documento">
      <div class="row">
        <div class="col-md-4">
          <label for="clavePersonaModal">* </label>
        </div>
        <div class="col-md-8">
          <form:input cssClass="form-control" placeholder="Numero de empleado" path="persona.clave" id="clavePersonaModal"
            data-toggle="tooltip" data-placement="bottom" title="Clave de persona" maxlength="8" value="${clavePersona}"/>
        </div>
      </div>
          
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="rutaModal">* </label>
        </div>
        <div class="col-md-8">
          <form:input class="form-control" placeholder="Ruta" path="ruta" id="rutaModal"
            data-toggle="tooltip" data-placement="bottom" title="Ruta"/>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="archivoModal">* </label>
        </div>
        <div class="col-md-8">
          <form:input type="file" class="form-control" placeholder="Archivo" path="archivo" id="archivoModal"
            data-toggle="tooltip" data-placement="bottom" title="Archivo"/>
        </div>
      </div>
    </form:form>
</body>
</html>