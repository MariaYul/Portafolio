$(document).ready(function() {
    $("#fechaExpiraContrasenniaModal").datepicker({
        maxDate: 0,
        minDate: -7,
        dateFormat: "dd/mm/yy",
        dayNamesMin: [ "Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa" ],
        monthNames: [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" ],
        monthNamesShort: [ "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic" ]
    });
    $("#fechaExpiraContrasenniaModal").keyup(function(event) {
        if(event.keyCode == 13) {
            $(this).datepicker("show");
        }
    });
});