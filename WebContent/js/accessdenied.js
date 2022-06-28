$(document).ready(function() {
    $('#btnRedirigir').click(function() {
        redirigir();
    });
    
});

function redirigir(){
    window.location = getContextPath() + "/inicio.htm";
}