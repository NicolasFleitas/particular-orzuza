/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Pagos;
import modelos.Usuarios;
//import modelos.TiposPagos;
import utiles.Conexion;
import utiles.Utiles;
public class PagosControlador {

    public static Pagos buscarId(int id) {
        Pagos pagos = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pagos where id_pago=?";
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        pagos = new Pagos();
                        pagos.setId_pago(rs.getInt("id_pago"));
                        pagos.setFecha_pago(rs.getDate("fecha_pago"));
                       // pagos.setMonto_inicial(rs.getInt("monto_inicial"));
                      // pagos.setEstado_pago(rs.getString("estado_pago"));
                    }

                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return pagos;
    }
    
    
        /*public static Pagos buscarIdpago(Pagos pagos) {
        if (Conexion.conectar()){
            String sql = "select * from pagos where id_pago='"+pagos.getId_pago()+"'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    pagos = new Pagos();
                        pagos.setId_pago(rs.getInt("id_pago"));
                        pagos.setFecha_pago(rs.getDate("fecha_pago"));
                        pagos.setMonto_inicial(rs.getInt("monto_inicial"));
                        pagos.setEstado_pago(rs.getString("estado_pago"));
                } 
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return pagos;
    }*/
    
    
    public static Pagos buscarIdestado(int usuario) throws SQLException {
        Pagos pagos = null;
        if (Conexion.conectar()) {
            try {
                /*
                String sql = "select * from pagos c "
                        + "left join usuarios u on c.id_usuario=u.id_usuario "
                        // + "left join tipospagos t on p.id_tipopago=t.id_tipopago "
                        + "where estado_pago='ABIERTO' and c.id_usuario=?";
                */
                String sql = "select * from pagos p "
                        + "left join usuarios u on p.id_usuario=u.id_usuario "
                        // + "left join tipospagos t on p.id_tipopago=t.id_tipopago "
                        + "where p.id_usuario=?";
                
                System.out.println(sql+usuario);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                  
                    ps.setInt(1, usuario);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        pagos = new Pagos();
                        pagos.setId_pago(rs.getInt("id_pago"));
                        pagos.setFecha_pago(rs.getDate("fecha_pago"));
                      //  pagos.setMonto_inicial(rs.getInt("monto_inicial"));
                       // pagos.setEstado_pago(rs.getString("estado_pago"));

                    }

                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return pagos;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pagos p "
                        + "left join usuarios u on p.id_usuario=u.id_usuario "
                        //   + "left join tipospagos t on t.id_tipopago=p.id_tipopago "
                        + "where upper(estado_pago) like '%"
                        //+ nombre.toUpperCase()
                        + "%' "
                        + "order by id_pago "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("-->pagonombre " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_pago") + "</td>"
                                + "<td>" + rs.getString("fecha_pago") + "</td>"
                              //  + "<td>" + rs.getString("monto_inicial") + "</td>"
                              //  + "<td>" + rs.getString("estado_pago") + "</td>"
                              //  + "<td>" + rs.getString("id_usuario") + "</td>"
                               // + "<td>" + rs.getString("nombre_usuario") + "</td>"
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

    public static boolean agregar(Pagos pago) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = pago.getUsuario().getId_usuario();

            String sql = "insert into pagos (fecha_pago) "
                    + "values('"+ pago.getFecha_pago()+"')";

            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_pago = keyset.getInt(1);
                    pago.setId_pago(id_pago);

                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }

            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(Pagos pago) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update pagos set fecha_pago='" + pago.getFecha_pago() + "', "
                    + "monto_inicial='" + pago.getMonto_inicial() + "' "
                    
                    //+ "estado_pago=" + pago.getEstado_pago() + ""
                    //+ "id_usuario=" + pago.getUsuario().getId_usuario() + ""
                    + "where id_pago=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                //ps.setInt(1, pago.getUsuario().getId_usuario());
                ps.setInt(1, pago.getId_pago());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> Grabado");
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

    public static boolean eliminar(Pagos pago) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from pagos where id_pago=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, pago.getId_pago());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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

    public static boolean modificarestado(Pagos pago) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update pagos set estado_pago='CERRADO' "
                    + " where id_pago=" + pago.getId_pago();
            try {

                Conexion.getSt().executeUpdate(sql);

                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> Grabado");
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
}
