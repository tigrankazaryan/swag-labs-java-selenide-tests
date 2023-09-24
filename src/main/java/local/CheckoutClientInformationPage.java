package local;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutClientInformationPage {

    private final SelenideElement firstNameInput = $(By.id("first-name"));
    private final SelenideElement lastNameInput = $(By.id("last-name"));
    private final SelenideElement postalCodeInput = $(By.id("postal-code"));
    private final SelenideElement continueButton = $(By.id("continue"));
    private final SelenideElement noFirstNameErrorMessage = $(By.xpath("//*[contains(text(), 'Error: First Name is required')]"));
    private final SelenideElement noLastNameErrorMessage = $(By.xpath("//*[contains(text(), 'Error: Last Name is required')]"));
    private final SelenideElement noPostalCodeErrorMessage = $(By.xpath("//*[contains(text(), 'Error: Postal Code is required')]"));

    public SelenideElement getNoFirstNameErrorMessage() {
        return noFirstNameErrorMessage;
    }

    public SelenideElement getNoLastNameErrorMessage() {
        return noLastNameErrorMessage;
    }

    public SelenideElement getNoPostalCodeErrorMessage() {
        return noPostalCodeErrorMessage;
    }

    public CheckoutClientInformationPage clickContinueWithError() {
        continueButton.click();
        return this;
    }

    public CheckoutClientInformationPage typeFirstName(String firstName) {
        firstNameInput.shouldBe(visible).setValue(firstName);
        return this;
    }

    public CheckoutClientInformationPage typeLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public CheckoutClientInformationPage typePostalCode(String postalCode) {
        postalCodeInput.setValue(postalCode);
        return this;
    }

    public CheckoutOverviewPage clickContinue() {
        continueButton.click();
        return new CheckoutOverviewPage();
    }

}
