<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Historico de Solicitudes</title>
</head>
<body>
    <c:choose>
      <c:when test="${not empty solicitud}">
        <table class="table table-striped">
          <tr>
           	<th>Clave Solicitud</th>
            <th>Nombre</th>
            <th>Estatus</th>
            <th>Comentario</th>
            <th>Fecha de Registro</th>
          </tr>
          
          <c:forEach items="${historico}" var="hist">
         	 <tr>
            <td>${hist.historicos.clave}</td>	
            <td>${hist.persona.nombre}</td>
            <td>${hist.edoSolicitud.nombre}</td>
            <td>
            	<c:choose>
      				<c:when test="${not empty hist.motivo}">
<!-- 	            	<br><br><strong>Motivo del rechazo:</strong><br> -->
	            		${hist.motivo}
					</c:when>
					<c:otherwise>
						${hist.comentario}
					</c:otherwise>
				</c:choose>
            </td>
            <td>${hist.fecha_registro}</td>
          </tr>
          </c:forEach>
        </table>
      </c:when>
      <c:otherwise>
        <div class="alert alert-info" role="alert">
          <span class="glyphicon glyphicon-info-sign"></span>&nbsp;No se encontr&oacute; historico
        </div>
      </c:otherwise>
    </c:choose>
</body>
</html>

