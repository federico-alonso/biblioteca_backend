package interfaces;

import datatypes.DtLibro;
import datatypes.DtPrestamo;

public interface IControladorPrestamo {

    public void altaDonacionLibro(DtLibro libro);

    public void altaPrestamo(DtPrestamo dtPrestamo);

}