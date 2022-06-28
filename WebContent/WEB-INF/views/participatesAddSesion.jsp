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
  <script src="<c:url value="/js/solicitud.js"/>"/></script>
  
  <script src="<c:url value="/js/bootstrap.min.js"/>"/></script>
  

</head>
<body>


	
	
    <div class="panel-body" >
    
      
      
     
      <div  id="validationmessage">  
        <div class="row">
          <div class="col-md-12">
            <div class="alert alert-danger" role="alert">
              <strong>Error de validaci&oacute;n: </strong><span aria-hidden="true" class="validationerror"></span>
            </div>
          </div>
        </div>
      </div>
    
      <table class="table table-striped table-bordered" >
              
               <tr>
	            <th>No.Emp</th>
	            <th>Participante</th>
	            <th>Puesto</th>
	            <th>Centro de Costos</th>
	            <th></th>
	          </tr>
	          
            <tr>
           		<th>
           		<input type="text" class="form-control" placeholder="Expediente" name="empleadosMap11" id="empleadosMap11" required onblur="ontenerDatosEmpleado(this.value,'2');"/>
           		<th><div id="nombre" > </div> </th>
	            <th><div id="puesto" > </div> </th>
	            <th><div id="costos" > </div> </th>
	            
	            <th> 
	           
                 	<button type="button" class="btn btn-primary" data-toggle="modal" onclick="addParticionatListaSesion();">
                  	<span class="glyphicon glyphicon-list-alt"></span> Adicionar Participantess
                	</button>
                 
                 <input type="hidden" id="idEmpl" >  
           		</th>
            </tr>
            
             </table>
             
            
          <c:choose>
      <c:when test="${not empty sessionScope.listAdicionales}">
      
        
        <table class="table table-striped table-bordered">
          <tr>
          	<th>Expediente</th>
            <th>Participante</th>
	        <th>Puesto</th>
	        <th>Centro de Costos</th>
	        <th>Eliminar</th>
          </tr>
          
            <c:forEach items="#{sessionScope.listAdicionales}" var="lpersonasListAdicionl"  varStatus="loop">
	          
	          <tr class="${loop.index % 2 == 0 ? 'even' : 'odd'}">
	          <th>${lpersonasListAdicionl.persona.clave} </th>
	           <th>${lpersonasListAdicionl.persona.nombre} ${lpersonasListAdicionl.persona.apellidos.apellidoPaterno} ${lpersonasListAdicionl.persona.apellidos.apellidoMaterno}</th>
	           <th>${lpersonasListAdicionl.persona.puesto.nombre} </th>
	           <th>${lpersonasListAdicionl.persona.costos.clave} </th>
	           <th> <button type="button" class="btn btn-primary" aria-describedby="basic-addon2" data-toggle="tooltip" data-placement="bottom" title="Eliminar" id="btnDeleteParticionatLista" value="${lpersonasListAdicionl.persona.clave}"> 
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


 
         