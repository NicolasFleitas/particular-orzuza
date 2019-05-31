package modelos;

public class DetallesConvocatorias {
    private int id_detalleconvocatoria;
    private Convocatorias convocatoria;
    private Profesores profesor;    
    private Materias materia;
    

    public DetallesConvocatorias() {
    }

    public DetallesConvocatorias(int id_detalleconvocatoria, Convocatorias convocatoria, Profesores profesor, Materias materia) {
        this.id_detalleconvocatoria = id_detalleconvocatoria;
        this.convocatoria = convocatoria;
        this.profesor = profesor;
        this.materia = materia;
    }

    public Materias getMateria() {
        return materia;
    }

    public void setMateria(Materias materia) {
        this.materia = materia;
    }

  

    public int getId_detalleconvocatoria() {
        return id_detalleconvocatoria;
    }

    public void setId_detalleconvocatoria(int id_detalleconvocatoria) {
        this.id_detalleconvocatoria = id_detalleconvocatoria;
    }

    public Convocatorias getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatorias convocatoria) {
        this.convocatoria = convocatoria;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        this.profesor = profesor;
    }
    
}
