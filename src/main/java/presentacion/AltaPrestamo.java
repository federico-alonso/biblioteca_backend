package presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import datatypes.*;
import excepciones.PrestamoYaExisteExcepcion;
import interfaces.IControladorPrestamo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AltaPrestamo extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    private IControladorPrestamo icon;

    private JComboBox<String> comboLector;
    private JComboBox<String> comboBibliotecario;
    private JComboBox<String> comboMaterial;
    private JComboBox<EstadoPmo> comboEstado;
    private JSpinner spinnerFechaSolicitud;
    private JSpinner spinnerFechaDevolucion;

    public AltaPrestamo(IControladorPrestamo icon) {
        this.icon = icon;

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setTitle("Alta de Prestamo");
        setBounds(100, 100, 500, 400);
        getContentPane().setLayout(null);

        // Lector
        JLabel lblLector = new JLabel("Lector:");
        lblLector.setBounds(10, 10, 100, 20);
        getContentPane().add(lblLector);

        comboLector = new JComboBox<>();
        comboLector.setBounds(120, 10, 200, 25);
        getContentPane().add(comboLector);

        // Bibliotecario
        JLabel lblBiblio = new JLabel("Bibliotecario:");
        lblBiblio.setBounds(10, 40, 100, 20);
        getContentPane().add(lblBiblio);

        comboBibliotecario = new JComboBox<>();
        comboBibliotecario.setBounds(120, 40, 200, 25);
        getContentPane().add(comboBibliotecario);

        // Material
        JLabel lblMaterial = new JLabel("Material:");
        lblMaterial.setBounds(10, 70, 100, 20);
        getContentPane().add(lblMaterial);

        comboMaterial = new JComboBox<>();
        comboMaterial.setBounds(120, 70, 200, 25);
        getContentPane().add(comboMaterial);


        // Fecha de solicitud
        JLabel lblFechaSol = new JLabel("Fecha Solicitud:");
        lblFechaSol.setBounds(10, 110, 120, 20);
        getContentPane().add(lblFechaSol);

        spinnerFechaSolicitud = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        spinnerFechaSolicitud.setBounds(140, 110, 180, 25);
        getContentPane().add(spinnerFechaSolicitud);

        // Fecha de devolución
        JLabel lblFechaDev = new JLabel("Fecha Devolucion:");
        lblFechaDev.setBounds(10, 140, 120, 20);
        getContentPane().add(lblFechaDev);

        spinnerFechaDevolucion = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        spinnerFechaDevolucion.setBounds(140, 140, 180, 25);
        getContentPane().add(spinnerFechaDevolucion);

        // Estado
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(10, 180, 100, 20);
        getContentPane().add(lblEstado);

        comboEstado = new JComboBox<>(EstadoPmo.values());
        comboEstado.setBounds(120, 180, 200, 25);
        getContentPane().add(comboEstado);

        cargarDatos();

        // Botones
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(120, 230, 100, 25);
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aceptarActionPerformed(e);
            }
        });
        getContentPane().add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(230, 230, 100, 25);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelarActionPerformed(e);
            }
        });
        getContentPane().add(btnCancelar);
    }

    public void cargarDatos(){
        ArrayList<String> listaLector = new ArrayList<>();
        for(DtLector dt : icon.getListadoLectores()) {
            if(dt.getEstado() == EstadoLector.ACTIVO){
                listaLector.add(dt.getNombre());
            }
        }
            habilitarAutocompletado(comboLector, listaLector);

        ArrayList<String> listaBibliotecario = new ArrayList<>();
        for(DtBibliotecario dt : icon.getListadoBibliotecarios()) listaBibliotecario.add(dt.getNombre());
        habilitarAutocompletado(comboBibliotecario, listaBibliotecario);

        ArrayList<String> listaMateriales = new ArrayList<>();
        for(DtMaterial dt : icon.getListadoMateriales()){
            if(dt instanceof DtLibro)
                listaMateriales.add(dt.getId() + "-" + ((DtLibro) dt).getTitulo());
            else
                listaMateriales.add(dt.getId() + "-" + ((DtArticuloEspecial) dt).getDescripcion());
        }
        habilitarAutocompletado(comboMaterial, listaMateriales);
    }

    private void aceptarActionPerformed(ActionEvent e) {
        String lector = (String) comboLector.getSelectedItem();
        String biblio = (String) comboBibliotecario.getSelectedItem();
        String[] material = ((String) comboMaterial.getSelectedItem()).split("-", 2);
        long idMaterial = Long.parseLong(material[0]);
        Date fechaSol = (Date) spinnerFechaSolicitud.getValue();
        Date fechaDev = (Date) spinnerFechaDevolucion.getValue();
        EstadoPmo estado = (EstadoPmo) comboEstado.getSelectedItem();

        if (checkFormulario()) {
            // Llamada al controlador para crear el préstamo
            try {
                icon.altaPrestamo(new DtPrestamo(new DtMaterial(idMaterial, null),
                        new DtLector(lector, null, null, null, null, null),
                        new DtBibliotecario(biblio, null, null, 0),
                        fechaSol, fechaDev, estado)
                );
                JOptionPane.showMessageDialog(this, "Préstamo agregado al sistema.");
                limpiarFormulario();
                setVisible(false);
            } catch (PrestamoYaExisteExcepcion ex) {
                //throw new RuntimeException(ex);
                JOptionPane.showMessageDialog(this,  ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void cancelarActionPerformed(ActionEvent e) {
        limpiarFormulario();
        setVisible(false);
    }

    private void limpiarFormulario() {
        comboLector.setSelectedIndex(-1);
        comboBibliotecario.setSelectedIndex(-1);
        comboMaterial.setSelectedIndex(-1);
        comboEstado.setSelectedIndex(0);
        spinnerFechaSolicitud.setValue(new Date());
        spinnerFechaDevolucion.setValue(new Date());
    }

    private boolean checkFormulario() {
        if (comboLector.getSelectedItem() == null ||
                comboBibliotecario.getSelectedItem() == null ||
                comboMaterial.getSelectedItem() == null ||
                comboEstado.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Hay parámetros en blanco");
            return false;
        }
        return true;
    }

    private void habilitarAutocompletado(JComboBox<String> combo, ArrayList<String> listaOpciones) {
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



