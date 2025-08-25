package datatypes;

import logica.ManejadorMaterial;

import java.util.Date;

public class DtArticulo extends DtMaterial {
    private String descripcion;
    private float pesoKg;
    private String dimensiones;

    public DtArticulo() {
        super();
        this.descripcion = "";
        this.pesoKg = 0.0f;
        this.dimensiones = "";
    }

    public DtArticulo(String descripcion, float pesoKg, String dimensiones) {
        super();
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
        this.dimensiones = dimensiones;
    }

    public DtArticulo(String descripcion, float pesoKg) {
        super();
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
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

