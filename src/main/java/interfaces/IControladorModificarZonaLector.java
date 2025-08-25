package interfaces;

import datatypes.Zona;
import excepciones.LectorNoExisteExcepcion;

public interface IControladorModificarZonaLector {
    void modificarZonaLector(String nombre, Zona nuevaZona) throws LectorNoExisteExcepcion;
}
