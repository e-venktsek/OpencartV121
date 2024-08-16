package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

  public static WebDriver driver;
  public Logger logger;
  public Properties p;

  @SuppressWarnings("deprecation")
@BeforeClass(groups = { "Sanity", "Regression", "Master" })
  @Parameters({ "os", "browser" })
  public void setup(String os, String br) throws IOException {
    //Loading config.properties file
    FileReader file = new FileReader(
      "./src//test//resources//config.properties"
    );
    p = new Properties();
    p.load(file);

    logger = LogManager.getLogger(this.getClass());

    //OS Selection for remote
    if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
      DesiredCapabilities capabilities = new DesiredCapabilities();

      //			capabilities.setPlatform(Platform.WIN11);
      //			capabilities.setBrowserName("chrome");

      if (os.equalsIgnoreCase("windows")) {
        capabilities.setPlatform(Platform.WIN11);
      } else if (os.equalsIgnoreCase("mac")) {
        capabilities.setPlatform(Platform.MAC);
      } else {
        System.out.println("No matching OS");
        return;
      }

      // Browser
      switch (br.toLowerCase()) {
        case "chrome":
          capabilities.setBrowserName("chrome");
          break;
        case "edge":
          capabilities.setBrowserName("MicrosoftEdge");
          break;
        case "firefox":
          capabilities.setBrowserName("firefox");
          break;
        default:
          System.out.println("No matching browser");
          return;
      }

      driver =
        new RemoteWebDriver(
          new URL("http://localhost:4444/wd/hub"),
          capabilities
        );
    }

    //Local test execution
    if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
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
    }
    driver.manage().deleteAllCookies();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    driver.get(p.getProperty("appURL1")); // Reading URL from properties file
  }

  @AfterClass(groups = { "Sanity", "Regression", "Master" })
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

  public String captureScreen(String testname) throws IOException {
    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss")
      .format(new Date());

    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

    String targetFilePath =
      System.getProperty("user.dir") +
      "/screenshots/" +
      testname +
      timeStamp +
      ".png";
    File targetFile = new File(targetFilePath);

    sourceFile.renameTo(targetFile);

    return targetFilePath;
  }
}
