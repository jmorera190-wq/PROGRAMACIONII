package repository;

import model.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private List<Product> products;
    private final File storageFile;
    private int nextId = 1;

    public ProductRepository(String filepath) {
        this.storageFile = new File(filepath);
        this.products = new ArrayList<>();
        loadFromFile();
        calculateNextId();
    }

    private void calculateNextId() {
        int max = 0;
        for (Product p : products) {
            if (p.getId() > max) max = p.getId();
        }
        nextId = max + 1;
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        if (!storageFile.exists()) {
            products = new ArrayList<>();
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storageFile))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                products = (List<Product>) obj;
            } else {
                products = new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No se pudo leer el archivo de datos: " + e.getMessage());
            products = new ArrayList<>();
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storageFile))) {
            oos.writeObject(products);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public synchronized Product create(String nombre, String descripcion, double precio, int cantidad) {
        Product p = new Product(nextId++, nombre, descripcion, precio, cantidad);
        products.add(p);
        saveToFile();
        return p;
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst();
    }

    public synchronized boolean update(int id, String nombre, String descripcion, double precio, int cantidad) {
        Optional<Product> opt = findById(id);
        if (!opt.isPresent()) return false;
        Product p = opt.get();
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        saveToFile();
        return true;
    }

    public synchronized boolean delete(int id) {
        boolean removed = products.removeIf(p -> p.getId() == id);
        if (removed) saveToFile();
        return removed;
    }
}
