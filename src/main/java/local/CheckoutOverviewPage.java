package local;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutOverviewPage {

    private final SelenideElement checkoutOverviewTitle = $(By.xpath("//*[contains(text(), 'Checkout: Overview')]"));
    private final List<SelenideElement> itemsNames = $$(By.className("inventory_item_name"));
    private final List<SelenideElement> itemsDescriptions = $$(By.className("inventory_item_desc"));
    private final List<SelenideElement> itemsPrices = $$(By.className("inventory_item_price"));
    private final List<SelenideElement> itemsCounters = $$(By.className("cart_quantity"));
    private final SelenideElement subtotal = $(By.className("summary_subtotal_label"));
    private final SelenideElement tax = $(By.className("summary_tax_label"));
    private final SelenideElement total = $(By.xpath("//*[@class='summary_info_label summary_total_label']"));
    private final SelenideElement finishButton = $(By.id("finish"));

    public SelenideElement getCheckoutOverviewTitle() {
        return checkoutOverviewTitle;
    }

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

    public SelenideElement getSubtotal() {
        return subtotal;
    }

    public SelenideElement getTax() {
        return tax;
    }

    public SelenideElement getTotal() {
        return total;
    }

    public CheckoutCompletePage clickFinish() {
        finishButton.click();
        return new CheckoutCompletePage();
    }

}
