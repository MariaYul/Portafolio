$(document).ready(function() {	
		 
    $("#fechaInicio").datepicker({
          dateFormat: "dd/mm/yy",
          dayNamesMin: [ "Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa" ],
          monthNames: [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" ],
          monthNamesShort: [ "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic" ]      
    });
    
    $("#fechaInicio").keyup(function(event) {
        if(event.keyCode == 13) {
            $(this).datepicker("show");
        }
    });
});