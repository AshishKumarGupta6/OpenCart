package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@title='My Account']")
     WebElement myAccountDropdown;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Register']")
     WebElement registerLink;

    @FindBy(xpath="//ul[contains(@class,'dropdown-menu-right')]//a[text()='Logout']")
    WebElement logout;

    public void myAccount() {
        myAccountDropdown.click();
    }

    public void register() {
        registerLink.click();
    }

    public void logOut(){

        logout.click();
    }
}
