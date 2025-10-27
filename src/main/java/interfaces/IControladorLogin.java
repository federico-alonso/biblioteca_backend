package interfaces;

import datatypes.DtLoginResultado;

public interface IControladorLogin {
    DtLoginResultado login(String email, String contrasena);
    void logout();
}
