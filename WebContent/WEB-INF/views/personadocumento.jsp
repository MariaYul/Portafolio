<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Expediente de Persona</title>
  
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/pagination.css"/>"/>
  <script src="<c:url value="/js/personadocumento.js"/>"></script>
</head>
<body>
    
    <div class="container">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">Información de la Persona ${persona.clave}</h4>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-md-3">
              <label class="bold">N&uacute;mero de empleado:&nbsp;</label>${persona.numero}
            </div>
            <div class="col-md-3">
              <label class="bold">RFC:&nbsp;</label>${persona.rfc}
            </div>
            <div class="col-md-3">
              <label class="bold">CURP:&nbsp;</label>${persona.curp}
            </div>
            <div class="col-md-3">
              <label class="bold">IMSS:&nbsp;</label>${persona.imss}
            </div>
          </div>
          
          <div class="row">
            <div class="col-md-6">
              <label class="bold">Nombre:&nbsp;</label>${persona.apellidoPaterno}&nbsp;${persona.apellidoMaterno}&nbsp;${persona.nombre}
            </div>
            <div class="col-md-6">
              <label class="bold">Puesto:&nbsp;</label>${persona.puesto.nombre}
            </div>
          </div>
          
          <div class="row">
            <div class="col-md-6">
              <label class="bold">Departamento:&nbsp;</label>${persona.departamento.nombre}
            </div>
            <div class="col-md-6">
              <label class="bold">Localidad:&nbsp;</label>${persona.localidad.nombre}
            </div>
          </div>
          
          <div class="row">
            <div class="col-md-3">
              <label class="bold">Regi&oacute;n:&nbsp;</label>${persona.region.nombre}
            </div>
            <div class="col-md-3">
              <label class="bold">Estatus:&nbsp;</label>${persona.estatus.nombre}
            </div>
            <div class="col-md-6">
              <label class="bold">Jefe:&nbsp;</label>${persona.numeroJefe}
            </div>
          </div>
        </div>
      </div>
      
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">Opciones</h4>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-md-12">
              <button type="button" class="btn btn-primary" data-toggle="modal" id="btnNuevoDocumento"  onclick="mostrarNuevoModal(${persona.clave},${persona.numero});">
                <span class="glyphicon glyphicon-file"></span>&nbsp;Nuevo Documento
              </button>
              <button type="button" class="btn btn-primary" data-toggle="modal" onclick="abrirModalExpediente(${persona.clave});">
                <span class="glyphicon glyphicon-folder-open"></span>&nbsp;&nbsp;Expediente
              </button>
              <button type="button" class="btn btn-primary" data-toggle="modal" id="btnModoRevista">
                <span class="glyphicon glyphicon-book"></span>&nbsp;&nbsp;Expediente Modo Revista
              </button>
            </div>
          </div>
        </div>
      </div>
      
      <div class="modal fade" id="modalExpediente" tabindex="-1" role="dialog" aria-labelledby="modalExpedienteLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="modalExpedienteLabel">Expediente</h4>
            </div>
            <div class="modal-body" id="modalExpedienteBody">
              ...
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>
            </div>
          </div>
        </div>
      </div>
      
      <div class="modal fade" id="modalNuevoDocumento" tabindex="-1" role="dialog" aria-labelledby="modalNuevoDocumentoLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="modalNuevoDocumentoLabel">Subir Archivo</h4>
            </div>
            <div class="modal-body" id="modalNuevoDocumentoBody">
              ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
                 <button type="button" class="btn btn-primary" id="btnNuevoDocumentoRegistrar" onclick="registrarNuevoDocumento();">Guardar</button>
            </div>
          </div>
        </div>
      </div>
      
    </div>
</body>
</html>