package app;

import controller.ProductController;
import repository.ProductRepository;
import view.ProductView;

public class Main {
    public static void main(String[] args) {
        String filepath = "products.dat";

        ProductRepository repo = new ProductRepository(filepath);
        ProductController controller = new ProductController(repo);
        ProductView view = new ProductView(controller);

        view.setVisible(true);

    }
}
