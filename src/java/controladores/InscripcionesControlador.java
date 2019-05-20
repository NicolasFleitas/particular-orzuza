package controladores;
import java.sql.Connection;
import java.sql.Date;
import modelos.Inscripciones;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;
import modelos.Alumnos;
import modelos.Convocatorias;
import modelos.Materias;


public class InscripcionesControlador {
    
     public static boolean agregar(Inscripciones inscripcion){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into inscripciones ("
                    + "fecha_inscripcion"
                    + ",id_alumno"
                    + ",id_convocatoria"
                    + ",nro_cuotas"
                    + ",vencimientocuota_inscripcion)" 
                    + " values ('" + inscripcion.getFecha_inscripcion()+ "','"
                    + inscripcion.getAlumno().getId_alumno() + "','"
                    + inscripcion.getConvocatoria().getId_convocatoria()+ "','" 
                    + inscripcion.getNro_cuotas()+ "','" 
                    + inscripcion.getVencimientocuota_inscripcion()+ "')";
            
                   // System.out.println("sql agregar: "+ sql);
                    
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);                
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();                                 
                if (keyset.next()) {
                    int id_inscripcion = keyset.getInt(1);
                    inscripcion.setId_inscripcion(id_inscripcion);
                    // Cannot commit when autoCommit is enabled.
                    //Conexion.getConn().commit();
                    Conexion.getConn().setAutoCommit(false);
                    // Cannot commit when autoCommit is enabled.
                }
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(InscripcionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
     
     public static boolean modificar(Inscripciones inscripcion){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update inscripciones set "
                    + "fecha_inscripcion='" + inscripcion.getFecha_inscripcion()+ "',"
                    + "id_alumno='"+ inscripcion.getAlumno().getId_alumno() + "',"
                    + "id_convocatoria='"+ inscripcion.getConvocatoria().getId_convocatoria()+ "',"
                    + "vencimientocuota_inscripcion='"+ inscripcion.getVencimientocuota_inscripcion()+ "',"
                    + "nro_cuotas='"+ inscripcion.getNro_cuotas()+ "'"
                    + " where id_inscripcion=" + inscripcion.getId_inscripcion();
                    System.out.println("sql modificar: "+sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(InscripcionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Inscripciones inscripcion){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from inscripciones where id_inscripcion=" + inscripcion.getId_inscripcion();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(InscripcionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Inscripciones buscarId(Inscripciones inscripcion) {
        if (Conexion.conectar()){
            String sql = "select * from inscripciones i,"
                    + "alumnos a,"
                    + "convocatorias cv"
                    + " where "
                    + "i.id_alumno=a.id_alumno"
                    + " and "
                    + "i.id_convocatoria=cv.id_convocatoria"
                    + " and "        
                    + " id_inscripcion ='"+inscripcion.getId_inscripcion()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    inscripcion.setId_inscripcion(rs.getInt("id_inscripcion"));
                    inscripcion.setFecha_inscripcion(rs.getDate("fecha_inscripcion"));
                    inscripcion.setVencimientocuota_inscripcion(rs.getDate("vencimientocuota_inscripcion"));
                    
                    Alumnos alumno = new Alumnos();
                    alumno.setId_alumno(rs.getInt("id_alumno"));
                    alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                    alumno.setApellido_alumno(rs.getString("apellido_alumno"));
                    alumno.setNroci_alumno(rs.getInt("nroci_alumno"));
                 
                    Convocatorias convocatoria = new Convocatorias();
                    convocatoria.setId_convocatoria(rs.getInt("id_convocatoria"));                    
                    convocatoria.setCodigo_convocatoria(rs.getString("codigo_convocatoria"));
                    convocatoria.setNombre_convocatoria(rs.getString("nombre_convocatoria"));
                    inscripcion.setAlumno(alumno);
                    inscripcion.setConvocatoria(convocatoria);                    
                    inscripcion.setNro_cuotas(rs.getInt("nro_cuotas"));
                    inscripcion.setFecha_inscripcion(rs.getDate("fecha_inscripcion"));
                } else {    
                   inscripcion.setId_inscripcion(0);
                   //Obtengo la fecha actual para luego asignarla si no se encuentra creada la inscripcion
                   java.sql.Date fecha_inscripcion = new java.sql.Date(new java.util.Date().getTime());
                   inscripcion.setFecha_inscripcion(fecha_inscripcion);
                   //**//
                   inscripcion.setVencimientocuota_inscripcion(null);                    
                   
                    Alumnos alumno = new Alumnos();
                    alumno.setId_alumno(0);
                    alumno.setNombre_alumno("");
                    alumno.setApellido_alumno("");
                    alumno.setNroci_alumno(0);                   
                                        
                    Convocatorias  convocatoria= new Convocatorias();
                    convocatoria.setId_convocatoria(0);        
                    
                    
                    inscripcion.setAlumno(alumno);
                    inscripcion.setConvocatoria(convocatoria);   
                    inscripcion.setNro_cuotas(0);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return inscripcion;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  //System.out.println(nombre);
                String sql = "SELECT * FROM inscripciones i,alumnos a, convocatorias cv"
                        + " WHERE "
                        + "i.id_alumno = a.id_alumno"
                        + " AND " 
                        + "i.id_convocatoria = cv.id_convocatoria"                      
                        + " AND "
                        + "(a.nombre_alumno) like '%" +
                        nombre.toUpperCase() + "%'"
                        + " ORDER BY id_inscripcion offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
               // System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_inscripcion") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("nombre_convocatoria") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    
    
    /* BUSCAR CUOTAS VENCIDAS POR NOMBRE */
     public static String buscarCuota(String nombre, Date fecha_buscar,  int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        
       //java.sql.Date hoy = new java.sql.Date(new java.util.Date().getTime());
                           
        if (Conexion.conectar()) {
            
            try {    
                String sql= "SELECT * FROM cuentas_cte cta, inscripciones i, alumnos a"
                        + " WHERE "                        
                        + "cta.id_inscripcion = i.id_inscripcion"
                        + " AND "
                        + "i.id_alumno = a.id_alumno"
                        + " AND " 
                        + "cta.estado LIKE 'PENDIENTE'"
                        + " AND " 
                        + "cta.vencimiento_cuota < '"+ fecha_buscar +"'"
                        + " AND "
                        + "(a.nombre_alumno) like '%" +nombre.toUpperCase() + "%'"
                        + " ORDER BY vencimiento_cuota, i.id_inscripcion offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                        
               System.out.println("SQL BUSCAR CUOTA por NOMBRE --> "+ sql);
               
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_inscripcion") + "</td>"
                               + "<td>" + rs.getDate("vencimiento_cuota") + "</td>"  
                                + "<td>" + rs.getString("nroci_alumno") + "</td>"
                               + "<td>" + rs.getString("nombre_alumno") + "</td>"
                               + "<td>" + rs.getString("cuota_cuota") + "</td>"
                               + "<td>" + rs.getInt("monto_cuota") + "</td>"      
                               + "<td>" + rs.getInt("total_cuota") + "</td>"      
                               + "<td>" + rs.getString("estado") + "</td>"
                               + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
     
    /*BUSCAR CUOTAS POR CI */       
        public static String buscarCuotaCi(int ci_alumno, Date fecha_buscar, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        java.sql.Date hoy = new java.sql.Date(new java.util.Date().getTime());
        if (Conexion.conectar()) {
            
            try {    
                String sql= "SELECT * FROM cuentas_cte cta, inscripciones i, alumnos a"
                        + " WHERE "                        
                        + "cta.id_inscripcion = i.id_inscripcion"
                        + " AND "
                        + "i.id_alumno = a.id_alumno"
                        + " AND " 
                        + "cta.estado LIKE 'PENDIENTE'"
                        + " AND " 
                        + "cta.vencimiento_cuota < '"+ fecha_buscar +"'"
                        + " AND "
                        + "a.nroci_alumno = '" +ci_alumno + "'"
                        + " ORDER BY a.nroci_alumno, cta.vencimiento_cuota, i.id_inscripcion offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                        
               System.out.println("SQL BUSCAR CUOTA por CI --> "+ sql);
               
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_inscripcion") + "</td>"
                               + "<td>" + rs.getDate("vencimiento_cuota") + "</td>"  
                                + "<td>" + rs.getString("nroci_alumno") + "</td>"
                               + "<td>" + rs.getString("nombre_alumno") + "</td>"
                               + "<td>" + rs.getString("cuota_cuota") + "</td>"
                               + "<td>" + rs.getInt("monto_cuota") + "</td>"      
                               + "<td>" + rs.getInt("total_cuota") + "</td>"      
                               + "<td>" + rs.getString("estado") + "</td>"
                               + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    
    
     
    
    
    
}
