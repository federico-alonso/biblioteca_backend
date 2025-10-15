package logica;

import datatypes.Zona;
import excepciones.LectorNoExisteExcepcion;
import interfaces.IControladorModificarZonaLector;
import java.util.List;
import datatypes.DtLector;

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

    @Override
    public DtLector getDtLectorPorCorreo(String correo) throws LectorNoExisteExcepcion {
        ManejadorLector manejadorLector = ManejadorLector.getInstance();
        DtLector lector = manejadorLector.buscarDtLectorPorCorreo(correo);

        if (lector == null) {
            throw new LectorNoExisteExcepcion("No se encontró lector con correo: " + correo);
        }

        return lector;
}


    @Override
    public List<String> listarNombresLectores() {
        ManejadorLector manejadorLector = ManejadorLector.getInstance();
        return manejadorLector.listarNombresLectores();
    }
}
