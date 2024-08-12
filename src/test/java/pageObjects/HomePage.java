package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

  public HomePage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath="//a[@title='My Account']") WebElement myAccount;

  @FindBy(xpath = "//a[normalize-space()='Register']")
  WebElement register;

  @FindBy(xpath = "//a[normalize-space()='Login']")
  WebElement login;

  @FindBy(xpath = "//a[@id='wishlist-total']//i[@class='fa fa-heart']")
  WebElement wishList;

  @FindBy(
    xpath = "//a[@title='Shopping Cart']//i[@class='fa fa-shopping-cart']"
  )
  WebElement cartIcon;

  @FindBy(xpath = "//i[@class='fa fa-phone']")
  WebElement phone;

  @FindBy(xpath = "//input[@placeholder='Search']")
  WebElement searchTextbox;

  @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
  WebElement searchButton;

  @FindBy(xpath = "//a[normalize-space()='Qafox.com']")
  WebElement qafoxLogo;

  @FindBy(xpath = "//button[@class='btn btn-link dropdown-toggle']")
  WebElement currency;

  @FindBy(xpath = "//a[normalize-space()='Desktops']")
  WebElement desktopsMenu;

  @FindBy(xpath = "//a[normalize-space()='About Us']")
  WebElement aboutUs;

  @FindBy(xpath = "//a[normalize-space()='Delivery Information']")
  WebElement deliveryInformation;

  @FindBy(xpath = "//a[normalize-space()='Privacy Policy']")
  WebElement privacyPolicy;

  @FindBy(xpath = "//a[normalize-space()='Terms & Conditions']")
  WebElement termsAndConditions;

  @FindBy(xpath = "//a[normalize-space()='Contact Us']")
  WebElement contactUs;

  @FindBy(xpath = "//a[normalize-space()='Returns']")
  WebElement returns;

  @FindBy(xpath = "//a[normalize-space()='Site Map']")
  WebElement siteMap;

  @FindBy(xpath = "//a[normalize-space()='Brands']")
  WebElement brands;

  @FindBy(xpath = "//a[normalize-space()='Gift Certificates']")
  WebElement giftCertificates;

  @FindBy(xpath = "//a[normalize-space()='Affiliate']")
  WebElement affiliate;

  @FindBy(xpath = "//a[normalize-space()='Specials']")
  WebElement specials;

  @FindBy(
    xpath = "//ul[@class='list-unstyled']//a[normalize-space()='My Account']"
  )
  WebElement footerMyAccount;

  @FindBy(
    xpath = "//ul[@class='list-unstyled']//a[normalize-space()='Order History']"
  )
  WebElement orderHistory;

  @FindBy(xpath = "//a[normalize-space()='Wish List']")
  WebElement footerWishList;

  @FindBy(xpath = "//a[normalize-space()='Newsletter']")
  WebElement footerNewsletter;

  public void clickMyAccount() {
    myAccount.click();
  }

  public void clickRegister() {
    register.click();
  }

  public void clickLogin() {
    login.click();
  }

	
}
