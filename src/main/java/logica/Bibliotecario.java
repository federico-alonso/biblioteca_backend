package logica;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

@Entity
public class Bibliotecario extends Usuario{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private int numero;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bibliotecario")
    private List<Prestamo> prestamos = new ArrayList<>();

    public Bibliotecario(){
        super();
    }

    public Bibliotecario(String nombre, String email){
        super(nombre, email);
    }
    public int getNumero(){
        return numero;
    }

}