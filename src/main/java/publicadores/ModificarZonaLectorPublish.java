package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

import java.util.List;

import datatypes.Zona;
import excepciones.LectorNoExisteExcepcion;
import interfaces.Fabrica;
import interfaces.IControladorModificarZonaLector;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ModificarZonaLectorPublish {

    private Fabrica fabrica;
    private IControladorModificarZonaLector controlador;
    private Endpoint endpoint;

    public ModificarZonaLectorPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorModificarZonaLector();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/modificarZonaLector";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void modificarZonaLector(String nombre, Zona nuevaZona) throws LectorNoExisteExcepcion {
        controlador.modificarZonaLector(nombre, nuevaZona);
    }

    @WebMethod
    public String[] listarNombresLectores() {
        List<String> nombres = controlador.listarNombresLectores();
        return nombres.toArray(new String[0]);
    }
}
