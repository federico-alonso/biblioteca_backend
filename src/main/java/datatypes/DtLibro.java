package datatypes;

import java.util.Date;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtLibro extends DtMaterial {
    private String titulo;
    private int cantidadPag;

    public DtLibro(){
        super();
    }

    public DtLibro(long id, String titulo, int cantidadPag, Date fechaIngreso){
        super(id, fechaIngreso);
        this.titulo = titulo;
        this.cantidadPag = cantidadPag;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public void setCantidadPag(int cantidadPag){
        this.cantidadPag = cantidadPag;
    }
    public String getTitulo(){
        return titulo;
    }
    public int getCantidadPag(){
        return cantidadPag;
    }

}
