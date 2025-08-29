package datatypes;

import java.util.Date;

public class DtPrestamo {
    private long id;
    private DtMaterial material;
    private DtLector lector;
    private DtBibliotecario bibliotecario;
    private Date fechaSolicitud;
    private Date fechaDevolucion;
    private EstadoPmo estado;

    DtPrestamo(){
        this.fechaSolicitud = new Date();
    }
   public DtPrestamo(DtMaterial material, DtLector lector, DtBibliotecario bibliotecario,
                     Date fechaSolicitud, Date fechaDevolucion, EstadoPmo estado){
        this.material = material;
        this.lector = lector;
        this.bibliotecario = bibliotecario;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public DtPrestamo(long id, DtMaterial material, DtLector lector, DtBibliotecario bibliotecario,
                      Date fechaSolicitud, Date fechaDevolucion, EstadoPmo estado){
        this.id = id;
        this.material = material;
        this.lector = lector;
        this.bibliotecario = bibliotecario;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public long getId(){ return id;}
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
    public EstadoPmo getEstado(){return estado;}
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
    public void setEstado(EstadoPmo estado){ this.estado = estado;}
}
