$(document).ready(function() {
    $('#nbdepartamento').click(function() {
        loadModalContent('#modalDepartamento'
                , '#modalDepartamentoBody'
                , "/departamento/undecorated/loadBusqueda.htm"
                , {});
    });
    
    $('#nblocalidad').click(function() {
        loadModalContent('#modalLocalidad'
                , '#modalLocalidadBody'
                , "/localidad/undecorated/loadBusqueda.htm"
                , {});
    });
    
    $('#nbpuesto').click(function() {
        loadModalContent('#modalPuesto'
                , '#modalPuestoBody'
                , "/puesto/undecorated/loadBusqueda.htm"
                , {});
    });
    
    $('#btnBuscar').click(function() {
        buscar();
    });
    
    $('#btnLimpiar').click(function() {
        cleanForm('#frmSearch');
        cleanSearchResults();
    });
    
    $('#clave, #numeroEmpleado').keypress(function(event) {
        return isNumberKey(event);
    });
    
    $('#clave, #numeroEmpleado').bind("paste", function(e) {
        e.preventDefault();
    });
});

function abrirexpediente(clavePersona) {
  //  var url = getContextPath() + "/persona/detalle/" + clavePersona + ".htm";
//    window.open(url);
    
    var clavePersona = $('#numerocandidato').val();
    var newWindow = window.open(getContextPath() + "/expedienteflipbook.htm?clavePersona=" + clavePersona, "ventanaFlippingBook", "width=800, height=600, resizable=yes");
}

function buscar() {
    var clave = $('#clave').val();
    var numeroEmpleado = $('#numeroEmpleado').val();
    var nombre = $('#nombre').val();
    var apellidoPaterno = $('#apellidoPaterno').val();
    var apellidoMaterno = $('#apellidoMaterno').val();
    var curp = $('#curp').val();
    var rfc = $('#rfc').val();
    var region = $('#region').val();
    var departamento = $('#departamento').val();
    var localidad = $('#localidad').val();
    var puesto = $('#puesto').val();
    
    var params = {"clave":clave, "numeroEmpleado":numeroEmpleado, "nombre":nombre
            , "apellidoPaterno":apellidoPaterno, "apellidoMaterno":apellidoMaterno, "curp":curp
            , "rfc":rfc, "region":region, "departamento":departamento
            , "localidad":localidad, "puesto":puesto};
    
    setOverlay();
    
    $('#resultadosbusqueda').load(getContextPath() + "/persona/undecorated/search.htm", params, function() {
        removeOverlay();
    });
}

function paginarResultados(offset) {
    var clave = $('#clave').val();
    var numeroEmpleado = $('#numeroEmpleado').val();
    var nombre = $('#nombre').val();
    var apellidoPaterno = $('#apellidoPaterno').val();
    var apellidoMaterno = $('#apellidoMaterno').val();
    var curp = $('#curp').val();
    var rfc = $('#rfc').val();
    var region = $('#region').val();
    var departamento = $('#departamento').val();
    var localidad = $('#localidad').val();
    var puesto = $('#puesto').val();
    
    var params = {"clave":clave, "numeroEmpleado":numeroEmpleado, "nombre":nombre
            , "apellidoPaterno":apellidoPaterno, "apellidoMaterno":apellidoMaterno, "curp":curp
            , "rfc":rfc, "region":region, "departamento":departamento
            , "localidad":localidad, "puesto":puesto, "offset":offset};
    
    setOverlay();
    
    $('#resultadosbusqueda').load(getContextPath() + "/persona/undecorated/search.htm", params, function() {
        removeOverlay();
    });
}
