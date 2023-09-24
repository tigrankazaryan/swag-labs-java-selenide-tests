package local;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ItemPage {

    private final SelenideElement itemName = $(By.xpath("//*[@class='inventory_details_name large_size']"));
    private final SelenideElement itemDescription = $(By.xpath("//*[@class='inventory_details_desc large_size']"));
    private final SelenideElement itemPrice = $(By.xpath("//*[@class='inventory_details_price']"));

    public SelenideElement getItemName() {
        return itemName;
    }

    public SelenideElement getItemDescription() {
        return itemDescription;
    }

    public SelenideElement getItemPrice() {
        return itemPrice;
    }

}
