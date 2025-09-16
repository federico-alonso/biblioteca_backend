package presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;

import datatypes.DtArticuloEspecial;
import interfaces.IControladorAltaDonacionEspecial;

public class AltaDonacionEspecial extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtDescripcion;
    private JTextField txtPeso;
    private JTextField txtDimensiones;
    private JButton btnRegistrar;
    private JButton btnLimpiar;
    private Principal principal;
    private IControladorAltaDonacionEspecial controlador;

    public AltaDonacionEspecial(IControladorAltaDonacionEspecial controlador, Principal principal) {
        this.controlador = controlador;
        this.principal = principal;
        initialize();
    }

    private void initialize() {
        setTitle("Registrar artículo especial");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        JLabel lblTituloPantalla = new JLabel("Registrar nueva donación de artículo especial");
        lblTituloPantalla.setBounds(50, 10, 350, 20);
        getContentPane().add(lblTituloPantalla);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(30, 50, 100, 20);
        getContentPane().add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(140, 50, 250, 20);
        getContentPane().add(txtDescripcion);

        JLabel lblPeso = new JLabel("Peso (kg):");
        lblPeso.setBounds(30, 90, 100, 20);
        getContentPane().add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(140, 90, 100, 20);
        getContentPane().add(txtPeso);

        JLabel lblDimensiones = new JLabel("Dimensiones:");
        lblDimensiones.setBounds(30, 130, 100, 20);
        getContentPane().add(lblDimensiones);

        txtDimensiones = new JTextField();
        txtDimensiones.setBounds(140, 130, 250, 20);
        getContentPane().add(txtDimensiones);

        btnRegistrar = new JButton("Registrar Donación");
        btnRegistrar.setBounds(80, 200, 140, 25);
        btnRegistrar.addActionListener(this::registrarDonacion);
        getContentPane().add(btnRegistrar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(240, 200, 100, 25);
        btnLimpiar.addActionListener(e -> limpiarCampos());
        getContentPane().add(btnLimpiar);
    }

    private void registrarDonacion(ActionEvent e) {
        try {
            if (txtDescripcion.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "La descripción es obligatoria");
                return;
            }

            float peso = Float.parseFloat(txtPeso.getText().trim());
            if (peso <= 0) {
                JOptionPane.showMessageDialog(this, "El peso debe ser mayor a 0");
                return;
            }

            if (txtDimensiones.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Las dimensiones son obligatorias");
                return;
            }

            DtArticuloEspecial dtArticulo = new DtArticuloEspecial(0,
                    null,
                    txtDescripcion.getText().trim(),
                    peso,
                    txtDimensiones.getText().trim()
            );

            controlador.altaDonacionEspecial(dtArticulo);

            JOptionPane.showMessageDialog(this, "Donación registrada exitosamente");
            principal.actualizarInternalFrames();
            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos para peso");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar la donación: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        txtDescripcion.setText("");
        txtPeso.setText("");
        txtDimensiones.setText("");
        txtDescripcion.requestFocus();
    }
}
