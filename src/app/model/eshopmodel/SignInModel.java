package app.model.eshopmodel;

import app.entity.Client;
import app.utilseshop.PasswordProcessing;

import java.io.IOException;
import java.util.Optional;

import static app.utilseshop.ClientsJsonPojo.clientJsonToPojo;

public class SignInModel {
    public static Optional<Client> signInClient (String email, String password) throws IOException {
        Optional<Client> ifClient =  clientJsonToPojo(email);
        if (ifClient.isEmpty()) {
             return Optional.empty();
        }
        else {
            Client client = ifClient.get();
            String hash = client.getPasswordHash();
            boolean validatePassword = PasswordProcessing.checkPassword(password, hash);
            if (!validatePassword) {
                return Optional.empty();
            } else {
                return ifClient;
            }
        }
    }
}
