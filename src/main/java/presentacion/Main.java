package presentacion;


import interfaces.IControladorAltaLector;
import interfaces.IControladorMaterial;
import datatypes.DtLibro;
import logica.ControladorMaterial;
import interfaces.Fabrica;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Fabrica fabrica = Fabrica.getInstancia();
        IControladorMaterial controlador = fabrica.getIControladorMaterial();

        DtLibro  dtLibro = new DtLibro();

        dtLibro.setTitulo("titulo prueba");
        dtLibro.setCantidadPag(200);
        dtLibro.setFechaIngreso(new Date());

        controlador.altaDonacionLibro(dtLibro);

        System.out.println("Lector creado y persistido.");


    }
}
