<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/taglibs/paginationTagLib.tld"%>
<!DOCTYPE html>
<html>
<head>
  <title>Resultados de busqueda de tipo de documento</title>
</head>
<body>
    <c:choose>
      <c:when test="${not empty tiposDocumento}">
        <div class="nav-pagination">
          <paginator:paginate max="5" offset="${offset}" count="${count}" uri="#" next="&raquo;" previous="&laquo;" />
        </div>
        
        <table class="table table-striped table-bordered">
          <tr>
            <th>Nombre</th>
            <th>&Aacute;rea</th>
            <th>Fase</th>
            <sec:authorize access="hasRole('ROL_TPDOCUMENTO_ACTUALIZAR')">
              <th class="textcenter">Editar</th>
            </sec:authorize>
          </tr>
          
          <c:forEach items="${tiposDocumento}" var="tipoDocumento">
          <tr>
            <td>${tipoDocumento.nombre}</td>
            <td>${tipoDocumento.area.nombre}</td>
            <td>${tipoDocumento.fase.nombre}</td>
            <sec:authorize access="hasRole('ROL_TPDOCUMENTO_ACTUALIZAR')">
              <td class="textcenter">
                <button type="button" class="btn btn-primary" data-toggle="modal" onclick="mostrarModalEditar(${tipoDocumento.clave});">
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