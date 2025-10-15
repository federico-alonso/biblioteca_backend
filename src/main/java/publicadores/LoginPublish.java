package publicadores;

import datatypes.DtLoginResultado;
import interfaces.Fabrica;
import interfaces.IControladorLogin;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class LoginPublish {

    private IControladorLogin controlador;
    private Endpoint endpoint;

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

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/login";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio Login publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
}
