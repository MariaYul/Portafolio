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
<title>Registro de Solicitud</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/jquery-ui-1.10.4.custom.css"/>" />
<script src="<c:url value="/js/jquery.js"/>"/></script>
  <script src="<c:url value="/js/solicitud.js"/>"/></script>
  <script src="<c:url value="/js/jquery-ui.js"/>"/></script>
  <script src="<c:url value="/js/solicitudutils.js"/>"/></script>
   <script src="<c:url value="/js/bootstrap.min.js"/>"/></script>  
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

	<form action="<c:url value="/solicitud/add.htm"/>" method="post"
		id="frmAdd">

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
										title="Nombre del Curso" maxlength="100" />
								</div>
							</div>

							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="fechaInicio">* Fecha Inicio: (1)</label>
								</div>
								<div class="col-md-4">
									<input type="date" class="form-control" placeholder="Fecha Inicio"
										name="fecha_inicio" id="fechaInicio"
										title="Favor de ingresar la fecha Inicial" required
										data-toggle="tooltip" data-placement="bottom" maxlength="10" />
								</div>
								<div class="col-md-2">
									<label for="fechaFin">* Fecha Fin:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control" placeholder="Fecha Fin"
										name="fecha_fin" id="fechaFin"
										title="Favor de dar click sobre el campo para calcular" required
										data-toggle="tooltip" data-placement="bottom" maxlength="10" readonly="readonly"/>
								</div>
							</div>


							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="duracionDias">* Duraci?n d?as:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Duraci?n en d?as" name="duracion"
										id="duracionModal"
										title="Favor de ingresar la duraci?n en dias" required
										data-toggle="tooltip" data-placement="bottom"
										title="Duraci?n en d?as" maxlength="3" />
								</div>
								<div class="col-md-2">
									<label for="horarioCurso">* Horario del curso:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="HH:mm - HH:mm" name="horario"
										id="horarioModal"
										title="Favor de ingresar el Horario del curso" required
										data-toggle="tooltip" data-placement="bottom"  maxlength="23"/>
<!-- 										pattern="[\d]{2}:[\d]{2}-[\d]{2}:[\d]{2}" -->
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
										 />
								</div>
								<div class="col-md-2">
									<label for="Telefono">* Tel?fono y Correo Electr?nico:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Tel?fono y Correo Electr?nico"
										name="datos_contacto" id="datos_contactoModal"
										title="Favor de ingresar el Tel?fono y Correo Electr?nico"
										required data-toggle="tooltip" data-placement="bottom"
										 />
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
										 />
								</div>
								<div class="col-md-2">
									<label for="extension">* Ext. y Correo Electr?nico:</label>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control"
										placeholder="Ext. y Correo Electr?nico:" name="datos_responble"
										id="datos_responbleModal"
										title="Favor de ingresar Ext. y Correo Electr?nico" required
										data-toggle="tooltip" data-placement="bottom"  />
								</div>
<!-- 								<div class="col-md-3"> -->
<!-- 									<input type="email" class="form-control" -->
<!-- 										placeholder="Correo Electr?nico" name="correo_responble" -->
<!-- 										id="datos_responbleModal" -->
<!-- 										title="Favor de ingresar Correo Electr?nico:" required -->
<!-- 										data-toggle="tooltip" data-placement="bottom"  /> -->
<!-- 								</div> -->
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
							<h4 class="panel-title">Inversi?n del Curso</h4>
						</div>
						<div class="panel-body">

							<div class="row">
								<div class="col-md-2">
									<label for="inversion">Inversi?n por Persona: (2)</label>
								</div>
								<div class="col-md-4">
									<input type="number" class="form-control"
										placeholder="Inversi?n por Persona" name="inver_person"
										id="inver_personModal" maxlength="10"
										onblur="calculoPersona(this.value);" title="Solo n?meros" 
										data-toggle="tooltip" data-placement="bottom" />
								</div>
								<div class="col-md-2">
									<label for="tipoMoneda">* Tipo de Moneda:</label>
								</div>
								<div class="col-md-2">
									<select class="form-control" name="tipo_moneda.clave"
										id="tipo_monedaModal">
										<option value="-1">-- Seleccione --</option>
<!-- 										<option value="1">M?xico Pesos MXN</option> -->
<!-- 										<option value="2">United States Dollars USD</option> -->
<!-- 										<option value="3">Euro EUR</option> -->
										<c:forEach items="${tipos}" var="tipo">
							                  <option value="${tipo.clave}">${tipo.nombre}</option>
							            </c:forEach>
									</select>
								</div>
							</div>


							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="InverGrupo">Inversi?n por Grupo:</label>
								</div>
								<div class="col-md-4">
									<input type="number" class="form-control"
										placeholder="Inversi?n por grupo" name="inver_grupo"
										id="inver_grupoModal" onblur="calculoGrupo(this.value);"
										data-toggle="tooltip" data-placement="bottom" maxlength="8" 
										title="Inversi?n por grupo"/>
								</div>
							</div>

							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="participantes">* No. de Participantes:</label>
								</div>
								<div class="col-md-4">
									<input type="number" class="form-control"
										placeholder="No. de Participantes" name="num_part"
										id="num_partModal" title="Solo n?meros" required
										onKeyup="calculoTotal(this.value);" data-toggle="tooltip"
										data-placement="bottom" maxlength="8" />
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
										data-placement="bottom" title="Total" maxlength="8"  readonly="readonly"/>
								</div>
							</div>

							<div class="shortbreakline"></div>

							<div class="row">
								<div class="col-md-2">
									<label for="costosAsociados">* Costos Asociados: (3)</label>
								</div>
								<div class="col-md-4">
									<input type="number" class="form-control"
										placeholder="Costos Asociados" name="costo_aso"
										id="costo_asoModal" data-toggle="tooltip"
										data-placement="bottom" title="Costos Asociados" maxlength="8"  />
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
							<h4 class="panel-title">Datos del Participante (4)</h4>
						</div>
						<div class="panel-body">

							<table class="table table-striped table-bordered">
								<tr>
									<th>No. Emp</th>
									<th>Participante</th>
									<th>Puesto</th>
									<th>Centro de Costos</th>
								</tr>
								<c:forEach items="${empMap.empleadosMap}" var="empleadosMap"
									varStatus="status">
									<tr>
										<th><input type="text" class="form-control"
											placeholder="Expediente"
											name="empleadosMap['${empleadosMap.key}']" id="empleadosMap${empleadosMap.key}"
											required
											onblur="ontenerDatosEmp(this.value,'${empleadosMap.key}');" /></th>
										<th><div id="nombre${empleadosMap.key}"></div></th>
										<th><div id="puesto${empleadosMap.key}"></div></th>
										<th><div id="costos${empleadosMap.key}"></div></th>
									</tr>
								</c:forEach>
							</table>
							<table>
								<tr>

									<td class="textcenter">
										<button id="addParticipants" type="button" class="btn btn-primary"
											data-toggle="modal" onclick="inicioListaAdicional();">
											<span class="glyphicon glyphicon-list-alt"></span> Adicionar
											Participantes
										</button>


									</td>
								</tr>
							</table>

						</div>
					</div>
				</div>
			</div>

			<div class="row"></div>
		</div>


		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">* Justificaci?n Espec?fica del Curso</h4>
							<br>
							<h5 class="panel-title">Seleccione una(s) opci?n(es) y
								descr?bala a detalle</h5>
						</div>
						<div class="panel-body">
							<table class="table table-striped table-bordered table-responsive">
								<tr>
									<td>
										<table>
											<tr>
												<td>Funciones:</td>
												<td><select class="form-control" name="funciones"
													id="funcionesModel">
														<option value="-1">-- Seleccione --</option>
														<option value="1">X</option>
												</select></td>
											</tr>

											<tr>
												<td>Proyectos:</td>
												<td><select class="form-control" name="proyecto"
													id="proyectoModel">
														<option value="-1">-- Seleccione --</option>
														<option value="1">X</option>
												</select></td>
											</tr>

											<tr>
												<td>Aplicabilidad:</td>
												<td><select class="form-control" name="aplicabilidad"
													id="aplicabilidadModel">
														<option value="-1">-- Seleccione --</option>
														<option value="1">X</option>
												</select></td>
											</tr>

											<tr>
												<td>Actualizaci?n:</td>
												<td><select class="form-control" name="actualizacion"
													id="actualizacionModel">
														<option value="-1">-- Seleccione --</option>
														<option value="1">X</option>
												</select></td>
											</tr>

											<tr>
												<td>Otro:</td>
												<td><input type="text" class="form-control"
													placeholder="Otro" name="otro" id="otroModel"
													title="Favor de ingresar otra opcion" data-toggle="tooltip"
													data-placement="bottom" maxlength="20" /></td>
											</tr>
										</table>
									</td>
									<td width="80%"><textarea rows="9" name="detalle"
											id="detalleModel" style="width: 100%"></textarea></td>
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
							<h4 class="panel-title">Consideraciones Importantes</h4>
						</div>

						<div class="panel-body">
							<div class="row">
								<div class="col-md-10">
									<label>(1) La Gerencia de Capacitaci?n y Desarrollo es
										la ?nica ?rea autorizada que confirmar? la fecha de curso con
										el Proveedor,una vez concluido el proceso de negociaci?n.</label> <label
										for="fecha_inicio"> (2) La negociaci?n de precios y
										condiciones de pago del curso, la realizar? exclusivamente la
										Direcci?n de Compras Telcel</label> <label for="fecha_inicio">
										(3) Los Costos asociados aplican cuando el curso se imparta
										fuera de la ciudad de orgine y(o en renta de salas de
										Capacitaci?n</label> <label for="fecha_inicio">(4) M?s de 3
										participantes ampliar la justificaci?n (anexar lista
										complementaria, misma que se encuentra en este archivo</label> <label
										for="fecha_inicio"> Anexar a esta Solicitud: Perfil de
										puesto Basado en Competencias Laborales y Contenido Tem?tico
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
								<h4 class="panel-title">Para uso exclusivo de Capacitaci?n</h4>
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
														id="presupuestoModel">
															<option value="-1">-- Seleccione --</option>
															<option value="1">X</option>
													</select></td>
												</tr>

												<tr>
													<td>Hist?rico de Capacitaci?n:</td>
													<td><select class="form-control" name="historico"
														id="historicoModel">
															<option value="-1">-- Seleccione --</option>
															<option value="1">X</option>
													</select></td>
												</tr>

												<tr>
													<td>Contenido Tem?tico:</td>
													<td><select class="form-control" name="contematico"
														id="contematicoModel">
															<option value="-1">-- Seleccione --</option>
															<option value="1">X</option>
													</select></td>
												</tr>

												<tr>
													<td>Trayector?a:</td>
													<td><select class="form-control" name="trayectoria"
														id="trayectoriaModel">
															<option value="-1">-- Seleccione --</option>
															<option value="1">X</option>
													</select></td>
												</tr>

											</table>
										<td>
											<div class="panel-heading">
												<h4 class="panel-title">Observaciones</h4>
											</div>
										</td>
										<td><textarea cols="40" rows="9" name="observaciones"
												id="observacionesModel"></textarea></td>
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
										<button type="button" class="btn btn-primary"
											id="btnNuevoRegistrar">Guardar Solicitud</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>	

	</form>

	<div class="modal fade" id="modalConsultaLista" tabindex="-1"
		role="dialog" aria-labelledby="modalNuevoLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="modalNuevoLabel">Agregar
						participante</h4>
				</div>
				<div class="modal-body" id="modalConsultaListaBody">...</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>


</body>
</html>