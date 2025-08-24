package interfaces;

import datatypes.EstadoLector;
import excepciones.LectorNoExisteExcepcion;

public interface IControladorModificarEstadoLector {
    void modificarEstadoLector(String nombre, EstadoLector nuevoEstado) throws LectorNoExisteExcepcion;
}
