<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
  <title>Usuarios</title>
  
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/pagination.css"/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui-1.10.4.custom.css"/>"/>
  <script src="<c:url value="/js/jquery-ui.js"/>"/></script>
  <script src="<c:url value="/js/usuario.js"/>"></script>
</head>
<body>
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-4">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">B&uacute;squeda de Usuario</h4>
            </div>
            <div class="panel-body">
              <form id="frmSearch">
                <div class="row">
                  <div class="col-md-4">
                    <label for="numeroEmpleado">N&uacute;mero de empleado</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="N&uacute;mero de empleado" id="numeroEmpleado" name="numeroEmpleado"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="nombre">Nombre</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="Nombre del empleado" id="nombre" name="nombre"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="apellidoPaterno">Apellido Paterno</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="Apellido Paterno" id="apellidoPaterno" name="apellidoPaterno"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="apellidoMaterno">Apellido Materno</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="Apellido Paterno" id="apellidoMaterno" name="apellidoMaterno"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="claveArea">&Aacute;rea</label>
                  </div>
                  <div class="col-md-8">
                    <select class="form-control" name="claveArea" id="claveArea">
                      <option value="-1">-- Todas --</option>
                      <c:forEach items="${areas}" var="area">
                        <option value="${area.clave}">${area.nombre}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="claveRegion">Regi&oacute;n</label>
                  </div>
                  <div class="col-md-8">
                    <select class="form-control" name="claveRegion" id="claveRegion">
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
                    <label for="estatus">Estatus</label>
                  </div>
                  <div class="col-md-8">
                    <select class="form-control" name="estatus" id="estatus">
                      <option value="-1">-- Todos --</option>
                      <option value="0">Inactivo</option>
                      <option value="1">Activo</option>
                    </select>
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
          
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">Opciones</h4>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-12">
                  <sec:authorize access="hasRole('ROL_USUARIO_REGISTRAR')">
                    <button type="button" class="btn btn-primary" data-toggle="modal" id="btnNuevoMostrar">
                      <span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Nuevo
                    </button>
                  </sec:authorize>
                </div>
              </div>
            </div>
          </div>
        </div>
        
        <div class="col-md-8">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">Usuarios</h4>
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
    
    <div class="modal fade" id="modalNuevo" tabindex="-1" role="dialog" aria-labelledby="modalNuevoLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="modalNuevoLabel">Registrar nuevo usuario</h4>
          </div>
          <div class="modal-body" id="modalNuevoBody">
            ...
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
            <button type="button" class="btn btn-primary" id="btnNuevoRegistrar">Guardar</button>
          </div>
        </div>
      </div>
    </div>
    
    <div class="modal fade" id="modalEditar" tabindex="-1" role="dialog" aria-labelledby="modalEditarLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="modalEditarLabel">Editar usuario</h4>
          </div>
          <div class="modal-body" id="modalEditarBody">
            ...
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
            <button type="button" class="btn btn-primary" id="btnActualizar">Guardar</button>
          </div>
        </div>
      </div>
    </div>
    
    <div class="modal fade" id="modalRoles" tabindex="-1" role="dialog" aria-labelledby="modalRolesLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="modalRolesLabel">Roles asignados al usuario</h4>
          </div>
          <div class="modal-body" id="modalRolesBody">
            ...
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>
          </div>
        </div>
      </div>
    </div>
</body>
</html>