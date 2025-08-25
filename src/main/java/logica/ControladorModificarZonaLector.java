package logica;

import datatypes.Zona;
import excepciones.LectorNoExisteExcepcion;
import interfaces.IControladorModificarZonaLector;

public class ControladorModificarZonaLector implements IControladorModificarZonaLector {

    public ControladorModificarZonaLector() {
        super();
    }

    @Override
    public void modificarZonaLector(String nombre, Zona nuevaZona) throws LectorNoExisteExcepcion {
        ManejadorLector manejadorLector = ManejadorLector.getInstance();
        Lector lector = manejadorLector.buscarLector(nombre);

        if (lector == null) {
            throw new LectorNoExisteExcepcion("El lector '" + nombre + "' no existe");
        }

        lector.setZona(nuevaZona);
        manejadorLector.actualizarLector(lector);
    }
}
