package app.model.eshopmodel;

import app.entity.Order;
import app.entity.Product;
import app.config.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class OrderModel {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .enable(SerializationFeature.INDENT_OUTPUT);
    private static final File ordersFile = new File(Config.PATH_ORDERS_JSON);

    public OrderModel() {

    }


    public static void createOrder(String clientId, Order order) throws IOException {
        Map<String, List<Order>> allOrders = readOrders();
        allOrders.computeIfAbsent(clientId, k -> new ArrayList<>()).add(order);
        writeOrders(allOrders);
    }

    public List<Order> showClientOrders(String clientId) throws IOException {
        Map<String, List<Order>> allOrders = readOrders();
        return allOrders.getOrDefault(clientId, Collections.emptyList());
    }

    public static void displayOrderDetails(Order order) {
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Buyer: " + order.getBayer());
        System.out.println("Total Cost: " + order.getTotalCost());
        System.out.println("Currency: " + order.getCurrency());
        System.out.println("With Delivery: " + order.isWithDelivery());
        System.out.println("Status: " + order.getStatus());
        System.out.println("Products:");
        List<Product> products = order.getListProducts();
        if (products != null) {
            products.forEach(product -> System.out.println(" - Model: " + product.getModel() + ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity()));
        } else {
            System.out.println(" - No products in order.");
        }
        System.out.println("--------------------------------");
    }

    private static Map<String, List<Order>> readOrders() throws IOException {
        if (ordersFile.exists()) {
            return objectMapper.readValue(ordersFile, new TypeReference<>() {});
        }
        return new HashMap<>();
    }

    private static void writeOrders(Map<String, List<Order>> orders) throws IOException {
        objectMapper.writeValue(ordersFile, orders);
    }
//    public static void main(String[] args) {
//        try {
//            OrderModel orderModel = new OrderModel();
//            String clientId = "8cc74ed3-9170-43fc-bb40-33e76c27e095";
//
//            // Create some product instances for the order
//            Product product1 = new Product("iphone15", 1250.0, "blue", 1, "phone");
//            Product product2 = new Product("iphone13", 1000.0, "red", 1, "phone");
//            ArrayList<Product> products = new ArrayList<>();
//            products.add(product1);
//            products.add(product2);
//
//            // Calculate the total price for the order
//            double totalPrice = 455.55;
//
//            // Example of creating an order with complete details
//            Order newOrder = new Order(
//                    "orderId-" + System.currentTimeMillis(), // Simple way to generate a unique order ID
//                    products,
//                    totalPrice,
//                    "zhogolevpv@gmail.com", // Assuming this is the buyer's email
//                    "USD",
//                    true, // withDelivery
//                    "IN_PROGRESS" // status
//            );
//
//            createOrder(clientId, newOrder);
//
//            // Display client orders to see the newly added order
//            orderModel.showClientOrders(clientId);
//        } catch (IOException e) {
//            System.err.println("An error occurred: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
}


