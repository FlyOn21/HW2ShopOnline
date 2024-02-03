package app.model.eshopmodel;

import app.config.Config;
import app.entity.Product;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import app.utilseshop.ProductsJsonPojo;

public class ProductModel {


    public static List<Product> loadProducts() throws IOException {
        return ProductsJsonPojo.productsJsonToPojo(Config.PATH_PRODUCTS_JSON);
    }

    public static void saveProducts(List<Product> products) throws IOException {
        ProductsJsonPojo.productsPogoToJson(Config.PATH_PRODUCTS_JSON, products);
    }

    public static boolean decreaseProductQuantity(String model, int decreaseBy) throws IOException {
        List<Product> products = loadProducts();

        Optional<Product> productOpt = products.stream()
                .filter(p -> p.getModel().equals(model))
                .findFirst();

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            if (product.getQuantity() >= decreaseBy) {
                product.setQuantity(product.getQuantity() - decreaseBy);
                saveProducts(products);
                return true;
            } else {
                System.out.println("Not enough stock available for " + model);
            }
        } else {
            System.out.println("Product not found: " + model);
        }
        return false;
    }

    public static boolean increaseProductQuantity(String model, int increaseBy) throws IOException {
        List<Product> products = loadProducts();

        Optional<Product> productOpt = products.stream()
                .filter(p -> p.getModel().equals(model))
                .findFirst();

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setQuantity(product.getQuantity() + increaseBy);
            saveProducts(products);
            return true;
        } else {
            System.out.println("Product not found: " + model);
        }
        return false;
    }

//    public static void main(String[] args) {
//        ProductModel productModel = new ProductModel();
//        try {
//            boolean success = productModel.decreaseProductQuantity("iphone14", 10);
//            if (success) {
//                System.out.println("Quantity decreased successfully.");
//            } else {
//                System.out.println("Failed to decrease quantity.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}