package app.views;

import app.entity.Client;
import app.utilseshop.ClientsJsonPojo;
import app.utilseshop.PasswordProcessing;
import app.utilseshop.Validation;
import app.model.eshopmodel.RegistrationModel;

import java.io.IOException;
import java.util.*;

public class RegistrationView {

    Scanner scanner = new Scanner(System.in);
    public Optional<Client> registerClient() throws IOException {


        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter second name:");
        String secondName = scanner.nextLine();

        String email;
        boolean ifExistClient;
        do {
            System.out.println("Enter email:");
            email = scanner.nextLine();

            ifExistClient = RegistrationModel.ifClientExist(email);
            if (ifExistClient) {
                System.out.println("Client with that email exists. Please try another email.");
            } else if (!Validation.isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email.");
            }
        } while (!Validation.isValidEmail(email) || ifExistClient);

        String phone;
        do {
            System.out.println("Enter phone number:");
            phone = scanner.nextLine();
        } while (!Validation.isValidPhoneNumber(phone));
        String password;
        String password2;
        do {
            System.out.println("Enter password:");
            password = scanner.nextLine();
            System.out.println("Repeat password:");
            password2 = scanner.nextLine();
        } while (!Objects.equals(password, password2));

        try {
            String passwordHash = PasswordProcessing.passwordHash(password);
            String id = UUID.randomUUID().toString();

            Client newClient = new Client(firstName, secondName, email, phone, id, passwordHash);


            ClientsJsonPojo.clientPogoToJson(newClient);
            System.out.println("Client registered successfully.");
            return Optional.of(newClient);
        } catch (IOException e) {
            System.err.println("Failed to register the client due to an IO error.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error occurred during registration.");
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public static void main(String[] args) throws IOException {
        RegistrationView registrationView = new RegistrationView();
        registrationView.registerClient();
    }
}

