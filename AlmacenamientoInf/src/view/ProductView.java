package view;

import controller.ProductController;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ProductView extends JFrame {
    private final ProductController controller;
    private final JTextField txtNombre = new JTextField(15);
    private final JTextField txtDescripcion = new JTextField(15);
    private final JTextField txtPrecio = new JTextField(8);
    private final JTextField txtCantidad = new JTextField(5);
    private final JTextField txtId = new JTextField(5);
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripción", "Precio", "Cantidad"}, 0);
    private final JTable table = new JTable(tableModel);

    public ProductView(ProductController controller) {
        this.controller = controller;
        setTitle("Gestión de Productos - AlmacenamientoInf");
        setSize(750, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel formPanel = new JPanel(new GridLayout(2, 5, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos del Producto"));

        formPanel.add(new JLabel("ID:"));
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(new JLabel("Descripción:"));
        formPanel.add(new JLabel("Precio:"));
        formPanel.add(new JLabel("Cantidad:"));

        formPanel.add(txtId);
        formPanel.add(txtNombre);
        formPanel.add(txtDescripcion);
        formPanel.add(txtPrecio);
        formPanel.add(txtCantidad);

        add(formPanel, BorderLayout.NORTH);

        // Panel central (Tabla)
        JScrollPane scroll = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(scroll, BorderLayout.CENTER);

        // Panel inferior (Botones)
        JPanel btnPanel = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnListar = new JButton("Listar Todo");
        JButton btnLimpiar = new JButton("Limpiar");

        btnPanel.add(btnAgregar);
        btnPanel.add(btnActualizar);
        btnPanel.add(btnEliminar);
        btnPanel.add(btnBuscar);
        btnPanel.add(btnListar);
        btnPanel.add(btnLimpiar);

        add(btnPanel, BorderLayout.SOUTH);


        btnAgregar.addActionListener(this::agregarProducto);
        btnActualizar.addActionListener(this::actualizarProducto);
        btnEliminar.addActionListener(this::eliminarProducto);
        btnBuscar.addActionListener(this::buscarProducto);
        btnListar.addActionListener(e -> listarProductos());
        btnLimpiar.addActionListener(e -> limpiarCampos());


        listarProductos();
    }

    private void agregarProducto(ActionEvent e) {
        try {
            String nombre = txtNombre.getText();
            String desc = txtDescripcion.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            int cant = Integer.parseInt(txtCantidad.getText());

            Product p = controller.crearProducto(nombre, desc, precio, cant);
            JOptionPane.showMessageDialog(this, "Producto agregado: " + p.getId());
            listarProductos();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar producto: " + ex.getMessage());
        }
    }

    private void actualizarProducto(ActionEvent e) {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String desc = txtDescripcion.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            int cant = Integer.parseInt(txtCantidad.getText());

            boolean ok = controller.actualizarProducto(id, nombre, desc, precio, cant);
            JOptionPane.showMessageDialog(this, ok ? "Producto actualizado" : "No encontrado");
            listarProductos();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminarProducto(ActionEvent e) {
        try {
            int id = Integer.parseInt(txtId.getText());
            boolean ok = controller.eliminarProducto(id);
            JOptionPane.showMessageDialog(this, ok ? "Producto eliminado" : "No encontrado");
            listarProductos();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void buscarProducto(ActionEvent e) {
        try {
            int id = Integer.parseInt(txtId.getText());
            var opt = controller.buscarPorId(id);
            if (opt.isPresent()) {
                Product p = opt.get();
                txtNombre.setText(p.getNombre());
                txtDescripcion.setText(p.getDescripcion());
                txtPrecio.setText(String.valueOf(p.getPrecio()));
                txtCantidad.setText(String.valueOf(p.getCantidad()));
                JOptionPane.showMessageDialog(this, "Producto encontrado");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el producto");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage());
        }
    }

    private void listarProductos() {
        tableModel.setRowCount(0);
        List<Product> list = controller.listarProductos();
        for (Product p : list) {
            tableModel.addRow(new Object[]{p.getId(), p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getCantidad()});
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
    }

    public void start() {
    }
}
