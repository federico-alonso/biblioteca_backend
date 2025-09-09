package presentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import datatypes.DtPrestamo;
import interfaces.IControladorModificarTodoPrestamo;
import datatypes.EstadoPmo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import datatypes.DtArticuloEspecial;
import datatypes.DtLibro;
import datatypes.DtMaterial;
import datatypes.DtLector;
import datatypes.DtBibliotecario;
import datatypes.DtPrestamo;

public class ModificarTodoPrestamo extends JInternalFrame {
    private IControladorModificarTodoPrestamo controlador;
    private JTable tablaPrestamos;
    private DefaultTableModel tableModel;
    private JButton btnCargar;
    private JButton btnGuardar;
    private JComboBox<String> materialComboBox;
    private JComboBox<String> lectorComboBox;
    private JComboBox<String> bibliotecarioComboBox;
    private JComboBox<String> estadoComboBox;

    public ModificarTodoPrestamo(IControladorModificarTodoPrestamo controlador) {
        this.controlador = controlador;
        setTitle("Modificar Préstamo");
        setBounds(100, 100, 700, 400);
        setClosable(true);
        setMaximizable(false);
        setIconifiable(false);
        setResizable(false);

        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 664, 280);
        getContentPane().add(scrollPane);

        tablaPrestamos = new JTable();
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ID", "Material", "Lector", "Bibliotecario", "Fecha Solicitud", "Fecha Devolución", "Estado"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // La columna 0 (ID) no es editable. El resto sí.
                return column > 0;
            }
        };
        tablaPrestamos.setModel(tableModel);

        // --- Setup cell editors ---
        materialComboBox = new JComboBox<>();
        TableColumn materialColumn = tablaPrestamos.getColumnModel().getColumn(1);
        materialColumn.setCellEditor(new DefaultCellEditor(materialComboBox));

        lectorComboBox = new JComboBox<>();
        TableColumn lectorColumn = tablaPrestamos.getColumnModel().getColumn(2);
        lectorColumn.setCellEditor(new DefaultCellEditor(lectorComboBox));

        bibliotecarioComboBox = new JComboBox<>();
        TableColumn bibliotecarioColumn = tablaPrestamos.getColumnModel().getColumn(3);
        bibliotecarioColumn.setCellEditor(new DefaultCellEditor(bibliotecarioComboBox));

        estadoComboBox = new JComboBox<>();
        TableColumn estadoColumn = tablaPrestamos.getColumnModel().getColumn(6);
        estadoColumn.setCellEditor(new DefaultCellEditor(estadoComboBox));

        scrollPane.setViewportView(tablaPrestamos);

        btnCargar = new JButton("Cargar Datos");
        btnCargar.setBounds(10, 302, 120, 23);
        getContentPane().add(btnCargar);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBounds(140, 302, 150, 23);
        getContentPane().add(btnGuardar);

        btnCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });
    }

    public void cargarDatos() {
        try {
            // Refresh ComboBox models
            materialComboBox.removeAllItems();
            controlador.getListadoMateriales().forEach(mat -> {
                if (mat instanceof DtLibro) {
                    materialComboBox.addItem(((DtLibro) mat).getTitulo());
                } else if (mat instanceof DtArticuloEspecial) {
                    materialComboBox.addItem(((DtArticuloEspecial) mat).getDescripcion());
                }
            });

            lectorComboBox.removeAllItems();
            controlador.getListadoLectores().forEach(l -> lectorComboBox.addItem(l.getNombre()));

            bibliotecarioComboBox.removeAllItems();
            controlador.getListadoBibliotecarios().forEach(b -> bibliotecarioComboBox.addItem(b.getNombre()));

            estadoComboBox.removeAllItems();
            for (EstadoPmo estado : EstadoPmo.values()) {
                estadoComboBox.addItem(estado.toString());
            }

            List<DtPrestamo> prestamos = controlador.listarPrestamos();
            tableModel.setRowCount(0); // Limpiar tabla
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (DtPrestamo p : prestamos) {
                String materialDisplay;
                if (p.getMaterial() instanceof DtLibro) {
                    materialDisplay = ((DtLibro) p.getMaterial()).getTitulo();
                } else if (p.getMaterial() instanceof DtArticuloEspecial) {
                    materialDisplay = ((DtArticuloEspecial) p.getMaterial()).getDescripcion();
                } else {
                    materialDisplay = "Material Desconocido";
                }

                Object[] row = new Object[] {
                    p.getId(),
                    materialDisplay,
                    p.getLector().getNombre(),
                    p.getBibliotecario().getNombre(),
                    sdf.format(p.getFechaSolicitud()),
                    p.getFechaDevolucion() != null ? sdf.format(p.getFechaDevolucion()) : "",
                    p.getEstado().toString()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los préstamos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarCambios() {
        int selectedRow = tablaPrestamos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un préstamo para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            long id = (long) tableModel.getValueAt(selectedRow, 0);

            // Obtener nuevos valores de la tabla
            String materialDisplay = (String) tableModel.getValueAt(selectedRow, 1);
            String lectorNombre = (String) tableModel.getValueAt(selectedRow, 2);
            String bibliotecarioNombre = (String) tableModel.getValueAt(selectedRow, 3);
            String fechaSolicitudStr = (String) tableModel.getValueAt(selectedRow, 4);
            String fechaDevolucionStr = (String) tableModel.getValueAt(selectedRow, 5);
            String estadoStr = (String) tableModel.getValueAt(selectedRow, 6);

            // Buscar los DTOs correspondientes
            DtMaterial material = findMaterialByDisplay(materialDisplay);
            DtLector lector = findLectorByName(lectorNombre);
            DtBibliotecario bibliotecario = findBibliotecarioByName(bibliotecarioNombre);

            if (material == null || lector == null || bibliotecario == null) {
                JOptionPane.showMessageDialog(this, "Uno de los valores (Material, Lector o Bibliotecario) no es válido.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parsear fechas y estado
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaSolicitud = sdf.parse(fechaSolicitudStr);
            Date fechaDevolucion = null;
            if (fechaDevolucionStr != null && !fechaDevolucionStr.isEmpty()) {
                fechaDevolucion = sdf.parse(fechaDevolucionStr);
            }
            EstadoPmo estado = EstadoPmo.valueOf(estadoStr.toUpperCase());

            // Crear el DTO de préstamo actualizado y enviarlo al controlador
            DtPrestamo prestamoActualizado = new DtPrestamo(id, material, lector, bibliotecario, fechaSolicitud, fechaDevolucion, estado);
            controlador.modificarPrestamo(prestamoActualizado);

            JOptionPane.showMessageDialog(this, "Préstamo modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarDatos(); // Recargar para ver los cambios
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar los cambios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private DtMaterial findMaterialByDisplay(String display) {
        for (DtMaterial mat : controlador.getListadoMateriales()) {
            String currentDisplay = "";
            if (mat instanceof DtLibro) {
                currentDisplay = ((DtLibro) mat).getTitulo();
            } else if (mat instanceof DtArticuloEspecial) {
                currentDisplay = ((DtArticuloEspecial) mat).getDescripcion();
            }
            if (currentDisplay.equals(display)) {
                return mat;
            }
        }
        return null;
    }

    private DtLector findLectorByName(String name) {
        return controlador.getListadoLectores().stream()
                .filter(l -> l.getNombre().equals(name))
                .findFirst()
                .orElse(null);
    }

    private DtBibliotecario findBibliotecarioByName(String name) {
        return controlador.getListadoBibliotecarios().stream()
                .filter(b -> b.getNombre().equals(name))
                .findFirst()
                .orElse(null);
    }

}
