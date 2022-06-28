$(document).ready(function() {
    $('#btnBuscarPuesto').click(function() {
        buscarPuesto();
    });
});

function buscarPuesto() {
    var nombre = $('#nombrePuestoModal').val();
    var params = {"nombre":nombre};
    
    loading('#resultadosbusquedapuesto');
    $('#resultadosbusquedapuesto').load(getContextPath() + "/puesto/undecorated/search.htm", params);
}

