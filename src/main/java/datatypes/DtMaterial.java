package datatypes;

import java.util.Date;

public abstract class DtMaterial {
    private int id;
    private Date fechaIngreso;

    protected DtMaterial(){
        this.fechaIngreso = new Date();
        this.id = 0;
    }
    
    protected DtMaterial(Date fechaIngreso){
        this.fechaIngreso = fechaIngreso;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setFechaIngreso(Date fechaIngreso){
        this.fechaIngreso = fechaIngreso;
    }
    public int getId(){
        return this.id;
    }
    public Date getFechaIngreso(){
        return this.fechaIngreso;
    }
}
