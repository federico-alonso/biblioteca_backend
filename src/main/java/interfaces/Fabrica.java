package interfaces;

import logica.ControladorAltaLector;
import logica.ControladorModificarEstadoLector;

public class Fabrica {
    private static Fabrica instancia = null;

    private Fabrica() {}

    public static Fabrica getInstancia() {
        if (instancia == null)
            instancia = new Fabrica();
        return instancia;
    }

    public IControladorAltaLector getIControladorAltaLector() {
        return new ControladorAltaLector();
    }

    public IControladorModificarEstadoLector getIControladorModificarEstadoLector() {
        return new ControladorModificarEstadoLector();
    }
}
