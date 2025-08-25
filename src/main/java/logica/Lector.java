package logica;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import datatypes.EstadoLector;
import datatypes.Zona;

@Entity
@PrimaryKeyJoinColumn(name = "nombre")
public class Lector extends Usuario {

    private String direccion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Enumerated(EnumType.STRING)
    private EstadoLector estado;

    @Enumerated(EnumType.STRING)
    private Zona zona;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lector")
    private List<Prestamo> prestamos = new ArrayList<>();

    public Lector() {
        super();
    }

    public Lector(String nombre, String email, Date fechaRegistro, String direccion, Zona zona) {
        super(nombre, email);
        this.fechaRegistro = fechaRegistro;
        this.direccion = direccion;
        this.estado = EstadoLector.ACTIVO;
        this.zona = zona;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public EstadoLector getEstado() {
        return estado;
    }

    public void setEstado(EstadoLector estado) {
        this.estado = estado;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
