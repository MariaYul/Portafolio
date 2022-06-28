<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/taglibs/paginationTagLib.tld"%>
<!DOCTYPE html>
<html>
<head>
  <title>Lista Adicional</title>
  
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/pagination.css"/>"/>
  <script src="<c:url value="/js/jquery-ui.js"/>"/></script>
  <script src="<c:url value="/js/jquery.js"/>"/></script>
  <script src="<c:url value="/js/personadetail.js"/>"></script>
  <script src="<c:url value="/js/participante.js"/>"/></script>

</head>
<body>

	
            <div class="panel-body" >
            
          <c:choose>
      <c:when test="${not empty personas}">
        <div class="nav-pagination">
          <paginator:paginate max="5" offset="${offset}" count="${count}" uri="#" next="&raquo;" previous="&laquo;" />
        </div>
        
        <table class="table table-striped table-bordered">
          <tr>
          	<th>Expediente</th>
            <th>Participante</th>
	        <th>Puesto</th>
	        <th>Centro de Costos</th>
	        <th>Eliminar</th>
            <sec:authorize access="hasRole('ROL_AREA_ACTUALIZAR')">
              <th class="textcenter">Editar</th>
            </sec:authorize>
          </tr>
          
            <c:forEach items="#{personas}" var="lpersonasListAdicionl"  varStatus="loop">
	          <th>${lpersonasListAdicionl.clave} </th>
	           <th>${lpersonasListAdicionl.nombre} </th>
	           <th>${lpersonasListAdicionl.puesto.nombre} </th>
	           <th>${lpersonasListAdicionl.costos.nombre} </th>
	           <th> <button type="button" class="btn btn-primary" aria-describedby="basic-addon2" data-toggle="tooltip" data-placement="bottom" title="Eliminar" id="btnDeleteParticionatLista" value="${lpersonasListAdicionl.clave}"> 
                      <span class="glyphicon glyphicon-remove"></span>
                    </button> </th>
	           
	           
	           </tr>
	         
	         	
				
			  </c:forEach>	
      
        </table>
      </c:when>
      <c:otherwise>
        <div class="alert alert-info" role="alert">
          <span class="glyphicon glyphicon-info-sign"></span>&nbsp;No se encontr&oacute; registros con los par&aacute;metros proporcionados
        </div>
      </c:otherwise>
    </c:choose> 
            
       </div>   
</body>
</html>


 
         