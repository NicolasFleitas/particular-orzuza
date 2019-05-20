package modelos;

import java.sql.Date;

public class Pagos {
    private int id_pago;
    private Date fecha_pago;
    private int monto_inicial;
    private String estado_pago;
    private Usuarios usuario;

    public Pagos() {
    }

    public Pagos(int id_pago, Date fecha_pago, int monto_inicial, String estado_pago, Usuarios usuario) {
        this.id_pago = id_pago;
        this.fecha_pago = fecha_pago;
        this.monto_inicial = monto_inicial;
        this.estado_pago = estado_pago;
        this.usuario = usuario;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public int getMonto_inicial() {
        return monto_inicial;
    }

    public void setMonto_inicial(int monto_inicial) {
        this.monto_inicial = monto_inicial;
    }

    public String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(String estado_pago) {
        this.estado_pago = estado_pago;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    
    
    
}
