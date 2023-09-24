import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import local.MainPage;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.visible;
import static local.ConfigProvider.STANDARD_USER_LOGIN;
import static local.ConfigProvider.STANDARD_USER_PASSWORD;

@Order(3)
@Feature("Client information tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientInformationTest extends BaseTest {

    @Test
    @Order(1)
    @DisplayName("Empty first name on client information page")
    @Description("Clicking \"Continue\" button on client information page with first name input field being not filled out.")
    public void checkoutFirstNameErrorMessage() {
        new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).addToCart(1).
                openCart().clickCheckout().clickContinueWithError().
                getNoFirstNameErrorMessage().shouldBe(visible);
    }

    @Test
    @Order(2)
    @DisplayName("Empty last name on client information page")
    @Description("Clicking \"Continue\" button on client information page with last name input field being not filled out.")
    public void checkoutLastNameErrorMessage() {
        new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).addToCart(1).
                openCart().clickCheckout().typeFirstName("Test").clickContinueWithError().
                getNoLastNameErrorMessage().shouldBe(visible);
    }

    @Test
    @Order(3)
    @DisplayName("Empty postal code on client information page")
    @Description("Clicking \"Continue\" button on client information page with postal code input field being not filled out.")
    public void checkoutPostalCodeErrorMessage() {
        new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).addToCart(1).
                openCart().clickCheckout().typeFirstName("Test").typeLastName("Testing").
                clickContinueWithError().getNoPostalCodeErrorMessage().shouldBe(visible);
    }

}
