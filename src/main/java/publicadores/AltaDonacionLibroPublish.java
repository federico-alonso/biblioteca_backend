package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

import datatypes.DtLibro;
import interfaces.Fabrica;
import interfaces.IControladorAltaDonacionLibro;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class AltaDonacionLibroPublish {

    private Fabrica fabrica;
    private IControladorAltaDonacionLibro controlador;
    private Endpoint endpoint;

    public AltaDonacionLibroPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorAltaDonacionLibro();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/altaDonacionLibro";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void altaDonacionLibro(DtLibro dtLibro) throws Exception {
        controlador.altaDonacionLibro(dtLibro);
    }
}
