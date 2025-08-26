package datatypes;

import java.util.Date;

public class DtArticuloEspecial extends DtMaterial {
    private String descripcion;
    private float pesoKg;
    private String dimensiones;

    public DtArticuloEspecial() {
        super();
        this.descripcion = "";
        this.pesoKg = 0.0f;
        this.dimensiones = "";
    }
    public DtArticuloEspecial(String descripcion, float pesoKg) {
        super();
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
    }
    public DtArticuloEspecial(long id,Date fechaIngreso,String descripcion, float pesoKg, String dimensiones) {
        super(id, fechaIngreso);
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
        this.dimensiones = dimensiones;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPesoKg(float pesoKg) {
        this.pesoKg = pesoKg;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPesoKg() {
        return pesoKg;
    }

    public String getDimensiones() {
        return dimensiones;
    }
}

