package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

import java.util.List;

import datatypes.DtPrestamo;
import datatypes.EstadoPmo;
import interfaces.Fabrica;
import interfaces.IControladorModificarEstadoPrestamo;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ModificarEstadoPrestamoPublish {

    private Fabrica fabrica;
    private IControladorModificarEstadoPrestamo controlador;
    private Endpoint endpoint;

    public ModificarEstadoPrestamoPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorModificarEstadoPrestamo();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/modificarEstadoPrestamo";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void modificarEstadoPrestamo(long idPrestamo, EstadoPmo nuevoEstado) {
        controlador.modificarEstadoPrestamo(idPrestamo, nuevoEstado);
    }

    @WebMethod
    public DtPrestamo[] listarPrestamos() {
        List<DtPrestamo> lista = controlador.listarPrestamos();
        DtPrestamo[] resultado = new DtPrestamo[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i] = lista.get(i);
        }
        return resultado;
    }
}
