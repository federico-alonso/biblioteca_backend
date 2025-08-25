package persistencia;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class PrestamoID implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long materialId;
    private String bibliotecarioNombre;
    private String lectorNombre;

    public PrestamoID() {}

    public PrestamoID(Long materialId, String bibliotecarioNombre, String lectorNombre) {
        this.materialId = materialId;
        this.bibliotecarioNombre = bibliotecarioNombre;
        this.lectorNombre = lectorNombre;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getBibliotecarioNombre() {
        return bibliotecarioNombre;
    }

    public void setBibliotecarioNombre(String bibliotecarioNombre) {
        this.bibliotecarioNombre = bibliotecarioNombre;
    }

    public String getLectorNombre() {
        return lectorNombre;
    }

    public void setLectorNombre(String lectorNombre) {
        this.lectorNombre = lectorNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrestamoID)) return false;
        PrestamoID that = (PrestamoID) o;
        return materialId.equals(that.materialId)
                && bibliotecarioNombre.equals(that.bibliotecarioNombre)
                && lectorNombre.equals(that.lectorNombre);
    }

    @Override
    public int hashCode() {
        int result = materialId.hashCode();
        result = 31 * result + bibliotecarioNombre.hashCode();
        result = 31 * result + lectorNombre.hashCode();
        return result;
    }
}
