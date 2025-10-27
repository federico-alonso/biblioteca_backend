package logica;

import datatypes.DtLoginResultado;
import datatypes.DtUsuario;
import datatypes.DtLector;
import datatypes.DtBibliotecario;
import datatypes.EstadoLector;
import interfaces.IControladorLogin;
import logica.ManejadorLector;
import logica.ManejadorBibliotecario;

public class ControladorLogin implements IControladorLogin {

    @Override
    public DtLoginResultado login(String email, String contrasena) {
        for (DtLector lector : ManejadorLector.getInstance().getLectores()) {
            if (lector.getEmail().equals(email) && lector.getContrasena().equals(contrasena)) {
                // Guardar sesión (los suspendidos pueden loguearse)
                SesionUsuario.getInstancia().iniciarSesion("lector", lector);
                return new DtLoginResultado("lector", lector);
            }
        }
        for (DtBibliotecario biblio : ManejadorBibliotecario.getInstance().getBibliotecarios()) {
            if (biblio.getEmail().equals(email) && biblio.getContrasena().equals(contrasena)) {
                // Guardar sesión
                SesionUsuario.getInstancia().iniciarSesion("bibliotecario", biblio);
                return new DtLoginResultado("bibliotecario", biblio);
            }
        }
        return null;
    }
    
    /**
     * Cierra la sesión del usuario actual
     */
    public void logout() {
        SesionUsuario.getInstancia().cerrarSesion();
    }
}
