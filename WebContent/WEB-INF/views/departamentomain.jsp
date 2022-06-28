<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/pagination.css"/>"/>
  <script src="<c:url value="/js/departamento.js"/>"></script>
</head>
<body>
    
    <form id="frmSearchDepartamento">
      <div class="row">
        <div class="col-md-2">
          <label for="nombreDepartamentoModal">Departamento</label>
        </div>
        <div class="col-md-8">
          <input type="text" class="form-control" placeholder="Nombre del departamento" id="nombreDepartamentoModal" name="nombreDepartamentoModal"/>
        </div>
        <div class="col-md-2">
          <button type="button" class="btn btn-primary" data-toggle="modal" id="btnBuscarDepartamento">
            <span class="glyphicon glyphicon-search"></span>&nbsp;Buscar
          </button>
        </div>
      </div>
    </form>
    
    <div class="shortbreakline"></div>
    
    <div id="resultadosbusquedadepartamento" class="scrollbar">
      
    </div>
</body>
</html>