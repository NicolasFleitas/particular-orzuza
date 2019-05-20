<%@page import="controladores.CuentasControlador"%>
<%@page import="modelos.DetallesCajas"%>

<%@page import="modelos.Cuentas"%>
<%@page import="utiles.Utiles"%>
<%@page import="modelos.Tipo_facturas"%>
<%@page import="modelos.Alumnos"%>


<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
    //String sfecha_cuenta = request.getParameter("fecha_cuenta");
    //java.sql.Date fecha_cuenta = Utiles.stringToSqlDate(sfecha_cuenta);
    //int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));
    //String ruc_cuenta = request.getParameter("ruc_cuenta");

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    //Cuentas facturacuenta = new Cuentas();
    //facturacuenta.setId_cuenta(id_cuenta);
    //java.sql.Date ssFecha_cuenta = new java.sql.Date(new java.util.Date().getTime());
    System.out.println("idfac " + id_cuenta);
    Cuentas facturacuenta = CuentasControlador.buscarTotalcuenta(id_cuenta);
    if (facturacuenta != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        facturacuenta = new Cuentas();
        facturacuenta.setId_cuenta(0);
        facturacuenta.setTotal(0);
    }
    /*int id_detallecaja = Integer.parseInt(request.getParameter("id_detallecaja"));
    DetallesCajas detallecaja = DetallesCajasControlador.buscarId(id_detallecaja);
    int total1 = facturacuenta.getTotal();
    System.out.println("total1 "+ total1);
    int importe = detallecaja.getImporte();
    int vuelto1 = importe - total1;*/

 /*Cuentas facturacuenta = CuentasControlador.buscarId(id_cuenta);
    if (facturacuenta != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        facturacuenta = new Cuentas();
        facturacuenta.setId_cuenta(0);
        //facturacuenta.setFecha_cuenta(ssFecha_cuenta);
        /*facturacuenta.setSubtotal_5(0);
        facturacuenta.setSubtotal_10(0);
        facturacuenta.setSubtotal_exenta(0);
        facturacuenta.setCantidad_cuotas(0);
  
        Clientes cliente = new Clientes();
        facturacuenta.setCliente(cliente);
        
        Tipo_facturas tipofactura = new Tipo_facturas();
        facturacuenta.setTipofactura(tipofactura);
        }*/
    //String contenido_detalle = DetalleCuentasControlador.buscarIdCuenta(id_cuenta);
    //CuentasControlador.buscarId(id_cuenta);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    //obj.put("id_cuenta", id_cuenta);
    //System.out.println("idfactura" + id_cuenta);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_cuenta", facturacuenta.getId_cuenta());
    System.out.println("idfactura==" + facturacuenta.getId_cuenta());
    obj.put("total", facturacuenta.getTotal());
    System.out.println("total " + facturacuenta.getTotal());
    //obj.put("fecha_cuenta", String.valueOf(facturacuenta.getFecha_cuenta()));
    //obj.put("id_cliente", facturacuenta.getCliente().getId_cliente());
    //obj.put("nombre_cliente", facturacuenta.getCliente().getNombre_cliente());
    //obj.put("ruc_cliente", facturacuenta.getCliente().getRuc_cliente());
    //obj.put("id_tipo_factura", facturacuenta.getTipofactura().getId_tipo_factura());
    //obj.put("nombre_tipo_factura", facturacuenta.getTipofactura().getNombre_tipo_factura());
    /*obj.put("subtotal_5", facturacuenta.getSubtotal_5());
    obj.put("subtotal_10", facturacuenta.getSubtotal_10());
    obj.put("subtotal_exenta", facturacuenta.getSubtotal_exenta());*/
    //obj.put("cantidad_cuotas", facturacuenta.getCantidad_cuotas());
    //obj.put("contenido_detalle", contenido_detalle);
    /* obj.put("vuelto", (vuelto1));
    System.out.println("vuelto "+ vuelto1);*/
    out.print(obj);
    out.flush();
%>