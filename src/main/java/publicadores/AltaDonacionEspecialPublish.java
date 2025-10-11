package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

import datatypes.DtArticuloEspecial;
import interfaces.IControladorAltaDonacionEspecial;
import logica.ControladorAltaDonacionEspecial;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class AltaDonacionEspecialPublish {

    private IControladorAltaDonacionEspecial controlador;
    private Endpoint endpoint;

    public AltaDonacionEspecialPublish() {
        controlador = new ControladorAltaDonacionEspecial();
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
