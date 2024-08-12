package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

  public AccountRegistrationPage(WebDriver driver) {
    super(driver);
  }

  //Locators

  @FindBy(xpath = "//input[@id='input-firstname']")
  WebElement txt_firstName;

  @FindBy(xpath = "//input[@id='input-lastname']")
  WebElement txt_lastName;

  @FindBy(xpath = "//input[@id='input-email']")
  WebElement txt_eMail;

  @FindBy(xpath = "//input[@id='input-telephone']")
  WebElement txt_telephone;

  @FindBy(xpath = "//input[@id='input-password']")
  WebElement txt_password;

  @FindBy(xpath = "//input[@id='input-confirm']")
  WebElement txt_passwordConfirm;

  @FindBy(xpath = "//input[@value='0']")
  WebElement radio_newsletterUnsubscribe;

  @FindBy(xpath = "//input[@name='agree']")
  WebElement check_agree;

  @FindBy(xpath = "//input[@value='Continue']")
  WebElement btn_continue;

  @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
  WebElement yourAccountHasBeenCreated;

  //Actions

  public void enterFirstName(String fname) {
    txt_firstName.sendKeys(fname);
  }

  public void enterLastName(String lname) {
    txt_lastName.sendKeys(lname);
  }

  public void enterEmail(String email) {
    txt_eMail.sendKeys(email);
  }

  public void enterTelephone(String tel) {
    txt_telephone.sendKeys(tel);
  }

  public void enterPassword(String pwd) {
    txt_password.sendKeys(pwd);
  }

  public void enterConfirmPassword(String pwd) {
    txt_passwordConfirm.sendKeys(pwd);
  }

  public void clickContinue() {
    btn_continue.click();
  }

  public void clickAgree() {
    check_agree.click();
  }

  public void clickNewsletter() {
    radio_newsletterUnsubscribe.click();
  }

  public String getYourAccountHasBeenCreated() {
    return yourAccountHasBeenCreated.getText();
  }
}
