
<%@page import="controladores.CobrosControlador"%>
<%@page import="modelos.Cobros"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cobro = Integer.parseInt(request.getParameter("id_cobro"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Cobros cobro = new Cobros();
    cobro.setId_cobro(id_cobro);
    
    if (CobrosControlador.eliminar(cobro)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>