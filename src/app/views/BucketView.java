package app.views;

import app.entity.Client;
import app.model.eshopmodel.BucketModel;

import java.io.IOException;
import java.util.Scanner;

public class BucketView {
    private final BucketModel bucketModel;
    private final Scanner scanner;
    private final Client client;

    public BucketView(Client client) {
        this.bucketModel = new BucketModel();
        this.scanner = new Scanner(System.in);
        this.client = client;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nBucket Management");
            System.out.println("1. Show My Bucket");
            System.out.println("2. Remove Product from Bucket");
            System.out.println("3. Buy All in Bucket");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    showBucket();
                    break;
                case "2":
                    removeProductFromBucket();
                    break;
                case "3":
                    buyAllInBucket();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    public void showBucket() {
        try {
            bucketModel.showBucket(client.getId());
        } catch (IOException e) {
            System.err.println("Error displaying bucket: " + e.getMessage());
        }
    }

    private void removeProductFromBucket() {
        System.out.print("Enter Product Model to Remove: ");
        String model = scanner.nextLine();

        try {
            if (bucketModel.removeProductFromBucket(client.getId(), model)) {
                System.out.println("Product removed from bucket.");
            } else {
                System.out.println("Product not found in bucket.");
            }
        } catch (IOException e) {
            System.err.println("Error removing product from bucket: " + e.getMessage());
        }
    }

    private void buyAllInBucket() {
        try {
            if (bucketModel.clearBucketAndCreateOrder(client)) {
                System.out.println("Purchase successful. Thank you!");
            } else {
                System.out.println("Bucket is already empty or there was an error.");
            }
        } catch (IOException e) {
            System.err.println("Error during purchase: " + e.getMessage());
        }
    }

}
