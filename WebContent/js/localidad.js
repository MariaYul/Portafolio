$(document).ready(function() {
    $('#btnBuscarLocalidad').click(function() {
        buscarLocalidad();
    });
});

function buscarLocalidad() {
    var nombre = $('#nombreLocalidadModal').val();
    var params = {"nombre":nombre};
    
    loading('#resultadosbusquedalocalidad');
    $('#resultadosbusquedalocalidad').load(getContextPath() + "/localidad/undecorated/search.htm", params);
}

