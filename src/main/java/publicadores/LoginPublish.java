package publicadores;

import datatypes.DtLoginResultado;
import interfaces.Fabrica;
import interfaces.IControladorLogin;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class LoginPublish {

    private IControladorLogin controlador;

    public LoginPublish() {
        controlador = Fabrica.getInstancia().getIControladorLogin();
    }

    @WebMethod
    public DtLoginResultado login(String email, String contrasena) {
        if (email == null || contrasena == null || email.isEmpty() || contrasena.isEmpty()) {
            return null;
        }
        return controlador.login(email, contrasena);
    }
}
