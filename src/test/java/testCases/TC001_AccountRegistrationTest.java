package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

  @Test(groups = { "Regression", "Master" })
  void testAccountRegistration() {
    try {
      logger.info("*****Registration Test starting******");

      HomePage hp = new HomePage(driver);

      hp.clickMyAccount();

      logger.info("Clicked on Registration link");

      hp.clickRegister();

      String password = randomeAlphaNumeric();

      AccountRegistrationPage registration = new AccountRegistrationPage(
        driver
      );

      registration.enterFirstName(randomeString().toUpperCase());
      registration.enterLastName(randomeString().toUpperCase());
      registration.enterEmail(randomeString() + "test@gmail.com");
      registration.enterTelephone("9" + randomeNumber());
      registration.enterPassword(password);
      registration.enterConfirmPassword(password);
      registration.clickAgree();
      registration.clickNewsletter();
      registration.clickContinue();
      String confMsg = registration.getYourAccountHasBeenCreated();

      logger.info("Validating registration successful message");

      if (confMsg.equals("Your Account Has Been Created!")) {
        Assert.assertTrue(true);
      } else {
        logger.error("Test Failed");
        logger.debug("Debug logs...");
        Assert.assertTrue(false);
      }
    } catch (Exception e) {
      Assert.fail();
    }

    logger.info("*****Registration Test execution completed******");
  }
}
