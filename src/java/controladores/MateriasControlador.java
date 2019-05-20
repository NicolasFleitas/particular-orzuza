/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author nicof
 */
import modelos.Materias;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class MateriasControlador {
    
    
     public static boolean agregar(Materias materia){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into materias (nombre_materia)" 
                    + "values ('" + materia.getNombre_materia() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(MateriasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
     
     public static boolean modificar(Materias materia){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update materias set nombre_materia='" + materia.getNombre_materia() + "'"
                    + " where id_materia=" + materia.getId_materia();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(MateriasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Materias materia){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from materias where id_materia=" + materia.getId_materia();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(MateriasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    public static Materias buscarId(Materias materia) {
        if (Conexion.conectar()){
            String sql = "select * from materias where id_materia ="+materia.getId_materia();
            System.out.println("SQL buscarId: "+ sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    materia.setId_materia(rs.getInt("id_materia"));
                    materia.setNombre_materia(rs.getString("nombre_materia"));
                } else {
                    materia.setId_materia(0);
                    materia.setNombre_materia("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return materia;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from materias where upper(nombre_materia) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_materia offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                //System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_materia") + "</td>"
                                + "<td>" + rs.getString("nombre_materia") + "</td>"
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
