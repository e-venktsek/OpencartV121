package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is valid - Login success - Test pass - logout
 * Data is invalid - Login fail - Test fail
   Data is invalid - login success - Test fail - logout
   Data is valid - login fail - Test fail
*/
public class TC003_LoginDDT extends BaseClass {

  @Test(
    dataProvider = "LoginData",
    dataProviderClass = DataProviders.class,
    groups = {"Datadriven","Master"}
  )
  public void verify_loginDDT(String email, String password, String exp) {
    logger.info("**** Login DDT Started ********");
    try {
      // Home page
      HomePage hp = new HomePage(driver);
      hp.clickMyAccount();
      hp.clickLogin();

      // Login Page
      LoginPage lp = new LoginPage(driver);

      lp.enterEmail(email);
      lp.enterPassword(password);
      lp.clickLogin();

      // My Account Page
      MyAccountPage map = new MyAccountPage(driver);
      boolean targetPage = map.isMyOrdersDisplayed();

      if (exp.equalsIgnoreCase("Valid")) {
        if (targetPage) {
          Assert.assertTrue(true);
          map.clickLogout();
        } else {
          Assert.fail("Expected to log in successfully, but login failed.");
        }
      } else if (exp.equalsIgnoreCase("Invalid")) {
        if (targetPage) {
          map.clickLogout();
          Assert.fail("Expected login to fail, but login succeeded.");
        } else {
          Assert.assertTrue(true);
        }
      }
    } catch (Exception e) {
      logger.error("An exception occurred: " + e.getMessage());
      Assert.fail("Test case failed due to an exception: " + e.getMessage());
    }
    logger.info("**** Login DDT Completed ********");
  }
}
