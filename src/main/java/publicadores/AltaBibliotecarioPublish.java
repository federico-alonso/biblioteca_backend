package publicadores;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

import excepciones.BibliotecarioRepetidoExcepcion;
import interfaces.Fabrica;
import interfaces.IControladorAltaBibliotecario;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class AltaBibliotecarioPublish {

    private Fabrica fabrica;
    private IControladorAltaBibliotecario controlador;
    private Endpoint endpoint;

    public AltaBibliotecarioPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorAltaBibliotecario();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/altaBibliotecario";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void altaBibliotecario(String nombre, String email, String contrasena) throws BibliotecarioRepetidoExcepcion {
        controlador.altaBibliotecario(nombre, email, contrasena);
    }
}
