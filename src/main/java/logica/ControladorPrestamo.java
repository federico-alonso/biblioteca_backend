package logica;

import excepciones.PrestamoYaExisteExcepcion;
import interfaces.IControladorPrestamo;

import jakarta.persistence.*;

import datatypes.DtPrestamo;
import datatypes.DtPrestamoSimple;
import datatypes.EstadoPmo;
import datatypes.DtLector;
import datatypes.DtBibliotecario;
import datatypes.DtMaterial;

import logica.ManejadorPrestamo;
import logica.ManejadorBibliotecario;
import logica.ManejadorLector;
import logica.ManejadorMaterial;

import logica.Prestamo;
import logica.Material;
import logica.Lector;
import logica.Bibliotecario;
import persistencia.Conexion;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import excepciones.BibliotecarioNoTienePrestamos;

public class ControladorPrestamo implements IControladorPrestamo {

    public ControladorPrestamo() {
        super();
    }

    @Override
    public void altaPrestamo(DtPrestamo dtPrestamo) throws PrestamoYaExisteExcepcion {
        Bibliotecario b = ManejadorBibliotecario.getInstance()
                .buscarBibliotecario(dtPrestamo.getBibliotecario().getNombre());

        Lector l = ManejadorLector.getInstance()
                .buscarLector(dtPrestamo.getLector().getNombre());

        Material m = ManejadorMaterial.getInstancia()
                .buscarMaterial(dtPrestamo.getMaterial().getId());

        if (b != null && l != null && m != null) {
            Prestamo p = new Prestamo(
                    dtPrestamo.getFechaSolicitud(),
                    dtPrestamo.getFechaDevolucion(),
                    m,
                    b,
                    l,
                    dtPrestamo.getEstado()
            );

            boolean prestamoActivo = ManejadorPrestamo.getInstancia().existePrestamoActivo(p);
            boolean estadoPendiente = p.getEstado() == EstadoPmo.PENDIENTE;

            if (!prestamoActivo || estadoPendiente) {
                ManejadorPrestamo.getInstancia().agregarPrestamo(p);
            } else {
                throw new PrestamoYaExisteExcepcion("Error: Este material está en un préstamo en curso");
            }
        } else {
            // TODO: lanzar excepción por datos faltantes
        }
    }

    @Override
    public List<DtPrestamoSimple> getPrestamosActivosPorLector(String lectorNombre) {
        List<Prestamo> prestamos = ManejadorPrestamo.getInstancia()
                .obtenerPrestamosActivosPorLector(lectorNombre);

        List<DtPrestamoSimple> resultado = new ArrayList<>();

        for (Prestamo prestamo : prestamos) {
            DtPrestamoSimple dto = new DtPrestamoSimple(
                    prestamo.getLector().getNombre(),
                    prestamo.getFechaSolicitud(),
                    prestamo.getFechaDevolucion(),
                    prestamo.getEstado()
            );
            resultado.add(dto);
        }

        return resultado;
    }

    @Override
    public List<DtPrestamo> consultarPrestamosBibliotecario(DtBibliotecario bibliotecario) throws BibliotecarioNoTienePrestamos {
        EntityManager em = Conexion.getInstancia().getEntityManager();

        List <Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p " +
                        "WHERE p.bibliotecario.nombre = :nomBibliotecario", Prestamo.class)
                .setParameter("nomBibliotecario", bibliotecario.getNombre())
                .getResultList();

        List <DtPrestamo> resultado = new ArrayList<>();

        for(Prestamo p :  prestamos) {
            resultado.add(p.obtenerDt());
        }

        em.close();

        if(prestamos.size() == 0){
            throw new BibliotecarioNoTienePrestamos("El bibliotecario no gestionó ningún préstamo");
        }

        return resultado;
    }

    @Override
    public List<DtLector> getListadoLectores() {
        return ManejadorLector.getInstance().getLectores();
    }

    @Override
    public List<DtBibliotecario> getListadoBibliotecarios() {
        return ManejadorBibliotecario.getInstance().getBibliotecarios();
    }

    @Override
    public List<DtMaterial> getListadoMateriales() {
        return ManejadorMaterial.getInstancia().getMateriales();
    }
}
