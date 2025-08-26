package interfaces;

import datatypes.DtLibro;
import datatypes.DtArticuloEspecial;

public interface IControladorMaterial {

    void altaDonacionLibro(DtLibro libro);
    void altaDonacionEspecial(DtArticuloEspecial dtArticulo);
}
