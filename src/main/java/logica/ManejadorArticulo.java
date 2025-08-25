package logica;

import jakarta.persistence.EntityManager;
import persistencia.Conexion;

public class ManejadorArticulo {

    private static ManejadorArticulo instancia = null;

    private ManejadorArticulo() {}

    public static ManejadorArticulo getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorArticulo();
        }
        return instancia;
    }

    public void agregarArticulo(Articulo articulo) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(articulo);
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

    public Articulo buscarArticulo(int id) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            return em.find(Articulo.class, id);
        } finally {
            em.close();
        }
    }

    public void actualizarArticulo(Articulo articulo) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(articulo);
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
}
