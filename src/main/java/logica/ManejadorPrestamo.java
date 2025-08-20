package logica;

import jakarta.persistence.EntityManager;
import persistencia.Conexion;

public class ManejadorPrestamo{
    private static ManejadorPrestamo instancia = null;

    public static ManejadorPrestamo getInstancia(){
        if(instancia == null){
            instancia = new ManejadorPrestamo();
        }
        return instancia;
    }
    public boolean existePrestamo(int idMaterial, String emailL, String emailB){
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        Long count = em.createQuery("SELECT count(p) FROM Prestamo p WHERE p.material.id = :idMaterial and " +
                "p.lector.email = :emailL and p.bibliotecario.email = :emailB", Long.class)
                .setParameter("idMaterial", idMaterial)
                .setParameter("emailL", emailL)
                .setParameter("emailB", emailB)
                .getSingleResult();
        conexion.close();

        em.close();
        return count > 0;
    }
}
