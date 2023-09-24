package local;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    private final List<SelenideElement> itemsNames = $$(By.className("inventory_item_name"));
    private final List<SelenideElement> itemsDescriptions = $$(By.className("inventory_item_desc"));
    private final List<SelenideElement> itemsPrices = $$(By.className("inventory_item_price"));
    private final List<SelenideElement> itemsCounters = $$(By.className("cart_quantity"));
    private final SelenideElement cartBadge = $(By.className("shopping_cart_badge"));
    private final SelenideElement checkoutButton = $(By.id("checkout"));

    public List<SelenideElement> getItemsNames() {
        return itemsNames;
    }

    public List<SelenideElement> getItemsDescriptions() {
        return itemsDescriptions;
    }

    public List<SelenideElement> getItemsPrices() {
        return itemsPrices;
    }

    public List<SelenideElement> getItemsCounters() {
        return itemsCounters;
    }

    public SelenideElement getCartBadge() {
        return cartBadge;
    }

    public CheckoutClientInformationPage clickCheckout() {
        checkoutButton.shouldBe(visible).click();
        return new CheckoutClientInformationPage();
    }

}
