<%@page import="utiles.Utiles"%>
<%@page  import="controladores.InscripcionesControlador"%>
<%@page  import="org.json.simple.JSONObject"%>
<%@page  import="java.sql.ResultSet"%>
<%
    int ci_alumno = 0;
    
    try { 
        ci_alumno = Integer.parseInt(request.getParameter("bnroci"));    
    } catch(NumberFormatException e) {
        
    }
    
    String sfecha_buscar = request.getParameter("fecha_buscar");   
    java.sql.Date fecha_buscar = Utiles.stringToSqlDate(sfecha_buscar);
    int pagina = Integer.parseInt(request.getParameter("bpagina"));

    String mensaje = "Busqueda exitosa";
    String contenido = InscripcionesControlador.buscarCuotaCi(ci_alumno,fecha_buscar, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);    
    out.print(obj);
    out.flush();
%>
