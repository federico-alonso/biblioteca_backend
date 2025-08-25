package logica;

import datatypes.EstadoLector;
import excepciones.LectorNoExisteExcepcion;
import interfaces.IControladorModificarEstadoLector;
import java.util.List;

public class ControladorModificarEstadoLector implements IControladorModificarEstadoLector {

    public ControladorModificarEstadoLector() {
        super();
    }

    @Override
    public void modificarEstadoLector(String nombre, EstadoLector nuevoEstado) throws LectorNoExisteExcepcion {
        ManejadorLector manejadorLector = ManejadorLector.getInstance();
        Lector lector = manejadorLector.buscarLector(nombre);

        if (lector == null) {
            throw new LectorNoExisteExcepcion("El lector '" + nombre + "' no existe");
        }

        lector.setEstado(nuevoEstado);
        manejadorLector.actualizarLector(lector);
    }

    @Override
    public List<String> listarNombresLectores() {
        ManejadorLector manejadorLector = ManejadorLector.getInstance();
        return manejadorLector.listarNombresLectores();
    }
}
