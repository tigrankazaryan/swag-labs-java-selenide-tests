import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import local.InventoryPage;
import local.ItemPage;
import local.MainPage;
import org.junit.jupiter.api.*;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static local.ConfigProvider.STANDARD_USER_LOGIN;
import static local.ConfigProvider.STANDARD_USER_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Order(2)
@Feature("Inventory tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InventoryTest extends BaseTest {

    @Test
    @Order(1)
    @DisplayName("Sorting items by name (from A to Z)")
    @Description("Sorting items on inventory page by name in ascending order.")
    public void sortingAToZ() {
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                sortByNameAToZ();
        assertTrue(inventoryPage.getOptionAToZ().isSelected(),
                "Checking \"Name (A to Z)\" option for being selected");
        List<SelenideElement> items = inventoryPage.getItemsNames();
        List<String> itemsNames = items.stream().map(SelenideElement::getText).collect(Collectors.toList());
        List<String> sortedItemsNames = itemsNames.stream().sorted().collect(Collectors.toList());
        assertEquals(sortedItemsNames, itemsNames,
                "Checking items for being sorted by name from A to Z");
    }

    @Test
    @Order(2)
    @DisplayName("Sorting items by name (from Z to A)")
    @Description("Sorting items on inventory page by name in descending order.")
    public void sortingZToA() {
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                sortByNameZToA();
        assertTrue(inventoryPage.getOptionZToA().isSelected(),
                "Checking \"Name (Z to A)\" option for being selected");
        List<SelenideElement> items = inventoryPage.getItemsNames();
        List<String> itemsNames = items.stream().map(SelenideElement::getText).collect(Collectors.toList());
        List<String> sortedItemsNames = itemsNames.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertEquals(sortedItemsNames, itemsNames,
                "Checking items for being sorted by name from Z to A");
    }

    @Test
    @Order(3)
    @DisplayName("Sorting items by price (from low to high)")
    @Description("Sorting items on inventory page by price in ascending order.")
    public void sortingLowToHigh() {
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                sortByPriceLowToHigh();
        assertTrue(inventoryPage.getOptionLowToHigh().isSelected(),
                "Checking \"Price (low to high)\" option for being selected");
        List<SelenideElement> prices = inventoryPage.getItemsPrices();
        List<Double> pricesValues = prices.stream().map(x -> Double.valueOf(x.getText().substring(1))).collect(Collectors.toList());
        List<Double> sortedPricesValues = pricesValues.stream().sorted().collect(Collectors.toList());
        assertEquals(sortedPricesValues, pricesValues,
                "Checking items for being sorted by price in ascending order");
    }

    @Test
    @Order(4)
    @DisplayName("Sorting items by price (from high to low)")
    @Description("Sorting items on inventory page by price in descending order.")
    public void sortingHighToLow() {
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                sortByPriceHighToLow();
        assertTrue(inventoryPage.getOptionHighToLow().isSelected(),
                "Checking \"Price (high to low)\" option for being selected");
        List<SelenideElement> prices = inventoryPage.getItemsPrices();
        List<Double> pricesValues = prices.stream().map(x -> Double.valueOf(x.getText().substring(1))).collect(Collectors.toList());
        List<Double> sortedPricesValues = pricesValues.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertEquals(sortedPricesValues, pricesValues,
                "Checking items for being sorted by price in descending order");
    }

    @Test
    @Order(5)
    @DisplayName("Add to cart and remove")
    @Description("Add an item to cart and then remove it.")
    public void addToCartAndRemove() {
        int itemNumber = 1;
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                addToCart(itemNumber);
        assertEquals("1", inventoryPage.getCartBadge().getText(),
                "Checking for cart badge indicating there is one item in the cart");
        inventoryPage.remove(itemNumber);
        assertEquals("Add to cart", inventoryPage.getItemsButtons().get(itemNumber - 1).getText(),
                "Checking item for being removed from cart");
    }

    @Test
    @Order(6)
    @DisplayName("Add to cart two items")
    @Description("Adding to cart two items and checking cart badge counter.")
    public void addToCartTwoItems() {
        int itemNumber1 = 1;
        int itemNumber2 = 2;
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                addToCart(itemNumber1).addToCart(itemNumber2);
        assertEquals("2", inventoryPage.getCartBadge().getText(),
                "Checking for cart badge indicating there is two items in the cart");
        assertEquals("Remove", inventoryPage.getItemsButtons().get(itemNumber1 - 1).getText(),
                "Checking item for being added to cart");
        assertEquals("Remove", inventoryPage.getItemsButtons().get(itemNumber2 - 1).getText(),
                "Checking item for being added to cart");
    }

    @Test
    @Order(7)
    @DisplayName("Open item page")
    @Description("Opening item page and checking item name, item description, item price.")
    public void openItemPage() {
        Random random = new Random();
        int itemNumber = random.nextInt(6);
        itemNumber++;
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD);
        String itemName = inventoryPage.getItemsNames().get(itemNumber - 1).getText();
        String itemDescription = inventoryPage.getItemsDescriptions().get(itemNumber - 1).getText();
        Double itemPrice = Double.valueOf(inventoryPage.getItemsPrices().get(itemNumber - 1).getText().substring(1));
        ItemPage itemPage = inventoryPage.clickItem(itemNumber);
        assertEquals(itemName, itemPage.getItemName().getText(),
                "Checking that item name is the same as one's on inventory page");
        assertEquals(itemDescription, itemPage.getItemDescription().getText(),
                "Checking that item description is the same as one's on inventory page");
        assertEquals(itemPrice, Double.valueOf(itemPage.getItemPrice().getText().substring(1)),
                "Checking that item price is the same as one's on inventory page");
    }

}
