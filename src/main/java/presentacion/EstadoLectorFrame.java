package presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

import datatypes.EstadoLector;
import interfaces.IControladorModificarEstadoLector;
import excepciones.LectorNoExisteExcepcion;

public class EstadoLectorFrame extends JInternalFrame {

    private IControladorModificarEstadoLector icon;


    private JComboBox<String> comboBoxNombre;
    private JComboBox<EstadoLector> comboBoxEstado;

    public EstadoLectorFrame(IControladorModificarEstadoLector icon) {
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

        comboBoxNombre = new JComboBox<>();
        comboBoxNombre.setBounds(180, 20, 180, 20);
        getContentPane().add(comboBoxNombre);
        cargarNombresLectores();

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
        String nombre = (String) comboBoxNombre.getSelectedItem();
        EstadoLector nuevoEstado = (EstadoLector) comboBoxEstado.getSelectedItem();

        if (nombre == null || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un lector");
            return;
        }

        try {
            icon.modificarEstadoLector(nombre, nuevoEstado);
            JOptionPane.showMessageDialog(this, "Estado actualizado correctamente");
        } catch (LectorNoExisteExcepcion ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
        System.out.println("Cambiar estado de '" + nombre + "' a " + nuevoEstado);
        limpiarFormulario();
        setVisible(false);
    }

    private void limpiarFormulario() {
        comboBoxNombre.setSelectedIndex(0);
        comboBoxEstado.setSelectedIndex(0);
    }

    private void cargarNombresLectores() {
        try {
            List<String> nombres = icon.listarNombresLectores();
            comboBoxNombre.removeAllItems();
            comboBoxNombre.addItem("Seleccione un lector");
            for (String nombre : nombres) {
                comboBoxNombre.addItem(nombre);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar nombres de lectores: " + e.getMessage());
        }
    }
}
