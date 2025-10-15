package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

import java.util.Date;

import datatypes.Zona;
import excepciones.LectorRepetidoExcepcion;
import interfaces.Fabrica;
import interfaces.IControladorAltaLector;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class AltaLectorPublish {

    private Fabrica fabrica;
    private IControladorAltaLector controlador;
    private Endpoint endpoint;

    public AltaLectorPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorAltaLector();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/altaLector";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void altaLector(String nombre, String email, String contrasena, String direccion, Date fechaRegistro, Zona zona) throws LectorRepetidoExcepcion {
        controlador.altaLector(nombre, email, contrasena, direccion, fechaRegistro, zona);
    }
}
