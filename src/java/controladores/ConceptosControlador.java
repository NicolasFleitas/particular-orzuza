package controladores;

import modelos.Conceptos;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConceptosControlador {    
    
     public static boolean agregar(Conceptos concepto){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "INSERT INTO conceptos (nombre_concepto)" 
                    + "VALUES('" + concepto.getNombre_concepto() + "')";                    
            try {
                Conexion.getSt().executeUpdate(sql);                
                valor = true;                
            } catch (SQLException ex) {
                Logger.getLogger(ConceptosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }        
        return valor;        
    }
     
     public static boolean modificar(Conceptos concepto){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "UPDATE conceptos SET nombre_concepto='" + concepto.getNombre_concepto() + "'"
                    + " WHERE id_concepto=" + concepto.getId_concepto();                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;                
            } catch (SQLException ex) {
                Logger.getLogger(ConceptosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }        
        return valor;
    }
    
    public static boolean eliminar(Conceptos concepto){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "DELETE FROM conceptos WHERE id_concepto=" + concepto.getId_concepto();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(ConceptosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static Conceptos buscarId(Conceptos concepto) {
        if (Conexion.conectar()){
            String sql = "SELECT * FROM conceptos WHERE id_concepto ='"+concepto.getId_concepto()+"'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    concepto.setId_concepto(rs.getInt("id_concepto"));
                    concepto.setNombre_concepto(rs.getString("nombre_concepto"));
                } else {
                    concepto.setId_concepto(0);
                    concepto.setNombre_concepto("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return concepto;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                  System.out.println(nombre);
                String sql = "SELECT * FROM conceptos WHERE upper(nombre_concepto) LIKE '%" +
                        nombre.toUpperCase() + "%'"
                        + "ORDER BY id_concepto OFFSET " + offset + " LIMIT " + Utiles.REGISTROS_PAGINA;
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_concepto") + "</td>"
                                + "<td>" + rs.getString("nombre_concepto") + "</td>"
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
