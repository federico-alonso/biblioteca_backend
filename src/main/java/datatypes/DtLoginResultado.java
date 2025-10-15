package datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtLoginResultado {
    private String tipo;
    private DtUsuario usuario;

    public DtLoginResultado() {} // requerido por Jakarta XML Bind

    public DtLoginResultado(String tipo, DtUsuario usuario) {
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public DtUsuario getUsuario() {
        return usuario;
    }
}
