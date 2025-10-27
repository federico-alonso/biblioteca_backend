package interfaces;

import datatypes.DtPrestamo;
import java.util.List;

/**
 * Interfaz para el controlador de autorización de préstamos
 */
public interface IControladorAutorizarPrestamo {
    
    /**
     * Autoriza un préstamo pendiente
     * @param idPrestamo ID del préstamo
     * @param nombreBibliotecario Nombre del bibliotecario que autoriza
     */
    void autorizarPrestamo(long idPrestamo, String nombreBibliotecario);
    
    /**
     * Rechaza un préstamo pendiente
     * @param idPrestamo ID del préstamo
     * @param nombreBibliotecario Nombre del bibliotecario que rechaza (opcional)
     */
    void rechazarPrestamo(long idPrestamo, String nombreBibliotecario);
    
    /**
     * Lista todos los préstamos pendientes
     */
    List<DtPrestamo> listarPrestamosPendientes();
}

