package pages;

import com.microsoft.playwright.*;
import java.nio.file.Paths;
import java.util.List;

public class BasePage {
    protected Page page;
    protected BrowserContext context;

    public BasePage(Page page) {
        this.page = page;
    }


    public static BrowserContext createContextWithVideo(Browser browser, String videoPath) {
        return browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get(videoPath))
                .setRecordVideoSize(1920, 1080)); // Ajusta el tamaño si es necesario
    }

    public void navigateTo(String url) {
        page.navigate(url);
    }

    public void click(String selector) {
        page.locator(selector).click();
    }


    public void selectElementByText(List<ElementHandle> elements, String visibleText) {
        for (ElementHandle element : elements) {
            System.out.println(element);
            System.out.println(element.innerText());
            System.out.println(element.asElement());
            System.out.println(element.getAttribute("onclick"));
            if (element.innerText().contains(visibleText)) {
                System.out.println(element);
                element.click();
                return;
            }
        }
        throw new RuntimeException("No se encontró un elemento con el texto: " + visibleText);
    }

    public void type(String selector, String text) {
        page.locator(selector).fill(text);
    }

    public String getText(String selector) {
        return page.locator(selector).textContent();
    }

    public void Assert(String gettext, String expectedMessage) {
        //Assertions.assertEquals(gettext, expectedMessage);
    }

}
