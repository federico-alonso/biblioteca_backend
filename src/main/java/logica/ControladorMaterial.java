package logica;

import interfaces.IControladorMaterial;
import datatypes.DtLibro;
import datatypes.DtArticulo;

public class ControladorMaterial implements IControladorMaterial {

    public ControladorMaterial() {
        super();
    }

    @Override
    public void altaDonacionLibro(DtLibro dtLibro) {
        ManejadorMaterial mm = ManejadorMaterial.getInstancia();
        Libro libro = new Libro(dtLibro.getTitulo(), dtLibro.getCantidadPag());
        mm.agregarMaterial(libro);
    }

    @Override
    public void altaDonacionEspecial(DtArticulo dtArticulo) {
        ManejadorArticulo ma = ManejadorArticulo.getInstancia();
        Articulo articulo = new Articulo(dtArticulo.getDescripcion(), dtArticulo.getPesoKg(),
                dtArticulo.getDimensiones());
        ma.agregarArticulo(articulo);
    }
}