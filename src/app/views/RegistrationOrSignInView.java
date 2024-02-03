package app.views;

import app.entity.Client;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class RegistrationOrSignInView {
    private final Scanner scanner;

    public RegistrationOrSignInView() {
        this.scanner = new Scanner(System.in);
    }

    public Optional<Client> displayMenu() throws IOException {
        while (true) {
            System.out.println("\nWelcome to the eShop!");
            System.out.println("1. Sign In");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    SignInView signInView = new SignInView();
                    Optional<Client> client = signInView.displaySignIn();
                    if (client.isPresent()) {
                        System.out.println("Successfully signed in as: " + client.get().getEmail());
                        return client;
                    }
                    break;
                case "2":
                    RegistrationView registrationView = new RegistrationView();
                    return registrationView.registerClient();
                case "3":
                    System.out.println("Exiting... Thank you for visiting the eShop.");
                    return Optional.empty();
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

}
