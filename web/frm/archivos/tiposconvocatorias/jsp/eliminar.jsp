
<%@page import="controladores.TipoConvocatoriasControlador"%>
<%@page import="modelos.TipoConvocatorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_tipoconvocatoria = Integer.parseInt(request.getParameter("id_tipoconvocatoria"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    TipoConvocatorias tipoconvocatoria = new TipoConvocatorias();
    tipoconvocatoria.setId_tipoconvocatoria(id_tipoconvocatoria);
    
    if (TipoConvocatoriasControlador.eliminar(tipoconvocatoria)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>