package presentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import datatypes.DtPrestamoSimple;
import interfaces.IControladorPrestamo;

public class ListarPrestamosFrame extends JInternalFrame {

    private IControladorPrestamo controlador;
    private JTextField lectorField;
    private JTable tablaPrestamos;
    private DefaultTableModel modeloTabla;

    public ListarPrestamosFrame(IControladorPrestamo controlador) {
        this.controlador = controlador;
        setTitle("Préstamos activos por lector");
        setSize(600, 400);
        setClosable(true);
        setLayout(new BorderLayout());

        initUI();
    }

    private void initUI() {
        // Top panel for input
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Nombre del lector:"));

        lectorField = new JTextField(20);
        inputPanel.add(lectorField);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(e -> cargarPrestamos());
        inputPanel.add(buscarButton);

        add(inputPanel, BorderLayout.NORTH);

        // Table setup
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Fecha Solicitud");
        modeloTabla.addColumn("Fecha Devolución");
        modeloTabla.addColumn("Estado");

        tablaPrestamos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPrestamos);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void cargarPrestamos() {
        String nombreLector = lectorField.getText().trim();
        modeloTabla.setRowCount(0); // Clear previous results

        if (nombreLector.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del lector.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            List<DtPrestamoSimple> prestamos = controlador.getPrestamosActivosPorLector(nombreLector);
            for (DtPrestamoSimple p : prestamos) {
                modeloTabla.addRow(new Object[]{
                        p.getFechaSolicitud(),
                        p.getFechaDevolucion(),
                        p.getEstado()
                });
            }

            if (prestamos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron préstamos activos para el lector.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al obtener préstamos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
