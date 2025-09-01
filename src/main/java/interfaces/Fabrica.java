package interfaces;

import logica.ControladorAltaBibliotecario;
import logica.ControladorAltaLector;
import logica.ControladorMaterial;
import logica.ControladorModificarEstadoLector;
import logica.ControladorModificarZonaLector;
import logica.ControladorPrestamo;
import logica.ControladorConsultarDonacion;
import logica.ControladorModificarEstadoPrestamo;
import logica.ControladorConsultaDonacionYFecha;

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

    public IControladorPrestamo getIControladorPrestamo() {
        return new ControladorPrestamo();
    }

    public IControladorConsultarDonacion getIControladorConsultarDonacion() {
        return new ControladorConsultarDonacion();
    }

    public IControladorModificarEstadoPrestamo getIControladorModificarEstadoPrestamo() {
        return new ControladorModificarEstadoPrestamo();
    }

    public IControladorConsultaDonacionYFecha getIControladorConsultaDonacionYFecha() {
        return new ControladorConsultaDonacionYFecha();
    }
}
