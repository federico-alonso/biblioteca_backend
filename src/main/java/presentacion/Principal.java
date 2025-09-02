package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import interfaces.Fabrica;
import interfaces.IControladorAltaLector;
import interfaces.IControladorAltaBibliotecario;
import interfaces.IControladorModificarEstadoLector;
import interfaces.IControladorMaterial;
import interfaces.IControladorModificarZonaLector;
import interfaces.IControladorConsultarDonacion;
import interfaces.IControladorPrestamo;
import interfaces.IControladorModificarEstadoPrestamo;

import interfaces.*;

public class Principal {
    private JFrame frame;

    private AltaLector agregarLectorInternalFrame;
    private AltaBibliotecario agregarBibliotecarioInternalFrame;
    private EstadoLectorFrame estadoLectorInternalFrame;

    private ModificarZonaLectorFrame modificarZonaInternalFrame;
    private AltaDonacionEspecial altaDonacionEspecialInternalFrame;
    private AltaDonacionLibro altaDonacionLibroInternalFrame;
    private AltaPrestamo altaPrestamoInternalFrame;
    private ConsultarDonacion consultarDonacionInternalFrame;
    private ConsultaDonacionYFechaFrame consultaDonacionYFechaInternalFrame;
    private ListarPrestamosFrame listarPrestamosInternalFrame;
    private ConsultarPrestamosBibliotecario listarPrestamosBibliotecarioInternalFrame;
    private ModificarEstadoPrestamoFrame modificarEstadoPrestamoInternalFrame;
    private ModificarTodoPrestamo modificarTodoPrestamoInternalFrame;
    private ConsultarZonaLectorFrame consultarZonaLectorInternalFrame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Principal window = new Principal();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public  Principal() {
        initialize();

        Fabrica fabrica = Fabrica.getInstancia();
        IControladorAltaLector controladorAlta = fabrica.getIControladorAltaLector();
        IControladorAltaBibliotecario controladorBibliotecario = fabrica.getIControladorAltaBibliotecario();
        IControladorModificarEstadoLector controladorModificarEstado = fabrica.getIControladorModificarEstadoLector();
        IControladorMaterial controladorMaterial = fabrica.getIControladorMaterial();
        IControladorConsultarDonacion controladorConsultarDonacion = fabrica.getIControladorConsultarDonacion();
        IControladorModificarZonaLector controladorModificarZona = fabrica.getIControladorModificarZonaLector();
        IControladorPrestamo controladorPrestamo = fabrica.getIControladorPrestamo();
        IControladorModificarEstadoPrestamo controladorModificarEstadoPrestamo = fabrica.getIControladorModificarEstadoPrestamo();
        IControladorConsultaDonacionYFecha controladorConsultaDonacionYFecha = fabrica.getIControladorConsultaDonacionYFecha();
        IControladorModificarTodoPrestamo controladorModificarTodoPrestamo = fabrica.getIControladorModificarTodoPrestamo();
        IControladorListarPrestamosZona controladorListarZona = fabrica.getIControladorListarPrestamosZona();


        Dimension desktopSize = frame.getSize();

        agregarLectorInternalFrame = new AltaLector(controladorAlta, this);
        agregarLectorInternalFrame.setLocation(
                (desktopSize.width - agregarLectorInternalFrame.getSize().width) / 2,
                (desktopSize.height - agregarLectorInternalFrame.getSize().height) / 2
        );
        agregarLectorInternalFrame.setVisible(false);
        frame.getContentPane().add(agregarLectorInternalFrame);

        agregarBibliotecarioInternalFrame = new AltaBibliotecario(controladorBibliotecario, this);
        agregarBibliotecarioInternalFrame.setLocation(
                (desktopSize.width - agregarBibliotecarioInternalFrame.getSize().width) / 2,
                (desktopSize.height - agregarBibliotecarioInternalFrame.getSize().height) / 2
        );
        agregarBibliotecarioInternalFrame.setVisible(false);
        frame.getContentPane().add(agregarBibliotecarioInternalFrame);


        estadoLectorInternalFrame = new EstadoLectorFrame(controladorModificarEstado, this);

        estadoLectorInternalFrame.setLocation(
                (desktopSize.width - estadoLectorInternalFrame.getSize().width) / 2,
                (desktopSize.height - estadoLectorInternalFrame.getSize().height) / 2
        );
        estadoLectorInternalFrame.setVisible(false);
        frame.getContentPane().add(estadoLectorInternalFrame);

        modificarZonaInternalFrame = new ModificarZonaLectorFrame(controladorModificarZona, this);
        modificarZonaInternalFrame.setLocation(
                (desktopSize.width - modificarZonaInternalFrame.getSize().width) / 2,
                (desktopSize.height - modificarZonaInternalFrame.getSize().height) / 2
        );
        modificarZonaInternalFrame.setVisible(false);
        frame.getContentPane().add(modificarZonaInternalFrame);

        altaDonacionEspecialInternalFrame = new AltaDonacionEspecial(controladorMaterial, this);
        altaDonacionEspecialInternalFrame.setLocation(
                (desktopSize.width - altaDonacionEspecialInternalFrame.getSize().width) / 2,
                (desktopSize.height - altaDonacionEspecialInternalFrame.getSize().height) / 2
        );
        altaDonacionEspecialInternalFrame.setVisible(false);
        frame.getContentPane().add(altaDonacionEspecialInternalFrame);

        altaDonacionLibroInternalFrame = new AltaDonacionLibro(controladorMaterial, this);
        altaDonacionLibroInternalFrame.setLocation(
                (desktopSize.width - altaDonacionLibroInternalFrame.getSize().width) / 2,
                (desktopSize.height - altaDonacionLibroInternalFrame.getSize().height) / 2
        );
        altaDonacionLibroInternalFrame.setVisible(false);
        frame.getContentPane().add(altaDonacionLibroInternalFrame);

        consultarDonacionInternalFrame = new ConsultarDonacion(controladorConsultarDonacion, this);
        consultarDonacionInternalFrame.setLocation(
                (desktopSize.width - consultarDonacionInternalFrame.getSize().width) / 2,
                (desktopSize.height - consultarDonacionInternalFrame.getSize().height) / 2
        );
        consultarDonacionInternalFrame.setVisible(false);
        frame.getContentPane().add(consultarDonacionInternalFrame);

        altaPrestamoInternalFrame = new AltaPrestamo(controladorPrestamo);
        altaPrestamoInternalFrame.setLocation(
                (desktopSize.width - altaPrestamoInternalFrame.getSize().width) / 2,
                (desktopSize.height - altaPrestamoInternalFrame.getSize().height) / 2
        );
        altaPrestamoInternalFrame.setVisible(false);
        frame.getContentPane().add(altaPrestamoInternalFrame);

        listarPrestamosInternalFrame = new ListarPrestamosFrame(controladorPrestamo);
        listarPrestamosInternalFrame.setLocation(
                (desktopSize.width - listarPrestamosInternalFrame.getSize().width) / 2,
                (desktopSize.height - listarPrestamosInternalFrame.getSize().height) / 2
        );
        listarPrestamosInternalFrame.setVisible(false);
        frame.getContentPane().add(listarPrestamosInternalFrame);

        listarPrestamosBibliotecarioInternalFrame = new ConsultarPrestamosBibliotecario(controladorPrestamo, this);
        listarPrestamosBibliotecarioInternalFrame.setLocation(
                (desktopSize.width - listarPrestamosBibliotecarioInternalFrame.getSize().width) / 2,
                (desktopSize.height - listarPrestamosBibliotecarioInternalFrame.getSize().height) / 2
        );
        listarPrestamosBibliotecarioInternalFrame.setVisible(false);
        frame.getContentPane().add(listarPrestamosBibliotecarioInternalFrame);

        modificarEstadoPrestamoInternalFrame = new ModificarEstadoPrestamoFrame(controladorModificarEstadoPrestamo);
        modificarEstadoPrestamoInternalFrame.setLocation(
            (desktopSize.width - modificarEstadoPrestamoInternalFrame.getSize().width) / 2,
            (desktopSize.height - modificarEstadoPrestamoInternalFrame.getSize().height) / 2
        );
        modificarEstadoPrestamoInternalFrame.setVisible(false);
        frame.getContentPane().add(modificarEstadoPrestamoInternalFrame);

        consultaDonacionYFechaInternalFrame = new ConsultaDonacionYFechaFrame(controladorConsultaDonacionYFecha);
        consultaDonacionYFechaInternalFrame.setLocation(
                (desktopSize.width - consultaDonacionYFechaInternalFrame.getSize().width) / 2,
                (desktopSize.height - consultaDonacionYFechaInternalFrame.getSize().height) / 2
        );
        consultaDonacionYFechaInternalFrame.setVisible(false);
        frame.getContentPane().add(consultaDonacionYFechaInternalFrame);

        modificarTodoPrestamoInternalFrame = new ModificarTodoPrestamo(controladorModificarTodoPrestamo);
        modificarTodoPrestamoInternalFrame.setLocation(
                (desktopSize.width - modificarTodoPrestamoInternalFrame.getSize().width) / 2,
                (desktopSize.height - modificarTodoPrestamoInternalFrame.getSize().height) / 2
        );
        modificarTodoPrestamoInternalFrame.setVisible(false);
        frame.getContentPane().add(modificarTodoPrestamoInternalFrame);

        consultarZonaLectorInternalFrame = new ConsultarZonaLectorFrame(controladorListarZona);
        consultarZonaLectorInternalFrame.setLocation(
                (desktopSize.width - consultarZonaLectorInternalFrame.getSize().width) / 2,
                (desktopSize.height - consultarZonaLectorInternalFrame.getSize().height) / 2
        );
        consultarZonaLectorInternalFrame.setVisible(false);
        frame.getContentPane().add(consultarZonaLectorInternalFrame);

    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(200, 200, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnLectores = new JMenu("Lectores");
        menuBar.add(mnLectores);

        JMenuItem mntmAgregarLector = new JMenuItem("Agregar lector");
        mntmAgregarLector.addActionListener(e -> agregarLectorInternalFrame.setVisible(true));
        mnLectores.add(mntmAgregarLector);

        JMenuItem mntmBibliotecario = new JMenuItem("Agregar bibliotecario");
        mntmBibliotecario.addActionListener(e -> agregarBibliotecarioInternalFrame.setVisible(true));
        mnLectores.add(mntmBibliotecario);

        JMenuItem mntmCambiarEstado = new JMenuItem("Cambiar estado de lector");
        mntmCambiarEstado.addActionListener(e -> {
            estadoLectorInternalFrame.limpiarFormulario();
            estadoLectorInternalFrame.cargarNombresLectores();
            estadoLectorInternalFrame.setVisible(true);
        });
        mnLectores.add(mntmCambiarEstado);

        JMenuItem mntmCambiarZona = new JMenuItem("Cambiar zona de lector");
        mntmCambiarZona.addActionListener(e -> modificarZonaInternalFrame.setVisible(true));
        mnLectores.add(mntmCambiarZona);

        JMenu mnDonaciones = new JMenu("Donaciones");
        menuBar.add(mnDonaciones);

        JMenuItem mntmAltaDonacionLibro = new JMenuItem("Registrar Libro");
        mntmAltaDonacionLibro.addActionListener(e -> altaDonacionLibroInternalFrame.setVisible(true));
        mnDonaciones.add(mntmAltaDonacionLibro);

        JMenuItem mntmAltaDonacionEspecial = new JMenuItem("Registrar Articulo Especial");
        mntmAltaDonacionEspecial.addActionListener(e -> altaDonacionEspecialInternalFrame.setVisible(true));
        mnDonaciones.add(mntmAltaDonacionEspecial);

        JMenuItem mntmConsultarDonacion = new JMenuItem("Consultar donaciones");
        mntmConsultarDonacion.addActionListener(e -> consultarDonacionInternalFrame.setVisible(true));
        mnDonaciones.add(mntmConsultarDonacion);

        JMenuItem mntmConsultarDonacionYFecha = new JMenuItem("Consultar donaciones por fecha");
        mntmConsultarDonacionYFecha.addActionListener(e -> consultaDonacionYFechaInternalFrame.setVisible(true));
        mnDonaciones.add(mntmConsultarDonacionYFecha);

        JMenu mnPrestamos = new JMenu("Prestamos");
        menuBar.add(mnPrestamos);

        JMenuItem mntmResumenPorZona = new JMenuItem("Resumen por zona");
        mntmResumenPorZona.addActionListener(e -> consultarZonaLectorInternalFrame.setVisible(true));
        mnPrestamos.add(mntmResumenPorZona);

        JMenuItem mntmAltaPrestamo = new JMenuItem("Registrar prestamo");
        mntmAltaPrestamo.addActionListener(e -> altaPrestamoInternalFrame.setVisible(true));
        mnPrestamos.add(mntmAltaPrestamo);

        JMenuItem mntmListarPrestamos = new JMenuItem("Listar préstamos por lector");
        mntmListarPrestamos.addActionListener(e -> listarPrestamosInternalFrame.setVisible(true));
        mnPrestamos.add(mntmListarPrestamos);

        JMenuItem mntmListarPrestamosBibliotecario = new JMenuItem("Listar préstamos por bibliotecario");
        mntmListarPrestamosBibliotecario.addActionListener(e -> listarPrestamosBibliotecarioInternalFrame.setVisible(true));
        mnPrestamos.add(mntmListarPrestamosBibliotecario);

        JMenuItem mntmModificarEstado = new JMenuItem("Modificar Estado de Préstamo");
        mntmModificarEstado.addActionListener(e -> {
            modificarEstadoPrestamoInternalFrame.setVisible(true);
        });
        mnPrestamos.add(mntmModificarEstado);

        JMenuItem mntmModificarPrestamo = new JMenuItem("Modificar préstamo");
        mntmModificarPrestamo.addActionListener(e -> modificarTodoPrestamoInternalFrame.setVisible(true));
        mnPrestamos.add(mntmModificarPrestamo);
    }


    public void actualizarInternalFrames(){
        altaPrestamoInternalFrame.cargarDatos();
        modificarZonaInternalFrame.cargarNombresLectores();
        estadoLectorInternalFrame.cargarNombresLectores();
        listarPrestamosBibliotecarioInternalFrame.cargarDatos();
        consultaDonacionYFechaInternalFrame.limpiarFormulario();
        modificarTodoPrestamoInternalFrame.cargarDatos();
    }

}
