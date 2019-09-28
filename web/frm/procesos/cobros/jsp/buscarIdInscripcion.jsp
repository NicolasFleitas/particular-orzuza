
<%@page import="modelos.Cuentas"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.InscripcionesControlador"%>
<%@page import="modelos.Inscripciones"%>
<%@page import="modelos.Convocatorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    int TOTAL_CUOTAS_PENDIENTES = 0;
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    
   Cuentas cuenta = new Cuentas();   
   InscripcionesControlador.buscarIdCte(inscripcion);
   
    if (inscripcion.getId_inscripcion()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";        
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_inscripcion", inscripcion.getId_inscripcion());    
    obj.put("fecha_inscripcion", String.valueOf(inscripcion.getFecha_inscripcion()));     
    obj.put("vencimientocuota_inscripcion",String.valueOf(inscripcion.getVencimientocuota_inscripcion()));        
    obj.put("id_alumno", inscripcion.getAlumno().getId_alumno());
    obj.put("nombre_alumno", inscripcion.getAlumno().getNombre_alumno());
    obj.put("apellido_alumno", inscripcion.getAlumno().getApellido_alumno());
    obj.put("nroci_alumno", inscripcion.getAlumno().getNroci_alumno());    
    obj.put("id_convocatoria",inscripcion.getConvocatoria().getId_convocatoria());    
    obj.put("nombre_convocatoria", inscripcion.getConvocatoria().getNombre_convocatoria());
    obj.put("codigo_convocatoria",inscripcion.getConvocatoria().getCodigo_convocatoria());
    obj.put("nro_cuotas", inscripcion.getNro_cuotas());
    obj.put("monto_cuota", inscripcion.getConvocatoria().getMonto_convocatoria());
    
    obj.put("TOTAL_CUOTAS_PENDIENTES", TOTAL_CUOTAS_PENDIENTES);
    obj.put("CANT_CUOTAS_PENDIENTES", inscripcion.getNro_cuotas());
   /* obj.put("cuota_cuota", cuenta.getCuota_cuota());    
    obj.put("estado", cuenta.getEstado());
     */       
    out.print(obj);
    out.flush();
%>
