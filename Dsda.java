import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class Dsda {

    static Playwright playwright;
    static Browser browser;
    static GetAAid apiService; // Update to the new class name
    Page page;
    String endpoint;  // Store the fetched endpoint for the test

    @BeforeAll
    static void setupAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        apiService = new GetAAid(); // Initialize GetAAid
    }

    @BeforeEach
    void setup() {
        endpoint = apiService.getEndpointFromApi(); // Fetch the dynamic endpoint
        if (endpoint == null) {
            Assertions.fail("Failed to fetch the endpoint. Test cannot continue.");
        }

        page = browser.newPage();  // Open a new page before each test
    }

    @Test
    void testWebsiteNavigation() {
        String baseUrl = "https://your-website.com";
        String fullUrl = baseUrl + endpoint;
        
        page.navigate(fullUrl);
        Assertions.assertEquals("Expected Title", page.title(), "Page title should be 'Expected Title'");
    }

    @AfterEach
    void teardown() {
        page.close();  // Close the page after each test
    }

    @AfterAll
    static void teardownAll() {
        browser.close();   // Close the browser after all tests are done
        playwright.close(); // Close Playwright
    }
}
