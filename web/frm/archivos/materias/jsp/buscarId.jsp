
<%@page import="controladores.MateriasControlador"%>
<%@page import="modelos.Materias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_materia = Integer.parseInt(request.getParameter("id_materia"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Materias materia = new Materias();
    materia.setId_materia(id_materia);
    
   MateriasControlador.buscarId(materia);
    if (materia.getId_materia()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }/*else {
        materia = new Materias();
    }*/
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_materia", materia.getId_materia());
    obj.put("nombre_materia", materia.getNombre_materia());
    out.print(obj);
    out.flush();
%>
