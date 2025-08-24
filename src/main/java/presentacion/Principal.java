package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import interfaces.Fabrica;
import interfaces.IControladorAltaLector;
import interfaces.IControladorModificarEstadoLector;
import interfaces.IControladorMaterial;

public class Principal {
    private JFrame frame;

    private AltaLector agregarLectorInternalFrame;
    private EstadoLectorFrame estadoLectorInternalFrame;
    private AltaLibro altaLibroInternalFrame;

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

        Fabrica fabrica = Fabrica.getInstancia();
        IControladorAltaLector controladorAlta = fabrica.getIControladorAltaLector();
        IControladorModificarEstadoLector controladorModificar = fabrica.getIControladorModificarEstadoLector();
        IControladorMaterial controladorMaterial = fabrica.getIControladorMaterial();

        Dimension desktopSize = frame.getSize();

        agregarLectorInternalFrame = new AltaLector(controladorAlta);
        agregarLectorInternalFrame.setLocation(
                (desktopSize.width - agregarLectorInternalFrame.getSize().width) / 2,
                (desktopSize.height - agregarLectorInternalFrame.getSize().height) / 2
        );
        agregarLectorInternalFrame.setVisible(false);
        frame.getContentPane().add(agregarLectorInternalFrame);

        estadoLectorInternalFrame = new EstadoLectorFrame(controladorModificar);
        estadoLectorInternalFrame.setLocation(
                (desktopSize.width - estadoLectorInternalFrame.getSize().width) / 2,
                (desktopSize.height - estadoLectorInternalFrame.getSize().height) / 2
        );
        estadoLectorInternalFrame.setVisible(false);
        frame.getContentPane().add(estadoLectorInternalFrame);

        altaLibroInternalFrame = new AltaLibro(controladorMaterial);
        altaLibroInternalFrame.setLocation(
                (desktopSize.width - altaLibroInternalFrame.getSize().width) / 2,
                (desktopSize.height - altaLibroInternalFrame.getSize().height) / 2
        );
        altaLibroInternalFrame.setVisible(false);
        frame.getContentPane().add(altaLibroInternalFrame);
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

        JMenuItem mntmCambiarEstado = new JMenuItem("Cambiar estado de lector");
        mntmCambiarEstado.addActionListener(e -> estadoLectorInternalFrame.setVisible(true));
        mnLectores.add(mntmCambiarEstado);

        JMenu mnDonaciones = new JMenu("Donaciones");
        menuBar.add(mnDonaciones);

        JMenuItem mntmAltaLibro = new JMenuItem("Registrar libro");
        mntmAltaLibro.addActionListener(e -> altaLibroInternalFrame.setVisible(true));
        mnDonaciones.add(mntmAltaLibro);

        JMenuItem mntmAgregarEspecial = new JMenuItem("Registrar articulo especial");
        mntmCambiarEstado.addActionListener(e -> estadoLectorInternalFrame.setVisible(true));
        mnLectores.add(mntmCambiarEstado);

    }
}
