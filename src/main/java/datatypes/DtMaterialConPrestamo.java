package datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtMaterialConPrestamo {
    private DtMaterial material;
    private DtPrestamo prestamo; // puede ser null

    public DtMaterialConPrestamo() {}

    public DtMaterialConPrestamo(DtMaterial material, DtPrestamo prestamo) {
        this.material = material;
        this.prestamo = prestamo;
    }

    public DtMaterial getMaterial() {
        return material;
    }

    public DtPrestamo getPrestamo() {
        return prestamo;
    }

    public void setMaterial(DtMaterial material) {
        this.material = material;
    }

    public void setPrestamo(DtPrestamo prestamo) {
        this.prestamo = prestamo;
    }
}
