package local;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class InventoryPage {

    private final SelenideElement productsTitle = $(By.xpath("//span[contains(text(), 'Products')]"));
    private final SelenideElement menuButton = $(By.id("react-burger-menu-btn"));
    private final SelenideElement logout = $(By.linkText("Logout"));
    private final SelenideElement cart = $(By.className("shopping_cart_link"));
    private final SelenideElement cartBadge = $(By.className("shopping_cart_badge"));
    private final List<SelenideElement> itemsNames = $$(By.className("inventory_item_name"));
    private final List<SelenideElement> itemsPrices = $$(By.className("inventory_item_price"));
    private final List<SelenideElement> itemsButtons = $$(By.xpath("//*[@class='pricebar']/button"));
    private final List<SelenideElement> itemsDescriptions = $$(By.className("inventory_item_desc"));
    private final SelenideElement sortDropdown = $(By.className("product_sort_container"));
    private final SelenideElement optionAToZ = $(By.xpath("//option[@value='az']"));
    private final SelenideElement optionZToA = $(By.xpath("//option[@value='za']"));
    private final SelenideElement optionLowToHigh = $(By.xpath("//option[@value='lohi']"));
    private final SelenideElement optionHighToLow = $(By.xpath("//option[@value='hilo']"));

    public SelenideElement getProductsTitle() {
        return productsTitle;
    }

    public SelenideElement getCartBadge() {
        return cartBadge;
    }

    public List<SelenideElement> getItemsNames() {
        return itemsNames;
    }

    public List<SelenideElement> getItemsPrices() {
        return itemsPrices;
    }

    public List<SelenideElement> getItemsButtons() {
        return itemsButtons;
    }

    public List<SelenideElement> getItemsDescriptions() {
        return itemsDescriptions;
    }

    public SelenideElement getOptionAToZ() {
        return optionAToZ;
    }

    public SelenideElement getOptionZToA() {
        return optionZToA;
    }

    public SelenideElement getOptionLowToHigh() {
        return optionLowToHigh;
    }

    public SelenideElement getOptionHighToLow() {
        return optionHighToLow;
    }

    public MainPage goBack() {
        back();
        return new MainPage();
    }

    public MainPage logout() {
        menuButton.shouldBe(visible).click();
        logout.shouldBe(visible).click();
        return new MainPage();
    }

    public InventoryPage sortByNameAToZ() {
        sortDropdown.shouldBe(visible).selectOptionByValue("az");
        return this;
    }

    public InventoryPage sortByNameZToA() {
        sortDropdown.shouldBe(visible).selectOptionByValue("za");
        return this;
    }

    public InventoryPage sortByPriceLowToHigh() {
        sortDropdown.shouldBe(visible).selectOptionByValue("lohi");
        return this;
    }

    public InventoryPage sortByPriceHighToLow() {
        sortDropdown.shouldBe(visible).selectOptionByValue("hilo");
        return this;
    }

    public InventoryPage addToCart(int itemNumber) {
        if (itemNumber < 1 || itemNumber > 6)
            throw new IllegalArgumentException("Value must be between 1 and 6.");
        int index = itemNumber - 1;
        if (!Objects.equals(itemsButtons.get(index).shouldBe(visible).getText(), "Add to cart"))
            throw new IllegalStateException("Button caption must be \"Add to cart\".");
        itemsButtons.get(index).click();
        return this;
    }

    public InventoryPage remove(int itemNumber) {
        if (itemNumber < 1 || itemNumber > 6)
            throw new IllegalArgumentException("Value must be between 1 and 6.");
        int index = itemNumber - 1;
        if (!Objects.equals(itemsButtons.get(index).getText(), "Remove"))
            throw new IllegalStateException("Button caption must be \"Remove\".");
        itemsButtons.get(index).click();
        return this;
    }

    public ItemPage clickItem(int itemNumber) {
        if (itemNumber < 1 || itemNumber > 6)
            throw new IllegalArgumentException("Value must be between 1 and 6.");
        int index = itemNumber - 1;
        itemsNames.get(index).shouldBe(visible).click();
        return new ItemPage();
    }

    public CartPage openCart() {
        cart.click();
        return new CartPage();
    }

}
