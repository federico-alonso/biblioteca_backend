package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import interfaces.Fabrica;
import interfaces.IControladorAltaLector;
import interfaces.IControladorAltaBibliotecario;

public class Principal {
    private JFrame frame;

    private AltaLector agregarLectorInternalFrame;
    private AltaBibliotecario agregarBibliotecarioInternalFrame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
        initialize();

        Fabrica fabrica = Fabrica.getInstancia();
        IControladorAltaLector icon = fabrica.getIControladorAltaLector();
        IControladorAltaBibliotecario iconBibliotecario = fabrica.getIControladorAltaBibliotecario();

        Dimension desktopSize = frame.getSize();
        Dimension jInternalFrameSize;

        agregarLectorInternalFrame = new AltaLector(icon);
        jInternalFrameSize = agregarLectorInternalFrame.getSize();
        agregarLectorInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                (desktopSize.height- jInternalFrameSize.height)/2);
        agregarLectorInternalFrame.setVisible(false);
        frame.getContentPane().add(agregarLectorInternalFrame);

        agregarBibliotecarioInternalFrame = new AltaBibliotecario(iconBibliotecario);
        jInternalFrameSize = agregarBibliotecarioInternalFrame.getSize();
        agregarBibliotecarioInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                (desktopSize.height- jInternalFrameSize.height)/2);
        agregarBibliotecarioInternalFrame.setVisible(false);
        frame.getContentPane().add(agregarBibliotecarioInternalFrame);




    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(200, 200, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnAltas = new JMenu("Altas");
        menuBar.add(mnAltas);

        JMenuItem mntmLector = new JMenuItem("Lector");
        mntmLector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                agregarLectorInternalFrame.setVisible(true);
            }
        });
        mnAltas.add(mntmLector);

        JMenuItem mntmBibliotecario = new JMenuItem("Bibliotecario");
        mntmBibliotecario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                agregarBibliotecarioInternalFrame.setVisible(true);
            }
        });
        mnAltas.add(mntmBibliotecario);
    }
}