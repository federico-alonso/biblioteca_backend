package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

import java.util.List;

import datatypes.DtPrestamo;
import interfaces.Fabrica;
import interfaces.IControladorAutorizarPrestamo;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class AutorizarPrestamoPublish {

    private Fabrica fabrica;
    private IControladorAutorizarPrestamo controlador;
    private Endpoint endpoint;

    public AutorizarPrestamoPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorAutorizarPrestamo();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/autorizarPrestamo";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void autorizarPrestamo(long idPrestamo, String nombreBibliotecario) {
        // El nombre del bibliotecario es opcional - si es null o vacío, usa la sesión
        controlador.autorizarPrestamo(idPrestamo, nombreBibliotecario);
    }

    @WebMethod
    public void rechazarPrestamo(long idPrestamo, String nombreBibliotecario) {
        // El nombre del bibliotecario es opcional para rechazar
        controlador.rechazarPrestamo(idPrestamo, nombreBibliotecario);
    }

    @WebMethod
    public DtPrestamo[] listarPrestamosPendientes() {
        List<DtPrestamo> lista = controlador.listarPrestamosPendientes();
        return lista.toArray(new DtPrestamo[0]);
    }
}

