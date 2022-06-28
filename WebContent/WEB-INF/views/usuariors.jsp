<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/taglibs/paginationTagLib.tld"%>
<!DOCTYPE html>
<html>
<head>
  <title>Resultados de busqueda de usuarios</title>
</head>
<body>
    <c:choose>
      <c:when test="${not empty usuarios}">
        <div class="nav-pagination">
          <paginator:paginate max="5" offset="${offset}" count="${count}" uri="#" next="&raquo;" previous="&laquo;" />
        </div>
        
        <table class="table table-striped table-bordered">
          <tr>
            <th>N&uacute;mero de empleado</th>
            <th>Nombre</th>
            <th>Apellido Paterno</th>
            <th>Apellido Materno</th>
            <th>&Aacute;rea</th>
            <th>Regi&oacute;n</th>
            <th>Bloqueo</th>
            <th class="textcenter">Roles</th>
            <sec:authorize access="hasRole('ROL_USUARIO_ACTUALIZAR')">
              <th class="textcenter">Editar</th>
            </sec:authorize>
          </tr>
          
          <c:forEach items="${usuarios}" var="usuario">
          <tr>
            <td>${usuario.numero}</td>
            <td>${usuario.nombre}</td>
            <td>${usuario.apellidos.apellidoPaterno}</td>
            <td>${usuario.apellidos.apellidoMaterno}</td>
            <td>${usuario.area.nombre}</td>
            <td>${usuario.region.nombre}</td>
            <td>${usuario.bloqueo}</td>
            <td class="textcenter">
              <button type="button" class="btn btn-primary" data-toggle="modal" onclick="mostrarModalRoles(${usuario.numero});">
                <span class="glyphicon glyphicon-th-list"></span>
              </button>
            </td>
            <sec:authorize access="hasRole('ROL_USUARIO_ACTUALIZAR')">
              <td class="textcenter">
                <button type="button" class="btn btn-primary" data-toggle="modal" onclick="mostrarModalEditar(${usuario.numero});">
                  <span class="glyphicon glyphicon-edit"></span>
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