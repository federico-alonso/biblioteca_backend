package presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import datatypes.Zona;
import excepciones.LectorRepetidoExcepcion;
import interfaces.IControladorAltaLector;

public class AltaLector extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    private IControladorAltaLector icon;

    private JTextField textFieldNombre;
    private JTextField textFieldEmail;
    private JTextField textFieldDireccion;
    private JSpinner spinnerFechaRegistro;
    private JComboBox<Zona> comboBoxZona;

    public AltaLector(IControladorAltaLector icon) {
        this.icon = icon;

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setTitle("AltaLector");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 10, 100, 20);
        getContentPane().add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(120, 10, 200, 20);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 40, 100, 20);
        getContentPane().add(lblEmail);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(120, 40, 200, 20);
        getContentPane().add(textFieldEmail);
        textFieldEmail.setColumns(10);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(10, 70, 100, 20);
        getContentPane().add(lblDireccion);

        textFieldDireccion = new JTextField();
        textFieldDireccion.setBounds(120, 70, 200, 20);
        getContentPane().add(textFieldDireccion);
        textFieldDireccion.setColumns(10);

        JLabel lblFechaRegistro = new JLabel("Fecha de Registro:");
        lblFechaRegistro.setBounds(10, 100, 120, 20);
        getContentPane().add(lblFechaRegistro);

        spinnerFechaRegistro = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        spinnerFechaRegistro.setBounds(140, 100, 180, 25);
        getContentPane().add(spinnerFechaRegistro);

        JLabel lblZona = new JLabel("Zona:");
        lblZona.setBounds(10, 130, 100, 20);
        getContentPane().add(lblZona);

        comboBoxZona = new JComboBox<>(Zona.values());
        comboBoxZona.setBounds(120, 130, 200, 25);
        getContentPane().add(comboBoxZona);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(120, 170, 100, 25);
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarLectorAceptarActionPerformed(e);
            }
        });
        getContentPane().add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(230, 170, 100, 25);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarLectorCancelarActionPerformed(e);
            }
        });
        getContentPane().add(btnCancelar);
    }

    protected void agregarLectorCancelarActionPerformed(ActionEvent arg0) {
        limpiarFormulario();
        setVisible(false);
    }

    protected void agregarLectorAceptarActionPerformed(ActionEvent arg0) {
        String nombre = textFieldNombre.getText();
        String email = textFieldEmail.getText();
        String direccion = textFieldDireccion.getText();
        Date fechaRegistro = (Date) spinnerFechaRegistro.getValue();
        Zona zona = (Zona) comboBoxZona.getSelectedItem();

        if (checkFormulario()) {
            try {
                icon.altaLector(nombre, email, direccion, fechaRegistro, zona);
                JOptionPane.showMessageDialog(this, "Lector Aceptado");
            } catch (LectorRepetidoExcepcion e) {
                JOptionPane.showMessageDialog(this, "Lector Repetido");
            }
            limpiarFormulario();
            setVisible(false);
        }
    }

    protected void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldEmail.setText("");
        textFieldDireccion.setText("");
        spinnerFechaRegistro.setValue(new Date());
        comboBoxZona.setSelectedIndex(0);
    }

    private boolean checkFormulario() {
        String nombre = textFieldNombre.getText();
        String email = textFieldEmail.getText();
        String direccion = textFieldDireccion.getText();
        Date fechaRegistro = (Date) spinnerFechaRegistro.getValue();
        Zona zona = (Zona) comboBoxZona.getSelectedItem();

        if (nombre.isEmpty() || email.isEmpty() || direccion.isEmpty() || fechaRegistro == null || zona == null) {
            JOptionPane.showMessageDialog(this, "Hay parámetros en blanco");
            return false;
        }
        return true;
    }
}
