<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Registro de &aacute;rea</title>
</head>
<body>
    <div class="row">
      <div class="col-md-12">
        <div class="well well-sm">
          *&nbsp;Campo requerido
        </div>
      </div>
    </div>
      
    <form action="<c:url value="/area/add.htm"/>" method="post" id="frmAdd">
      <div class="row">
        <div class="col-md-4">
          <label for="nombreModal">* Nombre</label>
        </div>
        <div class="col-md-8">
          <input type="text" class="form-control" placeholder="Nombre del &aacute;rea" name="nombre" id="nombreModal"
              data-toggle="tooltip" data-placement="bottom" title="Nombre del &aacute;rea" maxlength="100"/>
        </div>
      </div>
      
      <div class="shortbreakline"></div>
      
      <div id="validationmessage">
        <div class="row">
          <div class="col-md-12">
            <div class="alert alert-danger" role="alert">
              <strong>Error de validaci&oacute;n: </strong><span class="validationerror"></span>
            </div>
          </div>
        </div>
      </div>
    </form>
</body>
</html>