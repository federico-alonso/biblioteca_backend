package logica;

import java.util.Date;

import datatypes.Zona;
import excepciones.LectorRepetidoExcepcion;
import interfaces.IControladorAltaLector;

public class ControladorAltaLector implements IControladorAltaLector {

    public ControladorAltaLector() {
        super();
    }

    @Override
    public void altaLector(String nombre, String email, String contrasena, String direccion, Date fechaRegistro, Zona zona) throws LectorRepetidoExcepcion {
        ManejadorLector manejadorLector = ManejadorLector.getInstance();

        // Now we search by nombre, since it's the @Id
        Lector lector = manejadorLector.buscarLector(nombre);

        if (lector != null) {
            throw new LectorRepetidoExcepcion("El lector '" + nombre + "' ya está registrado");
        }

        lector = new Lector(nombre, email, contrasena, fechaRegistro, direccion, zona);
        manejadorLector.agregarLector(lector);
    }
}
