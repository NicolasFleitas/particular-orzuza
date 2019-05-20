
package modelos;

public class DetallesCajas {

    private int id_detallecaja;
    private Cuentas cuenta;
    //private Formas_pagos pago;
    private Cajas caja;
    private int importe;
    private int vuelto;

    public DetallesCajas() {
    }

    public DetallesCajas(int id_detallecaja, Cuentas cuenta, Cajas caja, int importe, int vuelto) {
        this.id_detallecaja = id_detallecaja;
        this.cuenta = cuenta;
        this.caja = caja;
        this.importe = importe;
        this.vuelto = vuelto;
    }

    public Cuentas getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuentas cuenta) {
        this.cuenta = cuenta;
    }
    
      

    public int getId_detallecaja() {
        return id_detallecaja;
    }

    public void setId_detallecaja(int id_detallecaja) {
        this.id_detallecaja = id_detallecaja;
    }

  
    public Cajas getCaja() {
        return caja;
    }

    public void setCaja(Cajas caja) {
        this.caja = caja;
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
