package logica;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity(name = "articuloespecial")
public class Articulo extends Material {
    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "peso")
    private float pesoKg;

    @Column(name="dimensiones")
    private String dimensiones;

    public Articulo() {
        super();
        this.descripcion = "";
        this.pesoKg = 0.0f;
        this.dimensiones = "";
    }

    public Articulo(String descripcion, float peso, String dimensiones) {
        super();
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

}