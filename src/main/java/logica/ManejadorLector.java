package logica;

import jakarta.persistence.EntityManager;
import persistencia.Conexion;
import java.util.List;

public class ManejadorLector {
    private static ManejadorLector instance = null;

    private ManejadorLector() {}

    public static ManejadorLector getInstance() {
        if (instance == null) {
            instance = new ManejadorLector();
        }
        return instance;
    }

    public void agregarLector(Lector lector) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(lector);
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

    public Lector buscarLector(String nombre) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            return em.find(Lector.class, nombre);
        } finally {
            em.close();
        }
    }

    public void actualizarLector(Lector lector) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(lector);
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

    public List<String> listarNombresLectores() {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            List<Lector> lectores = em.createQuery("SELECT l FROM Lector l", Lector.class).getResultList();
            return lectores.stream()
                    .map(Lector::getNombre)
                    .collect(java.util.stream.Collectors.toList());
        } finally {
            em.close();
        }
    }
}
