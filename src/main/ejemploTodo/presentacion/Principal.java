package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfaces.Fabrica;
import interfaces.IControlador;
import publicadores.ControladorPublish;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnSistema;
	private JMenu mnAltas;
	private JMenu mnInscripciones;
	private JMenu mnInformacin;
	private JMenuItem mntmSalir;
	private JMenuItem mntmAltaClase;
	private JMenuItem mntmAltaSocio;
	private JMenuItem mntmAgregarborrar;
	private JMenuItem mntmClasessocios;
	
	private AgregarClase agregarClaseInternalFrame;
	private AgregarSocio agregarSocioInternalFrame;
	private InfoClase infoClaseInternalFrame;
	private AgregarBorrarInscripcion agregarBorrarInscripcionInternalFrame;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		ControladorPublish cp = new ControladorPublish();
		cp.publicar();	
		
		Fabrica fabrica = Fabrica.getInstancia();
		IControlador ic = fabrica.getIControlador();
		
		agregarClaseInternalFrame = new AgregarClase(ic);
		agregarClaseInternalFrame.setVisible(false);
		
		agregarSocioInternalFrame = new AgregarSocio(ic);
		agregarSocioInternalFrame.setVisible(false);
		
		infoClaseInternalFrame = new InfoClase(ic);
		infoClaseInternalFrame.setVisible(false);
		
		agregarBorrarInscripcionInternalFrame = new AgregarBorrarInscripcion(ic);
		agregarBorrarInscripcionInternalFrame.setVisible(false);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(agregarClaseInternalFrame);
		contentPane.add(agregarSocioInternalFrame);
		contentPane.add(agregarBorrarInscripcionInternalFrame);
		contentPane.add(infoClaseInternalFrame);
		{
			menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 438, 21);
			contentPane.add(menuBar);
			{
				mnSistema = new JMenu("Sistema");
				menuBar.add(mnSistema);
				{
					mntmSalir = new JMenuItem("Salir");
					mntmSalir.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					mnSistema.add(mntmSalir);
				}
			}
			{
				mnAltas = new JMenu("Altas");
				menuBar.add(mnAltas);
				{
					mntmAltaSocio = new JMenuItem("Alta Socio");
					mntmAltaSocio.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							agregarSocioInternalFrame.setVisible(true);
						}
					});
					mnAltas.add(mntmAltaSocio);
				}
				{
					mntmAltaClase = new JMenuItem("Alta Clase");
					mntmAltaClase.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							agregarClaseInternalFrame.setVisible(true);
						}
					});
					mnAltas.add(mntmAltaClase);
				}
			}
			{
				mnInscripciones = new JMenu("Inscripciones");
				menuBar.add(mnInscripciones);
				{
					mntmAgregarborrar = new JMenuItem("Agregar/Borrar");
					mntmAgregarborrar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							agregarBorrarInscripcionInternalFrame.setVisible(true);
						}
					});
					mnInscripciones.add(mntmAgregarborrar);
				}
			}
			{
				mnInformacin = new JMenu("Información");
				menuBar.add(mnInformacin);
				{
					mntmClasessocios = new JMenuItem("Clases/Socios");
					mntmClasessocios.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							infoClaseInternalFrame.setVisible(true);
						}
					});
					mnInformacin.add(mntmClasessocios);
				}
			}
		}
	}
}
