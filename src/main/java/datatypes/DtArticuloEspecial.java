package datatypes;

import java.util.Date;

public class DtArticuloEspecial extends DtMaterial{
    private String descripcion;
    private float peso;
    //private DtDimension dtDimension;

    DtArticuloEspecial(){}

    public DtArticuloEspecial(long id, Date fechaIngreso, String descripcion, float peso){
        super(id, fechaIngreso);
        this.descripcion = descripcion;
        this.peso = peso;
    }

    public String getDescripcion(){ return descripcion;}

}
