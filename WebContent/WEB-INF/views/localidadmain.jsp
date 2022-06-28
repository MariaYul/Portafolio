<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/pagination.css"/>"/>
  <script src="<c:url value="/js/localidad.js"/>"></script>
</head>
<body>
    
    <form id="frmSearchLocalidad">
      <div class="row">
        <div class="col-md-2">
          <label for="nombreLocalidadModal">Localidad</label>
        </div>
        <div class="col-md-8">
          <input type="text" class="form-control" placeholder="Nombre de la localidad" id="nombreLocalidadModal" name="nombreLocalidadModal"/>
        </div>
        <div class="col-md-2">
          <button type="button" class="btn btn-primary" data-toggle="modal" id="btnBuscarLocalidad">
            <span class="glyphicon glyphicon-search"></span>&nbsp;Buscar
          </button>
        </div>
      </div>
    </form>
    
    <div class="shortbreakline"></div>
    
    <div id="resultadosbusquedalocalidad" class="scrollbar">
      
    </div>
</body>
</html>