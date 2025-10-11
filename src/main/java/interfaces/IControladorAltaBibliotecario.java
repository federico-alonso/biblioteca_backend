package interfaces;

import excepciones.BibliotecarioRepetidoExcepcion;

public interface IControladorAltaBibliotecario {
    void altaBibliotecario(String nombre, String email, String contrasena) throws BibliotecarioRepetidoExcepcion;
}
