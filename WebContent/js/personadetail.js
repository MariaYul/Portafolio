$(document).ready(function() {
    loadDocumentosPersonaFase(1);
    
    $('#btnFlippingbookMostrar').click(function() {
        openExpedienteFlippingBook();
    });
    
    $('#btnNuevoMostrar').click(function() {
        mostrarModalNuevo();
    });
    
    $('#btnRegistrarNuevoDocumento').click(function() {
        $('#frmAdd').submit();
    });
});

function loadDocumentosPersonaFase(claveFase) {
    eliminarEstiloActivoNavPills(claveFase);
    asignarEstiloActivoNavPills(claveFase);
    
    var clavePersona = $('#numerocandidato').val();
    var params = {"clavePersona":clavePersona, "claveFase":claveFase};
    var url = getContextPath() + "/documento/undecorated/bypersonabyfase.htm";
    
    loading('#resultadosbusqueda');
    
    $('#resultadosbusqueda').load(url, params);
}

function openExpedienteFlippingBook(){
    var clavePersona = $('#numerocandidato').val();
    var newWindow = window.open(getContextPath() + "/expedienteflipbook.htm?clavePersona=" + clavePersona, "ventanaFlippingBook", "width=800, height=600, resizable=yes");
}

function asignarEstiloActivoNavPills(claveFase) {
    $('#divnavpills .nav-pills li').each(function(index, value) {
        if(index + 1 == claveFase) {
            $(this).addClass('active');
        }
    });
}

function eliminarEstiloActivoNavPills(claveFase) {
    $('#divnavpills .nav-pills li').each(function(index, value) {
        $(this).removeClass('active');
    });
}

function mostrarModalNuevo() {
    var clavePersona = $('#numerocandidato').val();
    var relativeUrl = "/documento/undecorated/loadNew.htm";
    var params = {"clavePersona":clavePersona};
    
    loadModalContent('#modalNuevoDocumento', '#modalNuevoDocumentoBody', relativeUrl, params);
}

function mostrarTiposDocumento(clavePersona, claveArea) {
    var fase = document.getElementById("fase").value;
    
    var relativeUrl = getContextPath() +"/tipodocumento/undecorated/loadTiposDocumentoToCandidate.ajax";
    var params = {"area":claveArea, "fase":fase, "candidato":clavePersona};
    
    setOverlay();
    
    $.getJSON(relativeUrl, params, function(response) {
        removeOverlay();
        popularCombo('#docto', response);
    });
}

function popularCombo(idCombo, jsonObject) {
    $(idCombo).empty().append("<option value='-1'>-- Seleccione Documento --</option>");
    
    $.each(jsonObject, function(key, val) {
        $(idCombo).append("<option value='" + val.clave + "'>" + val.nombre + "</option>");
    });
}

function registrarNuevoDocumento(){
    if(validarRegistro('#frmRegDoc')) {
        setOverlay();
        $('#frmRegDoc').submit();
    }
}

function validarRegistro(idForm){
    
    var nulo = "";
    var opcionNoValida = -1;
    var fase = $(idForm +' #fase').val();
    var documento = $(idForm +' #docto').val();
    var archvioDocumento = $.trim($(idForm +' #archivoDoc').val());
    var ext = $('#archivoDoc').val().split('.').pop().toLowerCase();
    var validated = true;
    var message = "";
    
    if(fase == opcionNoValida){
        validated = false;
        message = "Seleccione una etapa laboral v&aacute;lida";
    }else if(documento == opcionNoValida){
        validated = false;
        message = "Seleccione un tipo de documento v&aacute;lido";
    }else if(archvioDocumento == nulo){
        validated = false;
        message = "El campo 'Archivo' es requerido";
    }else if($.inArray(ext, ['pdf']) == -1) {
        validated = false;
        message = "El formato del archivo que se desea subir debe ser PDF";
    }
    
    if(!validated) {
        $('#validationmessage').fadeIn("fast");
        $('#validationmessage .validationerror').html(message);
    }
    
    return validated;
}
