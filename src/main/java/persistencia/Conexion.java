package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Conexion {
    private static Conexion instancia = null;
    private static EntityManagerFactory emf;

    private Conexion() {
        emf = Persistence.createEntityManagerFactory("bdpapPU"); // Match your persistence.xml
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager(); // Always return a fresh one
    }

    public void close() {
        emf.close();
    }
}
