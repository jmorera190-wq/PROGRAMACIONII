package controller;

import model.Product;
import repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductController {
    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    public Product crearProducto(String nombre, String descripcion, double precio, int cantidad) {
        return repo.create(nombre, descripcion, precio, cantidad);
    }

    public List<Product> listarProductos() {
        return repo.findAll();
    }

    public Optional<Product> buscarPorId(int id) {
        return repo.findById(id);
    }

    public boolean actualizarProducto(int id, String nombre, String descripcion, double precio, int cantidad) {
        return repo.update(id, nombre, descripcion, precio, cantidad);
    }

    public boolean eliminarProducto(int id) {
        return repo.delete(id);
    }
}
