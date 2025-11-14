package inventapro.dao;

import inventapro.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private final List<Producto> productos = new ArrayList<>();
    private int ultimoId = 1;


    public List<Producto> listar() {
        return new ArrayList<>(productos);
    }


    public Producto insertar(Producto p) {
        p.setId(ultimoId++);
        productos.add(p);
        return p;
    }


    public Producto crearYAgregar(String nombre, String categoria, int cantidad, double precio) {
        Producto p = new Producto(ultimoId++, nombre, categoria, cantidad, precio);
        productos.add(p);
        return p;
    }


    public void actualizarProducto(Producto p) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == p.getId()) {
                productos.set(i, p);
                return;
            }
        }
    }


    public void eliminarProducto(int id) {
        productos.removeIf(p -> p.getId() == id);
    }


    public Producto buscarPorId(int id) {
        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public List<Producto> buscarPorNombre(String texto) {
        String t = texto.toLowerCase();
        List<Producto> resultado = new ArrayList<>();

        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(t)) {
                resultado.add(p);
            }
        }
        return resultado;
    }


    public List<Producto> obtenerBajoStock(int umbral) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCantidad() <= umbral) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
