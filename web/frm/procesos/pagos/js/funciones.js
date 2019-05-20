function hidden() {
    var t = $("#id_tfac").val();
    
    if (t === "1") {


        $("#tf").prop('hidden', false);
        $("#tf1").prop('hidden', true);
        $("#id_factura_venta").prop('readonly', false);
        $("#id_cuenta").val(0);
        $("#id_factura_venta").val(0);
        $("#total").val(0);
        $("#vuelto").prop('style', 'color: black');
        $("#importe").val(0);
        $("#id_forma_pago").val(0);
        $("#vuelto").val(0);

    }else{
        if (t === "2"){
            $("#tf").prop('hidden', true);
            $("#tf1").prop('hidden', false);
            $("#id_factura_venta").prop('readonly', true);
            $("#id_factura_venta").val(0);
            $("#nro_cuota").val(0);
            $("#total").val(0);
            $("#vuelto").prop('style', 'color: black');
            $("#importe").val(0);
        $("#id_forma_pago").val(0);
        $("#vuelto").val(0);
            
        }
        
    }
}

function agregarPago() {
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
            $("#id_pago").focus();
            $("#id_pago").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_pago").focus();
        }
    });
}

function modificarPago() {
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
            $("#id_pago").focus();
            $("#id_pago").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function eliminarPago() {
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
            $("#id_pago").focus();
            $("#id_pago").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}

function buscarIdPago() {
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
            $("#id_pago").val(json.id_pago);
            $("#fecha_pago").val(json.fecha_pago);
            //$("#monto_inicial").val(json.monto_inicial);
            //$("#estado_pago").val(json.estado_pago);
           // $("#monto_inicial").val(json.monto_inicial);
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#detalle").prop('hidden', true);
                //siguienteCampo("#nombre_pago", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                $("#detalle").prop('hidden', false);
                //siguienteCampo("#nombre_pago", "#botonModificar", true);
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

function buscarNombrePago() {
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
                $("#id_pago").val(id);
                //$("#nombre_pago").focus();
                buscarIdPago();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
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

/*function buscarNombrePago() {
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
 $("#id_pago").val(id);
 //    $("#nombre_proveedor").focus();
 buscarIdPago();
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
 }*/

function buscarPago() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarPago.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);


            if (json.nuevo === "false") {
                $("#nombre_pago").val("");
                $("#nombre_pago").focus();
            } else {

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

function buscarIdPagoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPagoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_detallepago").val(json.id_detallepago);
//            $("#id_factura_pago").val(json.id_articulo);
//            $("#precio_venta").val(json.precio_venta);
//            $("#precio_compra").val(json.precio_compra);
//            $("#iva_articulo").val(json.iva_articulo);
//            $("#cantidad_venta").val(json.cantidad_venta);

            $("#id_factura_venta").val(json.id_factura_venta);
            //$("#numero_factura_venta").val(json.numero_factura_venta);
            $("#id_forma_pago").val(json.id_forma_pago);
            //$("#nombre_forma_pago").val(json.nombre_forma_pago);



            //$("#total").val(json.total);
            $("#importe").val(json.importe);
            //$("#vuelto").val(json.vuelto);
            //$("#subtotal_venta").val(json.subtotal_venta);
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

function agregarPagoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_pago = $("#id_pago").val();
    datosFormulario += "&id_pago=" + id_pago;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarPagoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            //$("#panelLinea").fadeOut("slow");
            //$("#panelPrograma").fadeIn("slow");
            $("#botonAgregarLinea").prop('disabled', true);
            buscarIdPago();
            
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
function modificarPagoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_pago = $("#id_pago").val();
    datosFormulario += "&id_pago=" + id_pago;

    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarPagoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdPago();
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
function eliminarPagoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_pago = $("#id_pago").val();
    datosFormulario += "&id_pago=" + id_pago;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarPagoDetalle.jsp',
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
            buscarIdPago();

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

function buscarIdVenta() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdVenta.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_factura_venta").val(json.id_factura_venta);
            $("#total").val(json.total);
            $("#id_cuenta").val(0);
            //$("#vuelto").val(json.vuelto);
            //$("#id_cliente").val(json.id_cliente);
            //$("#nombre_cliente").val(json.nombre_cliente);
            //$("#ruc_cliente").val(json.ruc_cliente);
            //$("#fecha_factura_venta").val(json.fecha_factura_venta);
            //$("#id_tipo_factura").val(json.id_tipo_factura);
            //$("#nombre_tipo_factura").val(json.nombre_tipo_factura);
            /*$("#subtotal_5").val(json.subtotal_5);
             $("#subtotal_10").val(json.subtotal_10);
             $("#subtotal_exenta").val(json.subtotal_exenta);*/
            //$("#cantidad_cuotas").val(json.cantidad_cuotas);
            //var fecha = $("#fecha_factura_venta").serialize();

            //$("#contenidoDetalle").html(json.contenido_detalle);
            /*if (json.nombre_tipo_factura === "CONTADO") {
             
             $("#cuota").prop('hidden', true);
             } else {
             
             $("#cuota").prop('hidden', false);
             }*/

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

function buscarIdCuenta() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCuenta.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_cuenta").val(json.id_cuenta);
            $("#nro_cuota").val(json.nro_cuota);
            $("#id_factura_venta").val(json.id_factura_venta);
            $("#total").val(json.total);
            //$("#vuelto").val(json.vuelto);
            //$("#id_cliente").val(json.id_cliente);
            //$("#nombre_cliente").val(json.nombre_cliente);
            //$("#ruc_cliente").val(json.ruc_cliente);
            //$("#fecha_factura_venta").val(json.fecha_factura_venta);
            //$("#id_tipo_factura").val(json.id_tipo_factura);
            //$("#nombre_tipo_factura").val(json.nombre_tipo_factura);
            /*$("#subtotal_5").val(json.subtotal_5);
             $("#subtotal_10").val(json.subtotal_10);
             $("#subtotal_exenta").val(json.subtotal_exenta);*/
            //$("#cantidad_cuotas").val(json.cantidad_cuotas);
            //var fecha = $("#fecha_factura_venta").serialize();

            //$("#contenidoDetalle").html(json.contenido_detalle);
            /*if (json.nombre_tipo_factura === "CONTADO") {
             
             $("#cuota").prop('hidden', true);
             } else {
             
             $("#cuota").prop('hidden', false);
             }*/

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

function buscarNombreFacturaVenta() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreVenta.jsp',
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
                $("#id_factura_venta").val(id);
                $("#nombre_cliente").focus();
                buscarIdVenta();
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

function buscarNombreCuenta() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCuenta.jsp',
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
                //var id1 = $(this).find("td:second").html();
                $("#panelBuscar").html("");
                //$("#id_factura_venta").val(id1);
                $("#id_cuenta").val(id);
                //$("#total").val(json.total);
                buscarIdCuenta();
                //$("#nombre_cliente").focus();
                //buscarIdCuenta();
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

function validarFormulario() {
    var valor = true;
    if ($("#fecha_pago").val().trim() === "" ) {
        valor = false;
        $("#mensajes").html("Seleccionar fecha.");
        $("#fecha_pago").focus();
    }
    if ($("#monto_inicial").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Monto no puede estar vacio.");
        $("#monto_inicial").focus();
    }
    
    

    return valor;
}

function validarFormularioDetalle() {
    var valor = true;
    if ($("#id_factura_venta").val().trim() === "" || $("#id_factura_venta").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Seleccionar Venta.");
        $("#id_venta").focus();
    }
    if ($("#id_forma_pago").val().trim() === "" || $("#id_forma_pago").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Seleccionar forma pago.");
        $("#id_forma_pago").focus();
    }
    
    if ($("#importe").val().trim() === "" || $("#importe").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Importe no puede estar vacio.");
        $("#importe").focus();
    }
    
    if ($("#importe").val().trim() < $("#total").val().trim()) {
        valor = false;
        $("#mensajes").html("Importe no debe ser menor que el total");
        $("#importe").focus();
    }

    return valor;
}

function limpiarFormulario() {
    $("#id_pago").val("");
    $("#fecha_pago").val("");
    $("#monto_inicial").val("");
    $("#estado_pago").val("");

}

function buscarIdForma_pago() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPago.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_forma_pago").val(json.id_forma_pago);
            $("#nombre_forma_pago").val(json.nombre_forma_pago);

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

function buscarNombreForma_pago() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrePago.jsp',
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
                $("#id_forma_pago").val(id);
                $("#nombre_forma_pago").focus();
                buscarIdForma_pago();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
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

function agregarLinea() {
    $("#id_detallepago").val("0");
    $("#id_factura_venta").val("0");
    $("#numero_factura_venta").val("");
    // $("#costo_producto").val("0");
    //  $("#iva_producto").val("");
    //  $("#cantidad_productopago").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_factura_venta").focus();
    $("#id_factura_venta").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_detallepago", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detallepago").val(id);
    $("#id_factura_venta").val("0");
    //$("#numero_factura_venta").val("");
//    $("#costo_producto").val("");
//    $("#iva_producto").val("");
//    $("#cantidad_productopago").val("0");
    $("#id_cuenta").val("0");
    $("#importe").val("0");
    $("#vuelto").val("0");
    $("#total").val("0");
    $("#id_forma_pago").val("0");
    //$("#nombre_forma_pago").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    
    //$("#id_tfac").focus();
    //$("#id_tfac").select();
    /*if ($("#id_tfac").val().trim() === "CREDITO"){
       $("#id_cuenta").focus();
    $("#id_cuenta").select(); 
    }*/
    if ($("#id_tfac").val().trim() === "CONTADO"){
       $("#id_factura_venta").focus();
    $("#id_factura_venta").select(); 
    }
    //$("#id_factura_venta").focus();
    //$("#id_factura_venta").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdPagoDetalle();
    buscarIdCuenta();
    //siguienteCampo("#cantidad_productopago", "#botonModificarLinea", true);
}
// pagosproductos
function buscarIdPagoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPagoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_factura_venta").val(json.id_factura_venta);
            $("#numero_factura_venta").val(json.numero_factura_venta);
            $("#id_forma_pago").val(json.id_forma_pago);
            //$("#nombre_forma_pago").val(json.nombre_forma_pago);
//            buscarIdVenta();
//            var total1 = $("#total").val();
//            alert("total "+ total1);
//            var importe1 = $("#importe").val(json.importe);
//            var vuelto1 = (total1 - importe1);
            //$("#total").val(json.total);
            $("#importe").val(json.importe);
            //$("#vuelto").val(vuelto1);


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

function vuelto(e) {
    //document.getElementById(e.id).value;
    var valor1, valor2, vuelto;
    valor1 = $("#total").val();
    valor2 = $("#importe").val();
    
    vuelto = (valor2 - valor1);
    if (vuelto > 0){
        $("#vuelto").prop('style', 'color: green');
        $("#vuelto").val(vuelto);
    }else{
        if (vuelto < 0){
        $("#vuelto").prop('style', 'color: red');
        $("#vuelto").val(vuelto);
        }else{
            $("#vuelto").prop('style', 'color: black');
            $("#vuelto").val(vuelto);
        }
    }
    
    
}

function agregarRecibo() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarRecibo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            
            //$("#id").val(json.id);
            //$("#id_pago").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_pago").focus();
        }
    });
}

function limpiarLinea() {
    $("#id_detallepago").val("0");
    $("#id_factura_venta").val("0");
    //$("#numero_factura_venta").val("");
//    $("#costo_producto").val("");
//    $("#iva_producto").val("");
//    $("#cantidad_productopago").val("0");
    $("#id_cuenta").val("0");
    $("#importe").val("0");
    $("#vuelto").val("0");
    $("#total").val("0");
    $("#nro_cuota").val("0");
    $("#id_forma_pago").val("0");
    //$("#nombre_forma_pago").val("");
    //$("#panelLinea").fadeIn("slow");
    //$("#panelPrograma").fadeOut("slow");
    
    //$("#id_tfac").focus();
    //$("#id_tfac").select();
    /*if ($("#id_tfac").val().trim() === "CREDITO"){
       $("#id_cuenta").focus();
    $("#id_cuenta").select(); 
    }*/
    /*if ($("#id_tfac").val().trim() === "CONTADO"){
       $("#id_factura_venta").focus();
    $("#id_factura_venta").select(); 
    }*/
    //$("#id_factura_venta").focus();
    //$("#id_factura_venta").select();
    //$("#botonAgregarLinea").prop('disabled', false);
    //$("#botonModificarLinea").prop('disabled', true);
    //$("#botonEliminarLinea").prop('disabled', true);
    //buscarIdPagoDetalle();
    //buscarIdCuenta();
    //siguienteCampo("#cantidad_productopago", "#botonModificarLinea", true);
}
function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) === -1 && !tecla_especial) {
        alert("ingrese solo letras");
        return false;
    }
}
/*function soloLetras(e){
    tecla = (document.all)? e.keyCode : e.which;
    if (tecla===8) return true;
    
    patron =/[A-Za-zñÑ]/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
        
}*/

function SoloNumeros(evt) {
    if (window.event) {//asignamos el valor de la tecla a keynum
        keynum = evt.keyCode; //IE
    } else {
        keynum = evt.which; //FF
    }
    //comprobamos si se encuentra en el rango numérico y que teclas no recibirá.
    if ((keynum > 47 && keynum < 58) || keynum === 8 || keynum === 13 || keynum === 6) {
        return true;
    } else {
        alert("Solo ingrese numeros");
        return false;
    }
}