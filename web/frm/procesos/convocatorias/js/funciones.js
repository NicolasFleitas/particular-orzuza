function validarDetalle(){
    var valor = true;
    if ($("#nombre_profesor").val().trim()===""){
        valor=false;
        $("#mensajes").html("Elija un profesor para la materia");
        $("#nombre_profesor").focus();    
    } else if ($("#nombre_materia").val().trim()===""){
        valor=false;
        $("#mensajes").html("Elija una materia");
        $("#nombre_materia").focus();    
    }
    return valor;
}

function validarFormulario() {
    var valor = true;
    if ($("#fecha_convocatoria").val().trim()===""){
        valor=false;
        $("#mensajes").html("Elija una fecha por favor");
        $("#fecha_convocatoria").focus();    
    }
    else if ($("#nombre_tipoconvocatoria").val().trim()===""){
        valor=false;
        $("#mensajes").html("Elija un tipoconvocatoria por favor");
        $("#id_tipoconvocatoria").focus();    
    }
    else if ($("#monto_convocatoria").val().trim()===""){
        valor=false;
        $("#mensajes").html("Elija el monto de la cuota por favor");
        $("#monto_convocatoria").focus();    
    }
    else if ($("#monto_convocatoria").val()==="0"){
        valor=false;
        $("#mensajes").html("Elija el monto de la cuota por favor");
        $("#monto_convocatoria").focus();    
    }else if ($("#monto_matricula").val().trim()===""){
        valor=false;
        $("#mensajes").html("Elija el monto de la matricula por favor");
        $("#monto_matricula").focus();    
    }else if ($("#monto_matricula").val().trim()==="0"){
        valor=false;
        $("#mensajes").html("Elija el monto de la matricula por favor");
        $("#monto_matricula").focus();    
    }
    else if ($("#codigo_convocatoria").val().trim()===""){
        valor=false;
        $("#mensajes").html("Escriba un codigo para la convocatoria por favor");
        $("#codigo_convocatoria").focus();    
    } else if ($("#convocatoria_estado").val().trim()===""){
        valor=false;
        $("#mensajes").html("Defina el estado de la convocatoria por favor");
        $("#convocatoria_estado").focus();    
    }/*
    else if ($("#codigo_convocatoria").val().trim()==="0"){
        valor=false;
        $("#mensajes").html("Escriba un codigo para la convocatoria por favor");
        $("#codigo_convocatoria").focus();    
    }   */          
    return valor;
}

function fechaHoy(){
var hoy = new  new Date().toJSON().slice(0,10);
console.log(hoy);
 $("#fecha_convocatoria").val(hoy);
}
function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}

function buscarIdConvocatoria() {
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
            $("#id_convocatoria").val(json.id_convocatoria);                   
          //  var fecha = $("#fecha_convocatoria").serialize();
            $("#fecha_convocatoria").val(json.fecha_convocatoria);   
            $("#nombre_convocatoria").val(json.nombre_convocatoria);
            $("#id_tipoconvocatoria").val(json.id_tipoconvocatoria);
            $("#nombre_tipoconvocatoria").val(json.nombre_tipoconvocatoria);
            $("#monto_convocatoria").val(json.monto_convocatoria);            
            $("#monto_matricula").val(json.monto_matricula);            
            $("#codigo_convocatoria").val(json.codigo_convocatoria);           
            $("#convocatoria_estado").val(json.convocatoria_estado);           
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_tipoconvocatoria", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#nombre_tipoconvocatoria", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreConvocatoria() {
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
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_convocatoria").val(id);
                $("#nombre_convocatoria").focus();
                // TURNO ADD
                buscarIdConvocatoria();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function agregarConvocatoria() {
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
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            
            $("#id_convocatoria").val(json.id_convocatoria);            
            //$("#monto_convocatoria").val(json.monto_convocatoria);
            buscarIdConvocatoria();
            $("#id_convocatoria").focus;
            $("#id_convocatoria").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarConvocatoria() {
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
            $("#id_convocatoria").focus;
            $("#id_convocatoria").select();
            buscarIdConvocatoria();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarConvocatoria() {
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
            eliminarConvocatoriaDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_convocatoria").focus;
            $("#id_convocatoria").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}


function agregarLinea() {
    $("#id_detalleconvocatoria").val("0");
    $("#id_profesor").val("0");
    $("#nombre_profesor").val("");  
    $("#id_materia").val("0");
    $("#nombre_materia").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_profesor").focus();
    $("#id_profesor").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_detalleconvocatoria", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detalleconvocatoria").val(id);
   // alert("linea" + id);    
    $("#id_profesor").val(0);
    $("#nombre_profesor").val(""); 
    $("#id_materia").val(0);
    $("#nombre_materia").val(""); 
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_profesor").focus();
    $("#id_profesor").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdConvocatoriaDetalle();
    //siguienteCampo("#", "#botonModificarLinea", true);    
}
function buscarIdConvocatoriaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
 
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdConvocatoriaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            // no hay en form linea
            // $("#id_convocatoria").val(json.id_convocatoria);
            $("#id_profesor").val(json.id_profesor);
            $("#nombre_profesor").val(json.nombre_profesor);
            $("#id_materia").val(json.id_materia);
            $("#nombre_materia").val(json.nombre_materia);
            
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
             // alert(datosFormulario);
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function agregarConvocatoriaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    
    var id_convocatoria = $("#id_convocatoria").val();
    datosFormulario += "&id_convocatoria=" + id_convocatoria;
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarConvocatoriaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdConvocatoria();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarConvocatoriaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    //var id_convocatoria = $("#id_convocatoria").val();
    var id_convocatoria = $("#id_convocatoria").val();
    datosFormulario += "&id_convocatoria=" + id_convocatoria;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarConvocatoriaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdConvocatoria();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarConvocatoriaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_convocatoria = $("#id_convocatoria").val();
    datosFormulario += "&id_convocatoria=" + id_convocatoria;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarConvocatoriaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdConvocatoria();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
//// profesores
function buscarIdProfesor() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdProfesor.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_profesor").val(json.id_profesor);
            $("#nombre_profesor").val(json.nombre_profesor);            
          
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreProfesor() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreProfesor.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_profesor").val(id);
                $("#nombre_profesor").focus();
                buscarIdProfesor();
                $("#buscar").fadeOut("slow");
                $("#panelLinea").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarIdTipoConvocatoria() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTipoConvocatoria.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_tipoconvocatoria").val(json.id_tipoconvocatoria);
            $("#nombre_tipoconvocatoria").val(json.nombre_tipoconvocatoria);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {}
        }
    });
}
function buscarNombreTipoConvocatoria() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreTipoConvocatoria.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_tipoconvocatoria").val(id);
                $("#nombre_tipoconvocatoria").focus();
                buscarIdTipoConvocatoria();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}

function limpiarFormulario() {
    $("#id_convocatoria").val("0");    
    $("#nombre_convocatoria").val("");        
    $("#fecha_convocatoria").val("");        
    $("#id_tipoconvocatoria").val("0");
    $("#nombre_tipoconvocatoria").val("");
    $("#monto_convocatoria").val("0");
    $("#codigo_convocatoria").val("");  
    $("#monto_matricula").val("0");
}

function buscarIdMateria() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdMateria.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_materia").val(json.id_materia);
            $("#nombre_materia").val(json.nombre_materia);            
            //alert(json.id_materia);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreMateria() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreMateria.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_materia").val(id);
                $("#nombre_materia").focus();
                buscarIdMateria();
                $("#buscar").fadeOut("slow");
                $("#panelLinea").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
