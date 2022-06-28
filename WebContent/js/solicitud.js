$(document).ready(function() {   
	
	$('#inver_grupoModal').keypress(function(event) {
		return isNumberKey(event);
	});
		
	$('#inver_personModal').keypress(function(event) {
		return isNumberKey(event);
	});
	
	$('#num_partModal').keypress(function(event) {
		return isNumberKey(event);
	});

	$('#costo_asoModal').keypress(function(event) {
		return isNumberKey(event);
	});
	
	$('#duracionModal').keypress(function(event) {
		return isNumberKey(event);
	});
	

	$('#empleadosMap1').keypress(function(event) {
			return isNumberKey(event);
	});
	 
	$('#empleadosMap2').keypress(function(event) {
			return isNumberKey(event);
	}); 
	
	$('#empleadosMap3').keypress(function(event) {
			return isNumberKey(event);
	});
	
	$('#empleadosMap4').keypress(function(event) {
		return isNumberKey(event);
});
	
	
    $('#btnLimpiarCalulo').click(function() {
    	//$('#inver_grupoModal').prop('enabled',true);
    	$('#inver_grupoModal').removeAttr('disabled');
    	$('#inver_personModal').removeAttr('disabled');
    	$('#inver_grupoModal').val('');
    	$('#inver_personModal').val('');
    	$('#num_partModal').val('');
    	$('#totalModal').val('');
    	
    });
    
    $('#btnLimpiar').click(function() {
        cleanForm('#frmAdd');
        cleanSearchResults();
    });
    
    $('#btnBuscar').click(function() {
        buscar();
    });
    
    $('#btnNuevoSolicitud').click(function() {    	
        registrarNuevo();
    });
    
    $('#btnActualizar').click(function() {
        editar();
    });
    
    $('#btnNuevoMostrar').click(function() {
        mostrarSolicitudNuevo();
    });
    
    $('#inicioListaAdicionalEdit').click(function() {
    	inicioListaAdicionalEdit($(this).val());
    });
    
    $('#modalNuevoBody, #modalEditarBody').bind("DOMSubtreeModified",function() {
        setSoloNumericos();
    });
   
    $('#btnDeleteParticionatLista').click(function() {
    	btnDeleteParticionatLista($(this).val());
    });
    
    $('#btnNuevoRegistrar').click(function() {
    	registrarCaptura();
    });
    
    $('#addParticipants').show();
    
    $('#fechaFin').click(function(event) {{
    	var fechaInicial = $('#fechaInicio').val();
    	var dias =  $('#duracionModal').val();
//    	
    	if(fechaInicial != ""){
    		if(Number(dias) > 0){
			var fechaInicio = moment(fechaInicial, 'DD/MM/YYYY');
			var fechaFinal = moment(fechaInicio.add(dias, 'd')).format('DD/MM/YYYY');
//			alert(fechaFinal);
//			$('#fechaFin').datepicker("setDate", new Date(2008,9,03) );
			$('#fechaFin').val(fechaFinal);
//			$('#fechaFin').datepicker({ dateFormat: 'dd-mm-yy' }).val(fechaFinal);
//    		var bits = fechaInicial.split('/');
//      	  var d = new Date(bits[2] + '/' + bits[1] + '/' + bits[0]);
//    		var d = new Date(fechaInicial);
//      	d.setDate(d.getDate() + dias);
//      	var d2 = new Date(d);
//      	var bits2 = d2.split('/');
//      	var fechaFin=new Date(bits2[2] + '/' + bits2[1] + '/' + bits2[0]);
//      	  alert(fechaFin);
//      	var fecha = new Date(d);
//      	fecha.setDate(fecha.getDate() + dias);
//      	alert(fecha);
    		}else{
    			alert("Debe ingresar la duracion en dias");
    		}
    	}else{
    		alert("Debe seleccionar una fecha de inicio");
    	}

    		
    	  
	    
    }});
    

    
});

function registrarNuevo() {
	 window.location = getContextPath() + "/solicitud/loadNew.htm";
}

function registrarCaptura() {
    if(validarRegistro('#frmAdd')) {
        $('#frmAdd').submit();
    }
}

function validarRegistro(idFormulario) {
	  var validated = true;
	  var nulo = "";
	  var vacio = "-1";
	  var message = ""; 
	  
	  var nombreCurso= $(idFormulario +' #nombreModal').val();
	  var fechaInicio= $(idFormulario +' #fechaInicio').val();
	  var fechaFin= $(idFormulario +' #fechaFin').val();
	  var duracion= $(idFormulario +' #duracionModal').val();
	  var horario= $(idFormulario +' #horarioModal').val();
	  var contact= $(idFormulario +' #contactoModal').val();
	  var telefono= $(idFormulario +' #datos_contactoModal').val();
	  var responsable= $(idFormulario +' #responsableModal').val();
	  var correo= $(idFormulario +' #datos_responbleModal').val();
	  
	  
	  var inverPersona=$(idFormulario +' #inver_personModal').val();
	  var inverGrupo=$(idFormulario +' #inver_grupoModal').val();
	  
	  var participantes= $(idFormulario +' #num_partModal').val();
	  var costos= $(idFormulario +' #costo_asoModal').val();
	  var tipo= $(idFormulario +' #tipo_monedaModal').val();
	  var detalle=$(idFormulario +' #detalleModel').val();
	  
	  var emp1 = $(idFormulario +' #empleadosMap1').val();
	  var emp2 = $(idFormulario +' #empleadosMap2').val();
	  var emp3 = $(idFormulario +' #empleadosMap3').val();
	  var emp4 = $(idFormulario +' #empleadosMap4').val();
	  
	  var funciones = $(idFormulario +' #funcionesModel').val();
	  var proyecto = $(idFormulario +' #proyectoModel').val();
	  var aplicabilidad = $(idFormulario +' #aplicabilidadModel').val();
	  var actualizacion = $(idFormulario +' #actualizacionModel').val();
	  var otro = $(idFormulario +' #otroModel').val();
	  
	  if(nombreCurso==''){
		  validated = false;
	      message = "El campo 'Nombre del Curso' es requerido";
	  }else if(fechaInicio==''){
		  validated = false;
	      message = "El campo 'Fecha Inicio' es requerido"; 
	  }else if(fechaFin==''){
		  validated = false;
	      message = "El campo 'Fecha Fin' es requerido";
	  }else if(duracion==''){
		  validated = false;
	      message = "El campo 'Duraci&oacute;n d&iacute;as' es requerido";
	  }else if(horario==''){
		  validated = false;
	      message = "El campo 'Horario del curso' es requerido";
	  }else if(contact==''){
		  validated = false;
	      message = "El campo 'Nombre del Contacto y Proveedor' es requerido";
	  }else if(telefono==''){
		  validated = false;
	      message = "El campo 'Tel&eacute;fono y Correo Electr&oacute;nico' es requerido";
	  }else if(responsable==''){
		  validated = false;
	      message = "El campo 'Responsable de seguimiento Telcel' es requerido";
	  }else if(correo==''){
		  validated = false;
	      message = "El campo 'Ext. y Correo Electr&oacute;nico' es requerido";
	  }else if(participantes==''){
		  validated = false;
	      message = "El campo 'No. de Participantes' es requerido";
	  }else if(Number(participantes) < 1){
		  validated = false;
	      message = "El campo 'No. de Participantes' no puede ser menor a 1";
	  }else if(costos==''){
		  validated = false;
	      message = "El campo 'Costos Asociados' es requerido";
	  }else if(tipo==vacio){
		  validated = false;
	      message = "El campo 'Tipo de Moneda' es requerido";
	  }else if(emp1==''){
		  validated = false;
	      message = "El campo 'Expediente campo1' es requerido";
	  }else if(Number(participantes) > 1 && emp2==''){
		  validated = false;
	      message = "El campo 'Expediente campo2' es requerido";
	  }else if(Number(participantes) > 2 && emp3==''){
		  validated = false;
	      message = "El campo 'Expediente campo3' es requerido";
//	  }else if(emp4==''){
//		  validated = false;
//	      message = "El campo 'Expediente campo4' es requerido";
	  }else if(detalle==''){
		  validated = false;
	      message = "El campo 'Detalle de la Justificaci&oacute;n' es requerido";
	  }else if (funciones == vacio && proyecto == vacio && aplicabilidad == vacio && actualizacion == vacio && otro == '') {
	        validated = false;
	        message = "Seleccionar al menos una opci&oacute;n de la sessci&oacute;n 'Justificaci&oacute;n Espec&iacute;fica del Curso' es requerido";
	  }else if(inverPersona == '' && inverGrupo == ''){
		  validated = false;
	        message = "El campo 'Inversi&oacute;n por persona o por grupo' es requerido";
	  }
	    
	  if(!validated) {
		  $('#validationmessage').fadeIn("fast");
	      $('#validationmessage .validationerror').html(message);
	  }
	    
	  return validated;

}

function editar() {
    if(validarEdicion('#frmEdit')) {
    	$('#frmEdit').submit();
    }
}

function validarEdicion(idFormulario) {
	var validated = true;
	  var nulo = "";
	  var vacio = "-1";
	  var message = ""; 
	  
	  var nombreCurso= $(idFormulario +' #nombreModal').val();
	  var fechaInicio= $(idFormulario +' #fechaInicio').val();
	  var fechaFin= $(idFormulario +' #fechaFin').val();
	  var duracion= $(idFormulario +' #duracionModal').val();
	  var horario= $(idFormulario +' #horarioModal').val();
	  var contact= $(idFormulario +' #contactoModal').val();
	  var telefono= $(idFormulario +' #datos_contactoModal').val();
	  var responsable= $(idFormulario +' #responsableModal').val();
	  var correo= $(idFormulario +' #datos_responbleModal').val();
	  
	  
	  var inverPersona=$(idFormulario +' #inver_personModal').val();
	  var inverGrupo=$(idFormulario +' #inver_grupoModal').val();
	  
	  var participantes= $(idFormulario +' #num_partModal').val();
	  var costos= $(idFormulario +' #costo_asoModal').val();
	  var tipo= $(idFormulario +' #tipo_monedaModal').val();
	  var detalle=$(idFormulario +' #detalleModel').val();
	  
	  var emp1 = $(idFormulario +' #empleadosMap1').val();
	  var emp2 = $(idFormulario +' #empleadosMap2').val();
	  var emp3 = $(idFormulario +' #empleadosMap3').val();
	  var emp4 = $(idFormulario +' #empleadosMap4').val();
	  
	  var funciones = $(idFormulario +' #funcionesModel').val();
	  var proyecto = $(idFormulario +' #proyectoModel').val();
	  var aplicabilidad = $(idFormulario +' #aplicabilidadModel').val();
	  var actualizacion = $(idFormulario +' #actualizacionModel').val();
	  var otro = $(idFormulario +' #otroModel').val();
	  
	  if(nombreCurso==''){
		  validated = false;
	      message = "El campo 'Nombre del Curso' es requerido";
	  }else if(fechaInicio==''){
		  validated = false;
	      message = "El campo 'Fecha Inicio' es requerido"; 
	  }else if(fechaFin==''){
		  validated = false;
	      message = "El campo 'Fecha Fin' es requerido";
	  }else if(duracion==''){
		  validated = false;
	      message = "El campo 'Duraci&oacute;n d&iacute;as' es requerido";
	  }else if(horario==''){
		  validated = false;
	      message = "El campo 'Horario del curso' es requerido";
	  }else if(contact==''){
		  validated = false;
	      message = "El campo 'Nombre del Contacto y Proveedor' es requerido";
	  }else if(telefono==''){
		  validated = false;
	      message = "El campo 'Tel&eacute;fono y Correo Electr&oacute;nico' es requerido";
	  }else if(responsable==''){
		  validated = false;
	      message = "El campo 'Responsable de seguimiento Telcel' es requerido";
	  }else if(correo==''){
		  validated = false;
	      message = "El campo 'Ext. y Correo Electr&oacute;nico' es requerido";
	  }else if(participantes==''){
		  validated = false;
	      message = "El campo 'No. de Participantes' es requerido";
	  }else if(Number(participantes) < 1){
		  validated = false;
		  message = "El campo 'No. de Participantes' no puede ser menor a 1";
	  }else if(costos==''){
		  validated = false;
	      message = "El campo 'Costos Asociados' es requerido";
	  }else if(tipo==vacio){
		  validated = false;
	      message = "El campo 'Tipo de Moneda' es requerido";
	  }else if(emp1==''){
		  validated = false;
	      message = "El campo 'Expediente campo1' es requerido";
	  }else if(Number(participantes) > 1 && emp2==''){
		  validated = false;
	      message = "El campo 'Expediente campo2' es requerido";
	  }else if(Number(participantes) > 2 && emp3==''){
		  validated = false;
	      message = "El campo 'Expediente campo3' es requerido";
//	  }else if(emp4==''){
//		  validated = false;
//	      message = "El campo 'Expediente campo4' es requerido";
	  }else if(detalle==''){
		  validated = false;
	      message = "El campo 'Detalle de la Justificaci&oacute;n' es requerido";
	  }else if (funciones == vacio && proyecto == vacio && aplicabilidad == vacio && actualizacion == vacio && otro == '') {
	        validated = false;
	        message = "Seleccionar al menos una opci&oacute;n de la sessci&oacute;n 'Justificaci&oacute;n Espec&iacute;fica del Curso' es requerido";
	  }else if(inverPersona == '' && inverGrupo == ''){
		  validated = false;
	        message = "El campo 'Inversi&oacute;n por persona o por grupo' es requerido";
	  }
	    
	  if(!validated) {
		  $('#validationmessage').fadeIn("fast");
	      $('#validationmessage .validationerror').html(message);
	  }
	    
	  return validated;
}


function buscar() {
    var clave = $('#clave').val();
    var params = {"clave":clave};
    
//    setOverlay();
    
    $('#resultadosbusqueda').load(getContextPath() + "/solicitud/undecorated/search.htm", params, function() {
//    	removeOverlay();
    });
    
}

function mostrarSolicitudNuevo() {
    var relativeUrl = "/solicitud/undecorated/add.htm";
    var params = {};
    loadModalContent('#modalSolicitud', '#modalSolicitudBody', relativeUrl, params);
}

function mostrarModalConsultar(clave) {
	 window.location = getContextPath() + "/solicitud/loadConsulta.htm?clave="+clave;
}

function mostrarModalEditar(clave) {
	 window.location = getContextPath() + "/solicitud/loadEdit.htm?clave="+clave;
}

function mostrarModalEliminar(clave) {
	 window.location = getContextPath() + "/solicitud/eliminar.htm?clave="+clave;
}

function mostrarModalHistorico(clave) {
    var relativeUrl = "/solicitud/undecorated/loadHistorico.htm?claveSolicitud="+clave;
    var params = {"clave": clave};
    $('#modalHistoricoLabel').html("Historico de la Solicitud;" + clave);
    loadModalContent('#modalHistorico', '#modalHistoricoBody', relativeUrl, params);
}

function inicioListaAdicional(){ 
	var relativeUrl = "/solicitud/undecorated/inicioListaPartites.htm"; 
	loadModalContent('#modalConsultaLista', '#modalConsultaListaBody', relativeUrl, null);
}

function inicioListaAdicionalEdit(clave) {
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=yes, width=1200, height=900, top=85, left=140";
	var newWindow = window.open(getContextPath() + "/solicitud/inicioListaAdicionalEdit.htm?clave="+clave,"ventanaAdicional", "width=800, height=600, resizable=yes");
}  

function addParticionatListaSesion() {	
	var empleado = $('#empleadosMap11').val();
	var validated = true;
	var message = ""; 
	var cve;		

	if(empleado=='') {
	    validated = false;
	    message = "El campo 'n&uacute;mero de empleado' es requerido";	
	    $('#validationmessage').fadeIn("fast");
        $('#validationmessage .validationerror').html(message);
	}else{
		var opcion = confirm( String.fromCharCode( 191 ) + "Realmente desea agregar al empleado?");
		if ( opcion == true){
		 
			$.ajax(
					{
						type: "GET",
						contentType:"text",
						url:getContextPath()+"/solicitud/undecorated/addParticipanteListaSesion.htm",
						data:{"claveEmp":empleado},
						dataType: 'text',
						success: function(data){
										
							var relativeUrl = "/solicitud/undecorated/inicioListaPartites.htm"; 
							var params = {"cve":cve};

							if(data=='1'){
								message = "El empleado fue registrado exitosamente";
							}else{
								message = "El empleado ya se encuentra registrado en el listado";
							}
							
							 $('#validationmessage').fadeIn("fast");
							 $('#validationmessage .validationerror').html(message);
							 loadModalContent('#modalConsultaLista', '#modalConsultaListaBody', relativeUrl, params);	
						},
						error: function(e){
							console.log( "ERROR: ", e);
							message = "El empleado no fue registrado exitosamente";
							 $('#validationmessage').fadeIn("fast");
							 $('#validationmessage .validationerror').html(message);
						}
					}
				);					  
		    
		    return validated;
		}
	}
	
}

function btnDeleteParticionatLista(expediente) {
	var params = {"clave":expediente};	
	setOverlay();
	    
	 $('#resultadosbusquedaEmp').load(getContextPath() + "/solicitud/undecorated/deleteParticipanteListaSesion.htm", params, function() {
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
			  $('#costos1').html('<p>'+val[6].clave+'</p>');
		  }else if(posicion==2){
			  $('#nombre2').html('<p>'+val[0].nombre+" "+val[0].apellidos.apellidoPaterno+ " " + val[0].apellidos.apellidoMaterno+'</p>');
			  $('#puesto2').html('<p>'+val[4].nombre+'</p>');
			  $('#costos2').html('<p>'+val[6].clave+'</p>');
		  }else //			  if(posicion==3)
			  {
			  $('#nombre3').html('<p>'+val[0].nombre+" "+val[0].apellidos.apellidoPaterno+ " " + val[0].apellidos.apellidoMaterno+'</p>');
			  $('#puesto3').html('<p>'+val[4].nombre+'</p>');
			  $('#costos3').html('<p>'+val[6].clave+'</p>');
//		  }else{
//			  $('#nombre4').html('<p>'+val[0].nombre+" "+val[0].apellidos.apellidoPaterno+ " " + val[0].apellidos.apellidoMaterno+'</p>');
//			  $('#puesto4').html('<p>'+val[4].nombre+'</p>');
//			  $('#costos4').html('<p>'+val[6].clave+'</p>');
		  }
		  $('#idEmpl').val(val[0].clave);
		 
	  });
	});
}

function ontenerDatosEmpleado(clave,posicion){
	
	var relativeUrl = getContextPath() +"/persona/undecorated/loadDatosEmpleado.ajax";
	var params = {"clave":clave};
	
	$.getJSON(relativeUrl, params, function(response) {
		
	  $.each(response, function(key, val) {	
		  
		  if(posicion==1){
			  $('#nombre1').html('<p>'+val[0].nombre+" "+val[0].apellidos.apellidoPaterno+ " " + val[0].apellidos.apellidoMaterno+'</p>');
			  $('#puesto1').html('<p>'+val[4].nombre+'</p>');
			  $('#costos1').html('<p>'+val[6].clave+'</p>');
		  }else{
			  $('#nombre').html('<p>'+val[0].nombre+" "+val[0].apellidos.apellidoPaterno+ " " + val[0].apellidos.apellidoMaterno+'</p>');
			  $('#puesto').html('<p>'+val[4].nombre+'</p>');
			  $('#costos').html('<p>'+val[6].clave+'</p>');
		  }
		  $('#idEmpl').val(val[0].clave);
		 
	  });
	});
}

function calculoPersona(inverPersona){	
	$('#inver_grupoModal').attr('disabled','disabled');
	$('#num_partModal').val('');
	$('#num_partModal').focus();
}

function calculoGrupo(inverGrupo){	
	$('#inver_personModal').attr('disabled','disabled');
	$('#num_partModal').val('');
	$('#num_partModal').focus();
}

function calculoTotal(noParticipantes){	
	var valorPerson=$('#inver_personModal').val();
	var valorGrupo=$('#inver_grupoModal').val();

	if(valorPerson!=''){
		var total=noParticipantes*valorPerson;
	}else{
		var total=noParticipantes*valorGrupo;
	}
	
	$('#totalModal').val(total);
	
	if(noParticipantes > 3){
		$('#addParticipants').show();
	}else{
		$('#addParticipants').hide();
	}
}

function validarSolicitud(){
	var claveSolicitud=$('#clave').val();
	var creatorSolicitud=$('#creatorSolicitud').val();
		
	$.ajax(
			{
				type: "GET",
				contentType:"text",
				url:getContextPath() + "/solicitud/undecorated/validarSolicitud.ajax",

				data:{"claveSolicitud":claveSolicitud,"creatorSolicitud":creatorSolicitud},
				dataType: 'text',
				success: function( data){
					 $.ajax({
						 	type: "GET",
	                        url:getContextPath() + "/solicitud/undecorated/buscarAdscriptor.ajax",
	                        data:{"claveSolicitud":claveSolicitud,"creatorSolicitud":creatorSolicitud},
	                        success: function (response) {
	                            //$("#divMessages").html("");
	                            //$("#divMessages").html(response);
	                        	message = " Autorizado exitosamente";
	       					 $('#validationmessageInfo').fadeIn("fast");
	       					 $('#validationmessageInfo.validationInfo').html(message);
	                        }
					 }) 
				},
				error: function(e){
					message = " Error: Validación de la solicitud";
					 $('#validationmessage').fadeIn("fast");
					 $('#validationmessage.validationerror').html(message);
				}
			}
		);	
}

function rechazarSolicitud(){
	var claveSolicitud=$('#clave').val();
	var motivoRechazo=$('#motivo').val();
	
	document.getElementById('divErrorMotivo').style.display = 'none';
//	document.getElementById('divError').style.display = 'none';
//	document.getElementById('divInfo').style.display = 'none';
	
	if(motivoRechazo == ""){
		document.getElementById('divErrorMotivo').style.display = 'block';
	}else{
//		console.log(motivoRechazo);
//		alert("motivoRechazo:" + motivoRechazo);
	$.ajax(
			{
				type: "POST",
//				contentType:"text",
				url:getContextPath() + "/solicitud/undecorated/rechazarSolicitud.ajax",

				data:{"claveSolicitud":claveSolicitud, "motivo": motivoRechazo},
				dataType: 'text',
				success: function( response){
					 var relativeUrl = "/solicitud/undecorated/buscarAdscriptor.ajax"; 
					 var params = {"cve":claveSolicitud, "motivo": motivoRechazo}; 
					 message = " Validacion Rechazada exitosamente";
					 $('#validationmessageInfo').fadeIn("fast");
					 $('#validationmessageInfo.validationInfo').html(message);
//					document.getElementById('divInfo').style.display = 'block';
					 $('#modalRechazo').modal('hide');
				},
				error: function(e){
					message = " Error: Rechazo de la solicitud";
					 $('#validationmessage').fadeIn("fast");
					 $('#validationmessage.validationerror').html(message);
					 document.getElementById('divError').style.display = 'block';
				}
			}
		);	
	}
}

function autorizarSolicitud(){
	var claveSolicitud=$('#clave').val();

	$.ajax(
			{
				type: "GET",
				contentType:"text",
				url:getContextPath() + "/solicitud/undecorated/autorizarSolicitud.ajax",

				data:{"claveSolicitud":claveSolicitud},
				dataType: 'text',
				success: function( data){
					 var relativeUrl = "/solicitud/undecorated/buscarAdscriptorSub.ajax"; 
					 var params = {"claveSolicitud":claveSolicitud}; 
					 message = "Autorización Realizada exitosamente";
					 $('#validationmessageInfo').fadeIn("fast");
					 $('#validationmessageInfo.validationInfo').html(message);
				},
				error: function(e){
					message = " Error: Al realizar la autiorización de la solicitud";
					 $('#validationmessage').fadeIn("fast");
					 $('#validationmessage.validationerror').html(message);
				}
			}
		);	
}

function rechazarAutoSolicitud(){
	var claveSolicitud=$('#clave').val();
	var motivoRechazo=$('#motivo').val();
	
	document.getElementById('divErrorMotivo').style.display = 'none';
	
	if(motivoRechazo == ""){
		document.getElementById('divErrorMotivo').style.display = 'block';
	}else{
//		console.log(motivoRechazo);
//		alert("motivoRechazo auto:" + motivoRechazo);
	
	$.ajax(
			{
				type: "POST",
//				contentType:"text",
//				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				url:getContextPath() + "/solicitud/undecorated/rechazarAutoSolicitud.ajax",
				data:{"claveSolicitud":claveSolicitud,"motivo":motivoRechazo},
				dataType: 'text',
				success: function( response){
//					 alert(message);
//					 document.getElementById('divInfo').style.display = 'block';
//					 message = "Rechazo de la Solicitud realizado exitosamente";
					 $('#validationmessageInfo').fadeIn("fast");
//					 $('#validationmessageInfo.validationInfo').html(message);

					 $('#modalRechazo').modal('hide');
				},
				error: function(e){
					console.log( "ERROR: ", e);
					 $('#validationmessage').fadeIn("fast");
					 $('#validationmessage .validationerror').html(e);
					 document.getElementById('divError').style.display = 'block';
				}
			}
		);
		
	}
}

function paginarResultados(offset) {
	 var clave = $('#clave').val();    
	 var params = {"clave":clave,"offset":offset};	    
//	 setOverlay();

  $('#resultadosbusqueda').load(getContextPath() + "/solicitud/undecorated/search.htm", params, function() {
//      removeOverlay();
  });
}

var parseDate = function(date){
	  var parts = date.split('-');
	  var temp = parts[0];
	  parts[0] = parts[1];
	  parts[1] = temp;
	  return new Date(Date.parse(parts.join('/')));  
	}

	var sumDays = function(date, days){
	  var fdate = parseDate(date);
	  fdate.setDate(fdate.getDate()+days);
	  return fdate;
	}
	
function calcularFechaFin(){
	var fechaInicio = $('#fechaInicio').val();
	console.log("fechaInicio " + fechaInicio);
	var dias = $('#duracionModal').val();
	console.log("duracionModal " + dias);
}

function mostrarModalRechazo(clave) {
    var relativeUrl = "/solicitud/undecorated/loadRechazo.htm?claveSolicitud="+clave;
    var params = {"clave": clave};
    $('#modalRechazoLabel').html("Rechazar Solicitud: " + clave);
    loadModalContent('#modalRechazo', '#modalRechazoBody', relativeUrl, params);
}
