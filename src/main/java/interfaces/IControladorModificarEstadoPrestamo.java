package interfaces;

import datatypes.EstadoPmo;
import datatypes.DtPrestamo;
import java.util.List;

public interface IControladorModificarEstadoPrestamo {
    void modificarEstadoPrestamo(long idPrestamo, EstadoPmo nuevoEstado);
    List<DtPrestamo> listarPrestamos();
}

