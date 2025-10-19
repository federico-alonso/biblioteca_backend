package publicadores;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;
import datatypes.DtMaterialConPrestamo;

import java.util.List;

import datatypes.DtPrestamo;
import datatypes.DtPrestamoSimple;
import datatypes.DtLector;
import datatypes.DtBibliotecario;
import datatypes.DtMaterial;
import excepciones.PrestamoYaExisteExcepcion;
import excepciones.BibliotecarioNoTienePrestamos;
import interfaces.Fabrica;
import interfaces.IControladorPrestamo;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PrestamoPublish {

    private Fabrica fabrica;
    private IControladorPrestamo controlador;
    private Endpoint endpoint;

    public PrestamoPublish() {
        fabrica = Fabrica.getInstancia();
        controlador = fabrica.getIControladorPrestamo();
    }

    @WebMethod(exclude = true)
    public void publicar(String ip, String port) {
        String url = "http://" + ip + ":" + port + "/prestamo";
        endpoint = Endpoint.publish(url, this);
        System.out.println("Servicio publicado en: " + url);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void altaPrestamo(DtPrestamo dtPrestamo) throws PrestamoYaExisteExcepcion {
        controlador.altaPrestamo(dtPrestamo);
    }

    @WebMethod
    public DtPrestamoSimple[] getPrestamosActivosPorLector(DtLector lector) {
        List<DtPrestamoSimple> lista = controlador.getPrestamosActivosPorLector(lector);
        return lista.toArray(new DtPrestamoSimple[0]);
    }

    @WebMethod
    public String[] getNombresLectores() {
        List<String> nombres = controlador.getNombresLectores();
        return nombres.toArray(new String[0]);
    }

    @WebMethod
    public DtPrestamo[] consultarPrestamosBibliotecario(DtBibliotecario bibliotecario) throws BibliotecarioNoTienePrestamos {
        List<DtPrestamo> lista = controlador.consultarPrestamosBibliotecario(bibliotecario);
        return lista.toArray(new DtPrestamo[0]);
    }

    @WebMethod
    public Object[] consultarPrestamosComunes() {
        List<Object[]> lista = controlador.consultarPrestamosComunes();
        return lista.toArray(new Object[0]);
    }

    @WebMethod
    public DtLector[] getListadoLectores() {
        List<DtLector> lista = controlador.getListadoLectores();
        return lista.toArray(new DtLector[0]);
    }

    @WebMethod
    public DtBibliotecario[] getListadoBibliotecarios() {
        List<DtBibliotecario> lista = controlador.getListadoBibliotecarios();
        return lista.toArray(new DtBibliotecario[0]);
    }

    @WebMethod
    public DtMaterial[] getListadoMateriales() {
        List<DtMaterial> lista = controlador.getListadoMateriales();
        return lista.toArray(new DtMaterial[0]);
    }

    @WebMethod
    public DtMaterialConPrestamo[] getMaterialesConPrestamo(DtLector lector) {
        List<DtMaterialConPrestamo> lista = controlador.getMaterialesConPrestamo(lector);
        return lista.toArray(new DtMaterialConPrestamo[0]);
    }

    @WebMethod
    public DtMaterialConPrestamo[] getMaterialesConPrestamoTodos() {
        List<DtMaterialConPrestamo> lista = controlador.getMaterialesConPrestamoTodos();
        return lista.toArray(new DtMaterialConPrestamo[0]);
    }


}
