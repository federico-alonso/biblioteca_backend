package logica;

import java.util.Date;

import jakarta.persistence.Entity;


@Entity
public class ArticuloEspecial extends Material{
    private String descripcion;
    private float peso;
 //   DtDimension dimensiones;

    public ArticuloEspecial(){
        super();
    }

    public ArticuloEspecial(String descripcion, float peso){
        super();
        this.descripcion = descripcion;
        this.peso = peso;
//        this.dimensiones = dimensiones;
    }

    public ArticuloEspecial(String descripcion, float peso, Date fechaIngreso){
        super(fechaIngreso);
        this.descripcion = descripcion;
        this.peso = peso;
//        this.dimensiones = dimensiones;
    }

}
