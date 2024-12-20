package steps;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.ShoppingPage;


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
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080));
        context = BasePage.createContextWithVideo(browser, "videos/");
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

    @And("I confirm the purchase")
    public void iConfirmThePurchase() {
        shoppingPage.iConfirmThePurchase();
    }

    @Then("I do not confirm the purchase {string}")
    public void iDoNotConfirmThePurchase(String mesasage) {
        shoppingPage.iDoNotConfirmThePurchase(mesasage);
        browser.close();
        playwright.close();
    }

    @Then("I should see the confirmation message {string}")
    public void iShouldSeeTheConfirmationMessage(String Message) {
        shoppingPage.iShouldSeeTheConfirmationMessage(Message);
        browser.close();
        playwright.close();
    }


}
