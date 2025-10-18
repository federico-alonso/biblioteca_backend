package datatypes;

import java.util.Date;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({DtLibro.class, DtArticuloEspecial.class})
public class DtMaterial {
    private long id;
    private Date fechaIngreso;

    protected DtMaterial(){
        this.fechaIngreso = new Date();
        this.id = 0;
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
