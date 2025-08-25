package presentacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interfaces.IControladorMaterial;
import datatypes.DtLibro;

public class AltaDonacionLibro extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtTitulo;
    private JTextField txtCantidadPaginas;
    private JButton btnRegistrar;
    private JButton btnLimpiar;

    private IControladorMaterial controlador;

    public AltaDonacionLibro(IControladorMaterial controlador) {
        this.controlador = controlador;
        initialize();
    }

    private void initialize() {
        setTitle("Alta Donación de Libro");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setBounds(120, 120, 500, 260);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblTituloPantalla = new JLabel("Registrar Nueva Donación de Libro");
        lblTituloPantalla.setFont(new Font("Arial", Font.BOLD, 16));
        lblTituloPantalla.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(lblTituloPantalla, gbc);

        JLabel lblTitulo = new JLabel("Título:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblTitulo, gbc);

        txtTitulo = new JTextField(22);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtTitulo, gbc);

        JLabel lblPaginas = new JLabel("Cantidad de páginas:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(lblPaginas, gbc);

        txtCantidadPaginas = new JTextField(10);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtCantidadPaginas, gbc);

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

        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void registrarDonacion() {
        try {
            if (txtTitulo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El título es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int paginas = Integer.parseInt(txtCantidadPaginas.getText().trim());
            if (paginas <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad de páginas debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DtLibro dtLibro = new DtLibro(
                txtTitulo.getText().trim(),
                paginas
            );

            controlador.altaDonacionLibro(dtLibro);

            JOptionPane.showMessageDialog(this,
                "Donación de libro registrada exitosamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Por favor, ingrese un número válido para cantidad de páginas",
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
        txtTitulo.setText("");
        txtCantidadPaginas.setText("");
        txtTitulo.requestFocus();
    }
}


