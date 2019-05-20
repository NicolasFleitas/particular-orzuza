<%@page import="modelos.Turnos"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.DetallesConvocatoriasControlador"%>
<%@page import="controladores.ConvocatoriasControlador"%>
<%@page import="modelos.Convocatorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));    
    
   // String sfecha_convocatoria = request.getParameter("fecha_convocatoria");
  //  java.sql.Date fecha_convocatoria = Utiles.stringToSqlDate(sfecha_convocatoria);
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Convocatorias convocatorias = ConvocatoriasControlador.buscarId(id_convocatoria);
    if (convocatorias != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        convocatorias = new Convocatorias();
        convocatorias.setId_convocatoria(0);
       // convocatorias.setFecha_convocatoria(fecha_convocatoria);
        convocatorias.setNombre_convocatoria("");
  
        convocatorias.setMonto_convocatoria(0);       
        convocatorias.setCodigo_convocatoria("");
        }    
    //String contenido_detalle = DetallesConvocatoriasControlador.buscarIdConvocatoria(id_convocatoria);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_convocatoria",convocatorias.getId_convocatoria());    
    
    //obj.put("monto_convocatoria", convocatorias.getMonto_convocatoria());    
    obj.put("codigo_convocatoria", convocatorias.getCodigo_convocatoria());
    obj.put("nombre_convocatoria", convocatorias.getNombre_convocatoria());
    
    //obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>