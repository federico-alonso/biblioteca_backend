package logica;

import java.util.List;
import java.util.ArrayList;

import datatypes.DtBibliotecario;
import jakarta.persistence.*;

@Entity
public class Bibliotecario extends Usuario {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bibliotecario_seq")
    @SequenceGenerator(name = "bibliotecario_seq", sequenceName = "bibliotecario_seq", allocationSize = 1)
    private int numero;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bibliotecario")
    private List<Prestamo> prestamos = new ArrayList<>();

    public Bibliotecario() {
        super();
    }

    public Bibliotecario(String nombre, String email, String contrasena) {
        super(nombre, email, contrasena);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public DtBibliotecario obtenerDt(){
        return new DtBibliotecario(nombre, email, contrasena, numero);
    }
}
