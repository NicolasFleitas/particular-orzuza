package modelos;
import java.sql.Date;
//import org.json.simple.JSONObject;
public class Convocatorias {
    private int id_convocatoria;
    private String nombre_convocatoria;
    private Date fecha_convocatoria;
    private int monto_convocatoria;
    private String codigo_convocatoria;
    private int monto_matricula;  
    private String convocatoria_estado;
    private TipoConvocatorias tipoconvocatoria;
    
    public Convocatorias() {
    }

    public Convocatorias(int id_convocatoria, String nombre_convocatoria, Date fecha_convocatoria, int monto_convocatoria, String codigo_convocatoria, int monto_matricula, String convocatoria_estado, TipoConvocatorias tipoconvocatoria) {
        this.id_convocatoria = id_convocatoria;
        this.nombre_convocatoria = nombre_convocatoria;
        this.fecha_convocatoria = fecha_convocatoria;
        this.monto_convocatoria = monto_convocatoria;
        this.codigo_convocatoria = codigo_convocatoria;
        this.monto_matricula = monto_matricula;
        this.convocatoria_estado = convocatoria_estado;
        this.tipoconvocatoria = tipoconvocatoria;
    }

    
    public String getNombre_convocatoria() {
        return nombre_convocatoria;
    }

    public void setNombre_convocatoria(String nombre_convocatoria) {
        this.nombre_convocatoria = nombre_convocatoria;
    }


    public TipoConvocatorias getTipoconvocatoria() {
        return tipoconvocatoria;
    }

    public void setTipoconvocatoria(TipoConvocatorias tipoconvocatoria) {
        this.tipoconvocatoria = tipoconvocatoria;
    }

   
    public String getConvocatoria_estado() {
        return convocatoria_estado;
    }

    public void setConvocatoria_estado(String convocatoria_estado) {
        this.convocatoria_estado = convocatoria_estado;
    }
    
   

    

    public Date getFecha_convocatoria() {
        return fecha_convocatoria;
    }

    public void setFecha_convocatoria(Date fecha_convocatoria) {
        this.fecha_convocatoria = fecha_convocatoria;
    }

    public int getId_convocatoria() {
        return id_convocatoria;
    }

    public void setId_convocatoria(int id_convocatoria) {
        this.id_convocatoria = id_convocatoria;
    }

    public int getMonto_convocatoria() {
        return monto_convocatoria;
    }

    public void setMonto_convocatoria(int monto_convocatoria) {
        this.monto_convocatoria = monto_convocatoria;
    }

    public String getCodigo_convocatoria() {
        return codigo_convocatoria;
    }

    public void setCodigo_convocatoria(String codigo_convocatoria) {
        this.codigo_convocatoria = codigo_convocatoria;
    }

    public int getMonto_matricula() {
        return monto_matricula;
    }

    public void setMonto_matricula(int monto_matricula) {
        this.monto_matricula = monto_matricula;
    }
    /*
     public JSONObject getJSONString() {
        JSONObject obj = new JSONObject();
       //obj.put("id_convocatoria", this.id_convocatoria);
        obj.put("nombre_materia", this.materia.getNombre_materia());   
        obj.put("nombre_turno", this.turno.getNombre_turno());               
        obj.put("monto_convocatoria", this.monto_convocatoria);       
        obj.put("monto_matricula", this.monto_matricula);
        return obj;
    }
*/
    
    
    
}
