package controladores;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import modelos.Cajas;
import modelos.DetallesCajas;
//import modelos.Formas_pagos;
import modelos.Cuentas;
import utiles.Conexion;
import utiles.Utiles;

public class DetallesCajasControlador {

    public static DetallesCajas buscarId(int id) throws SQLException {
        DetallesCajas detallecaja = null;
        if (Conexion.conectar()) {
            try {
                /*
                String sql = "select * from detallescajas dc "
                        + "LEFT JOIN cajas c ON c.id_caja=dc.id_caja "
                        + "LEFT JOIN cuentas_cte cta ON cta.id_cuenta=dc.id_cuenta "                        
                        + "WHERE dc.id_detallecaja=?";
                */
                
                 String sql = "SELECT * FROM detallescajas dc, cajas c, cuentas_cte cta"
                        + " WHERE "
                        + "c.id_caja=dc.id_caja"
                        + " AND "
                        + "cta.id_cuenta=dc.id_cuenta"
                         + " AND "                        
                        + "dc.id_detallecaja=?";
                
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    System.out.println("detallecaja--> " + sql+id);
                    if (rs.next()) {                        
                        detallecaja = new DetallesCajas();
                        detallecaja.setId_detallecaja(rs.getInt("id_detallecaja"));
                        detallecaja.setImporte(rs.getInt("importe"));
                      //detallecaja.setVuelto(rs.getInt("vuelto"));

                        Cuentas cuenta = new Cuentas();
                        cuenta.setCuota_cuota(rs.getInt("id_cuenta"));
                        //venta.setNumero_factura_venta(rs.getInt("numero_factura_venta"));
                        //detallecaja.setFacturaventa(venta);

                        Cajas caja = new Cajas();
                        caja.setId_caja(rs.getInt("id_caja"));
                        detallecaja.setCaja(caja);
                        //pago.setNombre_forma_pago(rs.getString("nombre_forma_pago"));
                      

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallecaja;
    }

    public static String buscarIdCaja(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                /*
                String sql = "SELECT * FROM detallescajas dc "
                        + "LEFT JOIN cajas c on c.id_caja=dc.id_caja "
                        + "LEFT JOIN cuentas_cte cta on cta.id_cuenta=dc.id_cuenta "
                       // + "left join formas_pagos a on a.id_forma_pago=dp.id_forma_pago "
                        + "WHERE dc.id_caja=" + id + " "
                        + "ORDER BY id_detallecaja";
                */
                
                  String sql = "SELECT * FROM detallescajas dc, cajas c, cuentas_cte cta"
                        + " WHERE "
                        + "c.id_caja = dc.id_caja"
                        + " AND "
                        + "cta.id_cuenta = dc.id_cuenta"
                        + " AND "
                        + "dc.id_caja = " + id
                        + " ORDER BY id_detallecaja";
                
                System.out.println("DETALLES_CAJA.buscarIdCaja--> " + sql);
                
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("importe");
                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                              //  + "<td>" + rs.getString("id_detallecaja") + "</td>"
                                + "<td>" + rs.getString("id_cuenta") + "</td>"
                              //  + "<td>" + rs.getString("numero_factura_venta") + "</td>"
                              //  + "<td>" + rs.getString("id_forma_pago") + "</td>"
                              //  + "<td>" + rs.getString("nombre_forma_pago") + "</td>"
                              //  + "<td>" + rs.getString("total") + "</td>"
                                  + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                              /*  + "<td class='centrado'>"
                                  + "<button onclick='editarLinea(" + rs.getString("id_detallecaja") + ")'"
                                  + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                  + "</span></button></td>"*/
                                  + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
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


    public static boolean agregar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            /*
            String sql = "insert into detallescajas "
                    + "(id_caja,id_cuenta,importe) "
                    + "values (?,?,?)";
                    */
            String sql = "insert into detallescajas "
                    + "(id_caja,importe) "
                    + "values (?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                
                ps.setInt(1, detallecaja.getCaja().getId_caja());
              //  ps.setInt(2, detallecaja.getCuenta().getId_cuenta());                            
                ps.setInt(2, detallecaja.getImporte());
                
                //ps.setInt(2, detallecaja.getPago().getId_forma_pago()); 
                //ps.setInt(5, detallecaja.getVuelto());

                ps.executeUpdate();
                System.out.println("agregarCajadetalle-> "+sql);
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

    public static boolean modificar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallescajas set "
                    + "id_caja=?,"
                    + "id_cuenta=?"
                   // + "cantidad_ventacaja=? "
                    + " WHERE id_detallecaja=?";
            
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getCaja().getId_caja());
                ps.setInt(2, detallecaja.getCuenta().getId_cuenta());
                //ps.setInt(3, detallecaja.getCantidad_ventacaja());
                ps.setInt(3, detallecaja.getId_detallecaja());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("DetallesCaja MODIFICADO-> ");
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

    public static boolean eliminar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "DELETE FROM detallescajas WHERE id_detallecaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getId_detallecaja());
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

    public static boolean eliminarc(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            try {
                String sql = "SELECT * FROM detallescajas dp "
                        + "LEFT JOIN cajas p ON p.id_caja=dp.id_caja "
                        + "LEFT JOIN ventas a ON a.id_factura_venta=dp.id_factura_venta"
                        + " WHERE p.id_caja= " + detallecaja.getCaja().getId_caja();
                System.out.println("detalle " + sql);
                
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        String sqli = "update stocks set cantidad_exi= cantidad_exi - " + rs.getInt("cantidad_ventacaja") + " where id_factura_venta=" + rs.getInt("id_factura_venta") + "";

                        System.out.println(" descontar stcok" + sqli);
                        try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                            psi.executeUpdate();
                            psi.close();
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

                    ps.close();
                    valor = true;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminarVentaCaja(Cajas caja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "DELETE FROM detallescajas where id_caja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, caja.getId_caja());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + caja.getId_caja());
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
    
    /* BUSCAR CUENTA */
    /*
    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                /*
                String sql = "select * from detallescajas dp "
                        + "left join cajas p on p.id_caja=dp.id_caja "
                        + "left join factura_ventas a on a.id_factura_venta=dp.id_factura_venta "
                        + "where upper(a.nombre_venta) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detallecaja "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                */
    
    /*
                
                String sql = "SELECT * FROM detallescajas dc, cajas c, cuentas_cte cta "
                        + "c.id_caja = dc.id_caja"
                        + " AND "
                        + "a.id_factura_venta = dc.id_factura_venta"
                        + " AND "
                        + "upper(a.nombre_venta) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detallecaja "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallecaja") + "</td>"
                                + "<td>" + rs.getString("id_caja") + "</td>"
                                + "<td>" + rs.getString("id_factura_venta") + "</td>"
                                + "<td>" + rs.getString("numero_factura_venta") + "</td>"
                                + "<td>" + rs.getInt("total") + "</td>"
                                + "<td>" + rs.getInt("iva_venta") + "</td>"
                                + "<td>" + rs.getInt("importe") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
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
 */
}
