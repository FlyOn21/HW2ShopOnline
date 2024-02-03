package app.model.eshopmodel;

import app.entity.Client;

import java.io.IOException;
import java.util.Optional;

import static app.utilseshop.ClientsJsonPojo.clientJsonToPojo;

public class RegistrationModel {
    public static boolean ifClientExist (String email) throws IOException {
        Optional<Client> ifClient = clientJsonToPojo(email);
        return ifClient.isPresent();
    }
}
