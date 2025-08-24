package logica;

import jakarta.persistence.EntityManager;
import persistencia.Conexion;

public class ManejadorBibliotecario {
    private static ManejadorBibliotecario instance = null;
    private ManejadorBibliotecario(){}

    public static ManejadorBibliotecario getInstance(){
        if(instance == null){
            instance = new ManejadorBibliotecario();
        }
        return instance;
    }

    public void agregarBibliotecario(Bibliotecario bibliotecario){
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(bibliotecario);
            em.getTransaction().commit();
        }catch(Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }finally{
            em.close();
        }
    }
    public Bibliotecario buscarBibliotecario(String nombre){
        EntityManager em = Conexion.getInstancia().getEntityManager();
        try{
            return em.find(Bibliotecario.class, nombre);
        }finally{
            em.close();
        }
    }
}
