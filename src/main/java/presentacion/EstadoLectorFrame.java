package presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;

import datatypes.EstadoLector;
import interfaces.IControladorAltaLector;

public class EstadoLectorFrame extends JInternalFrame {

    private IControladorAltaLector icon;

    private JTextField textFieldNombre;
    private JComboBox<EstadoLector> comboBoxEstado;

    public EstadoLectorFrame(IControladorAltaLector icon) {
        this.icon = icon;

        setTitle("Cambiar Estado del Lector");
        setBounds(100, 100, 400, 200);
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setLayout(null);

        JLabel lblNombre = new JLabel("Nombre del lector:");
        lblNombre.setBounds(20, 20, 150, 20);
        getContentPane().add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(180, 20, 180, 20);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);

        JLabel lblEstado = new JLabel("Nuevo estado:");
        lblEstado.setBounds(20, 60, 150, 20);
        getContentPane().add(lblEstado);

        comboBoxEstado = new JComboBox<>(EstadoLector.values());
        comboBoxEstado.setBounds(180, 60, 180, 20);
        getContentPane().add(comboBoxEstado);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(80, 110, 100, 25);
        btnAceptar.addActionListener(this::cambiarEstadoActionPerformed);
        getContentPane().add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(200, 110, 100, 25);
        btnCancelar.addActionListener(e -> {
            limpiarFormulario();
            setVisible(false);
        });
        getContentPane().add(btnCancelar);
    }

    private void cambiarEstadoActionPerformed(ActionEvent e) {
        String nombre = textFieldNombre.getText();
        EstadoLector nuevoEstado = (EstadoLector) comboBoxEstado.getSelectedItem();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del lector");
            return;
        }

        // TODO: Call controller logic to update lector's estado
        System.out.println("Cambiar estado de '" + nombre + "' a " + nuevoEstado);
        limpiarFormulario();
        setVisible(false);
    }

    private void limpiarFormulario() {
        textFieldNombre.setText("");
        comboBoxEstado.setSelectedIndex(0);
    }
}
