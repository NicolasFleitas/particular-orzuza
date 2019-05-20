
package controladores;

import modelos.TipoConvocatorias;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class TipoConvocatoriasControlador {
    
    
     public static boolean agregar(TipoConvocatorias tipoconvocatoria){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into tipoconvocatorias (nombre_tipoconvocatoria)" 
                    + "values ('" + tipoconvocatoria.getNombre_tipoconvocatoria() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TipoConvocatoriasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
     
     public static boolean modificar(TipoConvocatorias tipoconvocatoria){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update tipoconvocatorias set nombre_tipoconvocatoria='" + tipoconvocatoria.getNombre_tipoconvocatoria() + "'"
                    + " where id_tipoconvocatoria=" + tipoconvocatoria.getId_tipoconvocatoria();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TipoConvocatoriasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(TipoConvocatorias tipoconvocatoria){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from tipoconvocatorias where id_tipoconvocatoria=" + tipoconvocatoria.getId_tipoconvocatoria();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TipoConvocatoriasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    public static TipoConvocatorias buscarId(TipoConvocatorias tipoconvocatoria) {
        if (Conexion.conectar()){
            String sql = "select * from tipoconvocatorias where id_tipoconvocatoria ='"+tipoconvocatoria.getId_tipoconvocatoria()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    tipoconvocatoria.setId_tipoconvocatoria(rs.getInt("id_tipoconvocatoria"));
                    tipoconvocatoria.setNombre_tipoconvocatoria(rs.getString("nombre_tipoconvocatoria"));
                } else {
                    tipoconvocatoria.setId_tipoconvocatoria(0);
                    tipoconvocatoria.setNombre_tipoconvocatoria("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return tipoconvocatoria;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from tipoconvocatorias where upper(nombre_tipoconvocatoria) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_tipoconvocatoria offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_tipoconvocatoria") + "</td>"
                                + "<td>" + rs.getString("nombre_tipoconvocatoria") + "</td>"
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
