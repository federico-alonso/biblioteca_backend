package interfaces;

import logica.*;

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

    public IControladorPrestamo getIControladorPrestamo() {
        return new ControladorPrestamo();
    }


}
