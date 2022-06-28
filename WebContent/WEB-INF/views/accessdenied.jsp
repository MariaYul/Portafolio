<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Recurso protegido</title>
  
  <script type="text/javascript" src="/solicitudcurso/js/accessdenied.js"></script>
</head>
<body>
    <div class="container">
      <div class="row">
        <div class="col-md-12 text-center">
          <div class="panel panel-warning">
            <div class="panel-heading">
              <h4 class="panel-title"><span class="glyphicon glyphicon-stop"></span>&nbsp;ACCESO DENEGADO</h4>
            </div>
            <div class="panel-body">
              <c:if test="${not empty msgdenied}">
                <p>
                  ${msgdenied}
                  <br />
                  <br />
                  <button type="submit" class="btn btn-primary" id="btnRedirigir">
                    <span class="glyphicon glyphicon-arrow-left">&nbsp;Inicio</span>
                  </button>
                </p>
               </c:if>
            </div>
          </div>
        </div>
        
      </div>
    </div>
</body>
</html>