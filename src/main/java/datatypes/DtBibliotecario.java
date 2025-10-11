package datatypes;

public class DtBibliotecario extends DtUsuario{
    private int numero;

    DtBibliotecario(){}
    public DtBibliotecario(String nombre, String email, String contrasena, int numero){
        super(nombre, email, contrasena);
        this.numero = numero;
    }

}
