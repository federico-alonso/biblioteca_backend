package presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

import datatypes.Zona;
import excepciones.LectorNoExisteExcepcion;
import interfaces.IControladorModificarZonaLector;
import logica.Lector;
import logica.ManejadorLector;

public class ModificarZonaLectorFrame extends JInternalFrame {

    private IControladorModificarZonaLector icon;
    private Principal principal;
    private JComboBox<String> comboBoxNombre;
    private JComboBox<Zona> comboBoxZona;
    private JButton btnAceptar;

    public ModificarZonaLectorFrame(IControladorModificarZonaLector icon, Principal principal) {
        this.icon = icon;
        this.principal = principal;

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

        comboBoxNombre = new JComboBox<>();
        comboBoxNombre.setBounds(180, 20, 180, 20);
        getContentPane().add(comboBoxNombre);
        cargarNombresLectores();

        JLabel lblZona = new JLabel("Nueva zona:");
        lblZona.setBounds(20, 60, 150, 20);
        getContentPane().add(lblZona);

        comboBoxZona = new JComboBox<>(Zona.values());
        comboBoxZona.setBounds(180, 60, 180, 20);
        getContentPane().add(comboBoxZona);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(80, 110, 100, 25);
        btnAceptar.addActionListener(this::cambiarZonaActionPerformed);
        getContentPane().add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(200, 110, 100, 25);
        btnCancelar.addActionListener(e -> {
            limpiarFormularioCompleto();
            setVisible(false);
        });
        getContentPane().add(btnCancelar);
    }

    private void cambiarZonaActionPerformed(ActionEvent e) {
        String nombre = (String) comboBoxNombre.getSelectedItem();
        Zona nuevaZona = (Zona) comboBoxZona.getSelectedItem();

        if (nombre == null || nombre.isEmpty() || nombre.equals("Seleccione un lector")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un lector", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            ManejadorLector manejador = ManejadorLector.getInstance();
            Lector lector = manejador.buscarLector(nombre);

            if (lector == null) {
                throw new LectorNoExisteExcepcion("El lector '" + nombre + "' no existe");
            }

            if (lector.getZona() == nuevaZona) {
                JOptionPane.showMessageDialog(this,
                        "El lector ya se encuentra en la zona: " + nuevaZona,
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            icon.modificarZonaLector(nombre, nuevaZona);
            JOptionPane.showMessageDialog(this,
                    "Zona del lector modificada exitosamente a: " + nuevaZona,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            principal.actualizarInternalFrames();

        } catch (LectorNoExisteExcepcion ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Limpiamos solo la selección de zona, el lector queda seleccionado
        comboBoxZona.setSelectedIndex(0);
    }

    private void limpiarFormularioCompleto() {
        comboBoxNombre.setSelectedIndex(0);
        comboBoxZona.setSelectedIndex(0);
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
}
