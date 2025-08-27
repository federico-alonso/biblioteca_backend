package interfaces;

import datatypes.DtBibliotecario;
import datatypes.DtLector;
import datatypes.DtMaterial;
import datatypes.DtPrestamo;
import datatypes.DtPrestamoSimple;
import excepciones.PrestamoYaExisteExcepcion;

import java.util.List;

public interface IControladorPrestamo {

    void altaPrestamo(DtPrestamo dtPrestamo) throws PrestamoYaExisteExcepcion;

    List<DtLector> getListadoLectores();
    List<DtBibliotecario> getListadoBibliotecarios();
    List<DtMaterial> getListadoMateriales();

    // Updated to reflect minimal DTO for ListarPréstamos
    List<DtPrestamoSimple> getPrestamosActivosPorLector(String lectorNombre);
}
