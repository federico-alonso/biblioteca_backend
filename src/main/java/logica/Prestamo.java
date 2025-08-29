package logica;

import datatypes.DtPrestamo;
import jakarta.persistence.*;
import java.util.Date;
import datatypes.EstadoPmo;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prestamo_seq")
    @SequenceGenerator(name = "prestamo_seq", sequenceName = "prestamo_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "lector_nombre", referencedColumnName = "nombre")
    private Lector lector;

    @ManyToOne
    @JoinColumn(name = "bibliotecario_nombre", referencedColumnName = "nombre")
    private Bibliotecario bibliotecario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucion;

    @Enumerated(EnumType.STRING)
    private EstadoPmo estado;

    public Prestamo() {}

    public Prestamo(Date fechaSolicitud, Date fechaDevolucion,
                    Material material, Bibliotecario bibliotecario, Lector lector, EstadoPmo estado) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
        this.material = material;
        this.bibliotecario = bibliotecario;
        this.lector = lector;
        this.estado = estado;
    }

    public void setId(long id) {this.id = id;}

    public void setEstado(EstadoPmo estado){ this.estado = estado; }

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

    public EstadoPmo getEstado(){ return estado;}

    public long getId() {
        return id;
    }

    public DtPrestamo obtenerDt(){
        return new DtPrestamo(id, material.obtenerDt(), lector.obtenerDt(), bibliotecario.obtenerDt(),
                fechaSolicitud, fechaDevolucion, estado);
    }

}
