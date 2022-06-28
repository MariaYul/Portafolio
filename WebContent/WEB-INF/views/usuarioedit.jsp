<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edicion de usuario</title>
  
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
    
    <form:form action="edit.htm" method="post" id="frmEdit" commandName="usuario">
      <div class="row">
        <div class="col-md-4">
          <label for="numeroModal">* N&uacute;mero de empleado</label>
        </div>
        <div class="col-md-8">
          <form:input cssClass="form-control" placeholder="N&uacute;mero de empleado" path="numero" id="numeroModal"
            data-toggle="tooltip" data-placement="bottom" readonly="true" maxlength="8"/>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="nombreModal">* Nombre</label>
        </div>
        <div class="col-md-8">
          <form:input cssClass="form-control" placeholder="Nombre del empleado" path="nombre" id="nombreModal"
              data-toggle="tooltip" data-placement="bottom" title="Nombre del empleado" maxlength="150"/>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="apellidoPaternoModal">* Apellido Paterno</label>
        </div>
        <div class="col-md-8">
          <form:input cssClass="form-control" placeholder="Apellido Paterno" path="apellidos.apellidoPaterno" id="apellidoPaternoModal"
              data-toggle="tooltip" data-placement="bottom" title="Apellido Paterno" maxlength="150"/>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="apellidoMaternoModal">* Apellido Materno</label>
        </div>
        <div class="col-md-8">
          <form:input cssClass="form-control" placeholder="Apellido Materno" path="apellidos.apellidoMaterno" id="apellidoMaternoModal"
              data-toggle="tooltip" data-placement="bottom" title="Apellido Materno" maxlength="150"/>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="areaModal">* &Aacute;rea</label>
        </div>
        <div class="col-md-8">
          <form:select cssClass="form-control" path="area.clave" id="areaModal">
            <form:option value="-1">-- Seleccione --</form:option>
            <form:options items="${areas}" itemLabel="nombre" itemValue="clave"/>
          </form:select>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="regionModal">* Regi&oacute;n</label>
        </div>
        <div class="col-md-8">
          <form:select cssClass="form-control" path="region.clave" id="regionModal">
            <form:option value="-1">-- Seleccione --</form:option>
            <form:options items="${regiones}" itemLabel="nombre" itemValue="clave"/>
          </form:select>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="activoModal">* Estatus</label>
        </div>
        <div class="col-md-8">
          <form:select cssClass="form-control" path="activo" id="activoModal">
            <c:choose>
              <c:when test="${tipoDocumento.activo == 1}">
                <form:option value="1" selected="selected">Activo</form:option>
              </c:when>
              <c:otherwise>
                <form:option value="1">Activo</form:option>
              </c:otherwise>
            </c:choose>
            
            <c:choose>
              <c:when test="${tipoDocumento.activo == 0}">
                <form:option value="0" selected="selected">Inactivo</form:option>
              </c:when>
              <c:otherwise>
                <form:option value="0">Inactivo</form:option>
              </c:otherwise>
            </c:choose>
          </form:select>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="contrasenniaModal">* Contrase&ntilde;a</label>
        </div>
        <div class="col-md-8">
          <form:password cssClass="form-control" path="contrasennia" id="contrasenniaModal" maxlength="15" showPassword="true"/>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="bloqueoModal">* Bloqueo</label>
        </div>
        <div class="col-md-8">
          <form:input cssClass="form-control" path="bloqueo" id="bloqueoModal" maxlength="1"/>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="fechaExpiraContrasenniaModal">* Expiraci&oacute;n de contrase&ntilde;a</label>
        </div>
        <div class="col-md-8">
          <form:input cssClass="form-control" path="fechaExpiraContrasennia" id="fechaExpiraContrasenniaModal" maxlength="10" readonly="true"/>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="rolModal">* Roles</label>
        </div>
        <div class="col-md-8">
          <form:select cssClass="form-control" path="roles" id="rolModal" multiple="multiple">
            <form:options items="${roles}" itemLabel="descripcion" itemValue="clave"/>
          </form:select>
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
    </form:form>
</body>
</html>