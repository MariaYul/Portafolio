$(document).ready(function() {
    $('#btnBuscarDepartamento').click(function() {
        buscarDepartamento();
    });
});

function buscarDepartamento() {
    var nombre = $('#nombreDepartamentoModal').val();
    var params = {"nombre":nombre};
    
    loading('#resultadosbusquedadepartamento');
    $('#resultadosbusquedadepartamento').load(getContextPath() + "/departamento/undecorated/search.htm", params);
}
