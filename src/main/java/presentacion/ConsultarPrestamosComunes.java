package presentacion;

import datatypes.*;
import interfaces.IControladorPrestamo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class ConsultarPrestamosComunes extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    // --- Constantes ---
    private static final String[] COLUMNAS_TABLA = {
            "ID Material",
            "Fecha Ingreso",
            "Título",
            "Cant. Páginas",
            "Descripción",
            "Peso",
            "Dimensiones",
            "Cantidad"
    };

    private static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy");

    // --- Dependencias ---
    private final IControladorPrestamo icon;
    private final Principal principal;

    // --- Componentes UI ---
    private JTable tablaPrestamos;
    private DefaultTableModel modeloTabla;
    private JButton botonRefrescar;
    private JButton botonCerrar;

    // --- Constructor ---
    public ConsultarPrestamosComunes(IControladorPrestamo icon, Principal principal) {
        this.icon = icon;
        this.principal = principal;
        initialize();
    }

    // --- Inicialización UI ---
    private void initialize() {
        setTitle("Consultar préstamos comunes");
        setClosable(true);
        setResizable(false);
        setMaximizable(false);
        setIconifiable(false);
        setBounds(100, 100, 800, 400);

        JPanel panelContenido = new JPanel(new BorderLayout(0, 10));
        panelContenido.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(panelContenido);

        // Panel superior con título
        JLabel etiquetaTitulo = new JLabel("Listado de préstamos comunes");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        etiquetaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelContenido.add(etiquetaTitulo, BorderLayout.NORTH);

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

        // Panel inferior con botones
        panelContenido.add(crearPanelBotones(), BorderLayout.SOUTH);

        // cargar datos iniciales
        cargarConsulta();
    }

    private JPanel crearPanelBotones() {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));

        botonRefrescar = new JButton("Refrescar");
        botonRefrescar.addActionListener(e -> cargarConsulta());

        botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> setVisible(false));

        panelBotones.add(botonRefrescar);
        panelBotones.add(botonCerrar);

        return panelBotones;
    }

    // --- Lógica pública ---
    public void cargarConsulta() {
        modeloTabla.setRowCount(0); // limpiar tabla
       List<Object[]> consulta = icon.consultarPrestamosComunes();

            for (Object[] tupla : consulta) {
                Object objeto = tupla[0];

                if (objeto instanceof DtLibro libro) {
                    modeloTabla.addRow(new Object[]{
                            libro.getId(),
                            FORMATO_FECHA.format(libro.getFechaIngreso()),
                            libro.getTitulo(),
                            libro.getCantidadPag(),
                            "N/A", "N/A", "N/A", tupla[1]
                    });
                } else if (objeto instanceof DtArticuloEspecial articulo) {
                    modeloTabla.addRow(new Object[]{
                            articulo.getId(),
                            FORMATO_FECHA.format(articulo.getFechaIngreso()),
                            "N/A", "N/A",
                            articulo.getDescripcion(),
                            articulo.getPesoKg(),
                            articulo.getDimensiones(), tupla[1]
                    });
                }
            }

    }
}
