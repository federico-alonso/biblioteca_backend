package logica;

import datatypes.EstadoPmo;
import jakarta.persistence.*;
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
    private Date fechaDevolucion;//private EstadoPmo estado;

    // Constructor vacío
    public Prestamo() {}

    // Constructor con todos los atributos (excepto id)
    public Prestamo(Date fechaSolicitud, Date fechaDevolucion,
                    Material material, Bibliotecario bibliotecario, Lector lector) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
        //  this.estado = estado;
        this.material = material;
        this.bibliotecario = bibliotecario;
        this.lector = lector;
    }

}
