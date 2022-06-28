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
    var params = {"nombre":nombre};
    
    setOverlay();
    
    $('#resultadosbusqueda').load(getContextPath() + "/area/undecorated/search.htm", params, function() {
        removeOverlay();
    });
}

function mostrarModalNuevo() {
    var relativeUrl = "/area/undecorated/loadNew.htm";
    var params = {};
    loadModalContent('#modalNuevo', '#modalNuevoBody', relativeUrl, params);
}

function mostrarModalEditar(clave) {
    var relativeUrl = "/area/undecorated/loadEdit.htm";
    var params = {"clave": clave};
    loadModalContent('#modalEditar', '#modalEditarBody', relativeUrl, params);
}

function paginarResultados(offset) {
    var clave = $('#clave').val();
    var params = {"offset":offset};
    
    setOverlay();
    
    $('#resultadosbusqueda').load(getContextPath() + "/historico/undecorated/search.htm?claveSolicitud="+clave, params, function() {
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
    var validated = true;
    var message = ""; 
    
    if(nombre == nulo) {
        validated = false;
        message = "El campo 'nombre' es requerido";
    }
    
    if(!validated) {
        $('#validationmessage').fadeIn("fast");
        $('#validationmessage .validationerror').html(message);
    }
    
    return validated;
}

function mostrarModalHistorico(clave) {
    var relativeUrl = "/historico/undecorated/search.htm?claveSolicitud="+clave;
    var params = {"clave": clave};
    $('#modalRolesLabel').html("Historico de la Solicitud;" + clave);
    loadModalContent('#modalRoles', '#modalRolesBody', relativeUrl, params);
}
