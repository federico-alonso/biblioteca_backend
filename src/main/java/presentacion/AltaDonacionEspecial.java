package presentacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import datatypes.DtArticuloEspecial;
import interfaces.IControladorMaterial;
import datatypes.DtArticuloEspecial;

public class AltaDonacionEspecial extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    private JTextField txtDescripcion;
    private JTextField txtPeso;
    private JTextField txtDimensiones;
    private JButton btnRegistrar;
    private JButton btnLimpiar;
    private Principal principal;
    private IControladorMaterial controlador;

    public AltaDonacionEspecial(IControladorMaterial controlador, Principal principal) {
        this.controlador = controlador;
        this.principal = principal;
        initialize();
    }

    private void initialize() {
        setTitle("Alta Donación Especial");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setBounds(100, 100, 500, 340);
        
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        // Panel principal con GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Título
        JLabel lblTitulo = new JLabel("Registrar Nueva Donación de Artículo Especial");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(lblTitulo, gbc);
        
        // Descripción
        JLabel lblDescripcion = new JLabel("Descripción:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblDescripcion, gbc);
        
        txtDescripcion = new JTextField(20);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtDescripcion, gbc);
        
        // Peso
        JLabel lblPeso = new JLabel("Peso (kg):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(lblPeso, gbc);
        
        txtPeso = new JTextField(10);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtPeso, gbc);

        // Dimensiones
        JLabel lblDimensiones = new JLabel("Dimensiones (ej: 10x20x30 cm):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(lblDimensiones, gbc);

        txtDimensiones = new JTextField(20);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtDimensiones, gbc);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        btnRegistrar = new JButton("Registrar Donación");
        btnRegistrar.setBackground(new Color(46, 204, 113));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarDonacion();
            }
        });
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBackground(new Color(52, 152, 219));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        
        buttonPanel.add(btnRegistrar);
        buttonPanel.add(btnLimpiar);
        
        // Agregar paneles al frame
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        // Agregar padding al panel principal
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
    
    private void registrarDonacion() {
        try {
            // Validar campos
            if (txtDescripcion.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "La descripción es obligatoria", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            float peso = Float.parseFloat(txtPeso.getText().trim());
            if (peso <= 0) {
                JOptionPane.showMessageDialog(this, "El peso debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validar dimensiones (opcional pero recomendado)
            if (txtDimensiones.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Las dimensiones son obligatorias", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear objeto de datos (descripción, peso y dimensiones)
            DtArticuloEspecial dtArticulo = new DtArticuloEspecial(0,
                null,
                txtDescripcion.getText().trim(),
                peso,
                txtDimensiones.getText().trim()
            );
            
            // Registrar la donación
            controlador.altaDonacionEspecial(dtArticulo);
            
            JOptionPane.showMessageDialog(this, 
                "Donación registrada exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            principal.actualizarInternalFrames();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, ingrese valores numéricos válidos para peso y dimensiones", 
                "Error de formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al registrar la donación: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        txtDescripcion.setText("");
        txtPeso.setText("");
        txtDimensiones.setText("");
        txtDescripcion.requestFocus();
    }
}
