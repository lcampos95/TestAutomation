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

        }
    }

    public void iConfirmThePurchase() {
        click("//a[@id='cartur']");
        click("//button[normalize-space()='Place Order']");
        type("input#name", "Luis Miguel Campos Navarro");
        type("input#country", "Peru");
        type("input#city", "Ica");
        type("input#card", "4111111111111111");
        type("input#month", "12");
        type("input#year", "26");
        click("div#orderModal > div[role='document'] .btn.btn-primary");
    }

    public void iShouldSeeTheConfirmationMessage(String Message) {
        String mensaje = getText(".showSweetAlert.sweet-alert.visible > h2");
        Assert(mensaje, Message);
        click(".btn.btn-lg.btn-primary.confirm");
    }

    public void iDoNotConfirmThePurchase(String message) {
        click("//a[@id='cartur']");
        click("//button[normalize-space()='Place Order']");
        click("div#orderModal > div[role='document'] .btn.btn-primary");
        page.onDialog(dialog -> {
            System.out.println("Texto del diálogo: " + dialog.message());
            String Message = dialog.message();
            dialog.accept(); // Aceptar el diálogo
            Assert(Message, message);
        });
    }

}
