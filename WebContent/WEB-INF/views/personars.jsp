<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/taglibs/paginationTagLib.tld"%>
<!DOCTYPE html>
<html>
<head>
  <title>Resultados de busqueda de personas</title>
</head>
<body>
    <c:choose>
      <c:when test="${not empty personas}">
        <div class="nav-pagination">
          <paginator:paginate max="5" offset="${offset}" count="${count}" uri="#" next="&raquo;" previous="&laquo;" />
        </div>
        
        <table class="table table-striped table-bordered">
          <tr>
            <th>N&uacute;mero de candidato</th>
            <th>N&uacute;mero de empleado</th>
            <th>Nombre</th>
            <th>Apellido Paterno</th>
            <th>Apellido Materno</th>
            <th>Estatus</th>
            <th>Regi&oacute;n</th>
            <sec:authorize access="hasRole('ROL_DOCUMENTACION_CONSULTAR')">
              <th class="textcenter">Expediente</th>
            </sec:authorize>
          </tr>
          
          <c:forEach items="${personas}" var="persona">
          <tr>
            <td>${persona.clave}</td>
            <td>${persona.numero}</td>
            <td>${persona.nombre}</td>
            <td>${persona.apellidos.apellidoPaterno}</td>
            <td>${persona.apellidos.apellidoMaterno}</td>
            <td>${persona.estatus.nombre}</td>
            <td>${persona.region.nombre}</td>
            <sec:authorize access="hasRole('ROL_DOCUMENTACION_CONSULTAR')">
              <td class="textcenter">
                <button type="submit" class="btn btn-primary" onclick="abrirexpediente(${persona.clave});">
                  <span class="glyphicon glyphicon-folder-open"></span>
                </button>
              </td>
            </sec:authorize>
          </tr>
          </c:forEach>
        </table>
      </c:when>
      <c:otherwise>
        <div class="alert alert-info" role="alert">
          <span class="glyphicon glyphicon-info-sign"></span>&nbsp;No se encontr&oacute; registros con los par&aacute;metros proporcionados
        </div>
      </c:otherwise>
    </c:choose>
</body>
</html>