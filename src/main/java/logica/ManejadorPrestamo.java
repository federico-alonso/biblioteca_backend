package logica;

import persistencia.Conexion;

import datatypes.DtPrestamo;
import datatypes.EstadoPmo;

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

    public void agregarPrestamo(Prestamo prestamo){
        EntityManager em = Conexion.getInstancia().getEntityManager();

        em.getTransaction().begin();
        em.persist(prestamo);
        em.getTransaction().commit();

        em.close();
    }

    public boolean existePrestamoActivo(Prestamo prestamo){
    //Busca un prestamo activo

        EntityManager em = Conexion.getInstancia().getEntityManager();

        List<Prestamo> p = em.createQuery("SELECT p FROM Prestamo p " +
                        "WHERE p.material.id = :idMaterial AND " +
                        "p.estado = 'ACTIVO'", Prestamo.class)
                .setParameter("idMaterial", prestamo.getMaterial().getId())
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
        query.setParameter("estado", EstadoPmo.ACTIVO);

        List<Prestamo> prestamos = query.getResultList();
        em.close();
        return prestamos;
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
        TypedQuery<Prestamo> query = em.createQuery("SELECT p FROM Prestamo p ORDER BY p.id ASC", Prestamo.class);
        List<Prestamo> prestamos = query.getResultList();
        em.close();
        return prestamos;
    }
}
