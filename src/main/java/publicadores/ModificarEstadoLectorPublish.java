package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

import java.util.List;

import datatypes.EstadoLector;
import excepciones.LectorNoExisteExcepcion;
import interfaces.Fabrica;
import interfaces.IControladorModificarEstadoLector;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ModificarEstadoLectorPublish {

    private Fabrica fabrica;
    private IControladorModificarEstadoLector controlador;
    private Endpoint endpoint;

    public ModificarEstadoLectorPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorModificarEstadoLector();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/modificarEstadoLector";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void modificarEstadoLector(String nombre, EstadoLector nuevoEstado) throws LectorNoExisteExcepcion {
        controlador.modificarEstadoLector(nombre, nuevoEstado);
    }

    @WebMethod
    public String[] listarNombresLectores() {
        List<String> nombres = controlador.listarNombresLectores();
        return nombres.toArray(new String[0]);
    }

    @WebMethod
    public EstadoLector getEstadoLector(String nombre) throws LectorNoExisteExcepcion {
        return controlador.getEstadoLector(nombre);
    }
}
