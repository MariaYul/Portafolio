<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <meta name="description" content="Sistema de Capacitación Técnica para gestionar los documentos de los candidatos, empleados y ex-empleados de Telcel"/>
  <meta name="author" content="Emmanuel Delgado Mej&iacute;a"/>
  
  <title>Solicitud de Cursos - <sitemesh:write property='title' /></title>
  
  <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen"/>
  <link href="<c:url value="/css/bootstrap-theme.min.css"/>" rel="stylesheet"/>
  
  <script src="<c:url value="/js/jquery.js"/>"/></script>
  <script src="<c:url value="/js/bootstrap.min.js"/>"/></script>
  <script src="<c:url value="/js/utils.js"/>"/></script>
  
  
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" />
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/navigation.css"/>" />
  
  <link rel="icon" type="image/png" href="<c:url value="/images/foldericon.png"/>" />
  
  <sitemesh:write property='head' />
</head>
<body>
    <nav class="navbar navbar-default navbar-fixed-top" id="navbarmenu">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<c:url value="/inicio.htm"/>"><span class="glyphicon glyphicon-folder-open"></span>&nbsp;&nbsp;Solicitud de Cursos</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
                      
            <sec:authorize access="hasRole('ROL_USUARIO_CONSULTAR')">
              <c:choose>
                <c:when test="${activeitemmenu == 'liusuario'}">
                  <li class="active"><a href="<c:url value="/usuario/main.htm"/>">Usuario</a></li>
                </c:when>
                <c:otherwise>
                  <li><a href="<c:url value="/usuario/main.htm"/>">Usuario</a></li>
                </c:otherwise>
              </c:choose>
            </sec:authorize>
            
            <c:choose>
              <c:when test="${activeitemmenu == 'lisolicitud'}">
                <li class="active"><a href="<c:url value="/solicitud/main.htm"/>">Solicitud Curso</a></li>
              </c:when>
              <c:otherwise>
                <li><a href="<c:url value="/solicitud/main.htm"/>">Solicitud Curso</a></li>
              </c:otherwise>
            </c:choose>
          
            
          </ul>
          
          <ul class="nav navbar-nav navbar-right">
            <li class="navbar-text">
              <span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;
              <sec:authentication property="principal" />
            </li>
            <li><a href="<c:url value="/logout"/>"><span class="glyphicon glyphicon-log-out" data-toggle="tooltip" data-placement="bottom" title="Cerrar Sesi&oacute;n"></span></a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
  
    <div class="breakline"></div>
    <div class="breakline"></div>
    
    <sitemesh:write property='body'>
      Contenido a reemplazar
    </sitemesh:write>
    
    <div class="breakline"></div>
    <div class="breakline"></div>
    
    <div class="footer">
      <div class="container textcenter">
        <p class="text-muted fontminus1">
          Solicitud de Cursos Versi&oacute;n 1.0
          <br/>&copy;&nbsp;Todos los derechos reservados Radiom&oacute;vil Dipsa S.A. de C.V. 2021
          &nbsp;|&nbsp;Contacto: <a href="mailto: capacitaciontecnica@telcel.com?subject=Sistema de Solicitud de Curso">capacitaciontecnica@telcel.com</a>
        </p>
      </div>
    </div>
</body>
</html>