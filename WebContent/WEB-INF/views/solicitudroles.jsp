<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Roles de usuario</title>
</head>
<body>
    <c:choose>
      <c:when test="${not empty usuario.roles}">
        <table class="table table-striped">
          <tr>
            <th>Clave</th>
            <th>Rol</th>
            <th>Descripci&oacute;n</th>
          </tr>
          
          <c:forEach items="${usuario.roles}" var="rol">
          <tr>
            <td>${rol.clave}</td>
            <td>${rol.nombre}</td>
            <td>${rol.descripcion}</td>
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