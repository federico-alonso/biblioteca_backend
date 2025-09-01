package logica;

import interfaces.IControladorModificarEstadoPrestamo;
import datatypes.EstadoPmo;
import datatypes.DtPrestamo;
import java.util.List;
import java.util.ArrayList;

public class ControladorModificarEstadoPrestamo implements IControladorModificarEstadoPrestamo {

    @Override
    public void modificarEstadoPrestamo(long idPrestamo, EstadoPmo nuevoEstado) {
        ManejadorPrestamo.getInstancia().modificarEstadoPrestamo(idPrestamo, nuevoEstado);
    }

    @Override
    public List<DtPrestamo> listarTodosLosPrestamos() {
        List<Prestamo> prestamos = ManejadorPrestamo.getInstancia().listarTodosLosPrestamos();
        List<DtPrestamo> dtPrestamos = new ArrayList<>();
        for (Prestamo p : prestamos) {
            dtPrestamos.add(p.obtenerDt());
        }
        return dtPrestamos;
    }
}
