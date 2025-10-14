/*  ackage persistencia;

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
}/* */

package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexion {
    private static Conexion instancia = null;
    private static EntityManagerFactory emf;

    private Conexion() {
        try {
            System.out.println("Inicializando EntityManagerFactory...");
            emf = Persistence.createEntityManagerFactory("bdpapPU");
            System.out.println("EntityManagerFactory creado: " + emf);
        } catch (Exception e) {
            System.err.println("Error al crear EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public EntityManager getEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory no fue inicializado.");
        }
        return emf.createEntityManager();
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
