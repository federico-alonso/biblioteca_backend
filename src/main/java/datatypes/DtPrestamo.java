package datatypes;

import java.util.Date;

public class DtPrestamo {
    private DtMaterial material;
    private DtLector lector;
    private DtBibliotecario bibliotecario;
    private Date fechaSolicitud;
    private Date fechaDevolucion;

    DtPrestamo(){
        this.fechaSolicitud = new Date();
    }
    DtPrestamo(DtMaterial material, DtLector lector, DtBibliotecario bibliotecario,  Date fechaSolicitud, Date fechaDevolucion){
        this.material = material;
        this.lector = lector;
        this.bibliotecario = bibliotecario;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
    }

    public DtMaterial getMaterial() {
        return material;
    }
    public DtLector getLector() {
        return lector;
    }
    public DtBibliotecario getBibliotecario() {
        return bibliotecario;
    }
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }
    public Date getFechaDevolucion(){
        return fechaDevolucion;
    }
    public void setMaterial(DtMaterial material) {
        this.material = material;
    }
    public void setLector(DtLector lector) {
        this.lector = lector;
    }
    public void setBibliotecario(DtBibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    public void setFechaDevolucion(Date fechaDevolucion){
        this.fechaDevolucion = fechaDevolucion;
    }
}
