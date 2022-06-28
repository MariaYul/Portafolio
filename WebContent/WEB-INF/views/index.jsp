<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Inicio</title>
  <script src="<c:url value="/js/jquery.js"/>" /></script>
  <script src="<c:url value="/js/jquery.min.js"/>" /></script>
  <script src="<c:url value="/js/bootstrap.min.js"/>" /></script>  
  <script src="<c:url value="/js/utils.js"/>" /></script>
  <script src="<c:url value="/js/index.js"/>" /></script>
</head>
<body>
 
    <div class="container">
      <div class="panel panel-primary">
        <div class="panel-heading">
          <h4 class="panel-title"><span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;&nbsp;Inicio de sesi&oacute;n</h4>
        </div>
        <div class="panel-body">
          <form id="startSession" action="<c:url value='/index.htm'/>"  Method="Post" onsubmit="return validarFormulario(this.id);">
            <div class="row">
              <div class="col-md-2">
                <label for="numeroEmpleado">N&uacute;mero de empleado</label>
              </div>
              <div class="col-md-4">
                <div class="input-group">
                  <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                  <input type="text" class="form-control" placeholder="N&uacute;mero de empleado" aria-describedby="basic-addon1"
                    data-toggle="tooltip" data-placement="bottom" title="N&uacute;mero de empleado" id="numeroEmpleado" autofocus="autofocus"
                    name = "numero"/>
                </div>
              </div>
              <div class="col-md-2">
                <label for="contrasennia">Contrase&ntilde;a</label>
              </div>
              <div class="col-md-4">
                <div class="input-group">
                  <span class="input-group-addon" id="basic-addon2"><i class="glyphicon glyphicon-lock"></i></span>
                  <input type="password" class="form-control" placeholder="Contrase&ntilde;a" aria-describedby="basic-addon2"
                    data-toggle="tooltip" data-placement="bottom" title="Contrase&ntilde;a" id="contrasennia" 
                    name = "password"/>
                </div>
              </div>
            </div>
            
            <div class="breakline"></div>
            
            <div class="row">
              <div class="col-md-4">
                <button type="submit" class="btn btn-primary" id="btnIniciarSesion">
                  Iniciar sesi&oacute;n
                </button>
              </div>
              <div class="col-md-8">
                <c:if test="${not empty msgerror}">
                  <div class="alert alert-danger" role="alert">
                    <strong>Error:&nbsp;</strong><span>${msgerror}</span>
                  </div>
                </c:if>
              </div>
            </div>
            
             <div class="modal fade" id="modalErrorInicio" tabindex="-1" role="dialog" aria-labelledby="modalErrorInicioLabel" aria-hidden="true">
                  <div class="modal-dialog">
                       <div class="modal-content">
                          <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="modalErrorInicioLabel">Error...</h4>
                          </div>
                          <div class="modal-body" id="modalErrorInicioBody">
                              ...
                          </div>
                          <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" data-dismiss="modal">Aceptar</button>
                          </div>
                    </div>
                   </div>
            </div>
          </form>
        </div>
      </div>
      
      <div id="carousel-index" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carousel-index" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-index" data-slide-to="1"></li>
          <li data-target="#carousel-index" data-slide-to="2"></li>
          <li data-target="#carousel-index" data-slide-to="3"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
          <div class="item active">
            <img src="./images/registro.png"/>
            <div class="carousel-caption">
              <h4>Registro de Solicitudes de Cursos</h4>
              
            </div>
          </div>
          <div class="item">
            <img src="./images/consulta.png"/>
            <div class="carousel-caption">
              <h4>Consulta de Solicitudes de Cursos</h4>
              
            </div>
          </div>
          <div class="item">
            <img src="./images/gestion.png"/>
            <div class="carousel-caption">
              <h4>Gestión de solicitudes de Cursos</h4>
              
            </div>
          </div>
          <div class="item">
            <img src="./images/seguimiento.png"/>
            <div class="carousel-caption">
              <h4>Seguimiento a Solicitudes de Cursos</h4>
            </div>
          </div>
        </div>
        <a class="left carousel-control" href="#carousel-index" role="button" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-index" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
    </div>
</body>
</html>