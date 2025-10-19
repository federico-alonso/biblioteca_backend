package logica;

import interfaces.IControladorModificarEstadoPrestamo;
import datatypes.EstadoPmo;
import datatypes.DtPrestamo;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class ControladorModificarEstadoPrestamo implements IControladorModificarEstadoPrestamo {

    @Override
    public void modificarEstadoPrestamo(long idPrestamo, EstadoPmo nuevoEstado) {
        Prestamo p = ManejadorPrestamo.getInstancia().buscarPrestamo(idPrestamo);
        if (p == null) {
            throw new IllegalArgumentException("Préstamo no encontrado");
        }

        p.setEstado(nuevoEstado);

        if (nuevoEstado == EstadoPmo.DEVUELTO) {
            p.setFechaDevolucion(new Date());
        }

        ManejadorPrestamo.getInstancia().actualizarPrestamo(p);
    }

    @Override
    public List<DtPrestamo> listarPrestamos() {
        List<Prestamo> prestamos = ManejadorPrestamo.getInstancia().listarPrestamos();
        List<DtPrestamo> dtPrestamos = new ArrayList<>();
        for (Prestamo p : prestamos) {
            dtPrestamos.add(p.obtenerDt());
        }
        return dtPrestamos;
    }
}
