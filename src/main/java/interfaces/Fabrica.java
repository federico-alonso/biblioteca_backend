package interfaces;

import logica.ControladorAltaBibliotecario;
import logica.ControladorAltaLector;
import logica.ControladorAltaDonacionLibro;
import logica.ControladorAltaDonacionEspecial;
import logica.ControladorModificarEstadoLector;
import logica.ControladorModificarZonaLector;
import logica.ControladorPrestamo;
import logica.ControladorConsultarDonacion;
import logica.ControladorModificarEstadoPrestamo;
import logica.ControladorConsultaDonacionYFecha;
import logica.ControladorModificarTodoPrestamo;
import logica.ControladorListarPrestamosZona;
import logica.ControladorDonaciones;
import logica.ControladorLogin;
import logica.ControladorListarPrestamosLector;

public class Fabrica {
    private static Fabrica instancia = null;

    private Fabrica() {}

    public static Fabrica getInstancia() {
        if (instancia == null)
            instancia = new Fabrica();
        return instancia;
    }

    // === Alta Lector / Bibliotecario ===
    public IControladorAltaLector getIControladorAltaLector() {
        return new ControladorAltaLector();
    }



    public IControladorLogin getIControladorLogin() {
        return new ControladorLogin();
    }

    

    public IControladorDonaciones getIControladorDonaciones() {
        return new ControladorDonaciones();
    }

    
    

    public IControladorAltaBibliotecario getIControladorAltaBibliotecario() {
        return new ControladorAltaBibliotecario();
    }

    // === Donaciones separadas ===
    public IControladorAltaDonacionLibro getIControladorAltaDonacionLibro() {
        return new ControladorAltaDonacionLibro();
    }

    public IControladorAltaDonacionEspecial getIControladorAltaDonacionEspecial() {
        return new ControladorAltaDonacionEspecial();
    }

    // === Modificaciones y Prestamos ===
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

    public IControladorModificarTodoPrestamo getIControladorModificarTodoPrestamo() {
        return new ControladorModificarTodoPrestamo();
    }

    public IControladorListarPrestamosZona getIControladorListarPrestamosZona() {
        return new ControladorListarPrestamosZona();
    }

    public IControladorListarPrestamosLector getIControladorListarPrestamosLector() {
        return new ControladorListarPrestamosLector();
    }
}
