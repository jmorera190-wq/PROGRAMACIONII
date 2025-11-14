package inventapro.service;

import inventapro.dao.ProductoDAO;
import inventapro.model.Producto;

import java.util.List;


public class InventarioService {

    private final ProductoDAO dao;
    private int umbralBajoStock = 5;

    public InventarioService(ProductoDAO dao) {
        this.dao = dao;
    }

    public ProductoDAO getDao() {
        return dao;
    }

    public List<Producto> listar() {
        return dao.listar();
    }


    public Producto agregarProducto(String nombre, String categoria, int cantidad, double precio) {

        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }

        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }

        Producto p = new Producto(nombre, categoria, cantidad, precio);
        return dao.insertar(p);
    }


    public void editarProducto(Producto p) {

        if (p.getCantidad() < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }

        if (p.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }

        dao.actualizarProducto(p);
    }

    public void eliminarProducto(int id) {
        dao.eliminarProducto(id);
    }

    public Producto buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public List<Producto> buscarPorNombre(String texto) {
        return dao.buscarPorNombre(texto);
    }


    public void registrarEntrada(Producto p, int cantidad) {

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }

        p.setCantidad(p.getCantidad() + cantidad);
        dao.actualizarProducto(p);
    }


    public boolean registrarSalida(Producto p, int cantidad) {

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }

        if (p.getCantidad() >= cantidad) {
            p.setCantidad(p.getCantidad() - cantidad);
            dao.actualizarProducto(p);
            return true;
        } else {
            return false;
        }
    }



    public List<Producto> obtenerBajoStock() {
        return dao.obtenerBajoStock(umbralBajoStock);
    }

    public void setUmbralBajoStock(int umbralBajoStock) {
        this.umbralBajoStock = umbralBajoStock;
    }

    public int getUmbralBajoStock() {
        return umbralBajoStock;
    }
}
