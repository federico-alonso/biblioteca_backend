package datatypes;

import java.util.Date;
import datatypes.EstadoPmo;

public class DtPrestamoSimple {
    private final String nombreLector;
    private final Date fechaSolicitud;
    private final Date fechaDevolucion;
    private final EstadoPmo estado;

    public DtPrestamoSimple(String nombreLector, Date fechaSolicitud, Date fechaDevolucion, EstadoPmo estado) {
        this.nombreLector = nombreLector;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public String getNombreLector() { return nombreLector; }
    public Date getFechaSolicitud() { return fechaSolicitud; }
    public Date getFechaDevolucion() { return fechaDevolucion; }
    public EstadoPmo getEstado() { return estado; }
}
