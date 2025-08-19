package logica;

import interfaces.IControlador;

import datatypes.DtLibro;

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



}