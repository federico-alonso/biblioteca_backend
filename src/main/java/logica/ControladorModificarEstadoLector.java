package logica;

import datatypes.EstadoLector;
import excepciones.LectorNoExisteExcepcion;
import interfaces.IControladorModificarEstadoLector;
import java.util.List;
import datatypes.DtLector;

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

    @Override
    public EstadoLector getEstadoLector(String nombre) throws LectorNoExisteExcepcion {
        ManejadorLector manejadorLector = ManejadorLector.getInstance();
        Lector lector = manejadorLector.buscarLector(nombre);

        if (lector == null) {
            throw new LectorNoExisteExcepcion("El lector '" + nombre + "' no existe");
        }

        return lector.getEstado();
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


    
}
