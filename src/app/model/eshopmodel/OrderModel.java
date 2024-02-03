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
}


