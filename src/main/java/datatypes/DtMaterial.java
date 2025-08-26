package datatypes;

import java.util.Date;

public class DtMaterial {
    private long id;
    private Date fechaIngreso;

    protected DtMaterial(){
        this.fechaIngreso = new Date();
    }
    
    public DtMaterial(long id, Date fechaIngreso){
        this.id = id; this.fechaIngreso = fechaIngreso;
    }

    public void setId(long id){
        this.id = id;
    }
    public void setFechaIngreso(Date fechaIngreso){
        this.fechaIngreso = fechaIngreso;
    }
    public long getId(){
        return this.id;
    }
    public Date getFechaIngreso(){
        return this.fechaIngreso;
    }

}
