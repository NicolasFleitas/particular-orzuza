
<%@page import="controladores.MateriasControlador"%>
<%@page import="modelos.Materias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_materia = Integer.parseInt(request.getParameter("id_materia"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Materias materia = new Materias();
    materia.setId_materia(id_materia);
    
    if (MateriasControlador.eliminar(materia)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>