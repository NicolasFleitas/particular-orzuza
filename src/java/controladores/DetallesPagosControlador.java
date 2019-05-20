package controladores;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import modelos.Pagos;
import modelos.DetallesPagos;
//import modelos.FacturaVentas;
import utiles.Conexion;
import utiles.Utiles;

//import javawebts.modelos.Ventas;

public class DetallesPagosControlador {

    public static DetallesPagos buscarId(int id) throws SQLException {
        DetallesPagos detallepago = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallespagos dp "
                        + "left join pagos p on p.id_pago=dp.id_pago "
                        + "left join inscripciones i on i.id_inscripcion = dp.id_inscripciones "                        
                        + "where dp.id_detallepago=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    System.out.println("detallecj--> " + sql+id);
                    if (rs.next()) {
                        
                        detallepago = new DetallesPagos();
                        detallepago.setId_detallepago(rs.getInt("id_detallepago"));
                        detallepago.setImporte(rs.getInt("importe"));
                        //detallepago.setVuelto(rs.getInt("vuelto"));

                  //      FacturaVentas venta = new FacturaVentas();
                  //      venta.setId_factura_venta(rs.getInt("id_factura_venta"));
                        //venta.setNumero_factura_venta(rs.getInt("numero_factura_venta"));
                 //       detallepago.setFacturaventa(venta);

                        Pagos pago = new Pagos();
                        pago.setId_pago(rs.getInt("id_pago"));
                        detallepago.setPago(pago);

               //         Formas_pagos pago = new Formas_pagos();
               //         pago.setId_forma_pago(rs.getInt("id_forma_pago"));
                        //pago.setNombre_forma_pago(rs.getString("nombre_forma_pago"));
               //         detallepago.setPago(pago);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallepago;
    }

    public static String buscarIdPago(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
              /*  String sql = "select * from detallespagos dp "
                        + "left join pagos p on p.id_pago=dp.id_pago "
                        + "left join factura_ventas v on v.id_factura_venta=dp.id_factura_venta "
                        + "left join formas_pagos a on a.id_forma_pago=dp.id_forma_pago "
                        + "where dp.id_pago=" + id + " "
                        + "order by id_detallepago";
                
                */
              
              String sql = "select * from detallespagos dp "
                        + "left join pagos p on p.id_pago=dp.id_pago "
                        + "left join factura_ventas v on v.id_factura_venta=dp.id_factura_venta "
                        + "left join formas_pagos a on a.id_forma_pago=dp.id_forma_pago "
                        + "where dp.id_pago=" + id + " "
                        + "order by id_detallepago";
              
              
                System.out.println("dca--> " + sql);
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
                              //  + "<td>" + rs.getString("id_detallepago") + "</td>"
                                + "<td>" + rs.getString("id_factura_venta") + "</td>"
                                + "<td>" + rs.getString("numero_factura_venta") + "</td>"
                                //+ "<td>" + rs.getString("id_forma_pago") + "</td>"
                                + "<td>" + rs.getString("nombre_forma_pago") + "</td>"
                               // + "<td>" + rs.getString("total") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                /*+ "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallepago") + ")'"
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

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallespagos dp "
                        + "left join pagos p on p.id_pago=dp.id_pago "
                        + "left join factura_ventas a on a.id_factura_venta=dp.id_factura_venta "
                        + "where upper(a.nombre_venta) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detallepago "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallepago") + "</td>"
                                + "<td>" + rs.getString("id_pago") + "</td>"
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

    public static boolean agregar(DetallesPagos detallepago) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallespagos "
                    + "(id_cuenta,id_inscripcion,id_forma_pago,id_pago,importe) "
                    + "values (?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                
              //  ps.setInt(1, detallepago.getFacturaventa().getId_factura_venta());
                // ps.setInt(2, detallepago.getPago().getId_forma_pago());
                ps.setInt(1, detallepago.getPago().getId_pago());
                ps.setInt(4, detallepago.getImporte());
                //ps.setInt(5, detallepago.getVuelto());

                ps.executeUpdate();
                System.out.println("agragerpagodetalle "+sql);
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

    public static boolean modificar(DetallesPagos detallepago) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallespagos set "
                    + "id_pago=?,"
                    + "id_factura_venta=?,"
                    + "cantidad_ventapago=? "
                    + "where id_detallepago=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallepago.getPago().getId_pago());
               // ps.setInt(2, detallepago.getFacturaventa().getId_factura_venta());
                //  ps.setInt(3, detallepago.getCantidad_ventapago());
                ps.setInt(4, detallepago.getId_detallepago());
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

    public static boolean eliminar(DetallesPagos detallepago) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallespagos where id_detallepago=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallepago.getId_detallepago());
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

    public static boolean eliminarc(DetallesPagos detallepago) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallespagos dp "
                        + "left join pagos p on p.id_pago=dp.id_pago "
                        + "left join ventas a on a.id_factura_venta=dp.id_factura_venta "
                        + " where p.id_pago= " + detallepago.getPago().getId_pago();
                System.out.println("detalle " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        String sqli = "update stocks set cantidad_exi= cantidad_exi - " + rs.getInt("cantidad_ventapago") + " where id_factura_venta=" + rs.getInt("id_factura_venta") + "";

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

    public static boolean eliminarVentaPago(Pagos pago) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallespagos where id_pago=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, pago.getId_pago());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + pago.getId_pago());
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
