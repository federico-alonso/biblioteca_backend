package logica;

import datatypes.EstadoPmo;
import javax.persistence.*;
import java.util.Date;

@Entity
//@IdClass(PrestamoID.class)
public class Prestamo {

    @Id
    @ManyToOne
    @JoinColumn
    private Material material;

    @Id
    @ManyToOne
    @JoinColumn
    private Lector lector;

    @Id
    @ManyToOne
    @JoinColumn
    private Bibliotecario bibliotecario;

    private Date fechaSolicitud;
    private Date fechaDevolucion; // private EstadoPmo estado;

    // Constructor vacío
    public Prestamo() {
        this.fechaSolicitud = new Date();
    }

    // Constructor con todos los atributos (excepto id)
    public Prestamo(Date fechaSolicitud, Date fechaDevolucion,
                    Material material, Bibliotecario bibliotecario, Lector lector) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
        this.material = material;
        this.bibliotecario = bibliotecario;
        this.lector = lector;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setfechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Material getMaterial() {
        return material;
    }

    public Lector getLector() {
        return lector;
    }

    public Bibliotecario getBibliotecario() {
        return bibliotecario;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
}
