package logica;

import interfaces.IControladorAutorizarPrestamo;
import datatypes.DtPrestamo;
import datatypes.EstadoPmo;
import java.util.List;
import java.util.ArrayList;

/**
 * Controlador para que los bibliotecarios autoricen o rechacen préstamos pendientes
 */
public class ControladorAutorizarPrestamo implements IControladorAutorizarPrestamo {

    public ControladorAutorizarPrestamo() {
        super();
    }

    /**
     * Autoriza un préstamo pendiente.
     * Aquí se forma la asociación entre el bibliotecario y el préstamo.
     * El estado cambia a EN_CURSO.
     * 
     * @param idPrestamo ID del préstamo a autorizar
     * @param nombreBibliotecario Nombre del bibliotecario que autoriza (opcional, si es null usa la sesión)
     */
    @Override
    public void autorizarPrestamo(long idPrestamo, String nombreBibliotecario) {
        // Si no se proporciona nombre, usar el de la sesión
        String nombreBibliotecarioFinal = nombreBibliotecario;
        
        if (nombreBibliotecarioFinal == null || nombreBibliotecarioFinal.trim().isEmpty()) {
            // Intentar obtener el nombre de la sesión
            if (SesionUsuario.getInstancia().esBibliotecario()) {
                nombreBibliotecarioFinal = SesionUsuario.getInstancia().getNombreUsuario();
            } else {
                throw new IllegalArgumentException("El nombre del bibliotecario es requerido para autorizar un préstamo. No hay un bibliotecario logueado.");
            }
        }
        
        // Validar que el nombre del bibliotecario no sea vacío
        if (nombreBibliotecarioFinal == null || nombreBibliotecarioFinal.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del bibliotecario es requerido para autorizar un préstamo");
        }
        
        jakarta.persistence.EntityManager em = persistencia.Conexion.getInstancia().getEntityManager();
        try {
            em.getTransaction().begin();
            
            Prestamo p = em.find(Prestamo.class, idPrestamo);
            
            if (p == null) {
                throw new IllegalArgumentException("Préstamo no encontrado con ID: " + idPrestamo);
            }
            
            if (p.getEstado() != EstadoPmo.PENDIENTE) {
                throw new IllegalArgumentException("Solo se pueden autorizar préstamos en estado PENDIENTE. Estado actual: " + p.getEstado());
            }
            
            Bibliotecario b = em.find(Bibliotecario.class, nombreBibliotecarioFinal.trim());
            
            if (b == null) {
                throw new IllegalArgumentException("Bibliotecario no encontrado: '" + nombreBibliotecarioFinal + 
                    "'. Verifique que el bibliotecario exista en la base de datos.");
            }
            
            // Asignar el bibliotecario al préstamo (forma la asociación)
            p.setBibliotecario(b);
            
            // Cambiar estado a EN_CURSO
            p.setEstado(EstadoPmo.EN_CURSO);
            
            // Actualizar en la base de datos
            em.merge(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Rechaza un préstamo pendiente.
     * El estado cambia a RECHAZADO.
     * Se puede asignar un bibliotecario si se desea registrar quién lo rechazó.
     * 
     * @param idPrestamo ID del préstamo a rechazar
     * @param nombreBibliotecario Nombre del bibliotecario que rechaza (opcional, puede ser null)
     */
    @Override
    public void rechazarPrestamo(long idPrestamo, String nombreBibliotecario) {
        jakarta.persistence.EntityManager em = persistencia.Conexion.getInstancia().getEntityManager();
        try {
            em.getTransaction().begin();
            
            Prestamo p = em.find(Prestamo.class, idPrestamo);
            
            if (p == null) {
                throw new IllegalArgumentException("Préstamo no encontrado");
            }
            
            if (p.getEstado() != EstadoPmo.PENDIENTE) {
                throw new IllegalArgumentException("Solo se pueden rechazar préstamos en estado PENDIENTE");
            }
            
            // Opcional: asignar el bibliotecario que rechazó
            if (nombreBibliotecario != null && !nombreBibliotecario.isEmpty()) {
                Bibliotecario b = em.find(Bibliotecario.class, nombreBibliotecario);
                
                if (b != null) {
                    p.setBibliotecario(b);
                }
            }
            
            // Cambiar estado a RECHAZADO
            p.setEstado(EstadoPmo.RECHAZADO);
            
            // Actualizar en la base de datos
            em.merge(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Lista todos los préstamos pendientes de autorización
     */
    @Override
    public List<DtPrestamo> listarPrestamosPendientes() {
        List<Prestamo> prestamos = ManejadorPrestamo.getInstancia().listarPrestamosPendientes();
        List<DtPrestamo> resultado = new ArrayList<>();
        
        for (Prestamo p : prestamos) {
            resultado.add(p.obtenerDt());
        }
        
        return resultado;
    }
}

