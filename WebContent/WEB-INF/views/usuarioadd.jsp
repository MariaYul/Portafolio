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
    
    <form:form action="add.htm" method="post" id="frmAdd" commandName="usuario">
      
        
          <div class="row">
            <div class="col-md-4">
              <label for="numeroModal">* N&uacute;mero de empleado</label>
            </div>
            <div class="col-md-8">
              <form:input cssClass="form-control" placeholder="Numero de empleado" path="numero" id="numeroModal"
                data-toggle="tooltip" data-placement="bottom" title="N&uacute;mero de empleado" maxlength="8"/>
            </div>
          </div>
          
          <div class="shortbreakline"></div>
          
          <div class="row">
            <div class="col-md-4">
              <label for="nombreModal">* Nombre</label>
            </div>
            <div class="col-md-8">
              <form:input cssClass="form-control" placeholder="Nombre" path="nombre" id="nombreModal"
                data-toggle="tooltip" data-placement="bottom" title="Nombre" maxlength="150"/>
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
                 <form:options items="${areas}" itemValue="clave" itemLabel="nombre"/>
               </form:select>
            </div>
          </div>
          
          <div class="shortbreakline"></div>
          
          <div class="row">
            <div class="col-md-4">
              <label for="regionModal">* Regi&oacute;n</label>
            </div>
            <div class="col-md-8">
              <form:select class="form-control" path="region.clave" id="regionModal">
                <form:option value="-1">-- Seleccione --</form:option>
                <form:options items="${regiones}" itemValue="clave" itemLabel="nombre"/>
              </form:select>
            </div>
          </div>
          
          <div class="shortbreakline"></div>
          
          <div class="row">
            <div class="col-md-4">
              <label for="activoModal">* Estatus</label>
            </div>
            <div class="col-md-8">
               <form:select class="form-control" path="activo" id="activoModal">
                 <form:option value="1" selected="selected">Activo</form:option>
                 <form:option value="0">Inactivo</form:option>
               </form:select>
            </div>
          </div>
          
          <div class="shortbreakline"></div>
          
          <div class="row">
            <div class="col-md-4">
              <label for="contrasenniaModal">* Contrase&ntilde;a</label>
            </div>
            <div class="col-md-8">
              <form:password cssClass="form-control" placeholder="Contraseña" path="contrasennia" id="contrasenniaModal" maxlength="15" showPassword="true"/>
            </div>
          </div>
          
          <div class="shortbreakline"></div>
          
          <div class="row">
            <div class="col-md-4">
              <label for="bloqueoModal">* Bloqueo</label>
            </div>
            <div class="col-md-8">
              <form:input cssClass="form-control" path="bloqueo" id="bloqueoModal" maxlength="1" readonly="true"/>
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
               <form:select class="form-control" path="roles" id="rolModal" multiple="multiple">
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