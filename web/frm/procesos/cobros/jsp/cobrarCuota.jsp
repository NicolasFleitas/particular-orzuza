<%@page import="modelos.Alumnos"%>
<%@page import="utiles.Utiles"%>
<%@page import="modelos.Inscripciones"%>
<%@page import="controladores.CobrosControlador"%>
<%@page import="modelos.Cobros"%>
<%@page import="modelos.Cuentas"%>
<%@page import="controladores.CuentasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));    
    int nro_cuota_pagar = Integer.parseInt(request.getParameter("nro_cuota_pagar"));    
    int monto_pagar = Integer.parseInt(request.getParameter("monto_pagar"));
    String nombre_alumno = request.getParameter("nombre_alumno");
    String apellido_alumno = request.getParameter("apellido_alumno");
    String sfecha_pago = request.getParameter("fecha_pago");    
    java.sql.Date fecha_pago = Utiles.stringToSqlDate(sfecha_pago);    
    String tipo_pago = request.getParameter("tipo_pago");
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
      
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    
    
    Cuentas cuenta = new Cuentas();
    cuenta.setInscripcion(inscripcion);    
    cuenta.setCuota_cuota(nro_cuota_pagar); 
    cuenta.setMonto_cuota(monto_pagar);
    cuenta.setEstado(tipo_pago);
    cuenta.setFecha_pago(fecha_pago);
     
    if (CuentasControlador.modificarestadocobro(cuenta)) {
        tipo = "success";
        mensaje = "Se cobro la cuota nro " 
                + nro_cuota_pagar + " | Monto Cobrado: " 
                + monto_pagar + " | Alumno: " 
                + nombre_alumno + " | " + apellido_alumno;        
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>