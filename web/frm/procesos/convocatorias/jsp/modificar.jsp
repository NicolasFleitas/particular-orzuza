<%@page import="modelos.TipoConvocatorias"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.ConvocatoriasControlador"%>
<%@page import="modelos.Convocatorias"%>
<%@page import="modelos.TipoConvocatorias"%>


<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    String nombre_convocatoria = request.getParameter("nombre_convocatoria");
    int id_tipoconvocatoria = Integer.parseInt(request.getParameter("id_tipoconvocatoria")); 
    int monto_convocatoria = Integer.parseInt(request.getParameter("monto_convocatoria")); 
    String codigo_convocatoria = (request.getParameter("codigo_convocatoria"));    
    int monto_matricula = Integer.parseInt(request.getParameter("monto_matricula"));
    String sfecha_convocatoria = request.getParameter("fecha_convocatoria");
    java.sql.Date fecha_convocatoria = Utiles.stringToSqlDate(sfecha_convocatoria);
    String convocatoria_estado = request.getParameter("convocatoria_estado");
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria); 
    convocatoria.setNombre_convocatoria(nombre_convocatoria);
    
    TipoConvocatorias tipoconvocatoria = new TipoConvocatorias();
    tipoconvocatoria.setId_tipoconvocatoria(id_tipoconvocatoria);    
    convocatoria.setTipoconvocatoria(tipoconvocatoria);  
    
    convocatoria.setFecha_convocatoria(fecha_convocatoria);    
    
    convocatoria.setMonto_convocatoria(monto_convocatoria);
    convocatoria.setMonto_matricula(monto_matricula);    
    convocatoria.setCodigo_convocatoria(codigo_convocatoria);    
    convocatoria.setConvocatoria_estado(convocatoria_estado);    
    if (ConvocatoriasControlador.modificar(convocatoria)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
