<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<title>Motivo del Rechazo</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<c:choose>
		<c:when test="${not empty solicitud}">
			<div class="contentmodal">
				<form>
					<div class="row">
						<div class="col-md-12">
							<input type="hidden" id="clave" name="clave"
								value="${solicitud.clave}" />
						</div>
					</div>
					<div class="col-md-12">
									<div class="shortbreakline"></div>

									<div id="divErrorMotivo" style="display: none;">
										<div class="row">
											<div class="col-md-12">
												<div class="alert alert-danger" role="alert">
													<strong>Error de validaci&oacute;n: Debe ingresar un motivo de rechazo</strong>
												</div>
											</div>
										</div>
									</div>
									
									<div class="shortbreakline"></div>

									<div id="divError" style="display: none;"> 
										<div class="row">
											<div class="col-md-12">
												<div class="alert alert-danger" role="alert">
													<strong>Error de validaci&oacute;n</strong>
												</div>
											</div>
										</div>
									</div>

									<div class="shortbreakline"></div>

									<div id="divInfo" style="display: none;">
										<div class="row">
											<div class="col-md-12">
												<div class="alert alert-success" role="alert">
													<strong>Mensaje: Solicitud rechazada.</strong><span
														class="mensjaeInfo"></span>
												</div>
											</div>
										</div>
									</div>
								</div>
					<div class="row">
						<div class="col-md-2">
							<label class="textbold" for="nombreSolicitud">Solicitud:</label>
						</div>
						<div class="col-md-10">
							<label class="" id="nombreSolicitud">${solicitud.nombre}</label>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<label class="textbold" for="motivo">Ingrese el motivo
								del rechazo:</label>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<textarea cols="95" rows="4" name="motivoRechazo" id="motivo" required="required"></textarea>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-12" align="right">
							<sec:authorize access="hasRole('ROL_RECHAZAR_SOLICITUD')">
								<button type="button" class="btn btn-primary"
									data-toggle="modal" onclick="rechazarSolicitud();">
									<span class="glyphicon glyphicon-erase"></span>&nbsp;Rechazar
									Solicitud
								</button>
							</sec:authorize>
							<sec:authorize access="hasRole('ROL_RECHAZAR_VALSOLICITUD')">
								<button type="button" class="btn btn-primary"
									data-toggle="modal" onclick="rechazarAutoSolicitud();">
									<span class="glyphicon glyphicon-erase"></span>&nbsp;Rechazar
									Solicitud
								</button>
							</sec:authorize>
						</div>
					</div>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<div class="alert alert-info" role="alert">
				<span class="glyphicon glyphicon-info-sign"></span>&nbsp;No se
				encontr&oacute; informaci&oacute;n de la solcitud
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>

