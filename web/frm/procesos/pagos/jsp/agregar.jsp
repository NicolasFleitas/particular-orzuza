
<%@page import="modelos.Usuarios"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.PagosControlador"%>
<%@page import="modelos.Pagos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pago = Integer.parseInt(request.getParameter("id_pago"));
    String sfecha_apertura = request.getParameter("fecha_apertura");
    java.sql.Date fecha_apertura = Utiles.stringToSqlDate(sfecha_apertura);
    int monto_inicial = Integer.parseInt(request.getParameter("monto_inicial"));
    String estado_pago = "ABIERTO";
    int id_usuario = Integer.parseInt(request.getParameter("sid_usuario"));
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);
    
    Pagos pago = new Pagos();
    pago.setId_pago(id_pago);
    pago.setFecha_pago(fecha_apertura);
    pago.setMonto_inicial(monto_inicial);
    pago.setEstado_pago(estado_pago);
    pago.setUsuario(usuario);
    
    if (PagosControlador.agregar(pago)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_pago", pago.getId_pago());
    out.print(obj);
    out.flush();
%>

