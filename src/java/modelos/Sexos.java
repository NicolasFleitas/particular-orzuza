package modelos;

public class Sexos {
 private int id_sexo;
 private String nombre_sexo;

    public Sexos() {
    }

    public Sexos(int id_sexo, String nombre_sexo) {
        this.id_sexo = id_sexo;
        this.nombre_sexo = nombre_sexo;
    }

    public int getId_sexo() {
        return id_sexo;
    }

    public void setId_sexo(int id_sexo) {
        this.id_sexo = id_sexo;
    }

    public String getNombre_sexo() {
        return nombre_sexo;
    }

    public void setNombre_sexo(String nombre_sexo) {
        this.nombre_sexo = nombre_sexo;
    }
 
 
}
