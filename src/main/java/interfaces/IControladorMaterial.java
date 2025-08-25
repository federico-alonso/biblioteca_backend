package interfaces;

import datatypes.DtLibro;
import datatypes.DtArticulo;

public interface IControladorMaterial {

    void altaDonacionLibro(DtLibro libro);
    void altaDonacionEspecial(DtArticulo dtArticulo);
}
