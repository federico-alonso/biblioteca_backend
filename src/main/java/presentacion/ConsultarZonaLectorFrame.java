package presentacion;

import datatypes.DtEstadoPorZona;
import datatypes.EstadoPmo;
import interfaces.IControladorListarPrestamosZona;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ConsultarZonaLectorFrame extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    private static final String[] COLUMNAS_TABLA = {
            "Zona", "Pendientes", "Activos", "Devueltos"
    };

    private JTable tablaResumen;
    private DefaultTableModel modeloTabla;
    private JButton botonCerrar;
    private JButton botonRefrescar;

    private final IControladorListarPrestamosZona controlador;

    public ConsultarZonaLectorFrame(IControladorListarPrestamosZona controlador) {
        this.controlador = controlador;
        initialize();
        cargarResumen();
    }

    private void initialize() {
        setTitle("Resumen de Préstamos por Zona");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setBounds(100, 100, 600, 400);

        JPanel panelContenido = new JPanel(new BorderLayout(0, 10));
        panelContenido.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(panelContenido);

        modeloTabla = new DefaultTableModel(COLUMNAS_TABLA, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaResumen = new JTable(modeloTabla);
        JScrollPane scrollPanel = new JScrollPane(tablaResumen);
        panelContenido.add(scrollPanel, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonRefrescar = new JButton("Refrescar");
        botonRefrescar.addActionListener(e -> cargarResumen());

        botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> setVisible(false));

        panelBotones.add(botonRefrescar);
        panelBotones.add(botonCerrar);

        panelContenido.add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarResumen() {
        modeloTabla.setRowCount(0);

        List<DtEstadoPorZona> resumen = controlador.getResumenPrestamosPorZona();
        for (DtEstadoPorZona dto : resumen) {
            Map<EstadoPmo, Integer> estados = dto.getResumen();
            modeloTabla.addRow(new Object[]{
                    dto.getZona().toString(),
                    estados.get(EstadoPmo.PENDIENTE),
                    estados.get(EstadoPmo.ACTIVO),
                    estados.get(EstadoPmo.DEVUELTO)
            });
        }
    }
}
