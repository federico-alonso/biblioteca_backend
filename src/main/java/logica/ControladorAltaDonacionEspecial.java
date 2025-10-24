package logica;

import datatypes.DtArticuloEspecial;
import interfaces.IControladorAltaDonacionEspecial;

public class ControladorAltaDonacionEspecial implements IControladorAltaDonacionEspecial {

    @Override
    public void altaDonacionEspecial(DtArticuloEspecial dtArticulo) throws Exception {
        ManejadorArticulo ma = ManejadorArticulo.getInstancia();
        ArticuloEspecial articulo = new ArticuloEspecial(
                dtArticulo.getDescripcion(),
                dtArticulo.getPesoKg(),
                dtArticulo.getDimensiones(),
                dtArticulo.getFechaIngreso()
        );
        ma.agregarArticulo(articulo);
    }
}
