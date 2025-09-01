package presentacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import datatypes.DtArticuloEspecial;
import datatypes.DtLibro;
import datatypes.DtMaterial;
import interfaces.IControladorConsultaDonacionYFecha;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultaDonacionYFechaFrame extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    private JTable tablaMateriales;
    private DefaultTableModel tableModel;
    private JButton btnConsultar;
    private JButton btnCerrar;
    private JButton btnLimpiar;
    private IControladorConsultaDonacionYFecha controlador;
    
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JLabel lblResultados;

    public ConsultaDonacionYFechaFrame(IControladorConsultaDonacionYFecha controlador) {
        this.controlador = controlador;
        initialize();
    }

    private void initialize() {
        setTitle("Consultar Donaciones por Rango de Fechas");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setBounds(100, 100, 800, 500);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                limpiarFormulario();
            }
        });

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 10));

        // Panel superior con controles de fecha
        JPanel panelFechas = new JPanel(new GridBagLayout());
        panelFechas.setBorder(BorderFactory.createTitledBorder("Seleccionar Rango de Fechas"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Fecha inicio
        gbc.gridx = 0; gbc.gridy = 0;
        panelFechas.add(new JLabel("Fecha Inicio:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        txtFechaInicio = new JTextField();
        txtFechaInicio.setPreferredSize(new Dimension(120, 25));
        panelFechas.add(txtFechaInicio, gbc);
        
        // Fecha fin
        gbc.gridx = 2; gbc.gridy = 0;
        panelFechas.add(new JLabel("Fecha Fin:"), gbc);
        
        gbc.gridx = 3; gbc.gridy = 0;
        txtFechaFin = new JTextField();
        txtFechaFin.setPreferredSize(new Dimension(120, 25));
        panelFechas.add(txtFechaFin, gbc);
        
        // Botones
        gbc.gridx = 4; gbc.gridy = 0;
        btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(e -> consultar());
        panelFechas.add(btnConsultar, gbc);
        
        gbc.gridx = 5; gbc.gridy = 0;
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        panelFechas.add(btnLimpiar, gbc);
        
        contentPane.add(panelFechas, BorderLayout.NORTH);

        // Tabla
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Label para mostrar resultados
        lblResultados = new JLabel("Ingrese un rango de fechas y haga clic en Consultar");
        lblResultados.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblResultados, BorderLayout.SOUTH);

        tablaMateriales = new JTable();
        String[] columnNames = {"ID", "Fecha Ingreso", "Tipo", "Título/Descripción", "Págs./Peso", "Dimensiones"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaMateriales.setModel(tableModel);
        scrollPane.setViewportView(tablaMateriales);
        
        // Panel de botones inferior
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> setVisible(false));
        
        buttonPanel.add(btnCerrar);
        
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void consultar() {
        String fechaInicioStr = txtFechaInicio.getText().trim();
        String fechaFinStr = txtFechaFin.getText().trim();
        
        if (fechaInicioStr.isEmpty() || fechaFinStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese ambas fechas en formato dd/MM/yyyy", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        
        Date fechaInicio = null;
        Date fechaFin = null;
        
        try {
            fechaInicio = sdf.parse(fechaInicioStr);
            fechaFin = sdf.parse(fechaFinStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, 
                "Formato de fecha incorrecto. Use dd/MM/yyyy", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (fechaInicio.after(fechaFin)) {
            JOptionPane.showMessageDialog(this, 
                "La fecha de inicio no puede ser posterior a la fecha fin", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            List<DtMaterial> materiales = controlador.consultarDonacionPorFecha(fechaInicio, fechaFin);
            cargarDatosEnTabla(materiales);
            
            lblResultados.setText("Resultados: " + materiales.size() + " donaciones encontradas");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al consultar los datos: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarDatosEnTabla(List<DtMaterial> materiales) {
        // Limpiar tabla
        tableModel.setRowCount(0);
        
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
    }
    
    public void limpiarFormulario() {
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        tableModel.setRowCount(0);
        lblResultados.setText("Ingrese un rango de fechas y haga clic en Consultar");
    }
}
