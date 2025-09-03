package interfaces;

import datatypes.DtPrestamo;
import datatypes.EstadoPmo;
import excepciones.PrestamoNoExisteExcepcion;
import datatypes.DtLector;
import datatypes.DtBibliotecario;
import datatypes.DtMaterial;

import java.util.List;

public interface IControladorModificarTodoPrestamo {
    List<DtPrestamo> listarPrestamos();
    void modificarPrestamo(DtPrestamo prestamo) throws PrestamoNoExisteExcepcion;
    List<DtLector> getListadoLectores();
    List<DtBibliotecario> getListadoBibliotecarios();
    List<DtMaterial> getListadoMateriales();
}