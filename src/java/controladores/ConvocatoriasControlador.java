
package controladores;

import modelos.Convocatorias;
import modelos.TipoConvocatorias;
import utiles.Conexion;
import utiles.Utiles;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelos.Cobros;

public class ConvocatoriasControlador {
    
     private static ArrayList<Convocatorias> listaConvocatoria;
   
    public static Convocatorias buscarId(int id) {
        Convocatorias convocatorias = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias cv "
                        + "left join tipoconvocatorias tp "
                        + "on cv.id_tipoconvocatoria=tp.id_tipoconvocatoria"                       
                        + " where id_convocatoria=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        convocatorias = new Convocatorias();
                        convocatorias.setId_convocatoria(rs.getInt("id_convocatoria"));        
                        convocatorias.setNombre_convocatoria(rs.getString("nombre_convocatoria"));
                        convocatorias.setFecha_convocatoria(rs.getDate("fecha_convocatoria"));                        
                        TipoConvocatorias tipoconvocatoria = new TipoConvocatorias();                        
                        tipoconvocatoria.setId_tipoconvocatoria(rs.getInt("id_tipoconvocatoria"));
                        tipoconvocatoria.setNombre_tipoconvocatoria(rs.getString("nombre_tipoconvocatoria"));                      
                        convocatorias.setMonto_convocatoria(rs.getInt("monto_convocatoria"));
                        convocatorias.setMonto_matricula(rs.getInt("monto_matricula"));                        
                        convocatorias.setCodigo_convocatoria(rs.getString("codigo_convocatoria"));                      
                        convocatorias.setConvocatoria_estado(rs.getString("convocatoria_estado"));                      
                        
                        convocatorias.setTipoconvocatoria(tipoconvocatoria);
                       
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return convocatorias;
        
    }
    
    public static Convocatorias buscarCodigoConvocatoria(String codigo) {
        Convocatorias convocatorias = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias cv "
                        + "left join tipoconvocatorias tp "
                        + "on cv.id_tipoconvocatoria=tp.id_tipoconvocatoria"
                        + " where codigo_convocatoria like ? ";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setString(1, codigo);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        convocatorias = new Convocatorias();
                        convocatorias.setId_convocatoria(rs.getInt("id_convocatoria"));  
                        convocatorias.setNombre_convocatoria(rs.getString("nombre_convocatoria"));
                        convocatorias.setFecha_convocatoria(rs.getDate("fecha_convocatoria"));
                        
                        TipoConvocatorias tipoconvocatoria = new TipoConvocatorias();                        
                        tipoconvocatoria.setId_tipoconvocatoria(rs.getInt("id_tipoconvocatoria"));
                        tipoconvocatoria.setNombre_tipoconvocatoria(rs.getString("nombre_tipoconvocatoria"));
                        
                        convocatorias.setMonto_convocatoria(rs.getInt("monto_convocatoria"));
                        convocatorias.setMonto_matricula(rs.getInt("monto_matricula"));                        
                        convocatorias.setCodigo_convocatoria(rs.getString("codigo_convocatoria"));                                                                
                        convocatorias.setTipoconvocatoria(tipoconvocatoria);                   
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return convocatorias;
        
    }

    
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias cv "
                        + "left join tipoconvocatorias tp "
                        + "on cv.id_tipoconvocatoria=tp.id_tipoconvocatoria"
                        + " where "
                        + "cv.convocatoria_estado like 'HABILITADO' and upper(nombre_tipoconvocatoria) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_convocatoria "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                //System.out.println("-->  CONVOCATORIA" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                                      
                    
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_convocatoria") + "</td>"                                
                               // + "<td>" + rs.getString("codigo_convocatoria") + "</td>"                                
                                + "<td>" + rs.getString("nombre_tipoconvocatoria") + "</td>" 
                                + "<td>" + rs.getString("convocatoria_estado") + "</td>"   
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }
 

    public static boolean agregar(Convocatorias convocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {            
            int v1 = convocatoria.getTipoconvocatoria().getId_tipoconvocatoria();
            String v2 = convocatoria.getNombre_convocatoria();
            int v3 = convocatoria.getMonto_convocatoria();                
            int v4 = convocatoria.getMonto_matricula();                
            String v5= convocatoria.getCodigo_convocatoria();                 
            Date v6 = convocatoria.getFecha_convocatoria();
            String v7 = convocatoria.getConvocatoria_estado();
            
            String sql = "insert into convocatorias("
                    + "id_tipoconvocatoria,"
                    + "nombre_convocatoria,"
                    + "monto_convocatoria,"                                      
                    + "monto_matricula,"
                    + "codigo_convocatoria,"                  
                    + "fecha_convocatoria,"  
                    + "convocatoria_estado)"  
                    + " values('" + v1 
                    + "','" + v2
                    + "','" + v3 
                    + "','" + v4
                    + "','" + v5                     
                    + "','" + v6 
                    + "','" + v7 + "')";
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_convocatoria = keyset.getInt(1);
                    convocatoria.setId_convocatoria(id_convocatoria);
                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }
        return valor;
    }

    public static boolean modificar(Convocatorias convocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update convocatorias set "
                    + "id_tipoconvocatoria=?,"
                    + "nombre_convocatoria=?,"
                    + "monto_convocatoria=?,"
                    + "monto_matricula=?,"
                    + "codigo_convocatoria=?,"                    
                    + "fecha_convocatoria=?," 
                    + "convocatoria_estado=? "
                    + "where id_convocatoria=?";
            System.out.println(sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
               ps.setInt(1, convocatoria.getTipoconvocatoria().getId_tipoconvocatoria());
               ps.setString(2, convocatoria.getNombre_convocatoria());
                ps.setInt(3, convocatoria.getMonto_convocatoria());
                ps.setInt(4, convocatoria.getMonto_matricula());
                ps.setString(5, convocatoria.getCodigo_convocatoria() );           
                ps.setDate(6, convocatoria.getFecha_convocatoria());
                ps.setString(7, convocatoria.getConvocatoria_estado());
                ps.setInt(8,convocatoria.getId_convocatoria());                
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
               // System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(Convocatorias convocatoria  ) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from convocatorias where id_convocatoria=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, convocatoria.getId_convocatoria());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    
    
     
      public ArrayList listarConvocatoria() {
        listaConvocatoria = new ArrayList();
        Convocatorias convocatoria = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias cv, tipoconvocatorias tp, turnos t, tipoconvocatorias tp"
                        + " where "
                        + "cv.id_tipoconvocatoria = tp.id_tipoconvocatoria"
                        + " and "
                        + " convocatoria_estado like 'HABILITADO'";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    //ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        convocatoria = new Convocatorias();
                        
                        TipoConvocatorias tipoconvocatoria = new TipoConvocatorias();
                        tipoconvocatoria.setNombre_tipoconvocatoria(rs.getString("nombre_tipoconvocatoria"));
                        convocatoria.setTipoconvocatoria(tipoconvocatoria);
                      
                        convocatoria.setMonto_convocatoria(rs.getInt("monto_convocatoria"));
                        convocatoria.setMonto_matricula(rs.getInt("monto_matricula"));
                                                
                        //convocatoria.setNombre_usuario(rs.getString("nombre_usuario"));
                        //System.out.println("NOM: " + usuario.getNombre_usuario());
                        listaConvocatoria.add(convocatoria);
                    }

                    //cliente.toString();
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return listaConvocatoria;

    }
    
    
}

