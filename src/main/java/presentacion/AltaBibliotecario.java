package presentacion;

import javax.swing.JInternalFrame;
import interfaces.IControladorAltaBibliotecario;
import excepciones.BibliotecarioRepetidoExcepcion;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaBibliotecario extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private Principal principal;
    private IControladorAltaBibliotecario iconBibliotecario;
    private JTextField textFieldNombre;
    private JTextField textFieldEmail;
    private JTextField textFieldContrasena;

    public AltaBibliotecario(IControladorAltaBibliotecario iconBibliotecario, Principal principal) {
        this.iconBibliotecario = iconBibliotecario;
        this.principal = principal;

        setTitle("Agregar bibliotecario");
        setBounds(100, 100, 450, 220);
        setResizable(false);
        setClosable(true);
        setIconifiable(false);
        setMaximizable(false);
        getContentPane().setLayout(null);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 11, 70, 14);
        getContentPane().add(lblNombre);
        
        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(90, 8, 260, 20);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 42, 70, 14);
        getContentPane().add(lblEmail);
        
        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(90, 39, 260, 20);
        getContentPane().add(textFieldEmail);
        textFieldEmail.setColumns(10);
        
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(10, 73, 70, 14);
        getContentPane().add(lblContrasena);
        
        textFieldContrasena = new JTextField();
        textFieldContrasena.setBounds(90, 70, 260, 20);
        getContentPane().add(textFieldContrasena);
        textFieldContrasena.setColumns(10);
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		altaBibliotecarioAceptarActionPerformed(e);
        	}
        });
        btnAceptar.setBounds(90, 110, 89, 23);
        getContentPane().add(btnAceptar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		altaBibliotecarioCancelarActionPerformed(e);
        	}
        });
        btnCancelar.setBounds(261, 110, 89, 23);
        getContentPane().add(btnCancelar);
    }
    
    protected void altaBibliotecarioAceptarActionPerformed(ActionEvent e) {
        String nombre = textFieldNombre.getText();
        String email = textFieldEmail.getText();
        String contrasena = textFieldContrasena.getText();
        
        if (checkFormulario()) {
            try {
                iconBibliotecario.altaBibliotecario(nombre, email, contrasena);
                JOptionPane.showMessageDialog(this, "Bibliotecario dado de alta exitosamente.", "Alta Bibliotecario", JOptionPane.INFORMATION_MESSAGE);
            } catch (BibliotecarioRepetidoExcepcion ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Alta Bibliotecario", JOptionPane.ERROR_MESSAGE);
            }
            limpiarFormulario();
            setVisible(false);
            principal.actualizarInternalFrames();
        }
    }

    protected void altaBibliotecarioCancelarActionPerformed(ActionEvent e) {
        limpiarFormulario();
        setVisible(false);
    }

    private boolean checkFormulario() {
        if (textFieldNombre.getText().isEmpty() || textFieldEmail.getText().isEmpty() || textFieldContrasena.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos.", "Alta Bibliotecario", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldEmail.setText("");
        textFieldContrasena.setText("");
    }
}
