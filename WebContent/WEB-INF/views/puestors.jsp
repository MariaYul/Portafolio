<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Resultados de busqueda de puestos</title>
</head>
<body>
    <c:choose>
      <c:when test="${not empty puestos}">
        <table class="table table-striped">
          <tr>
            <th>Nombre</th>
            <th class="textcenter">Seleccionar</th>
          </tr>
          
          <c:forEach items="${puestos}" var="puesto">
          <tr>
            <td>${puesto.nombre}</td>
            <td class="textcenter">
              <button type="button" class="btn btn-primary" data-toggle="modal" onclick="seleccionarItemCatalogoModal('${puesto.clave}', '${puesto.nombre}', '#puesto', '#nbpuesto', '#modalPuesto');">
                <span class="glyphicon glyphicon-ok"></span>
              </button>
            </td>
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