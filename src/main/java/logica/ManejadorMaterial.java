package logica;

import javax.persistence.*;

import persistencia.Conexion;
import datatypes.*;
import java.util.ArrayList;
import java.util.List;


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

        em.getTransaction().begin();
        em.persist(material);
        em.getTransaction().commit();

        em.close();
    }

    public Material buscarMaterial(long id) {
        EntityManager em = Conexion.getInstancia().getEntityManager();

        Material material = em.find(Material.class, id);

        em.close();
        return material;
    }

    public List<DtMaterial> getMateriales() {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            TypedQuery<Material> query = em.createQuery("SELECT m FROM Material m", Material.class);
            List<Material> materiales = query.getResultList();
            List<DtMaterial> dtMateriales = new ArrayList<>();

            for (Material m : materiales) {
                dtMateriales.add(m.obtenerDt());
            }
            return dtMateriales;
        } finally {
            em.close();
        }
    }

}
