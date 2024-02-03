package app.utilseshop;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import app.entity.Client;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import app.config.Config;

public class ClientsJsonPojo {
    public static Optional<Client> clientJsonToPojo(String email) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File productsJsonFile = new File(Config.PATH_CLIENTS_JSON);
        // read json structure
        JsonNode clients = objectMapper.readTree(productsJsonFile);
        boolean ifExists = clients.has(email);
        if (ifExists) {
            JsonNode currentClient = clients.get(email);
            Client client = objectMapper.treeToValue(currentClient, Client.class);

            return  Optional.of(client);
        }
        else {
            return Optional.empty();
        }
    }

    public static void clientPogoToJson(Client newClient) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        File clientsJsonFile = new File(Config.PATH_CLIENTS_JSON);

        Map<String, Client> clientsMap;
        if(clientsJsonFile.exists() && clientsJsonFile.length() != 0) {

            clientsMap = objectMapper.readValue(clientsJsonFile, new TypeReference<Map<String, Client>>() {});
        } else {
            clientsMap = new java.util.HashMap<>();
        }
        clientsMap.put(newClient.getEmail(), newClient);
        objectMapper.writeValue(clientsJsonFile, clientsMap);
    }
}
