package excepciones;

public class LectorNoExisteExcepcion extends Exception {
    private static final long serialVersionUID = 1L;

    public LectorNoExisteExcepcion(String mensaje) {
        super(mensaje);
    }
}
