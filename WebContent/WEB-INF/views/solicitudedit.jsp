<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/taglibs/paginationTagLib.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<title>Actualización de Solicitud</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/jquery-ui-1.10.4.custom.css"/>" />
	
<script src="<c:url value="/js/jquery.js"/>" /></script>
<script src="<c:url value="/js/solicitud.js"/>" /></script>
<script src="<c:url value="/js/jquery-ui.js"/>" /></script>
<script src="<c:url value="/js/solicitudutils.js"/>" /></script>
<script src="<c:url value="/js/moment.min.js"/>"/></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="well well-sm">*&nbsp;Campo requerido</div>
			</div>
		</div>
	</div>
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
							<input type="hidden" id="id_status" name="id_status" value="1" />
							<input type="hidden" id="clave" name="clave"
								value="<c:out value="${solicitud.clave}"/>" />
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-2">
									<label for="nom_curso">* Nombre del Curso:</label>
								</div>
								<div class="col-md-10">
									<input type="text" class="form-control"
										placeholder="Nombre del Curso" name="nombre" id="nombreModal"
										title="Favor de ingresar el nombre del Curso" required
										data-toggle="tooltip" data-placement="bottom"
										title="Nombre del Curso"
										value="<c:out value="${solicitud.nombre}"/>" maxlength="100" />
								</div>
							</div>

							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="fechaInicio">* Fecha Inicio: (1)</label>
								</div>
								<div class="col-md-4">
									<input type="date" class="form-control"
										placeholder="Fecha Inicio" name="fecha_inicio"
										id="fechaInicio" title="Favor de ingresar la fecha Inicial"
										required data-toggle="tooltip" data-placement="bottom"
										<fmt:formatDate value="${solicitud.fecha_inicio}" pattern="dd/MM/yyy" var="newdateInicio" />
										value="<c:out value="${newdateInicio}"/>" maxlength="10" />
								</div>
								<div class="col-md-2">
									<label for="fechaFin">* Fecha Fin:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Horario del curso" name="fecha_fin" id="fechaFin"
										title="Favor de ingresar la fecha Final" required
										data-toggle="tooltip" data-placement="bottom"
										<fmt:formatDate value="${solicitud.fecha_fin}" pattern="dd/MM/yyy" var="newdatefin" />
										value="<c:out value="${newdatefin}"/>" maxlength="18" />
								</div>
							</div>


							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="duracion">* Duración días:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Duración en días" name="duracion"
										id="duracionModal"
										title="Favor de ingresar la duración en dias" required
										data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.duracion}"/>" maxlength="3" />
								</div>
								<div class="col-md-2">
									<label for="horario">* Horario del curso:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="HH:mm - HH:mm" name="horario"
										id="horarioModal"
										title="Favor de ingresar el Horario del curso" required
										data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.horario}"/>" maxlength="23" />
								</div>
							</div>

							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="NombreContacto">* Nombre del Contacto y
										Proveedor:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Nombre del Contacto y Proveedor" name="contacto"
										id="contactoModal"
										title="Favor de ingresar el Nombre del Contacto y Proveedor"
										required data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.contacto}"/>" />
								</div>
								<div class="col-md-2">
									<label for="Telefono">* Teléfono y Correo Electrónico:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Teléfono y Correo Electrónico"
										name="datos_contacto" id="datos_contactoModal"
										title="Favor de ingresar el Teléfono y Correo Electrónico"
										required data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.datos_contacto}"/>" />
								</div>
							</div>


							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="responsable">* Responsable de seguimiento
										Telcel:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Responsable de seguimiento Telcel"
										name="responsable" id="responsableModal"
										title="Favor de ingresar el Responsable de seguimiento Telcel"
										required data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.responsable}"/>"/>
								</div>
								<div class="col-md-2">
									<label for="extension">* Ext. y Correo Electrónico:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Ext. y Correo Electrónico" name="datos_responble"
										id="datos_responbleModal"
										title="Favor de ingresar Ext. y Correo Electrónico:" required
										data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.datos_responble}"/>"/>
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
										value="<c:out value="${solicitud.inver_person}"  />"
										maxlength="10" />
								</div>
								<div class="col-md-2">
									<label for="tipoMoneda">* Tipo de Moneda:</label>
								</div>
								<div class="col-md-2">
								   <select class="form-control" name="tipo_moneda.clave" id="tipo_monedaModal">
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
										data-toggle="tooltip" data-placement="bottom"
										value="<c:out value="${solicitud.inver_grupo}"/>"
										maxlength="8" />
								</div>
							</div>

							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="participantes">* No. de Participantes:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="No. de Participantes" name="num_part"
										id="num_partModal" title="Solo números" required
										onKeyup="calculoTotal(this.value);" data-toggle="tooltip"
										data-placement="bottom"
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
										value="<c:out value="${solicitud.total}"/>" maxlength="8" readonly="readonly"/>
								</div>
							</div>

							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="costoAsociados">* Costos Asociados: (3)</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Costos Asociados" name="costo_aso"
										id="costo_asoModal" data-toggle="tooltip"
										data-placement="bottom" title="Costos Asociados"
										value="<c:out value="${solicitud.costo_aso}"/>" maxlength="8" />
								</div>
								<div class="col-md-2">
									<button type="button" class="btn btn-primary"
										data-toggle="modal" id="btnLimpiarCalulo">
										<span class="glyphicon glyphicon-erase"></span>&nbsp;Limpiar
									</button>
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
											placeholder="Expediente"
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
											data-toggle="modal" aria-describedby="basic-addon2"
											data-toggle="tooltip" data-placement="bottom"
											id="inicioListaAdicionalEdit" value="${solicitud.clave}">
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
							<h4 class="panel-title">* Justificación Especifica del Curso</h4>
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
													id="funcionesModel" >
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
													id="proyectoModel" >
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
													id="aplicabilidadModel" >
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
													id="actualizacionModel" >
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
													title="Favor de ingresar otra opcion" data-toggle="tooltip"
													data-placement="bottom" maxlength="20" value="<c:out value="${solicitud.otro}"/>"  /></td>
											</tr>
										</table>
									</td>
									<td><textarea cols="114" rows="9" name="detalle"
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

	<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4 class="panel-title">Opciones</h4>
								<br>
							</div>
							<div class="panel-body">

								<div class="row">
									<div class="col-md-12">
									 <div id="validationmessage">
								        <div class="row">
								          <div class="col-md-12">
								            <div class="alert alert-danger" role="alert">
								              <strong>Error de validaci&oacute;n: </strong><span class="validationerror"></span>
								            </div>
								          </div>
								        </div>
								    </div>
								    </div>
			     
									<div class="col-md-12">
										<button type="button" class="btn btn-primary"
											data-toggle="modal" id="btnLimpiar">
											<span class="glyphicon glyphicon-erase"></span>&nbsp;Limpiar
										</button>
										<button type="button" class="btn btn-primary" id="btnActualizar">Actualizar</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
	</div>	
</form>
</body>
</html>