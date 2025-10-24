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
import interfaces.IControladorListarPrestamosLector;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ListarPrestamosLectorPublish {

    private Fabrica fabrica;
    private IControladorListarPrestamosLector controlador;
    private Endpoint endpoint;

    public ListarPrestamosLectorPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorListarPrestamosLector();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/listarPrestamosLector";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    /**
     * Lista todos los préstamos ACTIVOS de un lector específico
     * @param nombreLector Nombre del lector
     * @return Array de DtPrestamo con préstamos activos
     */
    @WebMethod
    public DtPrestamo[] listarPrestamosActivosLector(String nombreLector) {
        List<DtPrestamo> lista = controlador.listarPrestamosActivosLector(nombreLector);
        return lista.toArray(new DtPrestamo[0]);
    }

    /**
     * Lista TODOS los préstamos de un lector (activos, devueltos, pendientes, etc.)
     * @param nombreLector Nombre del lector
     * @return Array de DtPrestamo con todos los préstamos
     */
    @WebMethod
    public DtPrestamo[] listarTodosPrestamosLector(String nombreLector) {
        List<DtPrestamo> lista = controlador.listarTodosPrestamosLector(nombreLector);
        return lista.toArray(new DtPrestamo[0]);
    }

    /**
     * Obtiene la lista de nombres de todos los lectores registrados
     * @return Array de String con los nombres de los lectores
     */
    @WebMethod
    public String[] obtenerNombresLectores() {
        List<String> nombres = controlador.obtenerNombresLectores();
        return nombres.toArray(new String[0]);
    }

    public static void main(String[] args) {
        ListarPrestamosLectorPublish publicador = new ListarPrestamosLectorPublish();
        publicador.publicar("localhost", "18025");
    }
}

