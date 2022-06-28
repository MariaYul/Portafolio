<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="es-MX">
<head>
  <meta charset="ISO-8859-1"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <!-- <meta name="viewport" content="width=device-width, initial-scale=1"/> -->
  <meta name="viewport" content="width = 1050, user-scalable = no" />
  <meta name="description" content="Sistema de Capacitación Técnica para gestionar los documentos de los candidatos, empleados y ex-empleados de Telcel" />
  <meta name="author" content="Emmanuel Delgado Mej&iacute;a" />
  
  <title>Error</title>
  
  <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen"/>
  <link href="<c:url value="/css/navigation.css"/>" rel="stylesheet"/>
  <link href="<c:url value="/css/main.css"/>" rel="stylesheet"/>
    
  <script src="<c:url value="/js/jquery.js"/>"/></script>
  
  <link rel="icon" type="image/png" href="<c:url value="/images/foldericon.png"/>"/>
</head>
<body>
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-12">
          <div class="alert alert-danger" role="alert" id="msgerror">
            <span class="glyphicon glyphicon-info-sign"></span>&nbsp;<strong>Error al procesar su petici&oacute;n: </strong><c:out value="${error}" escapeXml="false"/>
          </div>
        </div>
      </div>
    </div>
</body>
</html>