import java.awt.*;
import javax.swing.*;

public class InterfazImpuestos extends JFrame {
    private JTextField txtMarca, txtLinea, txtModelo, txtValor;
    private JCheckBox chkServicioPublico, chkProntoPago, chkTraslado;
    private JTextArea txtResultado;

    public InterfazImpuestos() {
        setTitle("Liquidación Impuesto Vehicular");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        add(txtMarca);

        add(new JLabel("Línea:"));
        txtLinea = new JTextField();
        add(txtLinea);

        add(new JLabel("Modelo (Año):"));
        txtModelo = new JTextField();
        add(txtModelo);

        add(new JLabel("Valor Comercial:"));
        txtValor = new JTextField();
        add(txtValor);

        chkServicioPublico = new JCheckBox("¿Es servicio público?");
        add(chkServicioPublico);
        add(new JLabel(""));

        chkProntoPago = new JCheckBox("Aplicar descuento pronto pago (10%)");
        chkTraslado = new JCheckBox("Aplicar descuento traslado de cuenta (15%)");
        add(chkProntoPago);
        add(chkTraslado);

        JButton btnCalcular = new JButton("Calcular");
        add(btnCalcular);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        add(new JScrollPane(txtResultado));


        btnCalcular.addActionListener(e -> calcularImpuesto());
    }

    private void calcularImpuesto() {
        try {
            String marca = txtMarca.getText();
            String linea = txtLinea.getText();
            int modelo = Integer.parseInt(txtModelo.getText());
            double valor = Double.parseDouble(txtValor.getText());
            boolean servicioPublico = chkServicioPublico.isSelected();

            Vehiculo v = new Vehiculo(marca, linea, modelo, valor, servicioPublico);
            ImpuestoVehicular impuesto = new ImpuestoVehicular(v, 10, 15, 200000);

            String reporte = impuesto.getReporte(chkProntoPago.isSelected(), chkTraslado.isSelected());
            txtResultado.setText(reporte);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
