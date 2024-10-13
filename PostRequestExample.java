import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostRequestExample {
    public static void main(String[] args) {
        try {
            // Create HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Define the JSON payload
            String jsonPayload = "{\"name\": \"example\", \"type\": \"demo\"}";

            // Create a POST request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://your-api-endpoint.com/resource"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonPayload))
                .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response body
            System.out.println("Response: " + response.body());

            // Parse the JSON response to extract the ID
            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseJson = mapper.readTree(response.body());
            String id = responseJson.get("id").asText();

            // Print the extracted ID
            System.out.println("Extracted ID: " + id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
