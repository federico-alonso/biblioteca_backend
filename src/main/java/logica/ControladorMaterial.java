package logica;

import datatypes.DtLibro;

import interfaces.IControladorMaterial;

public class ControladorMaterial implements IControladorMaterial {

    public ControladorMaterial() {
        super();
    }

    @Override
    public void altaDonacionLibro(DtLibro dtLibro){
        ManejadorMaterial mm = ManejadorMaterial.getInstancia();
        Libro libro = new Libro(dtLibro.getTitulo(), dtLibro.getCantidadPag(), dtLibro.getFechaIngreso());
        mm.agregarMaterial(libro);
    }


}
