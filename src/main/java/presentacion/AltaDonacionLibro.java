package presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Date;

import datatypes.DtLibro;
import interfaces.IControladorAltaDonacionLibro;

public class AltaDonacionLibro extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtTitulo;
    private JTextField txtCantidadPaginas;
    private JButton btnRegistrar;
    private JButton btnLimpiar;

    private Principal principal;
    private IControladorAltaDonacionLibro controlador;

    public AltaDonacionLibro(IControladorAltaDonacionLibro controlador, Principal principal) {
        this.controlador = controlador;
        this.principal = principal;
        initialize();
    }

    private void initialize() {
        setTitle("Registrar libro");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setBounds(100, 100, 450, 250);
        getContentPane().setLayout(null);

        JLabel lblTituloPantalla = new JLabel("Registrar nueva donación de libro");
        lblTituloPantalla.setBounds(100, 10, 250, 20);
        getContentPane().add(lblTituloPantalla);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(30, 50, 100, 20);
        getContentPane().add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(140, 50, 250, 20);
        getContentPane().add(txtTitulo);
        txtTitulo.setColumns(10);

        JLabel lblPaginas = new JLabel("Cantidad de páginas:");
        lblPaginas.setBounds(30, 90, 150, 20);
        getContentPane().add(lblPaginas);

        txtCantidadPaginas = new JTextField();
        txtCantidadPaginas.setBounds(180, 90, 80, 20);
        getContentPane().add(txtCantidadPaginas);
        txtCantidadPaginas.setColumns(10);

        btnRegistrar = new JButton("Registrar Donación");
        btnRegistrar.setBounds(80, 150, 140, 25);
        btnRegistrar.addActionListener(this::registrarDonacion);
        getContentPane().add(btnRegistrar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(240, 150, 100, 25);
        btnLimpiar.addActionListener(e -> limpiarCampos());
        getContentPane().add(btnLimpiar);
    }

    private void registrarDonacion(ActionEvent e) {
        try {
            if (txtTitulo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El título es obligatorio");
                return;
            }

            int paginas = Integer.parseInt(txtCantidadPaginas.getText().trim());
            if (paginas <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad de páginas debe ser mayor a 0");
                return;
            }

            DtLibro dtLibro = new DtLibro(0,
                    txtTitulo.getText().trim(),
                    paginas,
                    new Date()
            );

            controlador.altaDonacionLibro(dtLibro);

            JOptionPane.showMessageDialog(this, "Donación de libro registrada exitosamente");
            principal.actualizarInternalFrames();
            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para cantidad de páginas");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar la donación: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        txtTitulo.setText("");
        txtCantidadPaginas.setText("");
        txtTitulo.requestFocus();
    }
}
