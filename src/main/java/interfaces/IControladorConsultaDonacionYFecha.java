package interfaces;
import datatypes.DtMaterial;
import java.util.Date;
import java.util.List;

public interface IControladorConsultaDonacionYFecha {
    List<DtMaterial> consultarDonacionPorFecha(Date fechaInicio, Date fechaFin);
}
