
<%@page import="controladores.TipoConvocatoriasControlador"%>
<%@page import="modelos.TipoConvocatorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_tipoconvocatoria = Integer.parseInt(request.getParameter("id_tipoconvocatoria"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    TipoConvocatorias tipoconvocatoria = new TipoConvocatorias();
    tipoconvocatoria.setId_tipoconvocatoria(id_tipoconvocatoria);
    
   TipoConvocatoriasControlador.buscarId(tipoconvocatoria);
    if (tipoconvocatoria.getId_tipoconvocatoria()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_tipoconvocatoria", tipoconvocatoria.getId_tipoconvocatoria());
    obj.put("nombre_tipoconvocatoria", tipoconvocatoria.getNombre_tipoconvocatoria());
    out.print(obj);
    out.flush();
%>
