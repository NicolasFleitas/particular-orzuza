function validarFormulario(){
    var valor=true;
    if ($("#nombre_cobro").val().trim()===""){
        valor=false;
        $("#mensajes").html("Ingrese un nombre de cobro por favor");
        $("#nombre_cobro").focus();    } 
    return valor;
}


function agregarCobro() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_cobro").focus();
            $("#id_cobro").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#id_cobro").focus();
        }
    });
}

function modificarCobro() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_cobro").focus();
            $("#id_cobro").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            
        }
    });
}

function eliminarCobro() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_cobro").focus();
            $("#id_cobro").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
                
            }
        }
    });
}

function buscarIdCobro() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_cobro").val(json.id_cobro);
            $("#nombre_cobro").val(json.nombre_cobro);
           
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
            siguienteCampo("#nombre_cobro", "#botonAgregar", true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#nombre_cobro", "#botonModificar", true);
           }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNombreCobro() {
    var datosFormulario = $("#formBuscar").serialize();
  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombre.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_cobro").val(id);
              $("#nombre_cobro").focus();
              buscarIdCobro();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

function limpiarFormulario() {
        $("#id_cobro").val("");
        $("#nombre_cobro").val("");
        
}

/* Inscripciones */

function buscarIdInscripcion() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdInscripcion.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_inscripcion").val(json.id_inscripcion);
            $("#fecha_inscripcion").val(json.fecha_inscripcion);
            $("#vencimientocuota_inscripcion").val(json.vencimientocuota_inscripcion);            
            $("#id_alumno").val(json.id_alumno);
            $("#nombre_alumno").val(json.nombre_alumno);
            $("#apellido_alumno").val(json.apellido_alumno);
            $("#nroci_alumno").val(json.nroci_alumno);            
            $("#id_convocatoria").val(json.id_convocatoria);            
            $("#nombre_convocatoria").val(json.nombre_convocatoria);         
          /*  $("#id_curso").val(json.id_curso);
            $("#nombre_curso").val(json.nombre_curso);            
            $("#id_turno").val(json.id_turno);
            $("#nombre_turno").val(json.nombre_turno);
            */
            $("#codigo_convocatoria").val(json.codigo_convocatoria);
            $("#nro_cuotas").val(json.nro_cuotas);      
            
            $("#monto_cuota").val(json.monto_cuota);  
           
          /*  if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
          siguienteCampo("#fecha_inscripcion", "#botonAgregar", true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#fecha_inscripcion", "#botonModificar", true);
           }*/
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNombreInscripcion() {
    var datosFormulario = $("#formBuscar").serialize();
  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreInscripcion.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("fast");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_inscripcion").val(id);
              $("#nombre_alumno").focus();              
              buscarIdInscripcion();
             
              $("#buscar").fadeOut("fast");
              $("#panelPrograma").fadeIn("fast");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}
/* Alumnos */

function buscarIdAlumno() {
    var datosFormulario = $("#formPrograma").serialize();
    
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdAlumno.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_alumno").val(json.id_alumno);
            $("#nombre_alumno").val(json.nombre_alumno);
            $("#apellido_alumno").val(json.apellido_alumno);
            $("#nroci_alumno").val(json.nroci_alumno);
            $("#fecha_nac_alumno").val(json.fecha_nac_alumno);            
            $("#nombre_medico").val(json.nombre_medico);
            $("#telefono_medico").val(json.telefono_medico);                        
            $("#id_sexo").val(json.id_sexo);
            $("#nombre_sexo").val(json.nombre_sexo);
        
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNombreAlumno() {
    var datosFormulario = $("#formBuscar").serialize();
  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreAlumno.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("fast");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_alumno").val(id);
              $("#nombre_alumno").focus();
                buscarIdAlumno();
              $("#buscar").fadeOut("fast");
              $("#panelPrograma").fadeIn("fast");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

/*BUSCAR CUOTAS POR NOMBRE*/
function buscarCuota() {
    var datosFormulario = $("#formBuscar").serialize();  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarCuota.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("fast");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_inscripcion").val(id);
              $("#nombre_alumno").focus();
              buscarIdInscripcion();
              $("#buscar").fadeOut("fast");
              $("#panelPrograma").fadeIn("fast");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}
/*BUSCAR CUOTAS POR NUMERO DE CI*/
function buscarCuotaCi() {
    var datosFormulario = $("#formBuscar").serialize();  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarCuotaCi.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);          
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("fast");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_inscripcion").val(id);
              $("#nombre_alumno").focus();
              buscarIdInscripcion();
              $("#buscar").fadeOut("fast");
              $("#panelPrograma").fadeIn("fast");
          });
       },
       error: function(e) {
         
           $("#mensajes").html("No se pudo modificar los datos(ci)");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

function validarBuscarCuota() { 
    var valor=true;
    if ($("#fecha_buscar").val().trim()===""){
        valor=false;
        $("#mensajes").html("Por favor complete la fecha");
        $("#fecha_buscar").focus();
        alert("Fecha vacia!");
    } 
    
    return valor;
}


function cobrarCuota() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/cobrarCuota.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_cobro").focus();
            $("#id_cobro").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo Cobrar la cuota.");
        },
        complete: function (objeto, exito, error){
            $("#id_cobro").focus();
        }
    });
}


