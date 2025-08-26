package interfaces;

import datatypes.DtBibliotecario;
import datatypes.DtLector;
import datatypes.DtMaterial;
import datatypes.DtPrestamo;
import excepciones.PrestamoYaExisteExcepcion;

import java.util.List;

public interface IControladorPrestamo {

    void altaPrestamo(DtPrestamo dtPrestamo) throws PrestamoYaExisteExcepcion;

    public List <DtLector> getListadoLectores();
    public List <DtBibliotecario> getListadoBibliotecarios();
    public List <DtMaterial> getListadoMateriales();


}
