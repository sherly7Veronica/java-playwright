import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PostRequestExample {
    public static void main(String[] args) {
        try {
            // Create HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Create a POST request with headers (no body)
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://your-api-endpoint.com/resource"))
                .header("Header1", "value1")
                .header("Header2", "value2")
                .header("Header3", "value3")
                .POST(HttpRequest.BodyPublishers.noBody())  // POST request with no body
                .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response body
            System.out.println("Response: " + response.body());

            // Optionally, handle the status code
            int statusCode = response.statusCode();
            System.out.println("Status Code: " + statusCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
