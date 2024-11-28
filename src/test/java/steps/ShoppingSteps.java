package steps;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import pages.BasePage;
import pages.ShoppingPage;

import java.util.Arrays;

public class ShoppingSteps {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;
    private static ShoppingPage shoppingPage;

    @Given("I open the {string} homepage")
    public void iOpenTheGoogleHomepage(String url) {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        // Crear un contexto con grabación de video
        // Resolución Full HD
        context = BasePage.createContextWithVideo(browser, "videos/");
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080));
        page = context.newPage();


        shoppingPage = new ShoppingPage(page);
        shoppingPage.navigateTo(url);
    }

    @When("I log in with username {string} and password {string}")
    public void LoginFor(String username, String password) {
        shoppingPage.LoginFor(username, password);
    }

    @And("I add the following items to the cart:")
    public void iAddTheFollowingItemsToTheCart(DataTable dataTable) {
        shoppingPage.iAddTheFollowingItemsToTheCart(dataTable);

    }

    @Then("I should see results containing {string}")
    public void iShouldSeeResultsContaining(String keyword) {
        Assertions.assertTrue(shoppingPage.containsResult(keyword));
        // Cerrar navegador y detener grabación
        browser.close();
        playwright.close();
    }
}