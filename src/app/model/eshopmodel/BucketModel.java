package app.model.eshopmodel;

import app.entity.Client;
import app.entity.Order;
import app.model.interfaces.BaseModelCostCalculation;
import app.utilseshop.RandomGenaret;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import app.entity.Product;
import java.io.File;
import java.io.IOException;
import java.util.*;
import app.config.Config;
import com.fasterxml.jackson.databind.SerializationFeature;

public class BucketModel {
    private final ObjectMapper objectMapper;
    private final File bucketFile;

    public BucketModel() {
        this.objectMapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.bucketFile = new File(Config.PATH_CLIENTS_BUCKET_JSON);
    }


    public void addProductToBucket(String clientId, String model, int quantity) throws IOException {
        Map<String, List<Product>> buckets = readBuckets();

        List<Product> allProducts = ProductModel.loadProducts();
        Product productToAdd = allProducts.stream()
                .filter(p -> p.getModel().equals(model))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + model));


        if (!ProductModel.decreaseProductQuantity(model, quantity)) {
            throw new IllegalArgumentException("Not enough stock for: " + model);
        }

        List<Product> clientProducts = buckets.computeIfAbsent(clientId, k -> new ArrayList<>());
        Product existingProduct = clientProducts.stream()
                .filter(p -> p.getModel().equals(model))
                .findFirst()
                .orElse(null);

        if (existingProduct == null) {
            clientProducts.add(new Product(productToAdd.getModel(), productToAdd.getPrice(), productToAdd.getColors(), quantity, productToAdd.getType()));
        } else {
            existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
        }

        writeBuckets(buckets);
    }
    public boolean removeProductFromBucket(String clientId, String model) throws IOException {
        Map<String, List<Product>> buckets = readBuckets();
        List<Product> clientProducts = buckets.getOrDefault(clientId, new ArrayList<>());

        Product productToRemove = clientProducts.stream()
                .filter(p -> p.getModel().equals(model))
                .findFirst()
                .orElse(null);

        if (productToRemove == null) {
            System.out.println("Product not found in bucket: " + model);
            return false;
        }

        if (!ProductModel.increaseProductQuantity(model, productToRemove.getQuantity())) {
            System.out.println("Could not increase product quantity in stock for: " + model);
            return false;
        }

        boolean removed = clientProducts.remove(productToRemove);
        if (!removed) {
            throw new IllegalStateException("Failed to remove product from bucket: " + model);
        }

        writeBuckets(buckets);
        return true;
    }

    public void showBucket(String bucketId) throws IOException {
        Map<String, List<Product>> buckets = readBuckets();
        List<Product> products = buckets.getOrDefault(bucketId, new ArrayList<>());
        if (products.isEmpty()) {
            System.out.println("The bucket is empty.");
            return;
        }
        System.out.println("Products in bucket " + bucketId + ":");
        products.forEach(this::displayProduct);
    }

    private void displayProduct(Product product) {
        System.out.println("--------------------------------");
        System.out.println("Model: " + product.getModel());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Colors: " + product.getColors());
        System.out.println("Quantity: " + product.getQuantity());
        System.out.println("Type: " + product.getType());
        System.out.println("--------------------------------");
    }
    public boolean clearBucketAndCreateOrder(Client client) throws IOException {
        Map<String, List<Product>> buckets = readBuckets();
        Scanner scanner = new Scanner(System.in);
        if (!buckets.containsKey(client.getId()) || buckets.get(client.getId()).isEmpty()) {
            System.out.println("Bucket is empty.");
            return false;
        }

        List<Product> bucketProducts = buckets.get(client.getId());
        double totalPrice = 0;
        BaseModelCostCalculation costCalculator;

        // Ask user for delivery preference
        System.out.println("Do you want the products delivered? (yes/no): ");
        String deliveryAnswer = scanner.nextLine().trim().toLowerCase();
        boolean withDelivery = "yes".equals(deliveryAnswer);


        if (withDelivery) {
            costCalculator = new DeliveryModel();
        } else {
            costCalculator = new PickupModel();
        }

        // Calculate total price
        for (Product product : bucketProducts) {
            totalPrice += costCalculator.calculatePrice(product.getQuantity(), product.getPrice());
        }

        // Create and save the order
        Order order = new Order(
                RandomGenaret.generate6DigitString(),
                bucketProducts,
                totalPrice,
                client.getId(),
                Config.CURRENCY,
                withDelivery,
                "IN_PROGRESS"

        );
        OrderModel.createOrder(client.getId(), order);


        buckets.put(client.getId(), new ArrayList<>());
        writeBuckets(buckets);

        System.out.println("Order created successfully with total price: " + totalPrice + (withDelivery ? " including delivery." : ""));
        return true;
    }

    private Map<String, List<Product>> readBuckets() throws IOException {
        if (bucketFile.exists()) {
            return objectMapper.readValue(bucketFile, new TypeReference<>() {});
        }
        return new HashMap<>();
    }

    private void writeBuckets(Map<String, List<Product>> buckets) throws IOException {
        objectMapper.writeValue(bucketFile, buckets);
    }

}
