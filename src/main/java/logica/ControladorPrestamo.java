package logica;

import excepciones.PrestamoYaExisteExcepcion;
import interfaces.IControladorPrestamo;
import datatypes.*;
import java.util.List;

public class ControladorPrestamo implements IControladorPrestamo{

    public ControladorPrestamo() {
        super();
    }

    @Override
    public void altaPrestamo(DtPrestamo dtPrestamo) throws PrestamoYaExisteExcepcion {

        Bibliotecario b = ManejadorBibliotecario.getInstance().buscarBibliotecario(dtPrestamo.getBibliotecario().getNombre());
        Lector l = ManejadorLector.getInstance().buscarLector(dtPrestamo.getLector().getNombre());
        Material m = ManejadorMaterial.getInstancia().buscarMaterial(dtPrestamo.getMaterial().getId());

        if(b != null && l != null && m != null){
            Prestamo p = new Prestamo();
            p.setBibliotecario(b);
            p.setLector(l);
            p.setMaterial(m);
            p.setFechaSolicitud(dtPrestamo.getFechaSolicitud());
            p.setFechaDevolucion(dtPrestamo.getFechaDevolucion());
            p.setEstado(dtPrestamo.getEstado());

            if (!ManejadorPrestamo.getInstancia().existePrestamoActivo(p) || p.getEstado() == EstadoPmo.PENDIENTE) {
                ManejadorPrestamo.getInstancia().agregarPrestamo(p);
            }else{
                throw new PrestamoYaExisteExcepcion("Error: Este material esta en un prestamo en curso");
            }
        }else{
            //tirar excepcion
        }

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
