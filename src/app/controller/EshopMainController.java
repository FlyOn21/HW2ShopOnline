package app.controller;

import app.entity.Client;
import app.views.RegistrationOrSignInView;
import app.views.EshopView;

import java.io.IOException;
import java.util.Optional;

public class EshopMainController {

    public void eshopProcessing() {
        try {
            RegistrationOrSignInView registrationOrSignInView = new RegistrationOrSignInView();
            Optional<Client> client = registrationOrSignInView.displayMenu();

            if (client.isPresent()) {
                EshopView eshopView = new EshopView(client.get());
                eshopView.showMenu();
            } else {
                System.out.println("Goodbye!");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
