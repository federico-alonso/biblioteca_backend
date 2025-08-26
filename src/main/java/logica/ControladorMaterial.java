package logica;

import datatypes.DtArticuloEspecial;
import interfaces.IControladorMaterial;
import datatypes.DtLibro;
import datatypes.DtArticuloEspecial;

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
    public void altaDonacionEspecial(DtArticuloEspecial dtArticulo) {
        ManejadorArticulo ma = ManejadorArticulo.getInstancia();
        ArticuloEspecial articuloEspecial = new ArticuloEspecial(dtArticulo.getDescripcion(), dtArticulo.getPesoKg(),
                dtArticulo.getDimensiones());
        ma.agregarArticulo(articuloEspecial);
    }
}