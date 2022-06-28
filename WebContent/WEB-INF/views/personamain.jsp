<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Personas</title>
  
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/pagination.css"/>"/>
  <script src="<c:url value="/js/persona.js"/>"></script>
</head>
<body>
    
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-5">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">B&uacute;squeda de Persona</h4>
            </div>
            <div class="panel-body">
              <form id="frmSearch">
                <div class="row">
                  <div class="col-md-4">
                    <label for="clave">N&uacute;mero de candidato</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="Identificador" id="clave" name="clave" maxlength="8"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="numeroEmpleado">N&uacute;mero de empleado</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="N&uacute;mero de empleado" id="numeroEmpleado" name="numeroEmpleado" maxlength="8"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="nombre">Nombre</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="Nombre del empleado" id="nombre" name="nombre" maxlength="150"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="apellidoPaterno">Apellido Paterno</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="Apellido Paterno" id="apellidoPaterno" name="apellidoPaterno" maxlength="150"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="apellidoMaterno">Apellido Materno</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="Apellido Paterno" id="apellidoMaterno" name="apellidoMaterno" maxlength="150"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="curp">CURP</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="curp" id="curp" name="curp" maxlength="18"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="rfc">RFC</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="rfc" id="rfc" name="rfc" maxlength="13"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="region">Regi&oacute;n</label>
                  </div>
                  <div class="col-md-8">
                    <select class="form-control" name="region" id="region">
                      <option value="-1">-- Todas --</option>
                      <c:forEach items="${regiones}" var="region">
                        <option value="${region.clave}">${region.nombre}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="nbdepartamento">Departamento</label>
                  </div>
                  <div class="col-md-8">
                    <input type="hidden" id="departamento" name="departamento">
                    <input type="text" class="form-control" placeholder="departamento" id="nbdepartamento" name="nbdepartamento" readonly="readonly"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="nblocalidad">Localidad</label>
                  </div>
                  <div class="col-md-8">
                    <input type="hidden" id="localidad" name="localidad">
                    <input type="text" class="form-control" placeholder="localidad" id="nblocalidad" name="nblocalidad" readonly="readonly"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="nbpuesto">Puesto</label>
                  </div>
                  <div class="col-md-8">
                    <input type="hidden" id="puesto" name="puesto">
                    <input type="text" class="form-control" placeholder="puesto" id="nbpuesto" name="nbpuesto" readonly="readonly"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-12">
                    <button type="button" class="btn btn-primary" data-toggle="modal" id="btnLimpiar">
                      <span class="glyphicon glyphicon-erase"></span>&nbsp;Limpiar
                    </button>
                    <button type="button" class="btn btn-primary" data-toggle="modal" id="btnBuscar">
                      <span class="glyphicon glyphicon-search"></span>&nbsp;Buscar
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
        
        <div class="col-md-7">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">Personas</h4>
            </div>
            <div id="resultadosbusqueda">
              <c:choose>
                <c:when test="${not empty info}">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="alert alert-success" role="alert" id="msginfo">
                        <span class="glyphicon glyphicon-info-sign"></span>&nbsp;<strong>Mensaje: </strong><c:out value="${info}" escapeXml="false"/>
                      </div>
                    </div>
                  </div>
                </c:when>
                <c:when test="${not empty error}">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="alert alert-danger" role="alert" id="msgerror">
                        <span class="glyphicon glyphicon-info-sign"></span>&nbsp;<strong>Error: </strong><c:out value="${error}" escapeXml="false"/>
                      </div>
                    </div>
                  </div>
                </c:when>
                <c:otherwise>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="alert alert-info" role="alert">
                        <span class="glyphicon glyphicon-info-sign"></span>&nbsp;Por favor realice una b&uacute;squeda
                      </div>
                    </div>
                  </div>
                </c:otherwise>
              </c:choose>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    
    <div class="modal fade" id="modalDepartamento" tabindex="-1" role="dialog" aria-labelledby="modalDepartamentoLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="modalDepartamentoLabel">Seleccione un Departamento</h4>
          </div>
          <div class="modal-body" id="modalDepartamentoBody">
            ...
          </div>
        </div>
      </div>
    </div>
    
    <div class="modal fade" id="modalLocalidad" tabindex="-1" role="dialog" aria-labelledby="modalLocalidadLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="modalLocalidadLabel">Seleccione una Localidad</h4>
          </div>
          <div class="modal-body" id="modalLocalidadBody">
            ...
          </div>
        </div>
      </div>
    </div>
    
    <div class="modal fade" id="modalPuesto" tabindex="-1" role="dialog" aria-labelledby="modalPuestoLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="modalPuestoLabel">Seleccione un Puesto</h4>
          </div>
          <div class="modal-body" id="modalPuestoBody">
            ...
          </div>
        </div>
      </div>
    </div>
</body>
</html>