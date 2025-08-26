package datatypes;

import java.util.Date;

public class DtLector extends DtUsuario {
    private String direccion;
    private Date fechaRegistro;
    private EstadoLector estado;

    DtLector() {
        this.fechaRegistro = new Date();
    }

    public DtLector(String nombre, String email, String direccion, Date fechaRegistro, EstadoLector estado) {
        super(nombre, email);
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    // Getters y setters
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
