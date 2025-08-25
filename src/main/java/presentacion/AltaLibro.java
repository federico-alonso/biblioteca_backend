package presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import datatypes.DtLibro;
import excepciones.LectorRepetidoExcepcion;
import interfaces.IControladorMaterial;

public class AltaLibro extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    private IControladorMaterial icon;

    private JTextField textFieldTitulo;
    private JTextField textFieldCantPag;
    private JSpinner spinnerFechaRegistro;

    public AltaLibro(IControladorMaterial icon) {
        this.icon = icon;

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setTitle("Donacion Libro");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        JLabel lblNombre = new JLabel("Titulo:");
        lblNombre.setBounds(10, 10, 100, 20);
        getContentPane().add(lblNombre);

        textFieldTitulo = new JTextField();
        textFieldTitulo.setBounds(120, 10, 200, 20);
        getContentPane().add(textFieldTitulo);
        textFieldTitulo.setColumns(10);

        JLabel lblEmail = new JLabel("Cantidad de paginas:");
        lblEmail.setBounds(10, 40, 100, 20);
        getContentPane().add(lblEmail);

        textFieldCantPag = new JTextField();
        textFieldCantPag.setBounds(120, 40, 200, 20);
        getContentPane().add(textFieldCantPag);
        textFieldCantPag.setColumns(10);

        JLabel lblFechaRegistro = new JLabel("Fecha de Registro:");
        lblFechaRegistro.setBounds(10, 100, 120, 20);
        getContentPane().add(lblFechaRegistro);

        spinnerFechaRegistro = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        spinnerFechaRegistro.setBounds(140, 100, 180, 25);
        getContentPane().add(spinnerFechaRegistro);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(120, 140, 100, 25);
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarLibroAceptarActionPerformed(e);
            }
        });
        getContentPane().add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(230, 140, 100, 25);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarLibroCancelarActionPerformed(e);
            }
        });
        getContentPane().add(btnCancelar);
    }

    protected void agregarLibroCancelarActionPerformed(ActionEvent arg0) {
        limpiarFormulario();
        setVisible(false);
    }

    protected void agregarLibroAceptarActionPerformed(ActionEvent arg0) {
        String titulo = textFieldTitulo.getText();
        int cantPag = Integer.parseInt(textFieldCantPag.getText());
        Date fechaRegistro = (Date) spinnerFechaRegistro.getValue();

        if (checkFormulario()) {
            icon.altaDonacionLibro(new DtLibro(titulo,cantPag,fechaRegistro));
            JOptionPane.showMessageDialog(this, "Libro agregado al sistema.");

            limpiarFormulario();
            setVisible(false);
        }
    }

    protected void limpiarFormulario() {
        textFieldTitulo.setText("");
        textFieldCantPag.setText("");
        spinnerFechaRegistro.setValue(new Date());
    }

    private boolean checkFormulario() {
        String titulo = textFieldTitulo.getText();
        String cantPag = textFieldCantPag.getText();
        Date fechaRegistro = (Date) spinnerFechaRegistro.getValue();

        if (titulo.isEmpty() || cantPag.isEmpty() || fechaRegistro == null) {
            JOptionPane.showMessageDialog(this, "Hay parámetros en blanco");
            return false;
        }
        return true;
    }
}
