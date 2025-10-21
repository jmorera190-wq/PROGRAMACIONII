package VistaJJO;

import ControladorJJO.ControladorAtencion;
import JJO.Cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

    private ControladorAtencion controlador = new ControladorAtencion();

    private JTextField txtId, txtNombre, txtEmail, txtTelefono;
    private JCheckBox chkUrgente;
    private JTextArea areaCola, areaPila, areaInfo;

    public VentanaPrincipal() {
        setTitle("Simulador de Atención - LinkedList (FIFO/LIFO)");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel panelIngreso = new JPanel(new GridLayout(5, 2));
        panelIngreso.setBorder(BorderFactory.createTitledBorder("Registrar Cliente"));

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtEmail = new JTextField();
        txtTelefono = new JTextField();
        chkUrgente = new JCheckBox("Urgente");

        panelIngreso.add(new JLabel("ID:"));
        panelIngreso.add(txtId);
        panelIngreso.add(new JLabel("Nombre:"));
        panelIngreso.add(txtNombre);
        panelIngreso.add(new JLabel("Email:"));
        panelIngreso.add(txtEmail);
        panelIngreso.add(new JLabel("Teléfono:"));
        panelIngreso.add(txtTelefono);
        panelIngreso.add(new JLabel("Tipo de atención:"));
        panelIngreso.add(chkUrgente);

        JButton btnAgregar = new JButton("Agregar Cliente");
        JButton btnAtender = new JButton("Atender Siguiente");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnAtender);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelIngreso, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);


        JPanel panelListas = new JPanel(new GridLayout(1, 2));

        areaCola = new JTextArea();
        areaCola.setEditable(false);
        areaPila = new JTextArea();
        areaPila.setEditable(false);

        panelListas.add(new JScrollPane(areaCola));
        panelListas.add(new JScrollPane(areaPila));

        areaCola.setBorder(BorderFactory.createTitledBorder("Cola Normal (FIFO)"));
        areaPila.setBorder(BorderFactory.createTitledBorder("Pila Urgentes (LIFO)"));

        add(panelListas, BorderLayout.CENTER);

        areaInfo = new JTextArea(4, 40);
        areaInfo.setEditable(false);
        areaInfo.setBorder(BorderFactory.createTitledBorder("Información del Sistema"));
        add(areaInfo, BorderLayout.SOUTH);


        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String nombre = txtNombre.getText();
                    String email = txtEmail.getText();
                    String telefono = txtTelefono.getText();
                    boolean urgente = chkUrgente.isSelected();

                    Cliente c = new Cliente(id, nombre, email, telefono, urgente);
                    controlador.agregarCliente(c);

                    actualizarVista();
                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Datos inválidos");
                }
            }
        });

        btnAtender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente atendido = controlador.atenderSiguiente();
                if (atendido != null)
                    JOptionPane.showMessageDialog(null, "Atendiendo a: " + atendido.getNombre());
                else
                    JOptionPane.showMessageDialog(null, "No hay clientes por atender.");
                actualizarVista();
            }
        });

        setVisible(true);
    }

    private void actualizarVista() {
        areaCola.setText("Cola Normal:\n");
        for (Cliente c : controlador.getColaTurnos()) {
            areaCola.append(c.toString() + "\n");
        }

        areaPila.setText("Pila Urgente:\n");
        for (Cliente c : controlador.getPilaUrgentes()) {
            areaPila.append(c.toString() + "\n");
        }

        areaInfo.setText(
                "Cliente actual: " + (controlador.getClienteActual() != null ? controlador.getClienteActual().getNombre() : "Ninguno") + "\n" +
                        "Atendidos normales: " + controlador.getAtendidosNormales() + "\n" +
                        "Atendidos urgentes: " + controlador.getAtendidosUrgentes() + "\n" +
                        "Máx. en cola: " + controlador.getMaxCola() + "\n" +
                        "Máx. en pila: " + controlador.getMaxPila()
        );
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        chkUrgente.setSelected(false);
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}