package logica;

import interfaces.IControladorConsultaDonacionYFecha;
import datatypes.DtMaterial;
import java.util.Date;
import java.util.List;

public class ControladorConsultaDonacionYFecha implements IControladorConsultaDonacionYFecha {
    
    public ControladorConsultaDonacionYFecha() {
        super();
    }

    @Override
    public List<DtMaterial> consultarDonacionPorFecha(Date fechaInicio, Date fechaFin) {
        ManejadorMaterial manejadorMaterial = ManejadorMaterial.getInstancia();
        return manejadorMaterial.getMaterialesPorFecha(fechaInicio, fechaFin);
    }
}
