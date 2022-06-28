<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>Consulta de Solicitud</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/jquery-ui-1.10.4.custom.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/main.css"/>" />
<%-- <script src="<c:url value="/js/jquery.js"/>"></script> --%>
<script src="<c:url value="/js/solicitud.js"/>"></script>
<script src="<c:url value="/js/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/solicitudutils.js"/>"></script>
<script src="<c:url value="/js/solicitudutils2.js"/>"></script>
<script src="<c:url value="/js/moment.min.js"/>"></script>

</head>
<body>

	<form action="<c:url value="/solicitud/edit.htm"/>" method="post"
		id="frmEdit">

		<div class="container">

			<header>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h2>
							<img src="../images/telcel.png" /><span class="title">&nbsp;&nbsp;Solicitud
								de Curso&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F-00.02.02.01.01-027</span>
						</h2>
					</div>
				</div>
			</header>



			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">Datos del Curso</h4>
							<input type="hidden" id="clave" name="clave"
								value="<c:out value="${solicitud.clave}"/>" /> <input
								type="hidden" id="creatorSolicitud" name="creatorSolicitud"
								value="<c:out value="${creatorSolicitud}"/>" />

						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-2">
									<label for="nom_curso">Nombre del Curso:</label>
								</div>
								<div class="col-md-10">
									<input type="text" class="form-control"
										placeholder="Nombre del Curso" name="nombre" id="nombreModal"
										title="favor de ingresar el nombre del Curso" disabled
										data-toggle="tooltip" data-placement="bottom"
										title="Nombre del Curso"
										value="<c:out value="${solicitud.nombre}"/>" maxlength="100" />
								</div>
							</div>

							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="fechaInicio">Fecha Inicio:(1)</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Fecha Inicio" name="fecha_inicio"
										id="fechaInicio" title="favor de ingresar la fecha Inicial"
										disabled data-toggle="tooltip" data-placement="bottom"
										<fmt:formatDate value="${solicitud.fecha_inicio}" pattern="dd/MM/yyy" var="newdateInicio" />
										value="<c:out value="${newdateInicio}"/>" maxlength="10" /> 
								</div>
								<div class="col-md-2">
									<label for="fechaFin">Fecha Fin:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Horario del curso" name="fecha_fin" id="fechaFin"
										title="favor de ingresar la fecha Final" disabled
										data-toggle="tooltip" data-placement="bottom"
										<fmt:formatDate value="${solicitud.fecha_inicio}" pattern="dd/MM/yyy" var="newdateFin" />
										value="<c:out value="${newdateFin}"/>" maxlength="10" />
								</div>
							</div>


							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="duracion">Duración días:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Duración en días" name="duracion"
										id="duracionModal"
										title="favor de ingresar la duración en dias" disabled
										data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.duracion}"/>" maxlength="3" />
								</div>
								<div class="col-md-2">
									<label for="horario">Horario del curso:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Horario del curso" name="horario"
										id="horarioModal"
										title="favor de ingresar el Horario del curso" disabled
										data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.horario}"/>" maxlength="10" />
								</div>
							</div>

							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="NombreContacto">Nombre del Contacto y
										Proveedor:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Nombre del Contacto y Proveedor" name="contacto"
										id="contactoModal"
										title="favor de ingresar el Nombre del Contacto y Proveedor"
										disabled data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.contacto}"/>" maxlength="20" />
								</div>
								<div class="col-md-2">
									<label for="Telefono">Teléfono y Correo Electrónico:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Teléfono y Correo Electrónico"
										name="datos_contacto" id="datos_contactoModal"
										title="favor de ingresar el Teléfono y Correo Electrónico"
										disabled data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.datos_contacto}"/>"
										maxlength="20" />
								</div>
							</div>


							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="responsable">Responsable de seguimiento
										Telcel:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Responsable de seguimiento Telcel"
										name="responsable" id="responsableModal"
										title="favor de ingresar el Responsable de seguimiento Telcel"
										disabled data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.responsable}"/>"
										maxlength="20" />
								</div>
								<div class="col-md-2">
									<label for="extension">Ext. y Correo Electrónico:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Ext. y Correo Electrónico" name="datos_responble"
										id="datos_responbleModal"
										title="favor de ingresar Ext. y Correo Electrónico:" disabled
										data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.datos_responble}"/>"
										maxlength="20" />
								</div>
							</div>


						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="inversionCurso">Inversión del Curso</h4>
						</div>
						<div class="panel-body">

							<div class="row">
								<div class="col-md-2">
									<label for="inversionPersona">Inversión por Persona:
										(2)</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Inversión por Persona" name="inver_person"
										id="inver_personModal" onblur="calculoPersona(this.value);"
										disabled value="<c:out value="${solicitud.inver_person}"  />"
										maxlength="10" />
								</div>
								<div class="col-md-2">
									<label for="tipoMoneda">Tipo de Moneda:</label>
								</div>
								<div class="col-md-2">
									 <select class="form-control" name="tipo_moneda.clave" id="tipo_monedaModal" disabled="disabled">
						             <option value="-1">-- Seleccione --</option>
							            <c:forEach items="${tipos}" var="tipo">
							              <c:choose>
							                <c:when test="${solicitud.tipo_moneda.clave == tipo.clave}">
							                  <option value="${tipo.clave}" selected="selected">${tipo.nombre}</option>
							                </c:when>
							                <c:otherwise>
							                  <option value="${tipo.clave}">${tipo.nombre}</option>
							                </c:otherwise>
							              </c:choose>
							            </c:forEach>
						          </select>
								</div>
							</div>


							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="inversionGrupo">Inversión por Grupo:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Inversión por grupo" name="inver_grupo"
										id="inver_grupoModal" onblur="calculoGrupo(this.value);"
										disabled data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.inver_grupo}"/>"
										maxlength="8" />
								</div>
							</div>

							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="participantes">No. de Participantes:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="No. de Participantes" name="num_part"
										id="num_partModal" title="Solo números" required
										onKeyup="calculoTotal(this.value);" disabled
										data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.num_part}"/>" maxlength="8" />
								</div>
							</div>


							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="total">Total:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control" placeholder="Total"
										name="total" id="totalModal" data-toggle="tooltip"
										data-placement="bottom" title="Total"
										value="<c:out value="${solicitud.total}"/>" maxlength="8"
										disabled />
								</div>
							</div>

							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="costoAsociados">Costos Asociados: (3)</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Costos Asociados" name="costo_aso"
										id="costo_asoModal" data-toggle="tooltip"
										data-placement="bottom" title="Costos Asociados"
										value="<c:out value="${solicitud.costo_aso}"/>" maxlength="8"
										disabled />
								</div>
							</div>


						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">Datos del Participantes (4)</h4>
						</div>
						<div class="panel-body">

							<table class="table table-striped table-bordered">
								<tr>
									<th>No.Emp</th>
									<th>Participante</th>
									<th>Puesto</th>
									<th>Centro de Costos</th>
								</tr>

								<c:forEach items="#{empMap.empleadoMap}" var="entry">
									<tr>
										<th><input type="text" class="form-control"
											placeholder="Expediente" disabled
											name="participantesMap['${entry.key}']"
											id="participantesMap['${entry.key}']" required
											onblur="ontenerDatosEmp(this.value,'${entry.key}');"
											value="${entry.value.clave}" /></th>
										<th><div id="nombre${entry.key}">${entry.value.nombre}</div>
										</th>
										<th><div id="puesto${entry.key}">${entry.value.puesto}
											</div></th>
										<th><div id="costos${entry.key}">${entry.value.costo}
											</div></th>
									</tr>
								</c:forEach>
								<tr>
									<td class="textcenter">
										<button type="button" class="btn btn-primary"
											data-toggle="modal" id="inicioListaAdicionalEdit"
											value="${solicitud.clave}">
											<span class="glyphicon glyphicon-list-alt"></span> Adicionar
											Participante
										</button>
									</td>
								</tr>

							</table>

						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">Justificación Especifica del Curso</h4>
							<br>
							<h5 class="panel-title">Seleccione una(s) opción(es) y
								descríbala a detalle</h5>
						</div>
						<div class="panel-body">
							<table class="table table-striped table-bordered">
								<tr>
									<td>
										<table>
											<tr>
												<td>Funciones:</td>
												<td><select class="form-control" name="funciones"
													id="funcionesModel" disabled>
														<c:choose>
															<c:when test="${solicitud.funciones==1}">
																<option value="1" selected="selected">X</option>
															</c:when>
															<c:otherwise>
																<option value="1">X</option>
															</c:otherwise>
														</c:choose>

														<c:choose>
															<c:when test="${solicitud.funciones==-1}">
																<option value="-1" selected="selected">-- Seleccione --</option>
															</c:when>
															<c:otherwise>
																<option value="-1" >-- Seleccione --</option>
															</c:otherwise>
														</c:choose>
												</select></td>
											</tr>

											<tr>
												<td>Proyectos:</td>
												<td><select class="form-control" name="proyecto"
													id="proyectoModel" disabled>
														<c:choose>
															<c:when test="${solicitud.proyecto == 1}">
																<option value="1" selected="selected">X</option>
															</c:when>
															<c:otherwise>
																<option value="1">X</option>
															</c:otherwise>
														</c:choose>

														<c:choose>
															<c:when test="${solicitud.proyecto  == -1}">
																<option value="-1" selected="selected">-- Seleccione --</option>
															</c:when>
															<c:otherwise>
																<option value="-1" >-- Seleccione --</option>
															</c:otherwise>
														</c:choose>
												</select></td>
											</tr>

											<tr>
												<td>Aplicabilidad:</td>
												<td><select class="form-control" name="aplicabilidad"
													id="aplicabilidadModel" disabled >
														<c:choose>
															<c:when test="${solicitud.aplicabilidad == 1}">
																<option value="1" selected="selected">X</option>
															</c:when>
															<c:otherwise>
																<option value="1">X</option>
															</c:otherwise>
														</c:choose>

														<c:choose>
															<c:when test="${solicitud.aplicabilidad  == -1}">
																<option value="-1" selected="selected">-- Seleccione --</option>
															</c:when>
															<c:otherwise>
																<option value="-1">-- Seleccione --</option>
															</c:otherwise>
														</c:choose>
												</select></td>
											</tr>

											<tr>
												<td>Actualización:</td>
												<td><select class="form-control" name="actualizacion"
													id="actualizacionModel" disabled>
														<c:choose>
															<c:when test="${solicitud.actualizacion == 1}">
																<option value="1" selected="selected">X</option>
															</c:when>
															<c:otherwise>
																<option value="1">X</option>
															</c:otherwise>
														</c:choose>

														<c:choose>
															<c:when test="${solicitud.actualizacion  == -1}">
																<option value="-1" selected="selected">-- Seleccione --</option>
															</c:when>
															<c:otherwise>
																<option value="-1" >-- Seleccione --</option>
															</c:otherwise>
														</c:choose>
												</select></td>
											</tr>

											<tr>
												<td>Otro:</td>
												<td><input type="text" class="form-control"
													placeholder="Otro" name="otro" id="otroModel"
													title="favor de ingresar otra opcion" data-toggle="tooltip" disabled
													data-placement="bottom" maxlength="20" value="<c:out value="${solicitud.otro}"/>"  /></td>
											</tr>
										</table>
									</td>
									<td><textarea cols="114" rows="9" name="detalle" disabled
											id="detalleModel" >${solicitud.detalle}</textarea></td>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">Cosideraciones Importantes</h4>
						</div>

						<div class="panel-body">
							<div class="row">
								<div class="col-md-10">
									<label>(1) La Gerencia de Capacitación y Desarrollo es
										la única área autorizada que confirmará la fecha de curso con
										el Proveedor,una vez concluido el proceso de negociación.</label> <label
										for="fecha_inicio"> (2) La negociación de precios y
										condiciones de pago del curso, la realizará exclusivamente la
										Dirección de Compras Telcel</label> <label for="fecha_inicio">
										(3) Los Costos asociados aplican cuando el curso se imparta
										fuera de la ciudad de orgine y(o en renta de salas de
										Capacitación</label> <label for="fecha_inicio">(4) Más de 3
										participantes ampliar la justificación (anexar lista
										complementaria, misma que se encuentra en este archivo</label> <label
										for="fecha_inicio"> Anexar a esta Solicitud: Perfil de
										puesto Basado en Competencias Laborales y Contenido Temático
										del curso</label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<sec:authorize access="hasRole('ROL_SOLICITUD_EXCLUSIVO')">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">Para uso exclusivo de Capacitación</h4>
							<br>
						</div>
						<div class="panel-body">
							<table class="table table-striped table-bordered">
								<tr>
									<td>
										<table>
											<tr>
												<td>Presupuesto:</td>
												<td><select class="form-control" name="presupuesto"
													id="presupuestoModel" >
														<c:choose>
															<c:when test="${solicitud.presupuesto == 1}">
																<option value="1" selected="selected">X</option>
															</c:when>
															<c:otherwise>
																<option value="1">X</option>
															</c:otherwise>
														</c:choose>

														<c:choose>
															<c:when test="${solicitud.presupuesto  == -1}">
																<option value="-1"  selected="selected">-- Seleccione --</option>
															</c:when>
															<c:otherwise>
																<option value="-1">-- Seleccione --</option>
															</c:otherwise>
														</c:choose>
												</select></td>
											</tr>

											<tr>
												<td>Histórico de Capacitación:</td>
												<td><select class="form-control" name="historico"
													id="historicoModel" >
														<c:choose>
															<c:when test="${solicitud.historico == 1}">
																<option value="1" selected="selected">X</option>
															</c:when>
															<c:otherwise>
																<option value="1">X</option>
															</c:otherwise>
														</c:choose>

														<c:choose>
															<c:when test="${solicitud.historico  == -1}">
																<option value="-1"  selected="selected">-- Seleccione --</option>
															</c:when>
															<c:otherwise>
																<option value="-1" >-- Seleccione --</option>
															</c:otherwise>
														</c:choose>
												</select></td>
											</tr>

											<tr>
												<td>Contenido Temático:</td>
												<td><select class="form-control" name="contematico"
													id="contematicoModel" >
														<c:choose>
															<c:when test="${solicitud.contematico == 1}">
																<option value="1" selected="selected">X</option>
															</c:when>
															<c:otherwise>
																<option value="1">X</option>
															</c:otherwise>
														</c:choose>

														<c:choose>
															<c:when test="${solicitud.contematico  == -1}">
																<option value="-1" selected="selected">-- Seleccione --</option>
															</c:when>
															<c:otherwise>
																<option value="-1">-- Seleccione --</option>
															</c:otherwise>
														</c:choose>
												</select></td>
											</tr>

											<tr>
												<td>Trayectoría:</td>
												<td><select class="form-control" name="trayectoria"
													id="trayectoriaModel" >
														<c:choose>
															<c:when test="${solicitud.trayectoria == 1}">
																<option value="1" selected="selected">X</option>
															</c:when>
															<c:otherwise>
																<option value="1">X</option>
															</c:otherwise>
														</c:choose>

														<c:choose>
															<c:when test="${solicitud.trayectoria  == -1}">
																<option value="-1" selected="selected">-- Seleccione --</option>
															</c:when>
															<c:otherwise>
																<option value="-1">-- Seleccione --</option>
															</c:otherwise>
														</c:choose>
												</select></td>
											</tr>

										</table>
									</td>
									<td>
										<div class="panel-heading">
											<h4 class="panel-title">Observaciones</h4>
										</div>
									</td>
									<td><textarea cols="40" rows="9" name="observaciones" 
											id="observacionesModel">${solicitud.observaciones}</textarea>
									</td>
									<td>
										<div class="panel-heading">
											<h4 class="panel-title">Nombre y fecha de quien recibe</h4>
										</div>
									</td>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		</sec:authorize>

<sec:authorize access="hasRole('ROL_VALIDAR_SOLICITUD') or hasRole('ROL_RECHAZAR_SOLICITUD') or hasRole('ROL_AUTORIZAR_SOLICITUD') or hasRole('ROL_RECHAZAR_VALSOLICITUD')">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">Seguimiento de Solicitud</h4>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<sec:authorize access="hasRole('ROL_VALIDAR_SOLICITUD')">
										<button type="button" class="btn btn-primary"
											data-toggle="modal" onclick="validarSolicitud();">
											<span class="glyphicon glyphicon-erase"></span>&nbsp;Validar
											Solicitud
										</button>
									</sec:authorize>
									<sec:authorize access="hasRole('ROL_RECHAZAR_SOLICITUD')">
<!-- 										<button type="button" class="btn btn-primary" -->
<!-- 											data-toggle="modal" onclick="rechazarSolicitud();"> -->
<!-- 											<span class="glyphicon glyphicon-erase"></span>&nbsp;Rechazar -->
<!-- 											Solicitud -->
<!-- 										</button> -->
										<button type="button" class="btn btn-primary" id="btnModalRechazo"
											onclick="mostrarModalRechazo(${solicitud.clave});">
											<span class="glyphicon glyphicon-erase"></span>&nbsp;Rechazar
											Solicitud
										</button>
									</sec:authorize>
									<sec:authorize access="hasRole('ROL_AUTORIZAR_SOLICITUD')">
										<button type="button" class="btn btn-primary"
											data-toggle="modal" onclick="autorizarSolicitud();">
											<span class="glyphicon glyphicon-erase"></span>&nbsp;Autorizar
											Solicitud
										</button>
									</sec:authorize>
									<sec:authorize access="hasRole('ROL_RECHAZAR_VALSOLICITUD')">
<!-- 										<button type="button" class="btn btn-primary" -->
<!-- 											data-toggle="modal" onclick="rechazarAutoSolicitud();"> -->
<!-- 											<span class="glyphicon glyphicon-erase"></span>&nbsp;Rechazar -->
<!-- 											Solicitud -->
<!-- 										</button> -->
										<button type="button" class="btn btn-primary"
											onclick="mostrarModalRechazo(${solicitud.clave});">
											<span class="glyphicon glyphicon-erase"></span>&nbsp;Rechazar
											Solicitud
										</button>
									</sec:authorize>

								</div>

								<div class="col-md-12">
									<div class="shortbreakline"></div>

									<div id="validationmessage">
										<div class="row">
											<div class="col-md-12">
												<div class="alert alert-danger" role="alert" id="msgerror">
													<strong>Error de validaci&oacute;n: </strong><span
														class="validationerror"></span>
												</div>
											</div>
										</div>
									</div>

									<div class="shortbreakline"></div>

									<div id="validationmessageInfo">
										<div class="row">
											<div class="col-md-12">
												<div class="alert alert-success" role="alert" id="msginfo">
													<strong>Mensaje: Acción Realizada</strong><span
														class="validationInfo"></span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</sec:authorize>
	</form>
	
	
	<div class="modal fade" id="modalRechazo" tabindex="-1" role="dialog" aria-labelledby="modalRechazoLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="modalRechazoLabel">Rechazar Solicitud</h4>
          </div>
          <div class="modal-body" id="modalRechazoBody">
            <form action="">
            	<div class="row">
					<div class="col-md-2">
						<label for="motivo">Ingrese el motivo del rechazo:</label>
					</div>
					<div class="col-md-4">
						<textarea  class="form-control" cols="15" rows="6" placeholder="Motivo del rechazo" name="motivo" id="motivo" >
						</textarea>
					</div>
				</div>
            </form>
          </div>
<!--           <div class="modal-footer"> -->
<!--             <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button> -->
<!--           </div> -->
        </div>
      </div>
   </div>
   
</body>
</html>