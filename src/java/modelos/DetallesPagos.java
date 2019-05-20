/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

public class DetallesPagos {

    private int id_detallepago;
    private Cuentas cuenta;
    private Inscripciones inscripcion;
    private Pagos pago;
    private int importe;
    private int vuelto;

    public DetallesPagos() {
    }

    public DetallesPagos(int id_detallepago, Cuentas cuenta, Inscripciones inscripcion, Pagos pago, int importe, int vuelto) {
        this.id_detallepago = id_detallepago;
        this.cuenta = cuenta;
        this.inscripcion = inscripcion;
        this.pago = pago;
        this.importe = importe;
        this.vuelto = vuelto;
    }

    public int getId_detallepago() {
        return id_detallepago;
    }

    public void setId_detallepago(int id_detallepago) {
        this.id_detallepago = id_detallepago;
    }

    public Cuentas getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuentas cuenta) {
        this.cuenta = cuenta;
    }

    public Inscripciones getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripciones inscripcion) {
        this.inscripcion = inscripcion;
    }

    public Pagos getPago() {
        return pago;
    }

    public void setPago(Pagos pago) {
        this.pago = pago;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public int getVuelto() {
        return vuelto;
    }

    public void setVuelto(int vuelto) {
        this.vuelto = vuelto;
    }
    
    

 

   
   
}
