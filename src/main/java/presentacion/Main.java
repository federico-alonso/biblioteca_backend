package presentacion;

import logica.ControladorPrestamo;
import logica.Lector;

import interfaces.IControladorPrestamo;
import datatypes.DtLibro;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        IControladorPrestamo controlador = new ControladorPrestamo();


        DtLibro  dtLibro = new DtLibro();

        dtLibro.setTitulo("titulo prueba");
        dtLibro.setCantidadPag(200);
        dtLibro.setFechaIngreso(new Date());

        controlador.altaDonacionLibro(dtLibro);

        System.out.println("Lector creado y persistido.");


    }
}
