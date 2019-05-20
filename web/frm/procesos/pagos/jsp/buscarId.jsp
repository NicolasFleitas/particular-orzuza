
<%@page import="controladores.DetallesPagosControlador"%>
<%@page import="controladores.PagosControlador"%>
<%@page import="modelos.Pagos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
   
    int id_usuario = Integer.parseInt(request.getParameter("sid_usuario"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    
   Pagos pago = PagosControlador.buscarIdestado(id_usuario);
    if (pago !=null){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else{
        pago = new Pagos();
        pago.setId_pago(0);
        java.sql.Date fecha_pago = new java.sql.Date(new java.util.Date().getTime());
        pago.setFecha_pago(fecha_pago);
       // pago.setMonto_inicial(0);
      //  pago.setEstado_pago("CERRADO");
        mensaje = "Debe abrir la caja.";
        
    }
    
   // int id_pago =pago.getId_pago();

    //String contenido_detalle = DetallesPagosControlador.buscarIdPago(id_pago);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_pago", pago.getId_pago());
    obj.put("fecha_pago", String.valueOf(pago.getFecha_pago()));
    //obj.put("monto_inicial", pago.getMonto_inicial());
   // obj.put("estado_pago", pago.getEstado_pago());
    //obj.put("contenido_detalle", contenido_detalle);
    out.print(obj);
    out.flush();
%>
