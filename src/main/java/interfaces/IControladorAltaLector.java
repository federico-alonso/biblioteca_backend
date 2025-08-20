package interfaces;

import excepciones.LectorRepetidoExcepcion;

import java.util.Date;

public interface IControladorAltaLector {
    void altaLector(String nombre, String email, String direccion, Date fechaRegistro) throws LectorRepetidoExcepcion;
}
