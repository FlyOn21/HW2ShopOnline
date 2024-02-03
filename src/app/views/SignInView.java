package app.views;

import app.entity.Client;
import app.model.eshopmodel.SignInModel;
import app.utilseshop.Validation;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class SignInView {
    Scanner scanner = new Scanner(System.in);

    public Optional<Client> displaySignIn() throws IOException {

        while (true) {
            System.out.println("Please sign in to your account.");

            String email;
            boolean checkEmail;
            do {
                System.out.print("Email: ");
                email = scanner.nextLine();
                checkEmail = Validation.isValidEmail(email);
                if (!checkEmail) {
                    System.out.println("Incorrect email format");
                }

            } while (!checkEmail);

            System.out.print("Password: ");
            String password = scanner.nextLine();

            // Assuming signInUser is a method that checks the credentials and returns true if they're correct
            Optional<Client> isSignedIn = signInUser(email, password);

            if (isSignedIn.isPresent()) {
                System.out.println("Sign-in successful. Welcome back!");
                return isSignedIn;
            } else {
                System.out.println("Sign-in failed. Please check your credentials and try again.");
                System.out.println("Do you want to try again? (yes/no)");
                String response = scanner.nextLine();
                if (!response.equalsIgnoreCase("yes")) {
                    return Optional.empty();
                }
            }
        }
    }


    private Optional<Client> signInUser(String email, String password) throws IOException {
        return SignInModel.signInClient(email, password);
    }

//    public static void main(String[] args) throws IOException {
//        SignInView signInView = new SignInView();
//        Scanner scanner = new Scanner(System.in);
//        signInView.displaySignIn(scanner);
//    }
}
