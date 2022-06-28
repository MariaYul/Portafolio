<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
  <title>Solicitud de Curso</title>
  
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
              <h4 class="panel-title">Solicitud de Curso</h4>
            </div>
            <div class="panel-body">
              <form id="frmSearch">
                <div class="row">
                  <div class="col-md-4">
                    <label for="numeroEmpleado">Nombre del Curso</label>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" placeholder="Id de solicitud" id="numeroEmpleado" name="numeroEmpleado"/>
                  </div>
                </div>
                
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
        
      
      </div>
    </div>
    
 
    
  
   
</body>
</html>