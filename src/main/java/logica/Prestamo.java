package logica;

import jakarta.persistence.*;
import java.util.Date;
import persistencia.PrestamoID;

@Entity
public class Prestamo {

    @EmbeddedId
    private PrestamoID id;

    @MapsId("materialId")
    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Material material;

    @MapsId("lectorNombre")
    @ManyToOne
    @JoinColumn(name = "lector_nombre", referencedColumnName = "nombre")
    private Lector lector;

    @MapsId("bibliotecarioNombre")
    @ManyToOne
    @JoinColumn(name = "bibliotecario_nombre", referencedColumnName = "nombre")
    private Bibliotecario bibliotecario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucion;

    // Optional: EstadoPmo estado;
    // If you want to include it, use:
    // @Enumerated(EnumType.STRING)
    // private EstadoPmo estado;

    public Prestamo() {
        this.fechaSolicitud = new Date();
    }

    public Prestamo(Date fechaSolicitud, Date fechaDevolucion,
                    Material material, Bibliotecario bibliotecario, Lector lector) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
        this.material = material;
        this.bibliotecario = bibliotecario;
        this.lector = lector;
        this.id = new PrestamoID(material.getId(), bibliotecario.getNombre(), lector.getNombre());
    }

    public PrestamoID getId() {
        return id;
    }

    public void setId(PrestamoID id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Bibliotecario getBibliotecario() {
        return bibliotecario;
    }

    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
