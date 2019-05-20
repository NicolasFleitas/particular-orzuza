<%@page import="modelos.TipoConvocatorias"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.ConvocatoriasControlador"%>
<%@page import="modelos.Convocatorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    String nombre_convocatoria = request.getParameter("nombre_convocatoria");
    int id_tipoconvocatoria = Integer.parseInt(request.getParameter("id_tipoconvocatoria"));    
    int monto_convocatoria = Integer.parseInt(request.getParameter("monto_convocatoria"));
    int monto_matricula = Integer.parseInt(request.getParameter("monto_matricula"));
    String convocatoria_estado = request.getParameter("convocatoria_estado");
    
    String sfecha_convocatoria = request.getParameter("fecha_convocatoria");
    
    java.sql.Date fecha_convocatoria = Utiles.stringToSqlDate(sfecha_convocatoria);  
    
    String codigo_convocatoria = (request.getParameter("codigo_convocatoria"));       
    String tipo = "error";
    String mensaje = "Datos no agregados.";

   

    TipoConvocatorias tipoconvocatoria = new TipoConvocatorias();
    tipoconvocatoria.setId_tipoconvocatoria(id_tipoconvocatoria);

    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    convocatoria.setNombre_convocatoria(nombre_convocatoria);
    convocatoria.setFecha_convocatoria(fecha_convocatoria);
    convocatoria.setTipoconvocatoria(tipoconvocatoria);
        
    convocatoria.setMonto_convocatoria(monto_convocatoria);
    convocatoria.setMonto_matricula(monto_matricula);       
    convocatoria.setCodigo_convocatoria(codigo_convocatoria);    
    convocatoria.setConvocatoria_estado(convocatoria_estado);
    
    if (ConvocatoriasControlador.agregar(convocatoria)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_convocatoria", String.valueOf(convocatoria.getId_convocatoria()));
    out.print(obj);
    out.flush();

%>