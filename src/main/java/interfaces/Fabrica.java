package interfaces;

import logica.ControladorAltaLector;
<<<<<<< HEAD
import logica.ControladorMaterial;
=======
import logica.ControladorModificarEstadoLector;
>>>>>>> ba5928c (Estado lector)

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
<<<<<<< HEAD
    public IControladorMaterial getIControladorMaterial(){
        return new ControladorMaterial();
=======

    public IControladorModificarEstadoLector getIControladorModificarEstadoLector() {
        return new ControladorModificarEstadoLector();
>>>>>>> ba5928c (Estado lector)
    }
}
