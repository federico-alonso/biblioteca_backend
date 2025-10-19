package interfaces;

import datatypes.DtBibliotecario;
import datatypes.DtLector;
import datatypes.DtMaterial;
import datatypes.DtPrestamo;
import datatypes.DtPrestamoSimple;
import excepciones.PrestamoYaExisteExcepcion;
import excepciones.BibliotecarioNoTienePrestamos;


import java.util.List;
import datatypes.DtMaterialConPrestamo;
import java.util.Map;
import java.util.HashMap;

public interface IControladorPrestamo {

    void altaPrestamo(DtPrestamo dtPrestamo) throws PrestamoYaExisteExcepcion;

    List<DtLector> getListadoLectores();
    List<DtBibliotecario> getListadoBibliotecarios();
    List<DtMaterial> getListadoMateriales();

    List<DtPrestamo> consultarPrestamosBibliotecario(DtBibliotecario bibliotecario) throws BibliotecarioNoTienePrestamos;
    List<Object[]> consultarPrestamosComunes();

    List<DtPrestamoSimple> getPrestamosActivosPorLector(DtLector lector);

    List<String> getNombresLectores();

    List<DtMaterialConPrestamo> getMaterialesConPrestamo(DtLector lector);

    List<DtMaterialConPrestamo> getMaterialesConPrestamoTodos();

}
