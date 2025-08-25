package logica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import persistencia.Conexion;

public class ManejadorMaterial {

    private static ManejadorMaterial instancia = null;

    public static ManejadorMaterial getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorMaterial();
        }
        return instancia;
    }

    public void agregarMaterial(Material material) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(material);
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

    public Material buscarMaterial(int id) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            return em.find(Material.class, id);
        } finally {
            em.close();
        }
    }
}
