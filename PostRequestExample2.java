import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.FileWriter;
import java.io.IOException;

public class PostRequestExample2 {
    public static void main(String[] args) {
        try {
            // Step 1: Send the POST request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://your-api-endpoint.com/resource"))
                    .header("Header1", "value1")
                    .header("Header2", "value2")
                    .header("Header3", "value3")
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Step 2: Extract the endpoint from the response
            String extractedEndpoint = "/your-extracted-endpoint";  // Replace with actual logic to parse response

            // Step 3: Write the extracted endpoint to a file
            try (FileWriter writer = new FileWriter("endpoint.txt")) {
                writer.write(extractedEndpoint);
                System.out.println("Endpoint written to file: " + extractedEndpoint);
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
