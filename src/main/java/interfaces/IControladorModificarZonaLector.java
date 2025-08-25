package interfaces;

import datatypes.Zona;
import excepciones.LectorNoExisteExcepcion;
import java.util.List;

public interface IControladorModificarZonaLector {
    void modificarZonaLector(String nombre, Zona nuevaZona) throws LectorNoExisteExcepcion;
    List<String> listarNombresLectores();
}
