import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import local.InventoryPage;
import local.MainPage;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.visible;
import static local.ConfigProvider.*;
import static local.CustomFunctions.invertFirstCharacter;

@Order(1)
@Feature("Login tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {

    @Test
    @Order(1)
    @DisplayName("Standard user login")
    @Description("Authorization of standard user with correct username and password.")
    public void standardUserLogin() {
        new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                getProductsTitle().shouldBe(visible);
    }

    @Test
    @Order(2)
    @DisplayName("Standard user login by pressing Enter")
    @Description("Authorization of standard user with correct username and password using Enter key instead of clicking Login button.")
    public void standardUserLoginByPressingEnter() {
        new MainPage().userLoginByPressingEnter(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                getProductsTitle().shouldBe(visible);
    }

    @Test
    @Order(3)
    @DisplayName("Blocked user login")
    @Description("Attempt to authorize blocked user.")
    public void blockedUserLogin() {
        new MainPage().incorrectUserLogin(BLOCKED_USER_LOGIN, BLOCKED_USER_PASSWORD).
                getBlockedUserErrorMessage().shouldBe(visible);
    }

    @Test
    @Order(4)
    @DisplayName("Wrong password")
    @Description("Attempt to log in with name of existing user and wrong password.")
    public void wrongPassword() {
        new MainPage().incorrectUserLogin(STANDARD_USER_LOGIN, "SomeWrongPassword").
                getNoSuchUsernameAndPasswordErrorMessage().shouldBe(visible);
    }

    @Test
    @Order(5)
    @DisplayName("Wrong username and correct password")
    @Description("Attempt to log in with nonexistent username and correct password of some user.")
    public void wrongUsernameAndCorrectPassword() {
        new MainPage().incorrectUserLogin("SomeWrongUsername", STANDARD_USER_PASSWORD).
                getNoSuchUsernameAndPasswordErrorMessage().shouldBe(visible);
    }

    @Test
    @Order(6)
    @DisplayName("Wrong case of correct username")
    @Description("Attempt to authorize existing user but with mistaken case in username.")
    public void wrongCaseOfCorrectUsername() {
        new MainPage().incorrectUserLogin(invertFirstCharacter(STANDARD_USER_LOGIN), STANDARD_USER_PASSWORD).
                getNoSuchUsernameAndPasswordErrorMessage().shouldBe(visible);
    }

    @Test
    @Order(7)
    @DisplayName("Wrong case of correct password")
    @Description("Attempt to authorize existing user but with mistaken case in password.")
    public void wrongCaseOfCorrectPassword() {
        new MainPage().incorrectUserLogin(STANDARD_USER_LOGIN, invertFirstCharacter(STANDARD_USER_PASSWORD)).
                getNoSuchUsernameAndPasswordErrorMessage().shouldBe(visible);
    }

    @Test
    @Order(8)
    @DisplayName("Empty username and password")
    @Description("Clicking \"Login\" button with username and password input fields not filled out.")
    public void emptyUsernameAndPassword() {
        new MainPage().incorrectUserLogin().getNoUsernameErrorMessage().shouldBe(visible);
    }

    @Test
    @Order(9)
    @DisplayName("Empty username")
    @Description("Clicking \"Login\" button with username input field not filled out. Password of existing user is used.")
    public void emptyUsername() {
        new MainPage().incorrectUserLogin(STANDARD_USER_PASSWORD).getNoUsernameErrorMessage().shouldBe(visible);
    }

    @Test
    @Order(10)
    @DisplayName("Empty password")
    @Description("Clicking \"Login\" button with password input field not filled out. Name of existing user is used.")
    public void emptyPassword() {
        new MainPage().typeUsername(STANDARD_USER_LOGIN).incorrectUserLogin().
                getNoPasswordErrorMessage().shouldBe(visible);
    }

    @Test
    @Order(11)
    @DisplayName("Navigating after user login")
    @Description("Navigating back and forward in browser after successful authorization and checking authorization for being kept after navigation.")
    public void navigateAfterLogin() {
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD);
        inventoryPage.getProductsTitle().shouldBe(visible);
        MainPage mainPage = inventoryPage.goBack();
        mainPage.getLoginButton().shouldBe(visible);
        mainPage.goForward().getProductsTitle().shouldBe(visible);
    }

    @Test
    @Order(12)
    @DisplayName("Navigating after user logout")
    @Description("Logging out and trying to navigate back to page that requires authorization.")
    public void navigateAfterLoginAndLogout() {
        MainPage mainPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).logout();
        mainPage.getLoginButton().shouldBe(visible);
        mainPage.goBackAfterLogout().getNoAuthorizationErrorMessage().shouldBe(visible);
    }

}
