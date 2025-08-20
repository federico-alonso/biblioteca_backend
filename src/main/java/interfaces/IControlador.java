package interfaces;

import datatypes.DtLibro;
import datatypes.DtPrestamo;

public interface IControlador{

    public void altaDonacionLibro(DtLibro libro);

    public void altaPrestamo(DtPrestamo dtPrestamo);

}