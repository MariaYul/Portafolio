<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
  <title>Tipos de documento</title>
  
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/pagination.css"/>"/>
  <script src="<c:url value="/js/tipodocumento.js"/>"></script>
</head>
<body>
    
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-4">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">B&uacute;squeda de Tipo de Documento</h4>
            </div>
            <div class="panel-body">
              <form id="frmSearch">
                <div class="row">
                  <div class="col-md-4">
                    <label for="nombre">Nombre</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="Nombre del tipo de documento" id="nombre" name="nombre" maxlength="250"/>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="area">&Aacute;rea</label>
                  </div>
                  <div class="col-md-8">
                    <select class="form-control" name="area" id="area">
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
                    <label for="fase">Fase</label>
                  </div>
                  <div class="col-md-8">
                    <select class="form-control" name="fase" id="fase">
                      <option value="-1">-- Todas --</option>
                      <c:forEach items="${fases}" var="fase">
                        <option value="${fase.clave}">${fase.nombre}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                
                <div class="shortbreakline"></div>
                
                <div class="row">
                  <div class="col-md-4">
                    <label for="multiregistro">Multiregistro</label>
                  </div>
                  <div class="col-md-8">
                    <select class="form-control" name="multiregistro" id="multiregistro">
                      <option value="-1">-- Todos --</option>
                      <option value="0">No</option>
                      <option value="1">Si</option>
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
                
                <div class="breakline"></div>
                
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
                  <sec:authorize access="hasRole('ROL_TPDOCUMENTO_REGISTRAR')">
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
              <h4 class="panel-title">Tipos de documento</h4>
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
            <h4 class="modal-title" id="modalNuevoLabel">Registro de Tipo de Documento</h4>
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
            <h4 class="modal-title" id="modalEditarLabel">Edición de Tipo de Documento</h4>
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
</body>
</html>