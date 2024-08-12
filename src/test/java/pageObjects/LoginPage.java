package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//input[@id='input-email']")
  WebElement txt_email;

  @FindBy(xpath = "//input[@id='input-password']")
  WebElement txt_password;

  @FindBy(xpath = "//input[@value='Login']")
  WebElement btn_login;

  @FindBy(
    xpath = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']"
  )
  WebElement link_forgottenPassword;

  @FindBy(xpath = "//a[normalize-space()='Continue']")
  WebElement btn_continueButton;

  public void enterEmail(String email) {
    txt_email.sendKeys(email);
  }

  public void enterPassword(String password) {
    txt_password.sendKeys(password);
  }

  public void clickLogin() {
    btn_login.click();
  }

  public void clickForgottenPassword() {
    link_forgottenPassword.click();
  }

  public void clickContinueButton() {
    btn_continueButton.click();
  }

}
