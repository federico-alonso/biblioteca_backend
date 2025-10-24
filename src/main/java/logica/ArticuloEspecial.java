package logica;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import datatypes.DtArticuloEspecial;
import java.util.Date;


@Entity(name = "articuloespecial")
public class ArticuloEspecial extends Material {
    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "peso")
    private float pesoKg;

    @Column(name = "dimensiones")
    private String dimensiones;

    public ArticuloEspecial() {
        super();
        this.descripcion = "";
        this.pesoKg = 0.0f;
        this.dimensiones = "";
    }

    public ArticuloEspecial(String descripcion, float peso, String dimensiones) {
        super();
        this.descripcion = descripcion;
        this.pesoKg = peso;
        this.dimensiones = dimensiones;
    }

    public ArticuloEspecial(String descripcion, float peso, String dimensiones, Date fechaIngreso) {
        super(fechaIngreso);
        this.descripcion = descripcion;
        this.pesoKg = peso;
        this.dimensiones = dimensiones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPeso() {
        return pesoKg;
    }

    public void setPeso(float peso) {
        this.pesoKg = peso;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    @Override
    public DtArticuloEspecial obtenerDt(){
        return new DtArticuloEspecial(this.getId(), this.getfechaIngreso(), this.descripcion,this.pesoKg,this.dimensiones);
    }
}
