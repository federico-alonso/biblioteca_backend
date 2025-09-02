package presentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import datatypes.DtPrestamoSimple;
import datatypes.DtLector;
import interfaces.IControladorPrestamo;

public class ListarPrestamosFrame extends JInternalFrame {

    private IControladorPrestamo controlador;
    private JComboBox<String> comboBoxLector;
    private JTable tablaPrestamos;
    private DefaultTableModel modeloTabla;

    public ListarPrestamosFrame(IControladorPrestamo controlador) {
        this.controlador = controlador;
        setTitle("Préstamos activos por lector");
        setSize(600, 400);
        setClosable(true);
        setLayout(new BorderLayout());

        initUI();
        cargarLectores();
    }

    private void initUI() {
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Seleccione lector:"));

        comboBoxLector = new JComboBox<>();
        comboBoxLector.setPreferredSize(new Dimension(200, 25));
        inputPanel.add(comboBoxLector);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(e -> cargarPrestamos());
        inputPanel.add(buscarButton);

        add(inputPanel, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Fecha Solicitud");
        modeloTabla.addColumn("Fecha Devolución");
        modeloTabla.addColumn("Estado");

        tablaPrestamos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPrestamos);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void cargarLectores() {
        comboBoxLector.removeAllItems();
        try {
            List<String> lectores = controlador.getNombresLectores();

            for (String nombre : lectores) {
                comboBoxLector.addItem(nombre);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar lectores: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarPrestamos() {
        String nombreSeleccionado = (String) comboBoxLector.getSelectedItem();
        modeloTabla.setRowCount(0);

        if (nombreSeleccionado == null || nombreSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un lector.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            DtLector lector = null;
            for (DtLector l : controlador.getListadoLectores()) {
                if (l.getNombre().equals(nombreSeleccionado)) {
                    lector = l;
                    break;
                }
            }

            if (lector == null) {
                JOptionPane.showMessageDialog(this, "Lector no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<DtPrestamoSimple> prestamos = controlador.getPrestamosActivosPorLector(lector);

            for (DtPrestamoSimple p : prestamos) {
                modeloTabla.addRow(new Object[]{
                    p.getFechaSolicitud(),
                    p.getFechaDevolucion(),
                    p.getEstado()
                });
            }

            if (prestamos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron préstamos activos para el lector.",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al obtener préstamos: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 
}
