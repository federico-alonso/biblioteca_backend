package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

import java.util.List;

import datatypes.DtEstadoPorZona;
import interfaces.Fabrica;
import interfaces.IControladorListarPrestamosZona;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ListarPrestamosZonaPublish {

    private Fabrica fabrica;
    private IControladorListarPrestamosZona controlador;
    private Endpoint endpoint;

    public ListarPrestamosZonaPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorListarPrestamosZona();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/listarPrestamosZona";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public DtEstadoPorZona[] getResumenPrestamosPorZona() {
        List<DtEstadoPorZona> lista = controlador.getResumenPrestamosPorZona();
        DtEstadoPorZona[] resultado = new DtEstadoPorZona[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i] = lista.get(i);
        }
        return resultado;
    }
}
