package presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import datatypes.DtBibliotecario;
import datatypes.DtPrestamo;
import interfaces.IControladorPrestamo;

public class ConsultarPrestamosBibliotecario extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    // --- Constantes ---
    private static final String[] COLUMNAS_TABLA = {
            "ID",
            "ID Material",
            "Lector",
            "Bibliotecario",
            "Fecha Solicitud",
            "Fecha Devolución",
            "Estado"
    };

    private static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy");

    // --- Componentes UI ---
    private JTable tablaPrestamos;
    private DefaultTableModel modeloTabla;
    private JButton botonRefrescar;
    private JButton botonCerrar;
    private JButton botonBuscar;
    private JComboBox<String> comboFiltro;

    // --- Dependencias ---
    private final IControladorPrestamo icon;
    private final Principal principal;

    // --- Constructor ---
    public ConsultarPrestamosBibliotecario(IControladorPrestamo icon, Principal principal) {
        this.icon = icon;
        this.principal = principal;
        initialize();
    }

    // --- Inicialización UI ---
    private void initialize() {
        setTitle("Consultar Préstamos de Materiales");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setBounds(100, 100, 800, 400);

        JPanel panelContenido = new JPanel(new BorderLayout(0, 10));
        panelContenido.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(panelContenido);

        // Panel superior
        panelContenido.add(crearPanelSuperior(), BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(COLUMNAS_TABLA, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false; // celdas no editables
            }
        };

        tablaPrestamos = new JTable(modeloTabla);
        JScrollPane scrollPanel = new JScrollPane(tablaPrestamos);
        panelContenido.add(scrollPanel, BorderLayout.CENTER);

        // Panel inferior
        panelContenido.add(crearPanelBotones(), BorderLayout.SOUTH);
    }

    private JPanel crearPanelSuperior() {
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));

        JLabel etiquetaTitulo = new JLabel("Listado de Préstamos");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        etiquetaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelSuperior.add(etiquetaTitulo);

        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.CENTER));
        comboFiltro = new JComboBox<>();
        comboFiltro.setToolTipText("Escriba un valor para filtrar...");
        comboFiltro.setEditable(true);

        botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(e -> cargarConsulta());

        panelFiltro.add(comboFiltro);
        panelFiltro.add(botonBuscar);

        panelSuperior.add(panelFiltro);

        // inicializar autocompletado
        cargarDatos();

        return panelSuperior;
    }

    private JPanel crearPanelBotones() {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));

        botonRefrescar = new JButton("Refrescar");
        botonRefrescar.addActionListener(e -> {
            ((JTextField) comboFiltro.getEditor().getEditorComponent()).setText("");
            cargarDatos();
        });

        botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> setVisible(false));

        panelBotones.add(botonRefrescar);
        panelBotones.add(botonCerrar);

        return panelBotones;
    }

    // --- Lógica pública ---
    public void cargarDatos() {
        List<DtBibliotecario> bibliotecarios = icon.getListadoBibliotecarios();
        List<String> nombres = bibliotecarios.stream()
                .map(DtBibliotecario::getNombre)
                .toList();

        habilitarAutocompletado(comboFiltro, nombres);
    }

    public void cargarConsulta() {
        modeloTabla.setRowCount(0); // limpiar tabla

        try {
            Object seleccionado = comboFiltro.getSelectedItem();
            if (seleccionado == null || seleccionado.toString().isBlank()) {
                return;
            }

            DtBibliotecario filtro = new DtBibliotecario(seleccionado.toString().trim(), null, 0);
            List<DtPrestamo> prestamos = icon.consultarPrestamosBibliotecario(filtro);

            for (DtPrestamo prestamo : prestamos) {
                modeloTabla.addRow(new Object[]{
                        prestamo.getId(),
                        prestamo.getMaterial().getId(),
                        prestamo.getLector().getNombre(),
                        prestamo.getBibliotecario().getNombre(),
                        FORMATO_FECHA.format(prestamo.getFechaSolicitud()),
                        FORMATO_FECHA.format(prestamo.getFechaDevolucion()),
                        prestamo.getEstado().toString()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Errorrrrr",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- Autocompletado ---
    private void habilitarAutocompletado(JComboBox<String> combo, List<String> listaOpciones) {
        combo.setEditable(true);
        combo.setMaximumRowCount(8);

        JTextField editor = (JTextField) combo.getEditor().getEditorComponent();
        editor.setText("");

        DefaultComboBoxModel<String> modeloOriginal = new DefaultComboBoxModel<>();
        for (String item : listaOpciones) {
            modeloOriginal.addElement(item);
        }
        combo.setModel(modeloOriginal);

        combo.setSelectedItem(null);

        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = editor.getText();
                DefaultComboBoxModel<String> modeloFiltrado = new DefaultComboBoxModel<>();

                if (texto.isEmpty()) {
                    // Mostrar todos los items
                    for (String item : listaOpciones) {
                        modeloFiltrado.addElement(item);
                    }
                } else {
                    for (String item : listaOpciones) {
                        if (item.toLowerCase().startsWith(texto.toLowerCase())) {
                            modeloFiltrado.addElement(item);
                        }
                    }
                }

                combo.setModel(modeloFiltrado);
                combo.setMaximumRowCount(8);

                editor.setText(texto);
                editor.setCaretPosition(texto.length());


                if (modeloFiltrado.getSize() > 0) {
                    combo.showPopup();
                } else {
                    combo.hidePopup();
                }
            }
        });

    }
}
