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


// Test case
//        public static void main(String[] args) {
//        try {
//            // Example usage
//            String path = "src/storage/clients.json";
//            String email = "zhogolepv@gmail.com";
//            Optional<Client> client = clientJsonToPojo(path, email);
//            if (client.isPresent()) {
//                Client clientUnit = client.get();
//                System.out.println(clientUnit.getFirstName());
//                System.out.println(clientUnit.getSecondName());
//                System.out.println(clientUnit.getEmail());
//                System.out.println(clientUnit.getPhone());
//                System.out.println(clientUnit.getId());
//            }
//            else {
//                String id = UUID.randomUUID().toString();
//                Client newClient = new Client(
//                       "asd", "asdas", "test@gmail.com", "+380504121644" , id, "sadsads"
//                );
//                clientPogoToJson(path, newClient);
//                System.out.println(newClient.getId());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
