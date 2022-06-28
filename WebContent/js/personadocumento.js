function mostrarModalEditar(claveDocumento){
    var relativeUrl = "/documento/undecorated/loadEdit.htm";
    var params = {"claveDocumento":claveDocumento};
    
    loadModalContent('#modalEditarDocumento', '#modalEditarDocumentoBody', relativeUrl, params);
}

function abrirModalExpediente(clavePersona) {
    var relativeUrl = "/documento/undecorated/bypersona.htm";
    var params = {"clavePersona":clavePersona, "claveFase":1};
    
    loadModalContent('#modalExpediente', '#modalExpedienteBody', relativeUrl, params);
}

function editarDocumento(){
    if(validaEdicion('#frmEditDocumento')) {
        setOverlay();
        $('#frmEditDocumento').submit();
    }
}

function validaEdicion(idForm){
    var nulo = "";
    var archvioDocumento = $.trim($(idForm +' #archivoDoc').val());
    var ext = $('#archivoDoc').val().split('.').pop().toLowerCase();
    var validated = true;
    var message = "";
    
    if(archvioDocumento == nulo){
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

function mostrarModalEliminar(claveDocumento, claveAreaTipoDocumento, claveAreaUsuario){
    if((claveAreaTipoDocumento == claveAreaUsuario) || (4 == claveAreaUsuario)){
        $('#modalEliminarDocumentoBody').html(getLeyendaPromptEliminar());
        $('#modalEliminarDocumentoBody').append(getControlesPromptEliminar(claveDocumento));
    } else {
        $('#modalEliminarDocumentoBody').html(getContenidoAccionNoPermitida());
    }
    
    $('#modalEliminarDocumento').modal('show');
}

function getLeyendaPromptEliminar() {
    var leyenda = "&iquest;Confirma la eliminaci&oacute;n del archivo seleccionado?";
    
    var mensaje = "<div class=\"row\">";
    mensaje += "<div class=\"col-md-12\">";
    mensaje += "<div class=\"alert alert-warning\" role=\"alert\">";
    mensaje += "<span class=\"glyphicon glyphicon-info-sign\"></span>&nbsp;<strong>";
    mensaje += leyenda;
    mensaje +="</strong>";
    mensaje += "</div></div></div>";
    mensaje += "<div class=\"shortbreakline\"></div>";
    
    return mensaje;
}

function getControlesPromptEliminar(claveDocumento) {
    var footer = "<div class=\"modal-footer\">";
    footer += "<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\">Cancelar</button>";
    footer += "<button type=\"button\" class=\"btn btn-primary\"";
    footer += " onclick=\"eliminarDocumento(" + claveDocumento + ");\">Aceptar</button>";
    footer += "</div>";
    
    return footer;
}

function getContenidoAccionNoPermitida() {
    var leyenda = "Usted no tiene permisos para modificar el documento seleccionado";
    var mensaje = "<div class=\"row\">";
    mensaje += "<div class=\"col-md-12\">";
    mensaje += "<div class=\"alert alert-danger\" role=\"alert\">";
    mensaje += "<span class=\"glyphicon glyphicon-info-sign\"></span>&nbsp;<strong>Error: ";
    mensaje += leyenda;
    mensaje += "</strong>";
    mensaje += "</div></div></div>";
    
    return mensaje;
}

function eliminarDocumento(claveDocumento){
    setOverlay();
    var url = getContextPath() +  '/documento/delete.htm?claveDocumento='+claveDocumento;
    
    location.href = url;
}

                                                                                  