package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

  public MyAccountPage(WebDriver driver) {
    super(driver);
  }

  //Locators
  @FindBy(xpath = "//a[normalize-space()='Edit your account information']")
  WebElement editYourAccountInformation;

  @FindBy(xpath = "//h2[normalize-space()='My Orders']")
  WebElement myOrders;

  @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
  WebElement logout;

  //Actions
  public void clickEditYourAccountInformation() {
    editYourAccountInformation.click();
  }

  public boolean isMyOrdersDisplayed() {
    try {
      return (myOrders.isDisplayed());
    } catch (Exception e) {
      return false;
    }
  }

  public void clickLogout() {
    logout.click();
  }
}
