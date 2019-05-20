function buscarCedula() {
    var datosFormulario = $("#formPrograma").serialize();
    
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCedula.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
           // $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            if (json.nroci_alumno === 0) {
                $("#mensajes").html("Ya existe el nro de cedula");
                $("#nroci_alumno").focus();
                $("#nroci_alumno").val("");
                siguienteCampo("#id_alumno", "#botonAgregar", true);
            } else {
                $("#nroci_alumno").val(json.nroci_alumno);
                $("#nombre_alumno").focus();
            }
        },
        error: function (e) {
           // $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}


function agregarAlumno() {
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
            $("#id_alumno").focus();
            $("#id_alumno").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_alumno").focus();
        }
    });
}

function modificarAlumno() {
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
            $("#id_alumno").focus();
            $("#id_alumno").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}
function eliminarAlumno() {
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
            $("#id_alumno").focus();
            $("#id_alumno").select();
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
function buscarIdAlumno() {
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
            $("#id_alumno").val(json.id_alumno);
            $("#nombre_alumno").val(json.nombre_alumno);
            $("#apellido_alumno").val(json.apellido_alumno);
            $("#nroci_alumno").val(json.nroci_alumno);
            $("#nroci_alumno").attr('disabled');
            $("#fecha_nac_alumno").val(json.fecha_nac_alumno);
            $("#id_sexo").val(json.id_sexo);
            $("#nombre_sexo").val(json.nombre_sexo);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_alumno", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_alumno", "#botonModificar", true);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {}
        }
    });
}
function buscarNombreAlumno() {
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
                $("#id_alumno").val(id);
                $("#nombre_alumno").focus();
                buscarIdAlumno();
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
function buscarIdSexo() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdSexo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_sexo").val(json.id_sexo);
            $("#nombre_sexo").val(json.nombre_sexo);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {}
        }
    });
}
function buscarNombreSexo() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreSexo.jsp',
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
                $("#id_sexo").val(id);
                $("#nombre_sexo").focus();
                buscarIdSexo();
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
    $("#id_alumno").val("");
    $("#nombre_alumno").val("");
    $("#apellido_alumno").val("");
    $("#nroci_alumno").val("");
    $("#fecha_nac_alumno").val("");
    $("#id_sexo").val("");
    $("#nombre_sexo").val("");
}

function validarFormulario() {
    var valor = true;
    if ($("#nroci_alumno").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nro de CI no puede estar vacio");
        $("#nroci_alumno").focus();
    } else if ($("#nombre_alumno").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nombre del alumno no puede estar vacio");
        $("#nombre_alumno").focus();
    } else if ($("#apellido_alumno").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Apellido del alumno no puede estar vacio");
        $("#apellido_alumno").focus();
    } else if ($("#fecha_nac_alumno").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Ingrese la fecha");
        $("#fecha_nac_alumno").focus();
    } else if ($("#nombre_sexo").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Favor elija el sexo del alumno");
        $("#id_sexo").focus();
    }
    return valor;
}
