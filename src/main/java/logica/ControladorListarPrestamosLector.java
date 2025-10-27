package logica;

import interfaces.IControladorListarPrestamosLector;
import datatypes.DtPrestamo;
import datatypes.EstadoPmo;
import persistencia.Conexion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class ControladorListarPrestamosLector implements IControladorListarPrestamosLector {

    public ControladorListarPrestamosLector() {
        super();
    }

    @Override
    public List<DtPrestamo> listarPrestamosActivosLector(String nombreLector) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            TypedQuery<Prestamo> query = em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.lector.nombre = :nombre AND p.estado = :estado ORDER BY p.fechaSolicitud DESC",
                Prestamo.class
            );
            query.setParameter("nombre", nombreLector);
            query.setParameter("estado", EstadoPmo.EN_CURSO);
            
            List<Prestamo> prestamos = query.getResultList();
            List<DtPrestamo> resultado = new ArrayList<>();
            
            for (Prestamo p : prestamos) {
                resultado.add(p.obtenerDt());
            }
            
            return resultado;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DtPrestamo> listarTodosPrestamosLector(String nombreLector) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            TypedQuery<Prestamo> query = em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.lector.nombre = :nombre ORDER BY p.fechaSolicitud DESC",
                Prestamo.class
            );
            query.setParameter("nombre", nombreLector);
            
            List<Prestamo> prestamos = query.getResultList();
            List<DtPrestamo> resultado = new ArrayList<>();
            
            for (Prestamo p : prestamos) {
                resultado.add(p.obtenerDt());
            }
            
            return resultado;
        } finally {
            em.close();
        }
    }

    @Override
    public List<String> obtenerNombresLectores() {
        ManejadorLector manejador = ManejadorLector.getInstance();
        List<String> nombres = new ArrayList<>();
        
        manejador.getLectores().forEach(lector -> nombres.add(lector.getNombre()));
        
        return nombres;
    }
}

