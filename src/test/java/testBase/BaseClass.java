package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

  public WebDriver driver;
  public Logger logger;
  public Properties p;

  @BeforeClass
  @Parameters({ "os", "browser" })
  public void setup(String os, String br) throws IOException {
	  
	  
    //Loading config.properties file
    FileReader file = new FileReader("./src//test//resources//config.properties");
    p = new Properties();
    p.load(file);

    logger = LogManager.getLogger(this.getClass());

    switch (br.toLowerCase()) {
      case "chrome":
        driver = new ChromeDriver();
        break;
      case "edge":
        driver = new EdgeDriver();
        break;
      case "firefox":
        driver = new FirefoxDriver();
        break;
      default:
        System.out.println("Invalid browser name...");
        return;
    }

    driver.manage().deleteAllCookies();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    driver.get(p.getProperty("appURL1")); // Reading URL from properties file
  }

  @AfterClass
  public void tearDown() {
    driver.quit();
  }

  public String randomeString() {
    String generatedString = RandomStringUtils.randomAlphabetic(5);
    return generatedString;
  }

  public String randomeNumber() {
    String generatedNumber = RandomStringUtils.randomNumeric(9);
    return generatedNumber;
  }

  public String randomeAlphaNumeric() {
    String generatedAlphaNumeric = RandomStringUtils.randomAlphanumeric(4);
    return (generatedAlphaNumeric + "@" + generatedAlphaNumeric);
  }
}
