package datatypes;

public class DtBibliotecario extends DtUsuario{
    private int numero;

    DtBibliotecario(){}
    public DtBibliotecario(String nombre, String email, int numero){
        super(nombre, email);
        this.numero = numero;
    }

}
