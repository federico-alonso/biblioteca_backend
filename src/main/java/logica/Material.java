package logica;

import datatypes.DtMaterial;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_seq")
    @SequenceGenerator(name = "material_seq", sequenceName = "material_seq", allocationSize = 1)
    private long id;

    private Date fechaIngreso;

    @OneToMany(mappedBy = "material")
    private List<Prestamo> prestamos = new ArrayList<>();

    protected Material() {
        this.fechaIngreso = new Date();
    }

    protected Material(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {this.id = id;}

    public Date getfechaIngreso() {
        return fechaIngreso;
    }

    public void setfechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
    public abstract DtMaterial obtenerDt();

}
