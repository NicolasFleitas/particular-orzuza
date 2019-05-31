package modelos;

public class Cobros {
 private int id_cobro  ;
 private String nombre_cobro;

    public Cobros() {
    }

    public Cobros(int id_cobro, String nombre_cobro) {
        this.id_cobro = id_cobro;
        this.nombre_cobro = nombre_cobro;
    }

    public int getId_cobro() {
        return id_cobro;
    }

    public void setId_cobro(int id_cobro) {
        this.id_cobro = id_cobro;
    }

    public String getNombre_cobro() {
        return nombre_cobro;
    }

    public void setNombre_cobro(String nombre_cobro) {
        this.nombre_cobro = nombre_cobro;
    }
    
 
}
