package logica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import persistencia.Conexion;

public class ManejadorMaterial {

    private static ManejadorMaterial instancia = null;

    public static ManejadorMaterial getInstancia(){
        if(instancia == null){
            instancia = new ManejadorMaterial();
        }
        return instancia;
    }

    public void agregarMaterial(Material material){
         Conexion conexion = Conexion.getInstancia();
         EntityManager em = conexion.getEntityManager();

         em.getTransaction().begin();
         em.persist(material);
         em.getTransaction().commit();
    }

}
