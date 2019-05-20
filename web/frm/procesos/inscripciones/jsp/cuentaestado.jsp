
<%@page import="utiles.Conexion"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/pdf"%>
<%
    String dirPath="/rpt";
    String realPath=this.getServletContext().getRealPath(dirPath);
   // String listado=request.getParameter("l");
    String listado = "cuotasAlumnos";
    int id_inscripcion =Integer.parseInt(request.getParameter("id"));
    int ci=Integer.parseInt(request.getParameter("ci"));
    String jasperReport=listado+".jasper";
    JasperPrint print=null;
    Connection conn=null;
    
    //Clientes clienteLogueado=(Clientes) sesion.getAttribute("clienteLoagueado");
    
    try{
        Conexion.conectar();
        conn=Conexion.getConn();
        Map parameters=new HashMap();
        parameters.put("id_inscripcion",id_inscripcion);
        parameters.put("nroci_alumno",ci);
        //parameters.put("USUARIO",clienteLogueado.getCliente_cliente());
        print =JasperFillManager.fillReport(realPath+"//"+jasperReport, parameters,conn);
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }catch(Exception ex){
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    }
    finally{
        if(conn!=null){
            conn.close();
        }
    }
%>
