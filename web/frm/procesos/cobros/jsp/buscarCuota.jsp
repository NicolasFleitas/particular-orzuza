<%@page import="java.text.ParseException"%>
<%@page import="utiles.Utiles"%>
<%@page  import="controladores.InscripcionesControlador"%>
<%@page  import="org.json.simple.JSONObject"%>
<%@page  import="java.sql.ResultSet"%>
<%
    String nombre_alumno = request.getParameter("bnombre");  
    String sfecha_buscar = request.getParameter("fecha_buscar");         
    java.sql.Date fecha_buscar;      
      
    if (sfecha_buscar.equals("")) {          
        fecha_buscar = new java.sql.Date(new java.util.Date().getTime());          
    } else {
        fecha_buscar = Utiles.stringToSqlDate(sfecha_buscar);  
    }      
    int pagina = Integer.parseInt(request.getParameter("bpagina"));

    String mensaje = "Busqueda exitosa";    
    String contenido = InscripcionesControlador.buscarCuota(nombre_alumno,fecha_buscar, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);    
    out.print(obj);
    out.flush();    
%>
