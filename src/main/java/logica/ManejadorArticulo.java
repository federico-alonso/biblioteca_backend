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

    public void agregarArticulo(ArticuloEspecial articuloEspecial) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(articuloEspecial);
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

    public ArticuloEspecial buscarArticulo(int id) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            return em.find(ArticuloEspecial.class, id);
        } finally {
            em.close();
        }
    }

    public void actualizarArticulo(ArticuloEspecial articuloEspecial) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(articuloEspecial);
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
