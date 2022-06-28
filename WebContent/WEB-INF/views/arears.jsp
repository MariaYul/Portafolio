<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/taglibs/paginationTagLib.tld"%>
<!DOCTYPE html>
<html>
<head>
  <title>Resultados de busqueda de &aacute;rea</title>
</head>
<body>
    <c:choose>
      <c:when test="${not empty areas}">
        <div class="nav-pagination">
          <paginator:paginate max="5" offset="${offset}" count="${count}" uri="#" next="&raquo;" previous="&laquo;" />
        </div>
        
        <table class="table table-striped table-bordered">
          <tr>
            <th>Nombre</th>
            <sec:authorize access="hasRole('ROL_AREA_ACTUALIZAR')">
              <th class="textcenter">Editar</th>
            </sec:authorize>
          </tr>
          
          <c:forEach items="${areas}" var="area">
          <tr>
            <td>${area.nombre}</td>
            <sec:authorize access="hasRole('ROL_AREA_ACTUALIZAR')">
              <td class="textcenter">
                <button type="button" class="btn btn-primary" data-toggle="modal" onclick="mostrarModalEditar(${area.clave});">
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