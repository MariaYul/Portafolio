<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
  <title>Lista Adicional</title>
  
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/pagination.css"/>"/>
  <script src="<c:url value="/js/personadetail.js"/>"></script>
    <script src="<c:url value="/js/solicitud.js"/>"/></script>
</head>
<body>
	  <div class="container">
	  
	 <div class="row">
        <div class="col-md-12">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">Datos del Curso</h4>
               <input type="hidden" id="clave" name="clave" value="<c:out value="${solicitud.clave}"/>"/>              
            </div>
            <div class="panel-body">
		    	<div class="row">
		              <div class="col-md-2">
		                <label for="nom_curso">Nombre del Curso:</label>
		              </div>
		              <div class="col-md-10">
		                <input type="text" class="form-control"  placeholder="Nombre del Curso" name="nombre" id="nombreModal"
		                data-toggle="tooltip" data-placement="bottom" title="Nombre del Curso" value="<c:out value="${solicitud.clave}"/>" maxlength="8"/> 
		              </div>
		       </div>
		       
		      <div class="shortbreakline"></div>
          
         	 <div class="row">
	              <div class="col-md-2">
	                <label for="fechaInicio">Fecha Inicio:(1)</label>
	              </div>
	              <div class="col-md-4">
	                   <input type="text" class="form-control"  placeholder="Duración en días" name="fecha_inicio" id="fechaInicio"
		                data-toggle="tooltip" data-placement="bottom" title="Duración en días" value="<c:out value="${solicitud.fecha_inicio}"/>" maxlength="8"/>
	              </div>
	              <div class="col-md-2">
	                <label for="fechaFin">Fecha Fin:</label>
	              </div>
	              <div class="col-md-4">
	                  <input type="text" class="form-control"  placeholder="Horario del curso" name="fecha_fin" id="fechaFin"
		                data-toggle="tooltip" data-placement="bottom" title="Horario del curso" value="<c:out value="${solicitud.fecha_fin}"/>" maxlength="8"/>
	              </div>
       		  </div>	     		   	 
            </div>
          </div>
        </div>
      </div>
      
      <div class="row">
        <div class="col-md-12">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">Listado Adicional de Participantes</h4>
            </div>
            <div class="panel-body"> 
            
              <table class="table table-striped table-bordered">
              
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
	             <button type="button" class="btn btn-primary" data-toggle="modal" id="btnAddListaAdicional"> 
                  		<span class="glyphicon glyphicon-list-alt"></span> Adicionar Participante
                	   </button> 
           		</th>
            </tr>
            
             </table>
             
             
          <table class="table table-striped table-bordered">  
	          <tr>
	            <th>No.Emp</th>
	            <th>Participante</th>
	            <th>Puesto</th>
	            <th>Centro de Costos</th>
	          </tr>
	         
	        
	          <c:forEach items="#{sessionScope.listAdicionales}" var="lpersonasListAdicionl">
	          
	          <tr>
	          <th>${lpersonasListAdicionl.persona.numero} </th>
	           <th>${lpersonasListAdicionl.persona.nombre} </th>
	           <th>${lpersonasListAdicionl.persona.puesto.nombre} </th>
	           <th>${lpersonasListAdicionl.persona.costos.nombre} </th>
	           
	           
	           </tr>
	         
	         	
				
			  </c:forEach>	
				
           </table>
                               
           </div>
          </div>
        </div>
      </div>
      
            <div class="row">
        <div class="col-md-12">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h4 class="panel-title">Listado Adicional de Participantes</h4>
            </div>
            <div class="panel-body"> 
            
             <table class="table table-striped table-bordered">
           
            
	          <tr>
	            <th>No.Emp</th>
	            <th>Participante</th>
	            <th>Puesto</th>
	            <th>Centro de Costos</th>
	          </tr>
	          <c:set var="s" value="0"></c:set>
	        
	          <c:forEach items="#{lpersonas}" var="lpersonasList">
	          <tr>
	          <th>${lpersonasList.numero} </th>
	           <th>${lpersonasList.nombre} </th>
	           <th>${lpersonasList.puesto.nombre} </th>
	           <th>${lpersonasList.costos.nombre} </th>
	           
	           
	           </tr>
	         
	         	
				
			  </c:forEach>	
				
           </table>
                               
           </div>
          </div>
        </div>
      </div>
      
     </div>   
</body>
</html>


 
         