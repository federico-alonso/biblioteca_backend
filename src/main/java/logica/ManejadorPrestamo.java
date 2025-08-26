package logica;

import persistencia.Conexion;

import datatypes.DtPrestamo;
import jakarta.persistence.*;
import java.util.List;

public class ManejadorPrestamo {

    private static ManejadorPrestamo instancia = null;

    public static ManejadorPrestamo getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorPrestamo();
        }
        return instancia;
    }

    public void agregarPrestamo(Prestamo prestamo){
        EntityManager em = Conexion.getInstancia().getEntityManager();

        em.getTransaction().begin();
        em.persist(prestamo);
        em.getTransaction().commit();

        em.close();
    }

    public boolean existePrestamoActivo(Prestamo prestamo){
    //Busca un prestamo activo

        EntityManager em = Conexion.getInstancia().getEntityManager();

        List<Prestamo> p = em.createQuery("SELECT p FROM Prestamo p " +
                        "WHERE p.material.id = :idMaterial AND " +
                        "p.estado = 'ACTIVO'", Prestamo.class)
                .setParameter("idMaterial", prestamo.getMaterial().getId())
                .getResultList();

        em.close();
        return (!p.isEmpty());
    }


}
