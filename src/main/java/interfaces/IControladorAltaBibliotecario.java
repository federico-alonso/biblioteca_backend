package interfaces;

import excepciones.BibliotecarioRepetidoExcepcion;

public interface IControladorAltaBibliotecario {
    void altaBibliotecario(String nombre, String email) throws BibliotecarioRepetidoExcepcion;
}
