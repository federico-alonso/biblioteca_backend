package logica;


import datatypes.EstadoUsuario;
import datatypes.Zona;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;


@Entity
public class Lector extends Usuario {
    private String direccion;
    private Date fechaRegistro;
   // private EstadoUsuario estado;
    //private Zona zona;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lector")
    private List<Prestamo> prestamos = new ArrayList<>();

    public Lector(){
        super();
    }

    public Lector(String direccion, Date fechaRegistro, EstadoUsuario estado, Zona zona, List<Prestamo> prestamos){
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
  //      this.estado = estado;
    //    this.zona = zona;
        this.prestamos = prestamos;
    }
//
//    // Setters
//    public void setDireccion(String direccion) {
//        this.direccion = direccion;
//    }
//
//    public void setFechaRegistro(Date fechaRegistro) {
//        this.fechaRegistro = fechaRegistro;
//    }
//
//    public void setEstado(EstadoUsuario estado) {
//        this.estado = estado;
//    }
//
//    public void setZona(Zona zona) {
//        this.zona = zona;
//    }
//
//    public void setPrestamos(List<Prestamo> prestamos) {
//        this.prestamos = prestamos;
//    }
//
//    // Getters
//    public String getDireccion() {
//        return direccion;
//    }
//
//    public Date getFechaRegistro() {
//        return fechaRegistro;
//    }
//
//    public EstadoUsuario getEstado() {
//        return estado;
//    }
//
//    public Zona getZona() {
//        return zona;
//    }
//
//    public List<Prestamo> getPrestamos() {
//        return prestamos;
//    }


}
