package presentacion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Controlador;
import logica.Lector;
import logica.Libro;

import interfaces.IControlador;
import datatypes.DtLibro;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        IControlador controlador = new Controlador();


        DtLibro  dtLibro = new DtLibro();

        dtLibro.setTitulo("titulo prueba");
        dtLibro.setCantidadPag(200);
        dtLibro.setFechaIngreso(new Date());

        controlador.altaDonacionLibro(dtLibro);

        System.out.println("Lector creado y persistido.");


    }
}
