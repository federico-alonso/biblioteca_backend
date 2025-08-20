package logica;

import interfaces.IControlador;

import datatypes.DtLibro;
import datatypes.DtPrestamo;

import java.util.Date;

public class Controlador implements IControlador{

    public Controlador() {
        super();
    }

    @Override
    public void altaDonacionLibro(DtLibro dtLibro){
        ManejadorMaterial mm = ManejadorMaterial.getInstancia();
        Libro libro = new Libro(dtLibro.getTitulo(), dtLibro.getCantidadPag(), dtLibro.getFechaIngreso());
        mm.agregarMaterial(libro);
    }

    @Override
    public void altaPrestamo(DtPrestamo dtPrestamo){
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        ManejadorMaterial mm = ManejadorMaterial.getInstancia();
        ManejadorPrestamo mp = ManejadorPrestamo.getInstancia();

        int idMaterial = dtPrestamo.getMaterial().getId();
        String emailL = dtPrestamo.getLector().getEmail();
        String emailB = dtPrestamo.getBibliotecario().getEmail();

        if(!mp.existePrestamo(idMaterial, emailL, emailB)) {
            Lector lector = mu.buscarUsuario(dtPrestamo.getLector().getEmail());
            Material material = mm.buscarMaterial(dtPrestamo.getMaterial().getId());
            Bibliotecario bibliotecario = mu.buscarUsuario(dtPrestamo.getLector().getEmail());

            Prestamo prestamo = new Prestamo();
            prestamo.setMaterial(material);
            prestamo.setBibliotecario(bibliotecario);
            prestamo.setLector(lector);
        }
    }

}