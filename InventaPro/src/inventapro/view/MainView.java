package inventapro.view;

import inventapro.dao.ProductoDAO;
import inventapro.dao.UsuarioDAO;
import inventapro.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainView extends JFrame {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ProductoDAO productoDAO = new ProductoDAO();

    private CardLayout layout = new CardLayout();
    private JPanel panelPrincipal = new JPanel(layout);

    // LOGIN
    private JTextField txtUser = new JTextField();
    private JPasswordField txtPass = new JPasswordField();
    private JButton btnLogin = new JButton("Iniciar sesión");

    // AGREGAR PRODUCTO
    private JTextField txtNombre = new JTextField();
    private JTextField txtCategoria = new JTextField();
    private JTextField txtCantidad = new JTextField();
    private JTextField txtPrecio = new JTextField();
    private JButton btnGuardar = new JButton("Guardar Producto");

    // TABLAS
    private DefaultTableModel modeloTablaStock;
    private DefaultTableModel modeloTablaBajoStock;
    private JTable tablaStock = new JTable();
    private JTable tablaBajoStock = new JTable();

    public MainView() {

        setTitle("Inventapro");
        setSize(780, 620);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelLogin = new JPanel(null);
        panelLogin.setBackground(new Color(230, 230, 255));

        JLabel titulo = new JLabel("INVENTAPRO");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setBounds(280, 40, 300, 40);
        panelLogin.add(titulo);

        JLabel sub = new JLabel("Bienvenido, inicia sesión");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sub.setBounds(260, 80, 300, 40);
        panelLogin.add(sub);

        JLabel lbl1 = new JLabel("Usuario:");
        lbl1.setBounds(220, 150, 120, 30);
        panelLogin.add(lbl1);

        txtUser.setBounds(320, 150, 200, 30);
        panelLogin.add(txtUser);

        JLabel lbl2 = new JLabel("Contraseña:");
        lbl2.setBounds(220, 200, 120, 30);
        panelLogin.add(lbl2);

        txtPass.setBounds(320, 200, 200, 30);
        panelLogin.add(txtPass);

        btnLogin.setBounds(320, 260, 200, 40);
        btnLogin.setBackground(new Color(70, 120, 240));
        btnLogin.setForeground(Color.WHITE);
        panelLogin.add(btnLogin);

        btnLogin.addActionListener(e -> {
            String u = txtUser.getText().trim();
            String p = new String(txtPass.getPassword()).trim();

            if (usuarioDAO.login(u, p)) {
                layout.show(panelPrincipal, "menu");
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }
        });

        JPanel panelMenu = new JPanel(new BorderLayout());

        JPanel barraMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        barraMenu.setBackground(new Color(40, 40, 80));

        JButton btnAdd = new JButton("Agregar Producto");
        JButton btnStock = new JButton("Stock");
        JButton btnBajo = new JButton("Bajo Stock");

        JButton[] btns = {btnAdd, btnStock, btnBajo};

        for (JButton b : btns) {
            b.setPreferredSize(new Dimension(160, 35));
            b.setBackground(new Color(90, 90, 160));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
        }

        barraMenu.add(btnAdd);
        barraMenu.add(btnStock);
        barraMenu.add(btnBajo);
        panelMenu.add(barraMenu, BorderLayout.NORTH);

        JPanel vistas = new JPanel(new CardLayout());

        JPanel panelAdd = new JPanel(null);

        JLabel ln = new JLabel("Nombre:");
        ln.setBounds(70, 40, 120, 30);
        panelAdd.add(ln);
        txtNombre.setBounds(170, 40, 200, 30);
        panelAdd.add(txtNombre);

        JLabel lc = new JLabel("Categoría:");
        lc.setBounds(70, 90, 120, 30);
        panelAdd.add(lc);
        txtCategoria.setBounds(170, 90, 200, 30);
        panelAdd.add(txtCategoria);

        JLabel lq = new JLabel("Cantidad:");
        lq.setBounds(70, 140, 120, 30);
        panelAdd.add(lq);
        txtCantidad.setBounds(170, 140, 200, 30);
        panelAdd.add(txtCantidad);

        JLabel lp = new JLabel("Precio:");
        lp.setBounds(70, 190, 120, 30);
        panelAdd.add(lp);
        txtPrecio.setBounds(170, 190, 200, 30);
        panelAdd.add(txtPrecio);

        btnGuardar.setBounds(170, 250, 200, 40);
        btnGuardar.setBackground(new Color(0, 128, 255));
        btnGuardar.setForeground(Color.WHITE);
        panelAdd.add(btnGuardar);

        btnGuardar.addActionListener(e -> guardarProducto());

        modeloTablaStock = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Categoría", "Cantidad", "Precio"}, 0
        );
        tablaStock.setModel(modeloTablaStock);

        JPanel panelStock = new JPanel(new BorderLayout());
        panelStock.add(new JScrollPane(tablaStock), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");

        btnModificar.setBackground(new Color(255, 165, 0));
        btnModificar.setForeground(Color.WHITE);

        btnEliminar.setBackground(new Color(200, 0, 0));
        btnEliminar.setForeground(Color.WHITE);

        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        panelStock.add(panelBotones, BorderLayout.SOUTH);

        btnModificar.addActionListener(e -> modificarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());

        modeloTablaBajoStock = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Categoría", "Cantidad", "Precio"}, 0
        );
        tablaBajoStock.setModel(modeloTablaBajoStock);

        JPanel panelBajo = new JPanel(new BorderLayout());
        panelBajo.add(new JScrollPane(tablaBajoStock), BorderLayout.CENTER);

        vistas.add(panelAdd, "add");
        vistas.add(panelStock, "stock");
        vistas.add(panelBajo, "bajo");

        panelMenu.add(vistas, BorderLayout.CENTER);

        btnAdd.addActionListener(e -> ((CardLayout) vistas.getLayout()).show(vistas, "add"));
        btnStock.addActionListener(e -> {
            cargarTablaStock();
            ((CardLayout) vistas.getLayout()).show(vistas, "stock");
        });
        btnBajo.addActionListener(e -> {
            cargarTablaBajoStock();
            ((CardLayout) vistas.getLayout()).show(vistas, "bajo");
        });

        panelPrincipal.add(panelLogin, "login");
        panelPrincipal.add(panelMenu, "menu");

        add(panelPrincipal);
        layout.show(panelPrincipal, "login");
    }

    // ------------------------------------------------------
    //  VALIDACIÓN Y GUARDADO DE PRODUCTO
    // ------------------------------------------------------
    private void guardarProducto() {
        try {
            String nombre = txtNombre.getText().trim();
            String categoria = txtCategoria.getText().trim();
            String scantidad = txtCantidad.getText().trim();
            String sprecio = txtPrecio.getText().trim();

            if (nombre.isEmpty() || categoria.isEmpty() ||
                    scantidad.isEmpty() || sprecio.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Complete todos los campos.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int cant = Integer.parseInt(scantidad);
            double precio = Double.parseDouble(sprecio);

            if (cant < 0) {
                JOptionPane.showMessageDialog(this,
                        "La cantidad no puede ser negativa.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (precio < 0) {
                JOptionPane.showMessageDialog(this,
                        "El precio no puede ser negativo.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Producto p = new Producto(nombre, categoria, cant, precio);
            productoDAO.insertar(p);

            JOptionPane.showMessageDialog(this, "Producto guardado correctamente.");

            txtNombre.setText("");
            txtCategoria.setText("");
            txtCantidad.setText("");
            txtPrecio.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Cantidad y precio deben ser valores numéricos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // ------------------------------------------------------
    //  MODIFICAR PRODUCTO CON VALIDACIÓN
    // ------------------------------------------------------
    private void modificarProducto() {
        int fila = tablaStock.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto.");
            return;
        }

        int id = (int) tablaStock.getValueAt(fila, 0);
        String nombre = (String) tablaStock.getValueAt(fila, 1);
        String categoria = (String) tablaStock.getValueAt(fila, 2);
        int cantidad = (int) tablaStock.getValueAt(fila, 3);
        double precio = (double) tablaStock.getValueAt(fila, 4);

        JTextField txtN = new JTextField(nombre);
        JTextField txtC = new JTextField(categoria);
        JTextField txtQ = new JTextField(String.valueOf(cantidad));
        JTextField txtP = new JTextField(String.valueOf(precio));

        Object[] campos = {
                "Nombre:", txtN,
                "Categoría:", txtC,
                "Cantidad:", txtQ,
                "Precio:", txtP
        };

        int opcion = JOptionPane.showConfirmDialog(this, campos,
                "Modificar producto", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                int nuevaCant = Integer.parseInt(txtQ.getText().trim());
                double nuevoPrecio = Double.parseDouble(txtP.getText().trim());

                if (nuevaCant < 0) {
                    JOptionPane.showMessageDialog(this,
                            "La cantidad no puede ser negativa.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (nuevoPrecio < 0) {
                    JOptionPane.showMessageDialog(this,
                            "El precio no puede ser negativo.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Producto p = productoDAO.buscarPorId(id);

                p.setNombre(txtN.getText().trim());
                p.setCategoria(txtC.getText().trim());
                p.setCantidad(nuevaCant);
                p.setPrecio(nuevoPrecio);

                productoDAO.actualizarProducto(p);
                cargarTablaStock();

                JOptionPane.showMessageDialog(this, "Producto modificado.");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Cantidad y precio deben ser valores numéricos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // ------------------------------------------------------
    //  ELIMINAR PRODUCTO
    // ------------------------------------------------------
    private void eliminarProducto() {
        int fila = tablaStock.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto.");
            return;
        }

        int id = (int) tablaStock.getValueAt(fila, 0);

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Eliminar este producto?",
                "Confirmar acción", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            productoDAO.eliminarProducto(id);
            cargarTablaStock();
            JOptionPane.showMessageDialog(this, "Producto eliminado.");
        }
    }

    // ------------------------------------------------------
    //  TABLAS
    // ------------------------------------------------------
    private void cargarTablaStock() {
        modeloTablaStock.setRowCount(0);
        for (Producto p : productoDAO.listar()) {
            modeloTablaStock.addRow(new Object[]{
                    p.getId(), p.getNombre(), p.getCategoria(), p.getCantidad(), p.getPrecio()
            });
        }
    }

    private void cargarTablaBajoStock() {
        modeloTablaBajoStock.setRowCount(0);
        for (Producto p : productoDAO.obtenerBajoStock(5)) {
            modeloTablaBajoStock.addRow(new Object[]{
                    p.getId(), p.getNombre(), p.getCategoria(), p.getCantidad(), p.getPrecio()
            });
        }
    }
}
