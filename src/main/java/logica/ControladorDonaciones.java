package logica;

import interfaces.IControladorDonaciones;
import datatypes.DtMaterial;
import datatypes.DtLibro;
import datatypes.DtArticuloEspecial;
import java.util.List;
import java.util.ArrayList;

public class ControladorDonaciones implements IControladorDonaciones {

    @Override
    public List<DtMaterial> obtenerDonaciones() throws Exception {
        return ManejadorMaterial.getInstancia().getMateriales();
    }


}
