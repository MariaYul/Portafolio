<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Registro de tipo de documento</title>
</head>
<body>
    <div class="row">
      <div class="col-md-12">
        <div class="well well-sm">
          *&nbsp;Campo requerido
        </div>
      </div>
    </div>
      
    <form action="<c:url value="/tipodocumento/add.htm"/>" method="post" id="frmAdd">
      <div class="row">
        <div class="col-md-4">
          <label for="nombreModal">* Nombre</label>
        </div>
        <div class="col-md-8">
          <input type="text" class="form-control" placeholder="Nombre de tipo de documento" name="nombre" id="nombreModal"
              data-toggle="tooltip" data-placement="bottom" title="Nombre de tipo de documento" maxlength="250"/>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="areaModal">* &Aacute;rea</label>
        </div>
        <div class="col-md-8">
          <select class="form-control" name="area.clave" id="areaModal">
            <option value="-1">-- Seleccione --</option>
            <c:forEach items="${areas}" var="area">
              <option value="${area.clave}">${area.nombre}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="faseModal">* Fase</label>
        </div>
        <div class="col-md-8">
          <select class="form-control" name="fase.clave" id="faseModal">
            <option value="-1">-- Seleccione --</option>
            <c:forEach items="${fases}" var="fase">
              <option value="${fase.clave}">${fase.nombre}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="multiregistroModal">* Multiregistro</label>
        </div>
        <div class="col-md-8">
          <select class="form-control" name="multiregistro" id="multiregistroModal">
            <option value="-1">-- Seleccione --</option>
            <option value="1">Si</option>
            <option value="0">No</option>
          </select>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div class="row">
        <div class="col-md-4">
          <label for="activoModal">* Estatus</label>
        </div>
        <div class="col-md-8">
          <select class="form-control" name="activo" id="activoModal">
            <option value="1" selected="selected">Activo</option>
            <option value="0">Inactivo</option>
          </select>
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