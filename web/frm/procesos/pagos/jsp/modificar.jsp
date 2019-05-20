<%@page import="utiles.Utiles"%>
<%@page import="controladores.PagosControlador"%>
<%@page import="modelos.Pagos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pago = Integer.parseInt(request.getParameter("id_pago"));
    //int monto_inicial = Integer.parseInt(request.getParameter("monto_inicial"));
    //String sfecha_apertura = request.getParameter("fecha_apertura");
    //java.sql.Date fecha_apertura = Utiles.stringToSqlDate(sfecha_apertura);
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Pagos pago = new Pagos();
    pago.setId_pago(id_pago);
    //pago.setFecha_apertura(fecha_apertura);
    //pago.setMonto_inicial(monto_inicial);
    //pago.setEstado_pago(estado_pago);
    //pago.setNombre_pago(nombre_pago);
    
    if (PagosControlador.modificarestado(pago)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
