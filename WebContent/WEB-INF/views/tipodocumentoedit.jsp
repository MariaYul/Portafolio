<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edicion de tipo de documento</title>
</head>
<body>
    <div class="row">
      <div class="col-md-12">
        <div class="well well-sm">
          *&nbsp;Campo requerido
        </div>
      </div>
    </div>
      
    <form action="<c:url value="/tipodocumento/edit.htm"/>" method="post" id="frmEdit">
      <div class="row">
        <div class="col-md-4">
          <label for="nombreModal">* Nombre</label>
        </div>
        <div class="col-md-8">
          <input type="hidden" id="clave" name="clave" value="${tipoDocumento.clave}"/>
          <input type="text" class="form-control" placeholder="Nombre de tipo de documento" name="nombre" id="nombreModal"
              data-toggle="tooltip" data-placement="bottom" title="Nombre de tipo de documento" value="${tipoDocumento.nombre}" maxlength="250"/>
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
              <c:choose>
                <c:when test="${tipoDocumento.area.clave == area.clave}">
                  <option value="${area.clave}" selected="selected">${area.nombre}</option>
                </c:when>
                <c:otherwise>
                  <option value="${area.clave}">${area.nombre}</option>
                </c:otherwise>
              </c:choose>
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
              <c:choose>
                <c:when test="${tipoDocumento.fase.clave == fase.clave}">
                  <option value="${fase.clave}" selected="selected">${fase.nombre}</option>
                </c:when>
                <c:otherwise>
                  <option value="${fase.clave}">${fase.nombre}</option>
                </c:otherwise>
              </c:choose>
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
            <c:choose>
              <c:when test="${tipoDocumento.multiregistro == 1}">
                <option value="1" selected="selected">Si</option>
              </c:when>
              <c:otherwise>
                <option value="1">Si</option>
              </c:otherwise>
            </c:choose>
            
            <c:choose>
              <c:when test="${tipoDocumento.multiregistro == 0}">
                <option value="0" selected="selected">No</option>
              </c:when>
              <c:otherwise>
                <option value="0">No</option>
              </c:otherwise>
            </c:choose>
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
            <c:choose>
              <c:when test="${tipoDocumento.activo == 1}">
                <option value="1" selected="selected">Activo</option>
              </c:when>
              <c:otherwise>
                <option value="1">Activo</option>
              </c:otherwise>
            </c:choose>
            
            <c:choose>
              <c:when test="${tipoDocumento.activo == 0}">
                <option value="0" selected="selected">Inactivo</option>
              </c:when>
              <c:otherwise>
                <option value="0">Inactivo</option>
              </c:otherwise>
            </c:choose>
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