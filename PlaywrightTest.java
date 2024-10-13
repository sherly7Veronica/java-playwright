import com.microsoft.playwright.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class PlaywrightTest {
    public static void main(String[] args) {
        // Step 1: Read the endpoint from the file
        String baseUrl = "https://your-website.com";
        String endpoint = "";

        try {
            endpoint = new String(Files.readAllBytes(Paths.get("endpoint.txt")));
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Step 2: Construct the full URL
        String fullUrl = baseUrl + endpoint;
        System.out.println("Navigating to: " + fullUrl);

        // Step 3: Use Playwright to navigate to the website
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // Headless mode can be true or false
            Page page = browser.newPage();

            // Navigate to the URL
            page.navigate(fullUrl);
            System.out.println("Page Title: " + page.title());

            // Example: Add more interactions or assertions here
        }
    }
}
