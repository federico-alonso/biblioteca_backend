package excepciones;

public class BibliotecarioNoTienePrestamos extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public BibliotecarioNoTienePrestamos(String message) {
        super(message);
    }
}
