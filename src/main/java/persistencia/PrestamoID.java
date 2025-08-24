package persistencia;

import java.io.Serializable;

public class PrestamoID implements Serializable {

    private static final long serialVersionUID = 1L;

    private int material;
    private String bibliotecario;
    private String lector;

    public PrestamoID() {}

    public PrestamoID(int material, String bibliotecario, String lector) {
        this.material = material;
        this.bibliotecario = bibliotecario;
        this.lector = lector;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public String getBibliotecario() {
        return bibliotecario;
    }

    public void setBibliotecario(String bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    public String getLector() {
        return lector;
    }

    public void setLector(String lector) {
        this.lector = lector;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + material;
        result = prime * result + ((bibliotecario == null) ? 0 : bibliotecario.hashCode());
        result = prime * result + ((lector == null) ? 0 : lector.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PrestamoID other = (PrestamoID) obj;
        if (material != other.material)
            return false;
        if (bibliotecario == null) {
            if (other.bibliotecario != null)
                return false;
        } else if (!bibliotecario.equals(other.bibliotecario))
            return false;
        if (lector == null) {
            if (other.lector != null)
                return false;
        } else if (!lector.equals(other.lector))
            return false;
        return true;
    }
}