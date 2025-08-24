package logica;
import excepciones.BibliotecarioRepetidoExcepcion;
import interfaces.IControladorAltaBibliotecario;

public class ControladorAltaBibliotecario implements IControladorAltaBibliotecario{

    public ControladorAltaBibliotecario(){
        super();
    }

    @Override
    public void altaBibliotecario(String nombre, String email) throws BibliotecarioRepetidoExcepcion{
        ManejadorBibliotecario manejadorBibliotecario = ManejadorBibliotecario.getInstance();
        Bibliotecario bibliotecario = manejadorBibliotecario.buscarBibliotecario(nombre);
        if(bibliotecario != null){
            throw new BibliotecarioRepetidoExcepcion("El bibliotecario '" + nombre + "' ya está registrado");
        }
        bibliotecario = new Bibliotecario(nombre, email);
        manejadorBibliotecario.agregarBibliotecario(bibliotecario);
    }
}
