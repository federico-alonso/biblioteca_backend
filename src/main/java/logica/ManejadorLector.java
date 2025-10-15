package logica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import persistencia.Conexion;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import datatypes.DtLector;

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

    public DtLector buscarDtLectorPorCorreo(String correo) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try {
            TypedQuery<Lector> query = em.createQuery(
                "SELECT l FROM Lector l WHERE LOWER(l.email) = LOWER(:correo)", Lector.class);
            query.setParameter("correo", correo);
            List<Lector> resultado = query.getResultList();
    
            if (resultado.isEmpty()) {
                return null;
            }
    
            Lector l = resultado.get(0);
            return new DtLector(l.getNombre(), l.getEmail(), l.getContrasena(), l.getDireccion(), l.getFechaRegistro(), l.getEstado());
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

    public List<DtLector> getLectores() {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        List<DtLector> dtLectores = new ArrayList<>();

        try {
            // Query para traer todos los lectores
            TypedQuery<Lector> query = em.createQuery("SELECT l FROM Lector l", Lector.class);
            List<Lector> lectores = query.getResultList();

            // Convertir a DTO y agregar a la lista
            for (Lector l : lectores) {
                dtLectores.add(new DtLector(l.getNombre(), l.getEmail(), l.getContrasena(), l.getDireccion(), l.getFechaRegistro(), l.getEstado()));
            }

            return dtLectores;
        } finally {
        em.close();
        }
    }
}
