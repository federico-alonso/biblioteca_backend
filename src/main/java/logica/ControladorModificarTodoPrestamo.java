package logica;

import datatypes.DtBibliotecario;
import datatypes.DtLector;
import datatypes.DtMaterial;
import datatypes.DtPrestamo;
import excepciones.PrestamoNoExisteExcepcion;
import interfaces.IControladorModificarTodoPrestamo;
import java.util.ArrayList;
import java.util.List;


public class ControladorModificarTodoPrestamo implements IControladorModificarTodoPrestamo{
    public ControladorModificarTodoPrestamo() {
        super();
    }

    @Override
    public List<DtPrestamo> listarPrestamos() {
        ManejadorPrestamo manejadorPrestamo = ManejadorPrestamo.getInstancia();
        List<Prestamo> prestamos = manejadorPrestamo.listarPrestamos();
        List<DtPrestamo> prestamosDT = new ArrayList<>();
        for (Prestamo p : prestamos) {
            prestamosDT.add(p.obtenerDt());
        }
        return prestamosDT;
    }

    @Override
    public void modificarPrestamo(DtPrestamo prestamo) throws PrestamoNoExisteExcepcion {
        ManejadorPrestamo manejadorPrestamo = ManejadorPrestamo.getInstancia();
        Prestamo prestamoEntidad = manejadorPrestamo.buscarPrestamo(prestamo.getId());

        if (prestamoEntidad == null) {
            throw new PrestamoNoExisteExcepcion("El préstamo con el id " + prestamo.getId() + " no existe");
        }
        
        ManejadorLector manejadorLector = ManejadorLector.getInstance();
        Lector lector = manejadorLector.buscarLector(prestamo.getLector().getNombre());
        if (lector == null) {
            // Aquí podrías lanzar una excepción LectorNoExisteExcepcion si la tienes definida
            throw new PrestamoNoExisteExcepcion("El lector con nombre " + prestamo.getLector().getNombre() + " no existe");
        }

        ManejadorBibliotecario manejadorBibliotecario = ManejadorBibliotecario.getInstance();
        Bibliotecario bibliotecario = manejadorBibliotecario.buscarBibliotecario(prestamo.getBibliotecario().getNombre());
        if (bibliotecario == null) {
            // Aquí podrías lanzar una excepción BibliotecarioNoExisteExcepcion si la tienes definida
            throw new PrestamoNoExisteExcepcion("El bibliotecario con nombre " + prestamo.getBibliotecario().getNombre() + " no existe");
        }

        ManejadorMaterial manejadorMaterial = ManejadorMaterial.getInstancia();
        Material material = manejadorMaterial.buscarMaterial(prestamo.getMaterial().getId());
        if (material == null) {
            // Aquí podrías lanzar una excepción MaterialNoExisteExcepcion si la tienes definida
            throw new PrestamoNoExisteExcepcion("El material con id " + prestamo.getMaterial().getId() + " no existe");
        }
        
        prestamoEntidad.setLector(lector);
        prestamoEntidad.setBibliotecario(bibliotecario);
        prestamoEntidad.setMaterial(material);
        prestamoEntidad.setFechaSolicitud(prestamo.getFechaSolicitud());
        prestamoEntidad.setFechaDevolucion(prestamo.getFechaDevolucion());
        prestamoEntidad.setEstado(prestamo.getEstado());

        manejadorPrestamo.actualizarPrestamo(prestamoEntidad);
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
