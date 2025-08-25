package interfaces;

import logica.ControladorAltaBibliotecario;
import logica.ControladorAltaLector;
import logica.ControladorMaterial;
import logica.ControladorModificarEstadoLector;
import logica.ControladorModificarZonaLector;

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

    public IControladorAltaBibliotecario getIControladorAltaBibliotecario() {
        return new ControladorAltaBibliotecario();
    }

    public IControladorMaterial getIControladorMaterial() {
        return new ControladorMaterial();
    }

    public IControladorModificarEstadoLector getIControladorModificarEstadoLector() {
        return new ControladorModificarEstadoLector();
    }

    public IControladorModificarZonaLector getIControladorModificarZonaLector() {
        return new ControladorModificarZonaLector();
    }
}
