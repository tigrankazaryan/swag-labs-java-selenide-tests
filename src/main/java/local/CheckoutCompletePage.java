package local;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutCompletePage {

    private final SelenideElement thankYou = $(By.xpath("//*[contains(text(), 'Thank you for your order!')]"));

    public SelenideElement getThankYou() {
        return thankYou;
    }

}
