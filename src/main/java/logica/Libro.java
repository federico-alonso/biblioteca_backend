package logica;

import datatypes.DtLibro;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Libro extends Material {
    private String titulo;
    private int cantidadPag;

    public Libro() {
        super();
    }

    public Libro(String titulo, int cantidadPag) {
        super();
        this.titulo = titulo;
        this.cantidadPag = cantidadPag;
    }

    public Libro(String titulo, int cantidadPag, Date fechaIngreso) {
        super(fechaIngreso);
        this.titulo = titulo;
        this.cantidadPag = cantidadPag;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCantidadPag() {
        return cantidadPag;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCantidadPag(int cantidadPag) {
        this.cantidadPag = cantidadPag;
    }

    public DtLibro getDtLibro(){
        return new DtLibro(this.getId(), titulo, cantidadPag, this.getfechaIngreso());
    }

    @Override
    public DtLibro obtenerDt() {
        return new DtLibro(this.getId(), titulo, cantidadPag, this.getfechaIngreso());
    }
}
