package datatypes;

public class DtLoginResultado {
    private String tipo; // "lector" o "bibliotecario"
    private DtUsuario usuario;

    public DtLoginResultado(String tipo, DtUsuario usuario) {
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public DtUsuario getUsuario() {
        return usuario;
    }
}
