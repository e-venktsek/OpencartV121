package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

  @Test(groups = {"Master","Sanity"})
  public void loginTest() {
    logger.info("**** Login test started******");

    try {
      //login
      logger.info("Login test started");
      HomePage hp = new HomePage(driver);
      hp.clickMyAccount();
      hp.clickLogin();

      LoginPage lp = new LoginPage(driver);

      lp.enterEmail(p.getProperty("email"));
      lp.enterPassword(p.getProperty("password"));
      lp.clickLogin();

      MyAccountPage map = new MyAccountPage(driver);
      boolean targetPage = map.isMyOrdersDisplayed();
      Assert.assertTrue(targetPage);
      logger.info("Login test passed");
      map.clickLogout();
      logger.info("Logout test passed");
    } catch (Exception e) {
      logger.error("Login test failed");
      Assert.fail();
    }
  }
}
