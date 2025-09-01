package presentacion;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import datatypes.EstadoLector;
import interfaces.IControladorModificarEstadoLector;
import excepciones.LectorNoExisteExcepcion;

public class EstadoLectorFrame extends JInternalFrame {

    private IControladorModificarEstadoLector icon;
    private Principal principal;

    private JComboBox<String> comboBoxNombre;
    private JComboBox<EstadoLector> comboBoxEstado;
    private JButton btnAceptar;

    public EstadoLectorFrame(IControladorModificarEstadoLector icon, Principal principal) {
        this.icon = icon;
        this.principal = principal;

        setTitle("Cambiar Estado del Lector");
        setClosable(true);
        setSize(450, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Seleccione lector:"), gbc);

        comboBoxNombre = new JComboBox<>();
        comboBoxNombre.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(comboBoxNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nuevo estado:"), gbc);

        comboBoxEstado = new JComboBox<>(new EstadoLector[]{EstadoLector.ACTIVO, EstadoLector.SUSPENDIDO});
        comboBoxEstado.setPreferredSize(new Dimension(150, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(comboBoxEstado, gbc);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> cambiarEstado());
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(btnAceptar, gbc);

        add(panel);

        cargarNombresLectores();
    }

    public void cargarNombresLectores() {
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

    public void limpiarFormulario() {
        comboBoxNombre.setSelectedIndex(0);
        comboBoxEstado.setSelectedIndex(0);
    }

    private void cambiarEstado() {
        String nombre = (String) comboBoxNombre.getSelectedItem();
        EstadoLector nuevoEstado = (EstadoLector) comboBoxEstado.getSelectedItem();

        if (nombre == null || nombre.equals("Seleccione un lector")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un lector", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // CORREGIDO: usar el método correcto de la interfaz
            EstadoLector estadoActual = icon.getEstadoLector(nombre); 
            if (estadoActual == nuevoEstado) {
                JOptionPane.showMessageDialog(this,
                        "El lector ya se encuentra en estado: " + nuevoEstado,
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            icon.modificarEstadoLector(nombre, nuevoEstado);
            JOptionPane.showMessageDialog(this,
                    "Estado del lector modificado exitosamente a: " + nuevoEstado,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            limpiarFormulario();
            cargarNombresLectores();
            principal.actualizarInternalFrames();

        } catch (LectorNoExisteExcepcion ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
