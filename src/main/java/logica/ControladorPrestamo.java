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
import datatypes.DtMaterialConPrestamo;
import java.util.Map;
import java.util.HashMap;
import excepciones.BibliotecarioNoTienePrestamos;

public class ControladorPrestamo implements IControladorPrestamo {

    public ControladorPrestamo() {
        super();
    }

    @Override
    public void altaPrestamo(DtPrestamo dtPrestamo) throws PrestamoYaExisteExcepcion {
        Bibliotecario b = null;
        if (dtPrestamo.getBibliotecario() != null) {
            b = ManejadorBibliotecario.getInstance()
                    .buscarBibliotecario(dtPrestamo.getBibliotecario().getNombre());
        }
    
        Lector l = ManejadorLector.getInstance()
                .buscarLector(dtPrestamo.getLector().getNombre());
    
        Material m = ManejadorMaterial.getInstancia()
                .buscarMaterial(dtPrestamo.getMaterial().getId());
    
        if (l == null || m == null) {
            throw new IllegalArgumentException("Lector o material no encontrados");
        }
    
        // ✅ Solo conservar fechaDevolucion si el estado es DEVUELTO
        Date fechaDevolucion = dtPrestamo.getEstado() == EstadoPmo.DEVUELTO
                ? dtPrestamo.getFechaDevolucion()
                : null;
    
        Prestamo p = new Prestamo(
                dtPrestamo.getFechaSolicitud(),
                fechaDevolucion,
                m,
                b, // puede ser null
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
    }
    
    



    @Override
    public List<DtPrestamoSimple> getPrestamosActivosPorLector(DtLector lector) {
        List<Prestamo> prestamos = ManejadorPrestamo.getInstancia()
                .obtenerPrestamosActivosPorLector(lector.getNombre());

        List<DtPrestamoSimple> resultado = new ArrayList<>();

        for (Prestamo p : prestamos) {
            DtPrestamoSimple dto = new DtPrestamoSimple(
                p.getLector().getNombre(),
                p.getFechaSolicitud(),
                p.getFechaDevolucion(),
                p.getEstado()
            );

            resultado.add(dto);
        }

        return resultado;
    }

    @Override
    public List<String> getNombresLectores() {
        List<String> nombres = new ArrayList<>();
        for (DtLector lector : this.getListadoLectores()) { 
            nombres.add(lector.getNombre());
        }
        return nombres;
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
    public List<Object[]> consultarPrestamosComunes(){
        EntityManager em = Conexion.getInstancia().getEntityManager();

        List<Object[]> materiales = em.createQuery("SELECT p.material.id, count(p) FROM Prestamo p " +
                "WHERE p.estado = 'PENDIENTE'" +
                "GROUP BY p.material.id " +
                "ORDER BY count(p) desc", Object[].class).getResultList();

        for(Object[] fila : materiales) {
            fila[0] =  ManejadorMaterial.getInstancia().buscarMaterial((Long)fila[0]).obtenerDt();
        }
        return materiales;
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

    @Override
    public List<DtMaterialConPrestamo> getMaterialesConPrestamo(DtLector lector) {
        List<Prestamo> todos = ManejadorPrestamo.getInstancia().listarPrestamos();
    
        List<DtMaterialConPrestamo> resultado = new ArrayList<>();
    
        for (Prestamo p : todos) {
            if (p.getLector() != null && p.getLector().getNombre().equals(lector.getNombre())) {
                DtMaterial mat = p.getMaterial().obtenerDt();
                DtPrestamo dto = p.obtenerDt();
                resultado.add(new DtMaterialConPrestamo(mat, dto));
            }
        }
    
        return resultado;
    }

    @Override
    public List<DtMaterialConPrestamo> getMaterialesConPrestamoTodos() {
        List<Prestamo> todos = ManejadorPrestamo.getInstancia().listarPrestamos();

        List<DtMaterialConPrestamo> resultado = new ArrayList<>();

        for (Prestamo p : todos) {
            if (p.getLector() != null) {
                DtMaterial mat = p.getMaterial().obtenerDt();
                DtPrestamo dto = p.obtenerDt();
                resultado.add(new DtMaterialConPrestamo(mat, dto));
            }
        }

    return resultado;
}

    


}
