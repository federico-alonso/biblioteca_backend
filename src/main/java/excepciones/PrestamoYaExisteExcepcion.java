package excepciones;

public class PrestamoYaExisteExcepcion extends Exception{
    private static final long serialVersionUID = 1L;

    public PrestamoYaExisteExcepcion(String string) {
        super(string);
    }
}