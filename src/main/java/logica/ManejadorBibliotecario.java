package logica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import persistencia.Conexion;
import datatypes.DtBibliotecario;

import java.util.ArrayList;
import java.util.List;

public class ManejadorBibliotecario {
    private static ManejadorBibliotecario instance = null;

    private ManejadorBibliotecario() {}

    public static ManejadorBibliotecario getInstance() {
        if (instance == null) {
            instance = new ManejadorBibliotecario();
        }
        return instance;
    }

    public void agregarBibliotecario(Bibliotecario bibliotecario) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(bibliotecario);
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

    public Bibliotecario buscarBibliotecario(String nombre) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            return em.find(Bibliotecario.class, nombre);
        } finally {
            em.close();
        }
    }

    public List<DtBibliotecario> getBibliotecarios() {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        List<DtBibliotecario> dtBibliotecarios = new ArrayList<>();

        try {
            // Query para traer todos los bibliotecarios
            TypedQuery<Bibliotecario> query = em.createQuery("SELECT b FROM Bibliotecario b", Bibliotecario.class);
            List<Bibliotecario> bibliotecarios = query.getResultList();

            // Convertir a DTO y agregar a la lista
            for (Bibliotecario b : bibliotecarios) {
                dtBibliotecarios.add(new DtBibliotecario(b.getNombre(),b.getEmail(),b.getNumero()));
            }

            return dtBibliotecarios;

        } finally {
            em.close();
        }
    }

}
