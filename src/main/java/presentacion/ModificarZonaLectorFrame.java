package presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;

import datatypes.Zona;
import interfaces.IControladorModificarZonaLector;
import excepciones.LectorNoExisteExcepcion;

public class ModificarZonaLectorFrame extends JInternalFrame {

    private IControladorModificarZonaLector icon;

    private JTextField textFieldNombre;
    private JComboBox<Zona> comboBoxZona;

    public ModificarZonaLectorFrame(IControladorModificarZonaLector icon) {
        this.icon = icon;

        setTitle("Cambiar Zona del Lector");
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

        JLabel lblZona = new JLabel("Nueva zona:");
        lblZona.setBounds(20, 60, 150, 20);
        getContentPane().add(lblZona);

        comboBoxZona = new JComboBox<>(Zona.values());
        comboBoxZona.setBounds(180, 60, 180, 20);
        getContentPane().add(comboBoxZona);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(80, 110, 100, 25);
        btnAceptar.addActionListener(this::cambiarZonaActionPerformed);
        getContentPane().add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(200, 110, 100, 25);
        btnCancelar.addActionListener(e -> {
            limpiarFormulario();
            setVisible(false);
        });
        getContentPane().add(btnCancelar);
    }

    private void cambiarZonaActionPerformed(ActionEvent e) {
        String nombre = textFieldNombre.getText();
        Zona nuevaZona = (Zona) comboBoxZona.getSelectedItem();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del lector");
            return;
        }

        try {
            icon.modificarZonaLector(nombre, nuevaZona);
            JOptionPane.showMessageDialog(this, "Zona actualizada correctamente");
        } catch (LectorNoExisteExcepcion ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
        System.out.println("Cambiar zona de '" + nombre + "' a " + nuevaZona);
        limpiarFormulario();
        setVisible(false);
    }

    private void limpiarFormulario() {
        textFieldNombre.setText("");
        comboBoxZona.setSelectedIndex(0);
    }
}
