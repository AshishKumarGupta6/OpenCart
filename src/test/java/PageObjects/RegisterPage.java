package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    @FindBy(id = "input-firstname")
     WebElement firstNameField;

    @FindBy(id = "input-lastname")
     WebElement lastNameField;

    @FindBy(id = "input-email")
     WebElement emailField;

    @FindBy(id = "input-telephone")
     WebElement telephoneField;

    @FindBy(id = "input-password")
     WebElement passwordField;

    @FindBy(id = "input-confirm")
     WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@value='Continue']")
     WebElement submitButton;

    @FindBy(xpath = "//input[@type='checkbox']")
     WebElement privacyCheckbox;

    By ele = By.xpath("//div[@id='content']/h1");


    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String value)       { firstNameField.sendKeys(value); }
    public void enterLastName(String value)        { lastNameField.sendKeys(value); }
    public void enterEmail(String value)           { emailField.sendKeys(value); }
    public void enterTelephone(String value)       { telephoneField.sendKeys(value); }
    public void enterPassword(int value)        { passwordField.sendKeys(String.valueOf(value)); }
    public void enterConfirmPassword(int value) { confirmPasswordField.sendKeys(String.valueOf(value)); }
    public void privacy()                          { privacyCheckbox.click(); }
    public void clickSubmit()                      { submitButton.click(); }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(ele)).getText();
    }


    public boolean isValidDestination(String destination) {
        if (destination == null || destination.trim().isEmpty()) return false;

        for (char c : destination.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                System.out.println("Invalid character found: " + c);
                return false;
            }
        }
        return true;
    }
}
