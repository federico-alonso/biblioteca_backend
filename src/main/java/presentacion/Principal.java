package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

import interfaces.Fabrica;
import interfaces.IControladorAltaLector;
import interfaces.IControladorAltaBibliotecario;
import interfaces.IControladorModificarEstadoLector;
import interfaces.IControladorAltaDonacionLibro;
import interfaces.IControladorAltaDonacionEspecial;
import interfaces.IControladorModificarZonaLector;
import interfaces.IControladorConsultarDonacion;
import interfaces.IControladorPrestamo;
import interfaces.IControladorModificarEstadoPrestamo;
import interfaces.IControladorConsultaDonacionYFecha;
import interfaces.IControladorModificarTodoPrestamo;
import interfaces.IControladorListarPrestamosZona;

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
    private ConsultarPrestamosComunes listarPrestamosComunesInternalFrame;
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

    public Principal() {
        initialize();

        publicadores.AltaLectorPublish publicadorLector = new publicadores.AltaLectorPublish();
        publicadorLector.publicar("localhost", "18015");

        publicadores.LoginPublish publicadorLogin = new publicadores.LoginPublish();
        publicadorLogin.publicar("localhost", "18016");

        publicadores.ModificarEstadoLectorPublish publicadorModificarEstadoLector = new publicadores.ModificarEstadoLectorPublish();
        publicadorModificarEstadoLector.publicar("localhost", "18017");

        publicadores.ModificarZonaLectorPublish publicadorModificarZonaLector = new publicadores.ModificarZonaLectorPublish();
        publicadorModificarZonaLector.publicar("localhost", "18018");
        
        Fabrica fabrica = Fabrica.getInstancia();
        IControladorAltaLector controladorAlta = fabrica.getIControladorAltaLector();
        IControladorAltaBibliotecario controladorBibliotecario = fabrica.getIControladorAltaBibliotecario();
        IControladorModificarEstadoLector controladorModificarEstado = fabrica.getIControladorModificarEstadoLector();
        IControladorAltaDonacionLibro controladorAltaLibro = fabrica.getIControladorAltaDonacionLibro();
        IControladorAltaDonacionEspecial controladorAltaEspecial = fabrica.getIControladorAltaDonacionEspecial();
        IControladorConsultarDonacion controladorConsultarDonacion = fabrica.getIControladorConsultarDonacion();
        IControladorModificarZonaLector controladorModificarZona = fabrica.getIControladorModificarZonaLector();
        IControladorPrestamo controladorPrestamo = fabrica.getIControladorPrestamo();
        IControladorModificarEstadoPrestamo controladorModificarEstadoPrestamo = fabrica.getIControladorModificarEstadoPrestamo();
        IControladorConsultaDonacionYFecha controladorConsultaDonacionYFecha = fabrica.getIControladorConsultaDonacionYFecha();
        IControladorModificarTodoPrestamo controladorModificarTodoPrestamo = fabrica.getIControladorModificarTodoPrestamo();
        IControladorListarPrestamosZona controladorListarZona = fabrica.getIControladorListarPrestamosZona();

        Dimension desktopSize = frame.getSize();

        // === Alta Lector ===
        agregarLectorInternalFrame = new AltaLector(controladorAlta, this);
        agregarLectorInternalFrame.setLocation(
                (desktopSize.width - agregarLectorInternalFrame.getSize().width) / 2,
                (desktopSize.height - agregarLectorInternalFrame.getSize().height) / 2
        );
        agregarLectorInternalFrame.setVisible(false);
        frame.getContentPane().add(agregarLectorInternalFrame);

        // === Alta Bibliotecario ===
        agregarBibliotecarioInternalFrame = new AltaBibliotecario(controladorBibliotecario, this);
        agregarBibliotecarioInternalFrame.setLocation(
                (desktopSize.width - agregarBibliotecarioInternalFrame.getSize().width) / 2,
                (desktopSize.height - agregarBibliotecarioInternalFrame.getSize().height) / 2
        );
        agregarBibliotecarioInternalFrame.setVisible(false);
        frame.getContentPane().add(agregarBibliotecarioInternalFrame);

        // === Estado Lector ===
        estadoLectorInternalFrame = new EstadoLectorFrame(controladorModificarEstado, this);
        estadoLectorInternalFrame.setLocation(
                (desktopSize.width - estadoLectorInternalFrame.getSize().width) / 2,
                (desktopSize.height - estadoLectorInternalFrame.getSize().height) / 2
        );
        estadoLectorInternalFrame.setVisible(false);
        frame.getContentPane().add(estadoLectorInternalFrame);

        // === Modificar Zona Lector ===
        modificarZonaInternalFrame = new ModificarZonaLectorFrame(controladorModificarZona, this);
        modificarZonaInternalFrame.setLocation(
                (desktopSize.width - modificarZonaInternalFrame.getSize().width) / 2,
                (desktopSize.height - modificarZonaInternalFrame.getSize().height) / 2
        );
        modificarZonaInternalFrame.setVisible(false);
        frame.getContentPane().add(modificarZonaInternalFrame);

        // === Alta Donación Especial ===
        altaDonacionEspecialInternalFrame = new AltaDonacionEspecial(controladorAltaEspecial, this);
        altaDonacionEspecialInternalFrame.setLocation(
                (desktopSize.width - altaDonacionEspecialInternalFrame.getSize().width) / 2,
                (desktopSize.height - altaDonacionEspecialInternalFrame.getSize().height) / 2
        );
        altaDonacionEspecialInternalFrame.setVisible(false);
        frame.getContentPane().add(altaDonacionEspecialInternalFrame);

        // === Alta Donación Libro ===
        altaDonacionLibroInternalFrame = new AltaDonacionLibro(controladorAltaLibro, this);
        altaDonacionLibroInternalFrame.setLocation(
                (desktopSize.width - altaDonacionLibroInternalFrame.getSize().width) / 2,
                (desktopSize.height - altaDonacionLibroInternalFrame.getSize().height) / 2
        );
        altaDonacionLibroInternalFrame.setVisible(false);
        frame.getContentPane().add(altaDonacionLibroInternalFrame);

        // === Consultar Donación ===
        consultarDonacionInternalFrame = new ConsultarDonacion(controladorConsultarDonacion, this);
        consultarDonacionInternalFrame.setLocation(
                (desktopSize.width - consultarDonacionInternalFrame.getSize().width) / 2,
                (desktopSize.height - consultarDonacionInternalFrame.getSize().height) / 2
        );
        consultarDonacionInternalFrame.setVisible(false);
        frame.getContentPane().add(consultarDonacionInternalFrame);

        // === Resto de frames (Préstamos, Listados, etc.) ===
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

        listarPrestamosComunesInternalFrame = new ConsultarPrestamosComunes(controladorPrestamo, this);
        listarPrestamosComunesInternalFrame.setLocation(
                (desktopSize.width - listarPrestamosComunesInternalFrame.getSize().width) / 2,
                (desktopSize.height - listarPrestamosComunesInternalFrame.getSize().height) / 2
        );
        listarPrestamosComunesInternalFrame.setVisible(false);
        frame.getContentPane().add(listarPrestamosComunesInternalFrame);

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
        frame.setTitle("Sistema de Biblioteca");
        frame.setBounds(200, 200, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
            System.err.println("No se pudo aplicar Look and Feel del sistema: " + e.getMessage());
        }

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnAñadir = new JMenu("Añadir");
        mnAñadir.setMnemonic('A');
        menuBar.add(mnAñadir);

        JMenuItem mntmAgregarLector = new JMenuItem("Agregar lector");
        mntmAgregarLector.addActionListener(e -> agregarLectorInternalFrame.setVisible(true));
        mnAñadir.add(mntmAgregarLector);

        JMenuItem mntmAgregarBibliotecario = new JMenuItem("Agregar bibliotecario");
        mntmAgregarBibliotecario.addActionListener(e -> agregarBibliotecarioInternalFrame.setVisible(true));
        mnAñadir.add(mntmAgregarBibliotecario);

        JMenuItem mntmRegistrarLibro = new JMenuItem("Registrar libro");
        mntmRegistrarLibro.addActionListener(e -> altaDonacionLibroInternalFrame.setVisible(true));
        mnAñadir.add(mntmRegistrarLibro);

        JMenuItem mntmRegistrarArticuloEspecial = new JMenuItem("Registrar artículo especial");
        mntmRegistrarArticuloEspecial.addActionListener(e -> altaDonacionEspecialInternalFrame.setVisible(true));
        mnAñadir.add(mntmRegistrarArticuloEspecial);

        JMenuItem mntmRegistrarPrestamo = new JMenuItem("Registrar préstamo");
        mntmRegistrarPrestamo.addActionListener(e -> altaPrestamoInternalFrame.setVisible(true));
        mnAñadir.add(mntmRegistrarPrestamo);

        JMenu mnEditar = new JMenu("Editar");
        mnEditar.setMnemonic('E');
        menuBar.add(mnEditar);

        JMenuItem mntmCambiarEstadoLector = new JMenuItem("Cambiar estado de lector");
        mntmCambiarEstadoLector.addActionListener(e -> {
            estadoLectorInternalFrame.limpiarFormulario();
            estadoLectorInternalFrame.cargarNombresLectores();
            estadoLectorInternalFrame.setVisible(true);
        });
        mnEditar.add(mntmCambiarEstadoLector);

        JMenuItem mntmCambiarZonaLector = new JMenuItem("Cambiar zona de lector");
        mntmCambiarZonaLector.addActionListener(e -> modificarZonaInternalFrame.setVisible(true));
        mnEditar.add(mntmCambiarZonaLector);

        JMenuItem mntmModificarEstadoPrestamo = new JMenuItem("Modificar estado de préstamo");
        mntmModificarEstadoPrestamo.addActionListener(e -> modificarEstadoPrestamoInternalFrame.setVisible(true));
        mnEditar.add(mntmModificarEstadoPrestamo);

        JMenuItem mntmModificarPrestamo = new JMenuItem("Modificar préstamo");
        mntmModificarPrestamo.addActionListener(e -> modificarTodoPrestamoInternalFrame.setVisible(true));
        mnEditar.add(mntmModificarPrestamo);

        JMenu mnBuscar = new JMenu("Buscar");
        mnBuscar.setMnemonic('B');
        menuBar.add(mnBuscar);

        JMenuItem mntmConsultarDonacion = new JMenuItem("Consultar donaciones");
        mntmConsultarDonacion.addActionListener(e -> consultarDonacionInternalFrame.setVisible(true));
        mnBuscar.add(mntmConsultarDonacion);

        JMenuItem mntmConsultarDonacionYFecha = new JMenuItem("Consultar donaciones por fecha");
        mntmConsultarDonacionYFecha.addActionListener(e -> consultaDonacionYFechaInternalFrame.setVisible(true));
        mnBuscar.add(mntmConsultarDonacionYFecha);

        JMenuItem mntmResumenPorZona = new JMenuItem("Resumen por zona");
        mntmResumenPorZona.addActionListener(e -> consultarZonaLectorInternalFrame.setVisible(true));
        mnBuscar.add(mntmResumenPorZona);

        JMenuItem mntmListarPrestamosPorLector = new JMenuItem("Listar préstamos por lector");
        mntmListarPrestamosPorLector.addActionListener(e -> listarPrestamosInternalFrame.setVisible(true));
        mnBuscar.add(mntmListarPrestamosPorLector);

        JMenuItem mntmListarPrestamosBibliotecario = new JMenuItem("Listar préstamos por bibliotecario");
        mntmListarPrestamosBibliotecario.addActionListener(e -> listarPrestamosBibliotecarioInternalFrame.setVisible(true));
        mnBuscar.add(mntmListarPrestamosBibliotecario);

        JMenuItem mntmListarPrestamosComunes = new JMenuItem("Listar préstamos comunes");
        mntmListarPrestamosComunes.addActionListener(e -> listarPrestamosComunesInternalFrame.setVisible(true));
        mnBuscar.add(mntmListarPrestamosComunes);
    }

    public void actualizarInternalFrames() {
        altaPrestamoInternalFrame.cargarDatos();
        modificarZonaInternalFrame.cargarNombresLectores();
        estadoLectorInternalFrame.cargarNombresLectores();
        listarPrestamosBibliotecarioInternalFrame.cargarDatos();
        consultaDonacionYFechaInternalFrame.limpiarFormulario();
        modificarTodoPrestamoInternalFrame.cargarDatos();
    }
}
