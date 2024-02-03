package app.entity;

public class Product {

    private String model;
    private double price;
    private String colors;
    private int quantity;
    private String type;

    public void setModel(String model) {
        this.model = model;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public String getColors() {
        return colors;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product() {
    }

    public Product(String model, double price, String colors, int quantity, String type) {
        this.model = model;
        this.price = price;
        this.colors = colors;
        this.quantity = quantity;
        this.type = type;
    }

}
