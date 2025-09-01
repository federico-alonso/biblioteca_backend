package presentacion;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import datatypes.DtPrestamo;
import datatypes.EstadoPmo;
import interfaces.IControladorModificarEstadoPrestamo;

public class ModificarEstadoPrestamoFrame extends JInternalFrame {

    private IControladorModificarEstadoPrestamo controlador;
    private JComboBox<String> comboPrestamos;
    private JComboBox<EstadoPmo> comboEstado;
    private JButton btnModificar;

    private List<DtPrestamo> prestamos; // lista completa de objetos

    public ModificarEstadoPrestamoFrame(IControladorModificarEstadoPrestamo controlador) {
        this.controlador = controlador;
        setTitle("Modificar Estado de Préstamo");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setBounds(100, 100, 500, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Seleccione lector:"), gbc);

        comboPrestamos = new JComboBox<>();
        comboPrestamos.setPreferredSize(new Dimension(250, 25));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(comboPrestamos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nuevo estado:"), gbc);

        comboEstado = new JComboBox<>(new EstadoPmo[]{EstadoPmo.ACTIVO, EstadoPmo.DEVUELTO});
        comboEstado.setPreferredSize(new Dimension(150, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(comboEstado, gbc);

        btnModificar = new JButton("Aceptar");
        btnModificar.addActionListener(e -> modificarEstado());
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(btnModificar, gbc);

        add(panel);

        // Cargar datos en segundo plano al iniciar
        cargarDatosEnHilo();
    }

    private void cargarDatosEnHilo() {
        comboPrestamos.setEnabled(false);
        comboPrestamos.removeAllItems();
        comboPrestamos.addItem("Cargando...");

        new Thread(() -> {
            try {
                prestamos = controlador.listarTodosLosPrestamos();

                // actualizar UI en el hilo de eventos
                SwingUtilities.invokeLater(() -> {
                    comboPrestamos.removeAllItems();
                    for (DtPrestamo p : prestamos) {
                        comboPrestamos.addItem(p.getLector().getNombre()); // solo nombre
                    }
                    comboPrestamos.setEnabled(true);
                });

            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    comboPrestamos.removeAllItems();
                    comboPrestamos.addItem("Error al cargar préstamos");
                    comboPrestamos.setEnabled(false);
                });
            }
        }).start();
    }

    private DtPrestamo getPrestamoSeleccionado() {
        String nombreSeleccionado = (String) comboPrestamos.getSelectedItem();
        if (nombreSeleccionado == null || prestamos == null) return null;

        for (DtPrestamo p : prestamos) {
            if (p.getLector().getNombre().equals(nombreSeleccionado)) {
                return p;
            }
        }
        return null;
    }

    private void modificarEstado() {
        DtPrestamo prestamoSeleccionado = getPrestamoSeleccionado();
        EstadoPmo nuevoEstado = (EstadoPmo) comboEstado.getSelectedItem();

        if (prestamoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un lector", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (prestamoSeleccionado.getEstado() == nuevoEstado) {
            String estadoTexto = (nuevoEstado == EstadoPmo.ACTIVO) ? "ACTIVO" : "DEVUELTO";
            JOptionPane.showMessageDialog(this,
                    "El préstamo ya se encuentra en estado: " + estadoTexto,
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        btnModificar.setEnabled(false);
        btnModificar.setText("Procesando...");

        new Thread(() -> {
            try {
                controlador.modificarEstadoPrestamo(prestamoSeleccionado.getId(), nuevoEstado);
                prestamoSeleccionado.setEstado(nuevoEstado);

                SwingUtilities.invokeLater(() -> {
                    String estadoTexto = (nuevoEstado == EstadoPmo.ACTIVO) ? "ACTIVO" : "DEVUELTO";
                    JOptionPane.showMessageDialog(this,
                            "Estado del préstamo modificado exitosamente a: " + estadoTexto,
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                });

            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this,
                        "Error al modificar el estado: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE));
            } finally {
                SwingUtilities.invokeLater(() -> {
                    btnModificar.setEnabled(true);
                    btnModificar.setText("Aceptar");
                });
            }
        }).start();
    }
}
