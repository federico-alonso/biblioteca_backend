package logica;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

@Entity
public class Bibliotecario extends Usuario {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private int numero;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bibliotecario")
    private List<Prestamo> prestamos = new ArrayList<>();

    public Bibliotecario() {
        super();
    }

    public Bibliotecario(String nombre, String email) {
        super(nombre, email);
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
}
