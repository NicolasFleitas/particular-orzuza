
<%@page import="controladores.FacturaVentasControlador"%>
<%@page import="modelos.Formas_pagos"%>
<%@page import="modelos.FacturaVentas"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="controladores.CajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallepago = Integer.parseInt(request.getParameter("id_detallepago"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesCajas detallepago = DetallesCajasControlador.buscarId(id_detallepago);
    if (detallepago != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallepago = new DetallesCajas();
        detallepago.setId_detallepago(0);
        detallepago.setImporte(0);

        Cajas pago = new Cajas();
        pago.setId_pago(0);
        detallepago.setCaja(pago);

        FacturaVentas facturaventa = new FacturaVentas();
        facturaventa.setId_factura_venta(0);
        detallepago.setFacturaventa(facturaventa);

        Formas_pagos pago = new Formas_pagos();
        pago.setId_forma_pago(0);
        pago.setNombre_forma_pago("");
        detallepago.setFacturaventa(facturaventa);
    }
    //int id_factura_venta = detallepago.getFacturaventa().getId_factura_venta();
    //System.out.println("idfactu "+ id_factura_venta);
    //FacturaVentas facturaventa = new FacturaVentas();
    //FacturaVentas facturaventa = FacturaVentasControlador.buscarTotalfactura(id_factura_venta);
    //int total1 = facturaventa.getTotal();
    //System.out.println("total1 "+ total1);
    //int importe = detallepago.getImporte();
    //int vuelto1 = importe - total1;

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detallepago", String.valueOf(detallepago.getId_detallepago()));
    obj.put("id_pago", String.valueOf(detallepago.getCaja().getId_pago()));
    System.out.println("pagoid= "+String.valueOf(detallepago.getCaja().getId_pago()));
    obj.put("id_factura_venta", String.valueOf(detallepago.getFacturaventa().getId_factura_venta()));
    //obj.put("numero_factura_venta", String.valueOf(detallepago.getFacturaventa().getNumero_factura_venta()));
    obj.put("id_forma_pago", detallepago.getPago().getId_forma_pago());
    //obj.put("nombre_forma_pago", detallepago.getPago().getNombre_forma_pago());
    obj.put("importe", String.valueOf(detallepago.getImporte()));
    //obj.put("vuelto", (vuelto1));
    //obj.put("total", facturaventa.getTotal());
    //System.out.println("total "+ facturaventa.getTotal());
    //System.out.println("vuelto "+ vuelto1);
    out.print(obj);
    out.flush();
%>
