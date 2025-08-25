package logica;

import datatypes.EstadoLector;
import excepciones.LectorNoExisteExcepcion;
import interfaces.IControladorModificarEstadoLector;

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
}
