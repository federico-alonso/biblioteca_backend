package interfaces;

import datatypes.EstadoLector;
import excepciones.LectorNoExisteExcepcion;
import java.util.List;
import datatypes.DtLector;

public interface IControladorModificarEstadoLector {
    void modificarEstadoLector(String nombre, EstadoLector nuevoEstado) throws LectorNoExisteExcepcion;

    List<String> listarNombresLectores();

    EstadoLector getEstadoLector(String nombre) throws LectorNoExisteExcepcion;

    DtLector getDtLectorPorCorreo(String correo) throws LectorNoExisteExcepcion;

}
