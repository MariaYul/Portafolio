$(document).ready(function() {
    $('#btnBuscar').click(function() {
        buscar();
    });
    
    $('#btnNuevoMostrar').click(function() {
        mostrarModalNuevo();
    });
    
    $('#btnNuevoRegistrar').click(function() {
        registrarNuevo();
    });
    
    $('#btnActualizar').click(function() {
        editar();
    });
    
    $('#modalNuevoBody, #modalEditarBody').bind("DOMSubtreeModified",function() {
        setSoloNumericos();
    });
    
    $('#btnLimpiar').click(function() {
        cleanForm('#frmSearch');
        cleanSearchResults();
    });
});

function buscar() {
    var numeroEmpleado = $('#numeroEmpleado').val();
    var nombre = $('#nombre').val();
    var apellidoPaterno = $('#apellidoPaterno').val();
    var apellidoMaterno = $('#apellidoMaterno').val();
    var claveArea = $('#claveArea').val();
    var claveRegion = $('#claveRegion').val();
    var estatus = $('#estatus').val();
    
    var params = {"numeroEmpleado":numeroEmpleado, "nombre":nombre, "apellidoPaterno":apellidoPaterno
            , "apellidoMaterno":apellidoMaterno, "claveArea":claveArea, "claveRegion":claveRegion
            , "estatus":estatus};
    
    setOverlay();
    
    $('#resultadosbusqueda').load(getContextPath() + "/usuario/undecorated/search.htm", params, function() {
        removeOverlay();
    });
}

function mostrarModalNuevo() {
    var relativeUrl = "/usuario/undecorated/loadNew.htm";
    var params = {};
    loadModalContent('#modalNuevo', '#modalNuevoBody', relativeUrl, params);
}

function mostrarModalEditar(numeroEmpleado) {
    var relativeUrl = "/usuario/undecorated/loadEdit.htm";
    var params = {"numeroEmpleado": numeroEmpleado};
    loadModalContent('#modalEditar', '#modalEditarBody', relativeUrl, params);
}

function mostrarModalRoles(numeroEmpleado) {
    var relativeUrl = "/usuario/undecorated/loadRoles.htm";
    var params = {"numeroEmpleado": numeroEmpleado};
    $('#modalRolesLabel').html("Roles asignados al usuario&nbsp;" + numeroEmpleado);
    loadModalContent('#modalRoles', '#modalRolesBody', relativeUrl, params);
}

function paginarResultados(offset) {
    var numeroEmpleado = $('#numeroEmpleado').val();
    var nombre = $('#nombre').val();
    var apellidoPaterno = $('#apellidoPaterno').val();
    var apellidoMaterno = $('#apellidoMaterno').val();
    var claveArea = $('#claveArea').val();
    var claveRegion = $('#claveRegion').val();
    var estatus = $('#estatus').val();
    
    var params = {"numeroEmpleado":numeroEmpleado, "nombre":nombre, "apellidoPaterno":apellidoPaterno
            , "apellidoMaterno":apellidoMaterno, "claveArea":claveArea, "claveRegion":claveRegion
            , "estatus":estatus, "offset":offset};
    
    setOverlay();
    
    $('#resultadosbusqueda').load(getContextPath() + "/usuario/undecorated/search.htm", params, function() {
        removeOverlay();
    });
}    

function registrarNuevo() {
    if(validarEdicion('#frmAdd')) {
        $('#frmAdd').submit();
    }
}

function editar() {
    if(validarEdicion('#frmEdit')) {
        $('#frmEdit').submit();
    }
}

function validarEdicion(idFormulario) {
    var nulo = "";
    var opcionNoValida = -1;
    var numero = $.trim($(idFormulario +' #numeroModal').val());
    var nombre = $.trim($(idFormulario +' #nombreModal').val());
    var apellidoPaterno = $.trim($(idFormulario +' #apellidoPaternoModal').val());
    var apellidoMaterno = $.trim($(idFormulario +' #apellidoMaternoModal').val());
    var contrasennia = $.trim($(idFormulario +' #contrasenniaModal').val());
    var bloqueo = $.trim($(idFormulario +' #bloqueoModal').val());
    var fechaExpiracion = $.trim($(idFormulario +' #fechaExpiraContrasenniaModal').val());
    var area = $(idFormulario + '#areaModal').val();
    var region = $(idFormulario + '#regionModal').val();
    var activo = $(idFormulario +'  #activoModal').val();
    var roles = $(idFormulario +'  #rolModal').val();
    var validated = true;
    var message = ""; 
    
    if(numero == nulo) {
        validated = false;
        message = "El campo 'n&uacute;mero de empleado' es requerido";
    } else if(nombre == nulo) {
        validated = false;
        message = "El campo 'nombre' es requerido";
    } else if(apellidoPaterno == nulo) {
        validated = false;
        message = "El campo 'apellido paterno' es requerido";
    } else if(apellidoMaterno == nulo) {
        validated = false;
        message = "El campo 'apellido materno' es requerido";
    } else if(area == opcionNoValida) {
        validated = false;
        message = "Seleccione una &aacute;rea v&aacute;lida";
    } else if(region == opcionNoValida) {
        validated = false;
        message = "Seleccione una regi&oacute;n v&aacute;lida";
    } else if(activo == opcionNoValida) {
        validated = false;
        message = "Seleccione un estatus v&aacute;lido";
    } else if(contrasennia == nulo) {
        validated = false;
        message = "El campo 'contrase&ntilde;a' es requerido";
    } else if(bloqueo == nulo) {
        validated = false;
        message = "El campo 'bloqueo' es requerido";
    } else if(fechaExpiracion == nulo) {
        validated = false;
        message = "El campo 'expiraci&oacute;n de contrase&ntilde;a' es requerido";
    } else if(roles == null) {
        validated = false;
        message = "Seleccione al menos un rol";
    }
    
    if(!validated) {
        $('#validationmessage').fadeIn("fast");
        $('#validationmessage .validationerror').html(message);
    }
    
    return validated;
}

function setSoloNumericos() {
    $('#numeroModal, #bloqueoModal').keypress(function(event) {
        return isNumberKey(event);
    });
    
    $('#numeroModal, #bloqueoModal').bind("paste", function(e) {
        e.preventDefault();
    });
}
