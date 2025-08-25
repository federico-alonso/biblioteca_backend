package logica;

import datatypes.EstadoPmo;
import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(persistencia.PrestamoID.class)
public class Prestamo {

    @Id
    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Material material;

    @Id
    @ManyToOne
    @JoinColumn(name = "lector_nombre", referencedColumnName = "nombre")
    private Lector lector;

    @Id
    @ManyToOne
    @JoinColumn(name = "bibliotecario_nombre", referencedColumnName = "nombre")
    private Bibliotecario bibliotecario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucion;

    // Optional: private EstadoPmo estado;

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

    public void setFechaDevolucion(Date fechaDevolucion) {
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
