<%@page import="modelos.Inscripciones"%>
<%@page import="controladores.CobrosControlador"%>
<%@page import="modelos.Cobros"%>
<%@page import="modelos.Cuentas"%>
<%@page import="controladores.CuentasControlador"%>
<%@page import="controladores.CajasControlador"%>
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="modelos.FacturaVentas"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));    
    int nro_cuota_pagar = Integer.parseInt(request.getParameter("nro_cuota_pagar"));    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
        
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    
    Cuentas cuenta = new Cuentas();
    cuenta.setInscripcion(inscripcion);    
    cuenta.setCuota_cuota(nro_cuota_pagar);    
 
    if (CuentasControlador.modificarestadocobro(cuenta)) {
        tipo = "success";
        mensaje = "Se cobro la cuota nro " + nro_cuota_pagar;
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);

    out.print(obj);
    out.flush();
    
%>