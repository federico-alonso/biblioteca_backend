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
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        em.getTransaction().begin();
        em.persist(material);
        em.getTransaction().commit();
        conexion.close();
    }

    public Material buscarMaterial(int id) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        em.getTransaction().begin();
        Material material = em.find(Material.class, id);
        em.getTransaction().commit();
        em.close();

        return material;
    }
}
