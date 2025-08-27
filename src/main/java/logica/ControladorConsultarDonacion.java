package logica;

import interfaces.IControladorConsultarDonacion;
import datatypes.DtMaterial;
import java.util.List;

public class ControladorConsultarDonacion implements IControladorConsultarDonacion {
    public ControladorConsultarDonacion() {
        super();
    }

    @Override
    public List<DtMaterial> consultarDonacion() {
        ManejadorMaterial manejadorMaterial = ManejadorMaterial.getInstancia();
        return manejadorMaterial.getMateriales();
    }
}