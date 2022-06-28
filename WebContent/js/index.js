$(document).ready(function() {
    $('[data-toggle="tooltip"]').tooltip();
    
    $('#numeroEmpleado').keypress(function(event) {
        return isNumberKey(event);
    });
    
    $('#numeroEmpleado').bind("paste", function(e) {
        e.preventDefault();
    });
});

function validarFormulario(idFormulario) {
    var nulo = "";
    var numeroEmpleado = $.trim($('#' + idFormulario +' #numeroEmpleado').val());
    var password = $.trim($('#' + idFormulario +' #contrasennia').val());
    var validated = true;
    var message = "";

    if(numeroEmpleado == nulo) {
        validated = false;
        message = "<div class='row'>";
        message += "<div class='col-md-12'>";
        message += "<div class='alert alert-danger' role='alert'>";
        message += "<span class='glyphicon glyphicon-info-sign'></span>&nbsp;El campo 'N\u00FAmero de empleado' no ha sido capturado. Favor de ingresar informaci\u00F3n";
        message += "</div>";
        message += "</div>";
        message += "</div>";
        updateModalContent('#modalErrorInicio', '#modalErrorInicioBody', message);
        
    } else if(password == nulo){
        validated = false;
        message = "<div class='row'>";
        message += "<div class='col-md-12'>";
        message += "<div class='alert alert-danger' role='alert'>";
        message += "<span class='glyphicon glyphicon-info-sign'></span>&nbsp;El campo 'Contrase\u00F1a' no ha sido capturado. Favor de ingresar informaci\u00F3n";
        message += "</div>";
        message += "</div>";
        message += "</div>";
        updateModalContent('#modalErrorInicio', '#modalErrorInicioBody', message);
    } else if(isNaN(numeroEmpleado)){
        validated = false;
        message = "<div class='row'>";
        message += "<div class='col-md-12'>";
        message += "<div class='alert alert-danger' role='alert'>";
        message += "<span class='glyphicon glyphicon-info-sign'></span>&nbsp;El campo 'N\u00FAmero de empleado' solo puede ser n\u00FAmerico. Favor de revisar informaci\u00F3n";
        message += "</div>";
        message += "</div>";
        message += "</div>";
        updateModalContent('#modalErrorInicio', '#modalErrorInicioBody', message);
    }
    
    if(validated) {
        setOverlay();
    }
    
    return  validated;
}