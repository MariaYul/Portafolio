<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edicion de area</title>
</head>
<body>
    <div class="row">
      <div class="col-md-12">
        <div class="well well-sm">
          *&nbsp;Campo requerido
        </div>
      </div>
    </div>

    <form action="<c:url value="/area/edit.htm"/>" method="post" id="frmEdit">
      <div class="row">
        <div class="col-md-4">
          <label for="nombre">* Nombre</label>
        </div>
        <div class="col-md-8">
          <input type="hidden" id="clave" name="clave" value="<c:out value="${area.clave}"/>"/>
          <input type="text" class="form-control" placeholder="Nombre del &aacute;rea" name="nombre" id="nombreModal"
              data-toggle="tooltip" data-placement="bottom" title="Nombre del &aacute;rea" value="<c:out value="${area.nombre}"/>" maxlength="100"/>
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