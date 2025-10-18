package publicadores;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import jakarta.xml.ws.Endpoint;

import interfaces.Fabrica;
import interfaces.IControladorDonaciones;
import datatypes.DtMaterial;

import java.util.List;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorDonacionesPublish {

    private Fabrica fabrica;
    private IControladorDonaciones controlador;
    private Endpoint endpoint;

    public ControladorDonacionesPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorDonaciones();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/donaciones";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    @XmlElementWrapper(name = "donaciones")
    @XmlElement(name = "material")
    public DtMaterial[] obtenerDonaciones() throws Exception {
        List<DtMaterial> lista = controlador.obtenerDonaciones();
        return lista.toArray(new DtMaterial[0]);
    }
    
}
