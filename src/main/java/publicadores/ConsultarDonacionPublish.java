package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

import java.util.List;

import datatypes.DtMaterial;
import interfaces.Fabrica;
import interfaces.IControladorConsultarDonacion;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ConsultarDonacionPublish {

    private Fabrica fabrica;
    private IControladorConsultarDonacion controlador;
    private Endpoint endpoint;

    public ConsultarDonacionPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorConsultarDonacion();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/consultarDonacion";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public DtMaterial[] consultarDonacion() {
        List<DtMaterial> lista = controlador.consultarDonacion();
        DtMaterial[] resultado = new DtMaterial[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i] = lista.get(i);
        }
        return resultado;
    }
}
