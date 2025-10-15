package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

import datatypes.DtArticuloEspecial;
import interfaces.Fabrica;
import interfaces.IControladorAltaDonacionEspecial;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class AltaDonacionEspecialPublish {

    private Fabrica fabrica;
    private IControladorAltaDonacionEspecial controlador;
    private Endpoint endpoint;

    public AltaDonacionEspecialPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorAltaDonacionEspecial();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/altaDonacionEspecial";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void altaDonacionEspecial(DtArticuloEspecial dtArticulo) throws Exception {
        controlador.altaDonacionEspecial(dtArticulo);
    }
}
