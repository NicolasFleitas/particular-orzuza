
<%@page import="controladores.TipoConvocatoriasControlador"%>
<%@page import="modelos.TipoConvocatorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_tipoconvocatoria = Integer.parseInt(request.getParameter("id_tipoconvocatoria"));
    String nombre_tipoconvocatoria = request.getParameter("nombre_tipoconvocatoria");

    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    TipoConvocatorias tipoconvocatoria = new TipoConvocatorias();
    tipoconvocatoria.setId_tipoconvocatoria(id_tipoconvocatoria);
    tipoconvocatoria.setNombre_tipoconvocatoria(nombre_tipoconvocatoria);

    if (TipoConvocatoriasControlador.agregar(tipoconvocatoria)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

