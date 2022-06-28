$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
});

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

function cleanForm(idFormulario) {
	$(idFormulario + ' input[type=text]').val('');
    $(idFormulario + ' input[type=hidden]').val('');
    $(idFormulario + ' select').val('-1');
}

function cleanSearchResults() {
    var content = "<div class='row'>";
    content += "<div class='col-md-12'>";
    content += "<div class='alert alert-info' role='alert'>";
    content += "<span class='glyphicon glyphicon-info-sign'></span>&nbsp;Por favor realice una b&uacute;squeda";
    content += "</div>";
    content += "</div>";
    content += "</div>";
    
    $('#resultadosbusqueda').html(content);
}

function loading(id) {
    var urlimg = getContextPath() + "/images/loading.gif";
    var imloading = "<img src='" + urlimg + "'/>";
    $(id).html(imloading);
}

function loadModalContent(idModal, idBody, relativeUrl, params) {
    loading(idBody);
    $(idModal).modal('show');
    $(idBody).load(getContextPath() + relativeUrl, params);
}

function updateModalContent(idModal, idBody,  msg) {
    $(idModal).modal('show');
    $(idBody).html(msg);
}

function removeOverlay() {
    $('#divOverlay').remove();
}

function seleccionarItemCatalogoModal(clave, nombre, inputTxtPuesto, inputHiddenPuesto, idModal) {
    $(inputTxtPuesto).val(clave);
    $(inputHiddenPuesto).val(nombre);
    $(idModal).modal('hide');
}

function setOverlay() {
    var divOverlay = "<div id='divOverlay' class='overlay'>"
        + "<div id='overlayContentWrapper'>"
        + "<img src='" + getContextPath() + "/images/loading.gif' id='imgOverlay'/>"
        + "<div class='breakline'></div><span>Por favor espere...</span>"
        + "</div>"
        + "</div>";
    
    $('body').append(divOverlay);
    
    //Obtiene el alto y el ancho de la pantalla
    var windowHeight = $(window).height();
    var windowWidth = $(window).width();
    var imgOverlayHeight = $('#overlayContentWrapper').height();
    var imgOverlayWidth = $('#overlayContentWrapper').width();
    
    //Centra la imagen
    var imgTop  = (windowHeight - imgOverlayHeight) / 2 ;
    var imgLeft = (windowWidth - imgOverlayWidth) / 2;
    
    $('#overlayContentWrapper').css('position', 'fixed');
    
    $('#overlayContentWrapper').css('top', imgTop);
    $('#overlayContentWrapper').css('left', imgLeft);
}

function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
        
    return true;
}