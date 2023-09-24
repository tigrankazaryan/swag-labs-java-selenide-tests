package local;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public static final String URL = "https://www.saucedemo.com/";

    private final SelenideElement usernameInput = $(By.id("user-name"));
    private final SelenideElement passwordInput = $(By.id("password"));
    private final SelenideElement loginButton = $(By.id("login-button"));
    private final SelenideElement blockedUserErrorMessage = $(By.xpath("//*[contains(text(), 'Epic sadface: Sorry, this user has been locked out.')]"));
    private final SelenideElement noSuchUsernameAndPasswordErrorMessage = $(By.xpath("//*[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]"));
    private final SelenideElement noUsernameErrorMessage = $(By.xpath("//*[contains(text(), 'Epic sadface: Username is required')]"));
    private final SelenideElement noPasswordErrorMessage = $(By.xpath("//*[contains(text(), 'Epic sadface: Password is required')]"));
    private final SelenideElement noAuthorizationErrorMessage = $(By.xpath("//*[contains(text(), 'Epic sadface: You can only access')]"));

    public MainPage() {
        open(URL);
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }

    public SelenideElement getBlockedUserErrorMessage() {
        return blockedUserErrorMessage;
    }

    public SelenideElement getNoSuchUsernameAndPasswordErrorMessage() {
        return noSuchUsernameAndPasswordErrorMessage;
    }

    public SelenideElement getNoUsernameErrorMessage() {
        return noUsernameErrorMessage;
    }

    public SelenideElement getNoPasswordErrorMessage() {
        return noPasswordErrorMessage;
    }

    public SelenideElement getNoAuthorizationErrorMessage() {
        return noAuthorizationErrorMessage;
    }

    public MainPage typeUsername(String username) {
        usernameInput.shouldBe(visible).setValue(username);
        return this;
    }

    public InventoryPage userLogin(String username, String password) {
        typeUsername(username);
        passwordInput.setValue(password);
        loginButton.click();
        return new InventoryPage();
    }

    public InventoryPage userLoginByPressingEnter(String username, String password) {
        typeUsername(username);
        passwordInput.setValue(password);
        passwordInput.sendKeys(Keys.ENTER);
        return new InventoryPage();
    }

    public MainPage incorrectUserLogin(String username, String password) {
        typeUsername(username);
        passwordInput.setValue(password);
        loginButton.click();
        return this;
    }

    public MainPage incorrectUserLogin() {
        loginButton.shouldBe(visible).click();
        return this;
    }

    public MainPage incorrectUserLogin(String password) {
        passwordInput.shouldBe(visible).setValue(password);
        loginButton.click();
        return this;
    }

    public InventoryPage goForward() {
        forward();
        return new InventoryPage();
    }

    public MainPage goBackAfterLogout() {
        back();
        return this;
    }

}
