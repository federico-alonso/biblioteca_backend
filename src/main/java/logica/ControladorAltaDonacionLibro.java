package logica;

import datatypes.DtLibro;
import interfaces.IControladorAltaDonacionLibro;

public class ControladorAltaDonacionLibro implements IControladorAltaDonacionLibro {

    @Override
    public void altaDonacionLibro(DtLibro dtLibro) throws Exception {
        ManejadorMaterial mm = ManejadorMaterial.getInstancia();
        Libro libro = new Libro(dtLibro.getTitulo(), dtLibro.getCantidadPag(), dtLibro.getFechaIngreso());
        mm.agregarMaterial(libro);
    }
}
