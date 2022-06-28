<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
  <title>Listado Adicional de Participantes</title>
  
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/pagination.css"/>"/>
  <script src="<c:url value="/js/jquery-ui.js"/>"/></script>
  <script src="<c:url value="/js/jquery.js"/>"/></script>
  <script src="<c:url value="/js/personadetail.js"/>"></script>
  <script src="<c:url value="/js/participante.js"/>"/></script>
</head>
<body>
	  <div class="container">
      
      <div class="row">
      
        <div class="col-md-12">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">Datos del Participante</h4>
            </div>
            
	
            <div class="panel-body" >
            
          <c:choose>
      <c:when test="${not empty lpersonas}">
       
        
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
          
            <c:forEach items="#{lpersonas}" var="lpersonasAdicional"  varStatus="loop">
	          
	          <tr class="${loop.index % 2 == 0 ? 'even' : 'odd'}">
	          <th>${lpersonasAdicional.clave} </th>
	           <th>${lpersonasAdicional.nombre} </th>
	           <th>${lpersonasAdicional.puesto.nombre} </th>
	           <th>${lpersonasAdicional.costos.nombre} </th>
	           <th> <button type="button" class="btn btn-primary" aria-describedby="basic-addon2" data-toggle="tooltip" data-placement="bottom" title="Eliminar" id="btnDeleteParticionatLista" value="${lpersonasAdicional.clave}"> 
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
          </div>
        </div> 
        
        <div class="col-md-12">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">Listado Adicional de Participantes</h4>
            </div>
            <div class="panel-body" > 
            
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
           		<input type="text" class="form-control" placeholder="Expediente" name="empleadosMap1" id="empleadosMap1" required onblur="ontenerDatosEmp(this.value,'1');"/>
           		<th><div id="nombre1" > </div> </th>
	            <th><div id="puesto1" > </div> </th>
	            <th><div id="costos1" > </div> </th>
	            
	            <th> 
	             <button type="button" class="btn btn-primary" data-toggle="modal" id="btnAddParticionatListaSesion"> 
                  		<span class="glyphicon glyphicon-list-alt"></span> Adicionar Participante
                	   </button>
                 <input type="hidden" id="idEmpl" >    
           		</th>
            </tr>
            
             </table>
             
              
           </div>
          </div>
        </div>
        
         <div class="col-md-12">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">Participantes Adicionales</h4>
            </div>
            <div id="resultadosbusquedaEmp">
              <c:choose>
                <c:when test="${not empty info}">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="alert alert-success" role="alert" id="msginfo">
                        <span class="glyphicon glyphicon-info-sign"></span>&nbsp;<strong>Mensaje: </strong><c:out value="${info}" escapeXml="false"/>
                      </div>
                    </div>
                  </div>
                </c:when>
                <c:when test="${not empty error}">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="alert alert-danger" role="alert" id="msgerror">
                        <span class="glyphicon glyphicon-info-sign"></span>&nbsp;<strong>Error: </strong><c:out value="${error}" escapeXml="false"/>
                      </div>
                    </div>
                  </div>
                </c:when>
                <c:otherwise>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="alert alert-info" role="alert">
                        <span class="glyphicon glyphicon-info-sign"></span>&nbsp;Por favor realice una b&uacute;squeda
                      </div>
                    </div>
                  </div>
                </c:otherwise>
              </c:choose>
            </div>
          </div>
        </div>
      </div>
    </div>
    
   
</body>
</html>


 
         