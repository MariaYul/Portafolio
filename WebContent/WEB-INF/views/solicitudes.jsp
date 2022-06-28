<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="paginator"
	uri="/WEB-INF/taglibs/paginationTagLib.tld"%>
<!DOCTYPE html>
<html>
<head>
<title>Resultados de busqueda de solicitudes</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty solicitudes}">
			<div class="nav-pagination">
				<paginator:paginate max="5" offset="${offset}" count="${count}"
					uri="#" next="&raquo;" previous="&laquo;" />
			</div>

			<table class="table table-striped table-bordered">
				<tr>
					<th>Id Solicitud</th>
					<th>Responsable</th>
					<th>Nombre del Curso</th>
					<th>Fecha Inicio</th>
					<th>Estatus Actual</th>
					<th class="textcenter">Editar</th>
					<th class="textcenter">Eliminar</th>
					<th>Historico</th>
				</tr>

				<c:forEach items="${solicitudes}" var="solicitud">
					<tr>
						<td>${solicitud.clave}</td>
						<td>${solicitud.persona.clave}</td>
						<td><a
							href="javascript:mostrarModalConsultar(${solicitud.clave});">${solicitud.nombre}</a></td>
						<td><fmt:formatDate pattern="dd/MM/yyy"
								value="${solicitud.fecha_inicio}" /></td>
						<td>${solicitud.edoSolicitud.nombre}</td>
						
						<c:choose>
						  <c:when test="${solicitud.edoSolicitud.clave==1 || solicitud.edoSolicitud.clave==21 || solicitud.edoSolicitud.clave==3 || solicitud.edoSolicitud.clave==10 || solicitud.edoSolicitud.clave==14 || solicitud.edoSolicitud.clave==17}">
						   	 <td class="textcenter">	
								<button type="button" class="btn btn-primary" data-toggle="modal"
									onclick="mostrarModalEditar(${solicitud.clave});">
									<span class="glyphicon glyphicon-edit"></span>
								</button>
						  	</td>
						  </c:when>
						  <c:otherwise>
						 	<td class="textcenter">
									<button type="button" class="btn btn-primary" data-toggle="modal" disabled="disabled">
										<span class="glyphicon glyphicon-edit"></span>
									</button>
							</td>
						  </c:otherwise>
						</c:choose>

						<c:choose>
						  <c:when test="${solicitud.edoSolicitud.clave==1 || solicitud.edoSolicitud.clave==21 }">
						    <td class="textcenter">
								<button type="button" class="btn btn-primary" data-toggle="modal"
									onclick="mostrarModalEliminar(${solicitud.clave});">
									<span class="glyphicon glyphicon-remove"></span>
								</button>
							</td>
						  </c:when>
						 
						  <c:otherwise>
						    <td class="textcenter">
								<button type="button" class="btn btn-primary" data-toggle="modal"  disabled="disabled">
									<span class="glyphicon glyphicon-remove"></span>
								</button>
							</td>
						  </c:otherwise>
						</c:choose>
							

						<td class="textcenter">
							<button type="button" class="btn btn-primary" data-toggle="modal"
								onclick="mostrarModalHistorico(${solicitud.clave});">
								<span class="glyphicon glyphicon-th-list"></span>
							</button>
						</td>

					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<div class="alert alert-info" role="alert">
				<span class="glyphicon glyphicon-info-sign"></span>&nbsp;No se
				encontr&oacute; registros con los par&aacute;metros proporcionados
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>