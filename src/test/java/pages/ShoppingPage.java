package pages;


import com.microsoft.playwright.Page;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static Locator.ShoppingPageLocator.*;

public class ShoppingPage extends BasePage {

    public ShoppingPage(Page page) {
        super(page);
    }

    public void LoginFor(String username, String password) {
        click("a#login2");
        type("input#loginusername", username);
        type("input#loginpassword", password);
        click("div#logInModal > div[role='document'] .btn.btn-primary");
    }

    public boolean containsResult(String keyword) {
        return page.locator("#search").textContent().contains(keyword);
    }

    public void iAddTheFollowingItemsToTheCart(DataTable dataTable) {
        List<Map<String, String>> items = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> item : items) {
            String Category = item.get("Category");
            if (Category.contains("Phones")) {
                click(Cat_Phones);
            }
            if (Category.contains("Laptops")) {
                click(Cat_Laptops);
            }
            if (Category.contains("Monitors")) {
                click(Cat_Monitors);
            }
            String productName = item.get("Product Name");
            click("body > div:nth-child(6) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h4:nth-child(1) > a:nth-child(1)");
            click(".btn.btn-success.btn-lg");
            page.onDialog(dialog -> {
                System.out.println("Texto del diálogo: " + dialog.message());
                dialog.accept(); // Aceptar el diálogo
            });
            click("//a[@id='cartur']");
            click("//button[normalize-space()='Place Order']");
        }
    }

}
