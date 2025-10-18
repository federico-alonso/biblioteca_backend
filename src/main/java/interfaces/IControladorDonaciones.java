package interfaces;

import datatypes.DtMaterial;
import java.util.List;

public interface IControladorDonaciones {
    List<DtMaterial> obtenerDonaciones() throws Exception;
}
