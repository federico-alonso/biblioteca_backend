package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;

import java.util.List;

import datatypes.DtPrestamo;
import datatypes.DtLector;
import datatypes.DtBibliotecario;
import datatypes.DtMaterial;
import excepciones.PrestamoNoExisteExcepcion;
import interfaces.IControladorModificarTodoPrestamo;
import logica.ControladorModificarTodoPrestamo;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ModificarTodoPrestamoPublish {

    private IControladorModificarTodoPrestamo controlador;
    private Endpoint endpoint;

    public ModificarTodoPrestamoPublish() {
        controlador = new ControladorModificarTodoPrestamo();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/modificarTodoPrestamo";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
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

    @WebMethod
    public void modificarPrestamo(DtPrestamo prestamo) throws PrestamoNoExisteExcepcion {
        controlador.modificarPrestamo(prestamo);
    }

    @WebMethod
    public DtLector[] getListadoLectores() {
        List<DtLector> lista = controlador.getListadoLectores();
        DtLector[] resultado = new DtLector[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i] = lista.get(i);
        }
        return resultado;
    }

    @WebMethod
    public DtBibliotecario[] getListadoBibliotecarios() {
        List<DtBibliotecario> lista = controlador.getListadoBibliotecarios();
        DtBibliotecario[] resultado = new DtBibliotecario[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i] = lista.get(i);
        }
        return resultado;
    }

    @WebMethod
    public DtMaterial[] getListadoMateriales() {
        List<DtMaterial> lista = controlador.getListadoMateriales();
        DtMaterial[] resultado = new DtMaterial[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i] = lista.get(i);
        }
        return resultado;
    }
}
