package app.views;

import app.model.eshopmodel.OrderModel;
import app.entity.Order;

import java.io.IOException;
import java.util.List;

public class OrderView {

    private final OrderModel orderModel;

    public OrderView() {
        this.orderModel = new OrderModel();
    }

    public void displayClientOrders(String clientId) {
        try {
            List<Order> orders = orderModel.showClientOrders(clientId);
            if (orders.isEmpty()) {
                System.out.println("No orders found for client ID: " + clientId);
            } else {
                System.out.println("Orders for client ID: " + clientId + ":");
                orders.forEach(OrderModel::displayOrderDetails);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while fetching orders: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        OrderView view = new OrderView();

        view.displayClientOrders("8cc74ed3-9170-43fc-bb40-33e76c27e095");
    }
}