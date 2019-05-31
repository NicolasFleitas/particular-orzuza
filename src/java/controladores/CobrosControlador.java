package controladores;
import modelos.Cobros;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CobrosControlador {  
    
     public static boolean agregar(Cobros cobro){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "INSERT INTO cobros (nombre_cobro) " 
                    + "VALUES('" + cobro.getNombre_cobro() + "')";                    
            try {
                Conexion.getSt().executeUpdate(sql);                
                valor = true;                
            } catch (SQLException ex) {
                Logger.getLogger(CobrosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }        
        return valor;        
    }
     
     public static boolean modificar(Cobros cobro){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "UPDATE cobros SET nombre_cobro='" + cobro.getNombre_cobro() + "'"
                    + " WHERE id_cobro=" + cobro.getId_cobro();                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;                
            } catch (SQLException ex) {
                Logger.getLogger(CobrosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }     
        return valor;        
    }
    
    public static boolean eliminar(Cobros cobro){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "DELETE FROM cobros WHERE id_cobro=" + cobro.getId_cobro();                    
            try {
                Conexion.getSt().executeUpdate(sql);                
                valor = true;                
            } catch (SQLException ex) {
                Logger.getLogger(CobrosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }        
        return valor;        
    }
    
    public static Cobros buscarId(Cobros cobro) {
        if (Conexion.conectar()){
            String sql = "SELECT * FROM cobros WHERE id_cobro ='"+cobro.getId_cobro()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    cobro.setId_cobro(rs.getInt("id_cobro"));
                    cobro.setNombre_cobro(rs.getString("nombre_cobro"));
                } else {
                    cobro.setId_cobro(0);
                    cobro.setNombre_cobro("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return cobro;
    }
    
    public static String buscarNombre(String nombre, int pagina) {      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {            
            try {
                  System.out.println(nombre);
                String sql = "SELECT * FROM cobros WHERE upper(nombre_cobro) LIKE '%" +
                        nombre.toUpperCase() + "%'"
                        + "ORDER BY id_cobro OFFSET " + offset + " LIMIT " + Utiles.REGISTROS_PAGINA; 
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cobro") + "</td>"
                                + "<td>" + rs.getString("nombre_cobro") + "</td>"
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
