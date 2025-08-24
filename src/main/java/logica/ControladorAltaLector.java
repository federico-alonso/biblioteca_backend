package logica;

import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.EntityManager;

import excepciones.LectorRepetidoExcepcion;
import interfaces.IControladorAltaLector;


public class ControladorAltaLector implements IControladorAltaLector {

    public ControladorAltaLector() {
        super();
    }

    @Override
    public void altaLector(String nombre, String email, String direccion, Date fechaRegistro) throws LectorRepetidoExcepcion {
        ManejadorLector manejadorLector = ManejadorLector.getInstance();
        
        // Now we search by nombre, since it's the @Id
        Lector lector = manejadorLector.buscarLector(nombre);

        if (lector != null) {
            throw new LectorRepetidoExcepcion("El lector '" + nombre + "' ya está registrado");
        }

        lector = new Lector(nombre, email, fechaRegistro, direccion);
        manejadorLector.agregarLector(lector);
    }

}