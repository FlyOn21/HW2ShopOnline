package app.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private List<Product> products;
    private double totalCost;
    private String bayer;
    private boolean withDelivery;
    private String status;
    private String currency;

    // Constructors
    public Order() {
        this.products = new ArrayList<>();
    }

    public Order(String orderId, List<Product> products, double totalCost, String bayer, String currency, boolean withDelivery, String status) {
        this.orderId = orderId;
        this.products = products;
        this.totalCost = totalCost;
        this.bayer = bayer;
        this.currency = currency;
        this.withDelivery = withDelivery;
        this.status = status;
    }

    // Getters and Setters
    public String getOrderId() { return orderId; }

    public List<Product> getListProducts() { return products; }

    public double getTotalCost() { return totalCost; }

    public String getBayer() { return bayer; }

    public boolean isWithDelivery() { return withDelivery; }

    public String getStatus() { return status; }

    public String getCurrency() { return currency; }

    public List<Product> getProducts() {
        return products;
    }
}