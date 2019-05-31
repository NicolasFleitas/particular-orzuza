package modelos;
public class TipoConvocatorias {
    private int id_tipoconvocatoria;
    private String nombre_tipoconvocatoria;   
    
    public TipoConvocatorias(){
        
    }

    public TipoConvocatorias(int id_tipoconvocatoria, String nombre_tipoconvocatoria) {
        this.id_tipoconvocatoria = id_tipoconvocatoria;
        this.nombre_tipoconvocatoria = nombre_tipoconvocatoria;
    }

    
  
    

    public int getId_tipoconvocatoria() {
        return id_tipoconvocatoria;
    }

    public void setId_tipoconvocatoria(int id_tipoconvocatoria) {
        this.id_tipoconvocatoria = id_tipoconvocatoria;
    }

    public String getNombre_tipoconvocatoria() {
        return nombre_tipoconvocatoria;
    }

    public void setNombre_tipoconvocatoria(String nombre_tipoconvocatoria) {
        this.nombre_tipoconvocatoria = nombre_tipoconvocatoria;
    }
    
}
