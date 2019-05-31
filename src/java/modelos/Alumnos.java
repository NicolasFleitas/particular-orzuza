package modelos;

public class Alumnos {
    private int id_alumno;
    private String nombre_alumno;
    private String apellido_alumno;
    private int nroci_alumno;
    private String fecha_nac_alumno;
    private String telefono_alumno;
    private Sexos sexo;


    public Alumnos() {
    }

    public Alumnos(int id_alumno, String nombre_alumno, String apellido_alumno, int nroci_alumno, String fecha_nac_alumno, String telefono_alumno, Sexos sexo) {
        this.id_alumno = id_alumno;
        this.nombre_alumno = nombre_alumno;
        this.apellido_alumno = apellido_alumno;
        this.nroci_alumno = nroci_alumno;
        this.fecha_nac_alumno = fecha_nac_alumno;
        this.telefono_alumno = telefono_alumno;
        this.sexo = sexo;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getApellido_alumno() {
        return apellido_alumno;
    }

    public void setApellido_alumno(String apellido_alumno) {
        this.apellido_alumno = apellido_alumno;
    }

    public int getNroci_alumno() {
        return nroci_alumno;
    }

    public void setNroci_alumno(int nroci_alumno) {
        this.nroci_alumno = nroci_alumno;
    }

    public String getFecha_nac_alumno() {
        return fecha_nac_alumno;
    }

    public void setFecha_nac_alumno(String fecha_nac_alumno) {
        this.fecha_nac_alumno = fecha_nac_alumno;
    }

    public String getTelefono_alumno() {
        return telefono_alumno;
    }

    public void setTelefono_alumno(String telefono_alumno) {
        this.telefono_alumno = telefono_alumno;
    }

    public Sexos getSexo() {
        return sexo;
    }

    public void setSexo(Sexos sexo) {
        this.sexo = sexo;
    }
    
   }
