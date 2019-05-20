<%@page import="modelos.Cajas"%>
<%@page import="controladores.StockControlador"%>
<%@page import="modelos.Stock"%>
<%@page import="modelos.FacturaVentas"%>
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pago = Integer.parseInt(request.getParameter("id_pago"));
    int id_factura_venta = Integer.parseInt(request.getParameter("id_factura_venta"));
    int id_detallepago = Integer.parseInt(request.getParameter("id_pago"));
    int importe = Integer.parseInt(request.getParameter("importe"));
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    Cajas pago = new Cajas();
    pago.setId_pago(id_pago);
    
    DetallesCajas detallepago = new DetallesCajas();
    detallepago.setId_detallepago(id_detallepago);
    FacturaVentas venta = new FacturaVentas();
    venta.setId_factura_venta(id_factura_venta);



    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>