package logica;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Material{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_seq")
    @SequenceGenerator(name = "material_seq", sequenceName = "material_seq", allocationSize = 1)
    private long id;
    private Date fechaIngreso;

    @OneToMany(mappedBy = "material")
    private List<Prestamo> prestamos = new ArrayList();

    protected Material(){
        this.fechaIngreso = new Date();
    }

    protected Material(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    // Getters y setters
    public long getId() {
        return id;
    }
    public void setId(long  id) {
        this.id = id;
    }

    public Date getfechaIngreso() {
        return fechaIngreso;
    }
    public void setfechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public List<Prestamo> getPrestamos() { return prestamos; }
    public void setPrestamos(List<Prestamo> prestamos) { this.prestamos = prestamos; }

}

