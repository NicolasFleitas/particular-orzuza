<%@page import="modelos.TipoConvocatorias"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.DetallesConvocatoriasControlador"%>
<%@page import="controladores.ConvocatoriasControlador"%>
<%@page import="modelos.Convocatorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
   int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
  // String sfecha_convocatoria = request.getParameter("fecha_convocatoria");
  //java.sql.Date fecha_convocatoria = Utiles.stringToSqlDate(sfecha_convocatoria);
   
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
  
    
        
        TipoConvocatorias tipoconvocatoria = new TipoConvocatorias();
        tipoconvocatoria.setId_tipoconvocatoria(0);
        tipoconvocatoria.setNombre_tipoconvocatoria("");
        convocatorias.setTipoconvocatoria(tipoconvocatoria);
        
        convocatorias.setMonto_convocatoria(0);       
        convocatorias.setMonto_matricula(0);       
        convocatorias.setCodigo_convocatoria("");
        convocatorias.setConvocatoria_estado("");
        } 
    String contenido_detalle = DetallesConvocatoriasControlador.buscarIdConvocatoria(id_convocatoria);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_convocatoria",convocatorias.getId_convocatoria());    
//    obj.put("fecha_convocatoria", Utiles.sqlDateToString((convocatorias.getFecha_convocatoria())));     
    obj.put("fecha_convocatoria", String.valueOf(convocatorias.getFecha_convocatoria()));     
    
    obj.put("id_tipoconvocatoria",convocatorias.getTipoconvocatoria().getId_tipoconvocatoria());
    obj.put("nombre_convocatoria", convocatorias.getNombre_convocatoria());
    obj.put("nombre_tipoconvocatoria",convocatorias.getTipoconvocatoria().getNombre_tipoconvocatoria());    
    obj.put("monto_convocatoria", convocatorias.getMonto_convocatoria());    
    obj.put("monto_matricula", convocatorias.getMonto_matricula());    
    obj.put("codigo_convocatoria", convocatorias.getCodigo_convocatoria());
    obj.put("convocatoria_estado", convocatorias.getConvocatoria_estado());
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>