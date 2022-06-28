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
    
    $('#btnLimpiar').click(function() {
        cleanForm('#frmSearch');
        cleanSearchResults();
    });
});

function buscar() {
    var nombre = $('#nombre').val();
    var area = $('#area').val();
    var fase = $('#fase').val();
    var multiregistro = $('#multiregistro').val();
    var estatus = $('#estatus').val();
    
    var params = {"nombre":nombre, "area":area, "fase":fase, "multiregistro":multiregistro, "estatus":estatus};
    
    setOverlay();
    
    $('#resultadosbusqueda').load(getContextPath() + "/tipodocumento/undecorated/search.htm", params, function() {
        removeOverlay();
    });
}

function mostrarModalNuevo() {
    var relativeUrl = "/tipodocumento/undecorated/loadNew.htm";
    var params = {};
    loadModalContent('#modalNuevo', '#modalNuevoBody', relativeUrl, params);
}

function mostrarModalEditar(clave) {
    var relativeUrl = "/tipodocumento/undecorated/loadEdit.htm";
    var params = {"clave": clave};
    loadModalContent('#modalEditar', '#modalEditarBody', relativeUrl, params);
}

function paginarResultados(offset) {
    var nombre = $('#nombre').val();
    var area = $('#area').val();
    var fase = $('#fase').val();
    var multiregistro = $('#multiregistro').val();
    var estatus = $('#estatus').val();
    
    var params = {"nombre":nombre, "area":area, "fase":fase, "multiregistro":multiregistro, "estatus":estatus, "offset":offset};
    
    setOverlay();
    
    $('#resultadosbusqueda').load(getContextPath() + "/tipodocumento/undecorated/search.htm", params, function() {
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
    var nombre = $.trim($(idFormulario +' #nombreModal').val());
    var area = $(idFormulario +'  #areaModal').val();
    var fase = $(idFormulario +'  #faseModal').val();
    var multiregistro = $(idFormulario +'  #multiregistroModal').val();
    var activo = $(idFormulario +'  #activoModal').val();
    var validated = true;
    var message = ""; 
    
    if(nombre == nulo) {
        validated = false;
        message = "El campo 'nombre' es requerido";
    } else if(area == opcionNoValida) {
        validated = false;
        message = "Seleccione una &aacute;rea v&aacute;lida";
    } else if(fase == opcionNoValida) {
        validated = false;
        message = "Seleccione una fase v&aacute;lida";
    } else if(multiregistro == opcionNoValida) {
        validated = false;
        message = "Ind&iacute;que si el tipo de documento es multiregistro";
    } else if(activo == opcionNoValida) {
        validated = false;
        message = "Seleccione un estatus v&aacute;lido";
    }
    
    if(!validated) {
        $('#validationmessage').fadeIn("fast");
        $('#validationmessage .validationerror').html(message);
    }
    
    return validated;
}

