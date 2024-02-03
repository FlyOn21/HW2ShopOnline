package app.views;

import app.entity.Client;
import app.model.eshopmodel.BucketModel;

import java.io.IOException;
import java.util.Scanner;

public class EshopView {
    private final Scanner scanner;
    private final Client client;

    public EshopView(Client client) {
        this.scanner = new Scanner(System.in);
        this.client = client;
    }

    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\nEshop Main Menu");
            System.out.println("1. Show list of products");
            System.out.println("2. View my bucket");
            System.out.println("3. Purchase products in bucket");
            System.out.println("4. View my orders");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    showProductsAndAddToBucket();
                    break;
                case "2", "3":
                    new BucketView(client).showMenu();
                    break;
                case "4":
                    new OrderView().displayClientOrders(client.getId());
                    break;
                case "5":
                    running = false;
                    System.out.println("Exiting Eshop...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void showProductsAndAddToBucket() {
        ProductsView productsView = new ProductsView();
        productsView.showProducts();
        System.out.println("Enter the model of the product to add to your bucket (or type 'back' to return): ");
        String model = scanner.nextLine();
        if (!"back".equalsIgnoreCase(model)) {
            try {
                System.out.println("Quantity");
                int quantity = Integer.parseInt(scanner.nextLine());
                BucketModel bucketModel = new BucketModel();
                bucketModel.addProductToBucket(client.getId(), model, quantity);
                System.out.println("Product added to your bucket.");
            } catch (IOException e) {
                System.err.println("Error adding product to bucket: " + e.getMessage());
            }
        }
    }
}