import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import local.CartPage;
import local.CheckoutOverviewPage;
import local.InventoryPage;
import local.MainPage;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static local.ConfigProvider.STANDARD_USER_LOGIN;
import static local.ConfigProvider.STANDARD_USER_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Order(4)
@Feature("System tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwagLabsTest extends BaseTest {

    @Test
    @Order(1)
    @DisplayName("Buying two items")
    @Description("Buying two items and checking item name, item description and item price at each step.")
    public void twoItemsPurchase() {
        int itemsAmount = 2;
        String firstName = "Test";
        String lastName = "Testing";
        String postalCode = "344000";
        double tax = 3.2;
        List<Integer> itemsNumbers = new ArrayList<Integer>();
        for (int i = 1; i <= itemsAmount; i++)
            itemsNumbers.add(i);
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                addToCart(itemsNumbers.get(0)).addToCart(itemsNumbers.get(1));
        List<String> inventoryItemsNames = new ArrayList<String>();
        List<String> inventoryItemsDescriptions = new ArrayList<String>();
        List<Double> inventoryItemsPrices = new ArrayList<Double>();
        double inventoryItemsPricesSum = 0.0;
        for (int i = 0; i < itemsAmount; i++) {
            inventoryItemsNames.add(inventoryPage.getItemsNames().get(i).getText());
            inventoryItemsDescriptions.add(inventoryPage.getItemsDescriptions().get(i).getText());
            inventoryItemsPrices.add(Double.valueOf(inventoryPage.getItemsPrices().get(i).getText().substring(1)));
            inventoryItemsPricesSum+=inventoryItemsPrices.get(i);
        }
        CartPage cartPage = inventoryPage.openCart();
        cartPage.getCartBadge().shouldBe(visible);
        for (int i = 0; i < itemsAmount; i++) {
            assertEquals(inventoryItemsNames.get(i), cartPage.getItemsNames().get(i).getText(),
                    "Checking that items names are the same as ones on inventory page");
            assertEquals(inventoryItemsDescriptions.get(i), cartPage.getItemsDescriptions().get(i).getText(),
                    "Checking that items descriptions are the same as ones on inventory page");
            assertEquals(inventoryItemsPrices.get(i), Double.valueOf(cartPage.getItemsPrices().get(i).getText().substring(1)),
                    "Checking that items prices are the same as ones on inventory page");
            assertEquals("1", cartPage.getItemsCounters().get(i).getText(),
                    "Checking that there are one unit of item");
        }
        CheckoutOverviewPage overviewPage = cartPage.clickCheckout().typeFirstName(firstName).
                typeLastName(lastName).typePostalCode(postalCode).clickContinue();
        overviewPage.getCheckoutOverviewTitle().shouldBe(visible);
        for (int i = 0; i < itemsAmount; i++) {
            assertEquals(inventoryItemsNames.get(i), overviewPage.getItemsNames().get(i).getText(),
                    "Checking that items names are the same as ones on inventory page");
            assertEquals(inventoryItemsDescriptions.get(i), overviewPage.getItemsDescriptions().get(i).getText(),
                    "Checking that items descriptions are the same as ones on inventory page");
            assertEquals(inventoryItemsPrices.get(i), Double.valueOf(overviewPage.getItemsPrices().get(i).getText().substring(1)),
                    "Checking that items prices are the same as ones on inventory page");
            assertEquals("1", overviewPage.getItemsCounters().get(i).getText(),
                    "Checking that there are one unit of item");
        }
        assertEquals(inventoryItemsPricesSum, Double.valueOf(overviewPage.getSubtotal().getText().substring(13)),
                "Checking the sum of all items prices");
        assertEquals(tax, Double.valueOf(overviewPage.getTax().getText().substring(6)),
                "Checking tax value");
        assertEquals(inventoryItemsPricesSum + tax, Double.valueOf(overviewPage.getTotal().getText().substring(8)),
                "Checking total price");
        overviewPage.clickFinish().getThankYou().shouldBe(visible);
    }

}
