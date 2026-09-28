package com.gitb.juandaaguilera.Envios;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class IGEnvios extends JFrame {

    private static final String ESTANDAR = "Estándar";
    private static final String EXPRESS = "Express";
    private static final String INTERNACIONAL = "Internacional";

    Registros registros;
    DefaultTableModel modeloTabla;
    DecimalFormat formatoMoneda;
    private JComboBox<String> comboModalidad;
    private JTextField campoCodigo;
    private JTextField campoDestinatario;
    private JTextField campoPeso;
    private JLabel etiquetaDireccion;
    private JTextField campoDireccion;
    private JLabel etiquetaHoraLimite;
    private JTextField campoHoraLimite;
    private JLabel etiquetaPais;
    private JTextField campoPais;

    private JButton btnRegistrar;
    private JTable tablaEnvios;

    public IGEnvios() {
        this.registros = new Registros();

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.forLanguageTag("es-CO"));
        simbolos.setGroupingSeparator('.');
        this.formatoMoneda = new DecimalFormat("$ #,##0", simbolos);

        initComponents();

        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Destinatario");
        modeloTabla.addColumn("Peso (kg)");
        modeloTabla.addColumn("Dato adicional");
        modeloTabla.addColumn("Costo");
        tablaEnvios.setModel(modeloTabla);

        this.actualizarCamposVisibles();
    }

    private void initComponents() {
        setTitle("Sistema de gestión de envíos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        comboModalidad = new JComboBox<>(new String[]{ESTANDAR, EXPRESS, INTERNACIONAL});
        comboModalidad.setName("comboModalidad");

        campoCodigo = new JTextField(14);
        campoCodigo.setName("campoCodigo");
        campoDestinatario = new JTextField(14);
        campoDestinatario.setName("campoDestinatario");
        campoPeso = new JTextField(14);
        campoPeso.setName("campoPeso");

        etiquetaDireccion = new JLabel("Dirección de entrega");
        campoDireccion = new JTextField(14);
        campoDireccion.setName("campoDireccion");
        etiquetaHoraLimite = new JLabel("Hora límite de entrega");
        campoHoraLimite = new JTextField(14);
        campoHoraLimite.setName("campoHoraLimite");
        etiquetaPais = new JLabel("País destino");
        campoPais = new JTextField(14);
        campoPais.setName("campoPais");

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setName("btnRegistrar");

        tablaEnvios = new JTable();
        tablaEnvios.setName("tablaEnvios");
        JScrollPane panelTabla = new JScrollPane(tablaEnvios);
        panelTabla.setBorder(BorderFactory.createTitledBorder("Envíos registrados"));
        comboModalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboModalidadActionPerformed(evt);
            }
        });

        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del envío"));

        this.agregarFila(panelForm, 0, new JLabel("Modalidad"), comboModalidad);
        this.agregarFila(panelForm, 1, new JLabel("Código"), campoCodigo);
        this.agregarFila(panelForm, 2, new JLabel("Destinatario"), campoDestinatario);
        this.agregarFila(panelForm, 3, new JLabel("Peso (kg)"), campoPeso);
        this.agregarFila(panelForm, 4, etiquetaDireccion, campoDireccion);
        this.agregarFila(panelForm, 4, etiquetaHoraLimite, campoHoraLimite);
        this.agregarFila(panelForm, 4, etiquetaPais, campoPais);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 6, 6, 6);
        panelForm.add(btnRegistrar, gbc);

        getContentPane().setLayout(new BorderLayout(8, 8));
        getContentPane().add(panelForm, BorderLayout.NORTH);
        getContentPane().add(panelTabla, BorderLayout.CENTER);

        setSize(760, 520);
        setLocationRelativeTo(null);
    }

    private void agregarFila(JPanel panel, int fila, JLabel etiqueta, java.awt.Component campo) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = fila;
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        panel.add(etiqueta, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(campo, gbc);
    }

    private void comboModalidadActionPerformed(java.awt.event.ActionEvent evt) {
        this.actualizarCamposVisibles();
    }

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {
        String modalidad = (String) comboModalidad.getSelectedItem();
        String codigo = campoCodigo.getText().trim();
        String destinatario = campoDestinatario.getText().trim();
        String textoPeso = campoPeso.getText().trim().replace(',', '.');

        if (codigo.isEmpty() || destinatario.isEmpty() || textoPeso.isEmpty()) {
            this.mostrarAdvertencia("Complete el código, el destinatario y el peso.");
            return;
        }

        double peso;
        try {
            peso = Double.parseDouble(textoPeso);
        } catch (NumberFormatException ex) {
            this.mostrarAdvertencia("El peso debe ser un número (por ejemplo 4 o 2.5).");
            return;
        }
        if (peso <= 0) {
            this.mostrarAdvertencia("El peso debe ser mayor que cero.");
            return;
        }
        Envio envio;
        if (modalidad.equals(ESTANDAR)) {
            String direccion = campoDireccion.getText().trim();
            if (direccion.isEmpty()) {
                this.mostrarAdvertencia("Ingrese la dirección de entrega.");
                return;
            }
            envio = new EnvioEstandar(codigo, destinatario, peso, direccion);
        } else if (modalidad.equals(EXPRESS)) {
            String horaLimite = campoHoraLimite.getText().trim();
            if (horaLimite.isEmpty()) {
                this.mostrarAdvertencia("Ingrese la hora límite de entrega.");
                return;
            }
            envio = new EnvioExpress(codigo, destinatario, peso, horaLimite);
        } else {
            String pais = campoPais.getText().trim();
            if (pais.isEmpty()) {
                this.mostrarAdvertencia("Ingrese el país destino.");
                return;
            }
            envio = new EnvioInternacional(codigo, destinatario, peso, pais);
        }

        if (this.registros.existeCodigo(codigo)) {
            this.mostrarAdvertencia("Ya existe un envío con el código " + codigo + ".");
            return;
        }

        this.registros.agregarEnvio(envio);
        this.actualizarTabla();
        this.mostrarMensaje("Envío " + envio.getCodigo() + " (" + envio.getTipo()
                + ") registrado con éxito.\nCosto: " + formatoMoneda.format(envio.calcularCosto()));
        this.vaciarCampos();
    }

    private void actualizarCamposVisibles() {
        String modalidad = (String) comboModalidad.getSelectedItem();

        boolean estandar = ESTANDAR.equals(modalidad);
        boolean express = EXPRESS.equals(modalidad);
        boolean internacional = INTERNACIONAL.equals(modalidad);

        etiquetaDireccion.setVisible(estandar);
        campoDireccion.setVisible(estandar);

        etiquetaHoraLimite.setVisible(express);
        campoHoraLimite.setVisible(express);

        etiquetaPais.setVisible(internacional);
        campoPais.setVisible(internacional);

        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void vaciarCampos() {
        campoCodigo.setText("");
        campoDestinatario.setText("");
        campoPeso.setText("");
        campoDireccion.setText("");
        campoHoraLimite.setText("");
        campoPais.setText("");
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Registro de envíos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Datos incompletos", JOptionPane.WARNING_MESSAGE);
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        ArrayList<Envio> envios = this.registros.obtenerEnvios();
        for (Envio e : envios) {
            modeloTabla.addRow(new Object[]{
                e.getCodigo(),
                e.getTipo(),
                e.getDestinatario(),
                e.getPeso(),
                e.getDatoAdicional(),
                formatoMoneda.format(e.calcularCosto())
            });
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(IGEnvios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IGEnvios().setVisible(true);
            }
        });
    }
}
