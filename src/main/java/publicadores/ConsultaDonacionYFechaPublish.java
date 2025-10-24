package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

import java.util.Date;
import java.util.List;

import datatypes.DtMaterial;
import interfaces.Fabrica;
import interfaces.IControladorConsultaDonacionYFecha;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ConsultaDonacionYFechaPublish {

    private Fabrica fabrica;
    private IControladorConsultaDonacionYFecha controlador;
    private Endpoint endpoint;

    public ConsultaDonacionYFechaPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorConsultaDonacionYFecha();
    }


    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/consultaDonacionYFecha";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public DtMaterial[] consultarDonacionPorFecha(Date fechaInicio, Date fechaFin) {
        List<DtMaterial> lista = controlador.consultarDonacionPorFecha(fechaInicio, fechaFin);
        DtMaterial[] resultado = new DtMaterial[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i] = lista.get(i);
        }
        return resultado;
    }

    public static void main(String[] args) {
        ConsultaDonacionYFechaPublish publicador = new ConsultaDonacionYFechaPublish();
        publicador.publicar("localhost", "18024");
    }
}
