package logica;

import persistencia.Conexion;
import datatypes.EstadoPmo;
import datatypes.DtPrestamo;

import jakarta.persistence.*;
import java.util.List;

public class ManejadorPrestamo {

    private static ManejadorPrestamo instancia = null;

    public static ManejadorPrestamo getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorPrestamo();
        }
        return instancia;
    }

    public void agregarPrestamo(Prestamo prestamo) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        em.getTransaction().begin();
        em.persist(prestamo);
        em.getTransaction().commit();
        em.close();
    }

    public boolean existePrestamoActivo(Prestamo prestamo) {
        EntityManager em = Conexion.getInstancia().getEntityManager();

        List<Prestamo> p = em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.material.id = :idMaterial AND p.estado = 'EN_CURSO'",
                Prestamo.class
        ).setParameter("idMaterial", prestamo.getMaterial().getId())
         .getResultList();

        em.close();
        return (!p.isEmpty());
    }

    /**
     * Verifica si ya existe un préstamo pendiente para el material
     */
    public boolean existePrestamoPendiente(long idMaterial) {
        EntityManager em = Conexion.getInstancia().getEntityManager();

        List<Prestamo> p = em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.material.id = :idMaterial AND p.estado = 'PENDIENTE'",
                Prestamo.class
        ).setParameter("idMaterial", idMaterial)
         .getResultList();

        em.close();
        return (!p.isEmpty());
    }

    public List<Prestamo> obtenerPrestamosActivosPorLector(String lectorNombre) {
        EntityManager em = Conexion.getInstancia().getEntityManager();

        TypedQuery<Prestamo> query = em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.lector.nombre = :nombre AND p.estado = :estado",
                Prestamo.class
        );
        query.setParameter("nombre", lectorNombre);
        query.setParameter("estado", EstadoPmo.EN_CURSO);

        List<Prestamo> prestamos = query.getResultList();
        em.close();
        return prestamos;
    }

    public void modificarEstadoPrestamo(long idPrestamo, EstadoPmo nuevoEstado) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            em.getTransaction().begin();
            Prestamo prestamo = em.find(Prestamo.class, idPrestamo);
            if (prestamo != null) {
                prestamo.setEstado(nuevoEstado);
                em.merge(prestamo);
                System.out.println("Estado del préstamo " + idPrestamo + " cambiado a " + nuevoEstado);
            }
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

    public Prestamo buscarPrestamo(long id) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        Prestamo prestamo = em.find(Prestamo.class, id);
        em.close();
        return prestamo;
    }

    public void actualizarPrestamo(Prestamo prestamo) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        em.getTransaction().begin();
        em.merge(prestamo);
        em.getTransaction().commit();
        em.close();
    }

    public List<Prestamo> listarPrestamos() {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        TypedQuery<Prestamo> query = em.createQuery(
                "SELECT p FROM Prestamo p ORDER BY p.id ASC",
                Prestamo.class
        );
        List<Prestamo> prestamos = query.getResultList();
        em.close();
        return prestamos;
    }

    public List<Prestamo> listarPrestamosPendientes() {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        TypedQuery<Prestamo> query = em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.estado = :estado ORDER BY p.fechaSolicitud ASC",
                Prestamo.class
        );
        query.setParameter("estado", EstadoPmo.PENDIENTE);
        List<Prestamo> prestamos = query.getResultList();
        em.close();
        return prestamos;
    }
}
