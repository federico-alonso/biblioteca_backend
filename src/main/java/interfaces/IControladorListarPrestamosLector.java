package interfaces;

import datatypes.DtPrestamo;
import java.util.List;

public interface IControladorListarPrestamosLector {
    List<DtPrestamo> listarPrestamosActivosLector(String nombreLector);
    List<DtPrestamo> listarTodosPrestamosLector(String nombreLector);
    List<String> obtenerNombresLectores();
}

