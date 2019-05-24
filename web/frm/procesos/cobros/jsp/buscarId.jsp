
<%@page import="controladores.CobrosControlador"%>
<%@page import="modelos.Cobros"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cobro = Integer.parseInt(request.getParameter("id_cobro"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Cobros cobro = new Cobros();
    cobro.setId_cobro(id_cobro);
    
   CobrosControlador.buscarId(cobro);
    if (cobro.getId_cobro()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        cobro = new Cobros();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_cobro", cobro.getId_cobro());
    obj.put("nombre_cobro", cobro.getNombre_cobro());
    out.print(obj);
    out.flush();
%>
