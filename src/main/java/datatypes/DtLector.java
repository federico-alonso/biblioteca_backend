package datatypes;

import java.util.Date;

public class DtLector extends DtUsuario{
    private String direccion;
    private Date fechaRegistro;

    DtLector(){
        this.fechaRegistro = new Date();
    }
    DtLector(String direccion, Date fechaRegistro){
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
    }

}
