<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>&Aacute;reas</title>
  
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/pagination.css"/>"/>
  <script src="<c:url value="/js/area.js"/>"></script>
</head>
<body>
    
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-4">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">B&uacute;squeda de &Aacute;rea</h4>
            </div>
            <div class="panel-body">
              <form id="frmSearch">
                <div class="row">
                  <div class="col-md-4">
                    <label for="nombre">&Aacute;rea</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="Nombre del &aacute;rea" id="nombre" name="nombre" maxlength="100"/>
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
                  <sec:authorize access="hasRole('ROL_AREA_REGISTRAR')">
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
              <h4 class="panel-title">&Aacute;reas</h4>
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
            <h4 class="modal-title" id="modalNuevoLabel">Registrar nueva &aacute;rea</h4>
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
            <h4 class="modal-title" id="modalEditarLabel">Editar &aacute;rea</h4>
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