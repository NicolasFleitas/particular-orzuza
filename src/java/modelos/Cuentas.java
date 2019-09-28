package modelos;

import java.sql.Date;

public class Cuentas {
   private int id_cuenta;
   private Inscripciones inscripcion;
   private int cuota_cuota;
   private Date vencimiento_cuota;
   private int monto_cuota;
   private int total_cuota;
   private String estado;
   private Conceptos concepto;
   private Date fecha_pago;
   private Date fecha_ultimo_pago;

    public Cuentas() {
    }

    public Cuentas(int id_cuenta, Inscripciones inscripcion, int cuota_cuota, Date vencimiento_cuota, int monto_cuota, int total_cuota, String estado, Conceptos concepto, Date fecha_pago, Date fecha_ultimo_pago) {
        this.id_cuenta = id_cuenta;
        this.inscripcion = inscripcion;
        this.cuota_cuota = cuota_cuota;
        this.vencimiento_cuota = vencimiento_cuota;
        this.monto_cuota = monto_cuota;
        this.total_cuota = total_cuota;
        this.estado = estado;
        this.concepto = concepto;
        this.fecha_pago = fecha_pago;
        this.fecha_ultimo_pago = fecha_ultimo_pago;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public Date getFecha_ultimo_pago() {
        return fecha_ultimo_pago;
    }

    public void setFecha_ultimo_pago(Date fecha_ultimo_pago) {
        this.fecha_ultimo_pago = fecha_ultimo_pago;
    }

   

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public Inscripciones getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripciones inscripcion) {
        this.inscripcion = inscripcion;
    }

    public int getCuota_cuota() {
        return cuota_cuota;
    }

    public void setCuota_cuota(int cuota_cuota) {
        this.cuota_cuota = cuota_cuota;
    }

    public Date getVencimiento_cuota() {
        return vencimiento_cuota;
    }

    public void setVencimiento_cuota(Date vencimiento_cuota) {
        this.vencimiento_cuota = vencimiento_cuota;
    }

    public int getMonto_cuota() {
        return monto_cuota;
    }

    public void setMonto_cuota(int monto_cuota) {
        this.monto_cuota = monto_cuota;
    }

    public int getTotal_cuota() {
        return total_cuota;
    }

    public void setTotal_cuota(int total_cuota) {
        this.total_cuota = total_cuota;
    }

    public Conceptos getConcepto() {
        return concepto;
    }

    public void setConcepto(Conceptos concepto) {
        this.concepto = concepto;
    }
}
