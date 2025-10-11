package interfaces;

import java.util.Date;
import datatypes.Zona;
import excepciones.LectorRepetidoExcepcion;

public interface IControladorAltaLector {
    void altaLector(String nombre, String email, String contrasena, String direccion, Date fechaRegistro, Zona zona) throws LectorRepetidoExcepcion;
}
