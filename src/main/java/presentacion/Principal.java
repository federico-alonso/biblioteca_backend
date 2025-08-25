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

public class Principal {
    private JFrame frame;

    private AltaLector agregarLectorInternalFrame;
    private AltaBibliotecario agregarBibliotecarioInternalFrame;
    private EstadoLectorFrame estadoLectorInternalFrame;

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
        IControladorAltaBibliotecario controladorBibliotecario = fabrica.getIControladorAltaBibliotecario();
        IControladorModificarEstadoLector controladorModificar = fabrica.getIControladorModificarEstadoLector();

        Dimension desktopSize = frame.getSize();

        agregarLectorInternalFrame = new AltaLector(controladorAlta);
        agregarLectorInternalFrame.setLocation(
                (desktopSize.width - agregarLectorInternalFrame.getSize().width) / 2,
                (desktopSize.height - agregarLectorInternalFrame.getSize().height) / 2
        );
        agregarLectorInternalFrame.setVisible(false);
        frame.getContentPane().add(agregarLectorInternalFrame);

        agregarBibliotecarioInternalFrame = new AltaBibliotecario(controladorBibliotecario);
        agregarBibliotecarioInternalFrame.setLocation(
                (desktopSize.width - agregarBibliotecarioInternalFrame.getSize().width) / 2,
                (desktopSize.height - agregarBibliotecarioInternalFrame.getSize().height) / 2
        );
        agregarBibliotecarioInternalFrame.setVisible(false);
        frame.getContentPane().add(agregarBibliotecarioInternalFrame);

        estadoLectorInternalFrame = new EstadoLectorFrame(controladorModificar);
        estadoLectorInternalFrame.setLocation(
                (desktopSize.width - estadoLectorInternalFrame.getSize().width) / 2,
                (desktopSize.height - estadoLectorInternalFrame.getSize().height) / 2
        );
        estadoLectorInternalFrame.setVisible(false);
        frame.getContentPane().add(estadoLectorInternalFrame);
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
        mntmCambiarEstado.addActionListener(e -> estadoLectorInternalFrame.setVisible(true));
        mnLectores.add(mntmCambiarEstado);
    }
}
