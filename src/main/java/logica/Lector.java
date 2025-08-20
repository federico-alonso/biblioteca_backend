package logica;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import datatypes.EstadoLector;

@Entity
public class Lector extends Usuario {

    private String direccion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Enumerated(EnumType.STRING)
    private EstadoLector estado;

    public Lector() {
        super();
    }

    public Lector(String nombre, String email, Date fechaRegistro, String direccion) {
        super(nombre, email);
        this.fechaRegistro = fechaRegistro;
        this.direccion = direccion;
        this.estado = EstadoLector.ACTIVO; // default state on creation
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
}
