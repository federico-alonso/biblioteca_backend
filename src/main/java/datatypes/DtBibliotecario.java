package datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtBibliotecario extends DtUsuario {
    private int numero;

    DtBibliotecario(){}
    public DtBibliotecario(String nombre, String email, String contrasena, int numero){
        super(nombre, email, contrasena);
        this.numero = numero;
    }

}
