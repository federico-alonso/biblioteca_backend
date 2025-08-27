package presentacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

import datatypes.DtArticuloEspecial;
import datatypes.DtLibro;
import datatypes.DtMaterial;
import interfaces.IControladorConsultarDonacion;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultarDonacion extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    private JTable tablaMateriales;
    private DefaultTableModel tableModel;
    private JButton btnConsultar;
    private JButton btnCerrar;
    private IControladorConsultarDonacion controlador;

    public ConsultarDonacion(IControladorConsultarDonacion controlador, Principal principal) {
        this.controlador = controlador;
        initialize();
    }

    private void initialize() {
        setTitle("Consultar Donaciones de Materiales");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setBounds(100, 100, 800, 400);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                cargarDatos();
            }
        });

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 10));

        // Título
        JLabel lblTitulo = new JLabel("Listado de Materiales Donados");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Tabla
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tablaMateriales = new JTable();
        String[] columnNames = {"ID", "Fecha Ingreso", "Tipo", "Título/Descripción", "Págs./Peso", "Dimensiones"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que todas las celdas no sean editables
            }
        };
        tablaMateriales.setModel(tableModel);
        scrollPane.setViewportView(tablaMateriales);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        btnConsultar = new JButton("Refrescar");
        btnConsultar.addActionListener(e -> cargarDatos());
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> setVisible(false));
        
        buttonPanel.add(btnConsultar);
        buttonPanel.add(btnCerrar);
        
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void cargarDatos() {
        // Limpiar tabla
        tableModel.setRowCount(0);
        
        try {
            List<DtMaterial> materiales = controlador.consultarDonacion();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (DtMaterial mat : materiales) {
                Object[] rowData = new Object[6];
                rowData[0] = mat.getId();
                rowData[1] = sdf.format(mat.getFechaIngreso());

                if (mat instanceof DtLibro) {
                    DtLibro libro = (DtLibro) mat;
                    rowData[2] = "Libro";
                    rowData[3] = libro.getTitulo();
                    rowData[4] = libro.getCantidadPag();
                    rowData[5] = "N/A";
                } else if (mat instanceof DtArticuloEspecial) {
                    DtArticuloEspecial art = (DtArticuloEspecial) mat;
                    rowData[2] = "Artículo Especial";
                    rowData[3] = art.getDescripcion();
                    rowData[4] = art.getPesoKg() + " kg";
                    rowData[5] = art.getDimensiones();
                }
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar los datos: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
