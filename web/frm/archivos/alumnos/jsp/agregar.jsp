<%@page import="modelos.Tutores"%>
<%@page import="utiles.Utiles"%>
<%@page import="modelos.Sexos"%>
<%@page import="controladores.AlumnosControlador"%>
<%@page import="modelos.Alumnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_alumno = Integer.parseInt(request.getParameter("id_alumno"));
    String nombre_alumno = request.getParameter("nombre_alumno");
    String apellido_alumno = request.getParameter("apellido_alumno");
    int nroci_alumno = Integer.parseInt(request.getParameter("nroci_alumno"));
    String fecha_nac_alumno = request.getParameter("fecha_nac_alumno");    
    String telefono_alumno = request.getParameter("telefono_alumno");    
    
    //String sfecha_nac_alumno = request.getParameter("fecha_nac_alumno");    
    //java.sql.Date fecha_nac_alumno = Utiles.stringToSqlDate(sfecha_nac_alumno);
    int id_sexo = Integer.parseInt(request.getParameter("id_sexo"));    
    Sexos sexo = new Sexos();
    sexo.setId_sexo(id_sexo);   
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Alumnos alumno = new Alumnos();
    alumno.setId_alumno(id_alumno);
    alumno.setNombre_alumno(nombre_alumno);
    alumno.setApellido_alumno(apellido_alumno);
    alumno.setNroci_alumno(nroci_alumno);
    alumno.setFecha_nac_alumno(fecha_nac_alumno); 
    alumno.setTelefono_alumno(telefono_alumno);    
    alumno.setSexo(sexo); 
    
    if (AlumnosControlador.agregar(alumno)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
