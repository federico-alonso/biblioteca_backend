package interfaces;
import datatypes.DtLibro;
import datatypes.DtMaterial;
import datatypes.DtArticuloEspecial;
import java.util.List;

public interface IControladorConsultarDonacion {
    List<DtMaterial> consultarDonacion();
}
