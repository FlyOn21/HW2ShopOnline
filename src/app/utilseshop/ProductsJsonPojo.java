package app.utilseshop;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import app.entity.Product;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProductsJsonPojo {

    public static List<Product> productsJsonToPojo(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File productsJsonFile = new File(path);
        // read json structure
        JsonNode root = objectMapper.readTree(productsJsonFile);
        // get base node
        JsonNode productsNode = root.get("products");

        if (productsNode != null) {
            return objectMapper.readerFor(new TypeReference<List<Product>>() {}).readValue(productsNode);
        } else {
            return null;
        }
    }

    public static void productsPogoToJson(String path, List<Product> products) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // To format the output JSON
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        File productsJsonFile = new File(path);

        // Wrapping the products list in a Map to match the expected JSON structure
        Map<String, List<Product>> productsMap = Map.of("products", products);
        objectMapper.writeValue(productsJsonFile, productsMap);
    }

//    public static void main(String[] args) {
//        try {
//            // Example usage
//            String path = "src/storage/product.json";
//            List<Product> products = productsJsonToPojo(path, "products");
//            if (products == null) {
//                products = new ArrayList<>();
//            }
//            // Example of adding a new product
//            // Make sure your Product class has a constructor or use setters to add properties
////            Product newProduct = new Product("iphone16", 1700.00, "green", 8, "phone");
////            products.add(newProduct);
//            String sellModel = "iphone14";
//            for (int i=0; i < products.size(); i++) {
//                Product unit = products.get(i);
//
//                if (Objects.equals(unit.getModel(), sellModel)) {
//                    unit.setQuantity(2);
//                }
//            }
//
//            productsPogoToJson(path, products);
//
//            System.out.println("JSON file has been updated.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}

