$(document).ready(function() {   
	 
  
   
    $('#empleadosMap1').keypress(function(event) {
		return isNumberKey(event);
	});
 
    $('#btnAddParticionatListaSesion').click(function() {
       	addParticionatListaSesion();
    });
    
});


function addParticionatListaSesion() {
	var clave = $('#empleadosMap1').val();
	var params = {"claveEmp":clave};
	
	setOverlay();
	    
	 $('#resultadosbusquedaEmp').load(getContextPath() + "/solicitud/undecorated/addParticipanteListaSesion.htm", params, function() {
		 removeOverlay();
	    });
}

function ontenerDatosEmp(clave,posicion){
	
	var relativeUrl = getContextPath() +"/persona/undecorated/loadDatosEmpleado.ajax";
	var params = {"clave":clave};
	
	$.getJSON(relativeUrl, params, function(response) {
		
	  $.each(response, function(key, val) {	
		 
		  if(posicion==1){
			  $('#nombre1').html('<p>'+val[0].nombre+" "+val[0].apellidos.apellidoPaterno+ " " + val[0].apellidos.apellidoMaterno+'</p>');
			  $('#puesto1').html('<p>'+val[4].nombre+'</p>');
			  $('#costos1').html('<p>'+val[0].curp+'</p>');
		  }else if(posicion==2){
			  $('#nombre2').html('<p>'+val[0].nombre+" "+val[0].apellidos.apellidoPaterno+ " " + val[0].apellidos.apellidoMaterno+'</p>');
			  $('#puesto2').html('<p>'+val[4].nombre+'</p>');
			  $('#costos2').html('<p>'+val[0].curp+'</p>');
		  }else{
			  $('#nombre3').html('<p>'+val[0].nombre+" "+val[0].apellidos.apellidoPaterno+ " " + val[0].apellidos.apellidoMaterno+'</p>');
			  $('#puesto3').html('<p>'+val[4].nombre+'</p>');
			  $('#costos3').html('<p>'+val[0].curp+'</p>');
		  }
		  $('#idEmpl').val(val[0].clave);
	  });
	});
}


