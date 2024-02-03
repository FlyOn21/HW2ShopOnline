package app.views;

import app.entity.Product;
import app.utilseshop.ProductsJsonPojo;
import java.io.IOException;
import java.util.List;
import app.config.Config;

public class ProductsView {

    public void showProducts() {
        try {
            // Load products from JSON file
            List<Product> products = ProductsJsonPojo.productsJsonToPojo(Config.PATH_PRODUCTS_JSON);

            // Check if there are any products
            if (products == null || products.isEmpty()) {
                System.out.println("No products available.");
                return;
            }

            // Display each product
            System.out.println("Available products:");
            for (Product product : products) {
                System.out.println("--------------------------------");
                System.out.printf("Model: %s\n", product.getModel());
                System.out.printf("Price: %.2f "+Config.CURRENCY+"\n", product.getPrice());
                System.out.printf("Colors: %s\n", product.getColors());
                System.out.printf("Quantity: %d\n", product.getQuantity());
                System.out.printf("Type: %s\n", product.getType());
                System.out.println("--------------------------------");
            }
        } catch (IOException e) {
            System.err.println("Failed to load products: " + e.getMessage());
        }
    }
    public static void main(String[] args) {


        ProductsView productsView = new ProductsView();

        productsView.showProducts();
    }
}


