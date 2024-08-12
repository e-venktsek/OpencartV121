package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	// ExtentSParkReporter  -- Responsible for UI report.
	// ExtentReports		-- Populate common info on the report.
	// ExtentTest			-- Responsible for updating the status of the test methods

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {
	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    repName = "Test-Report-" + timeStamp + ".html"; // Assign to the instance variable
	    sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

	    sparkReporter.config().setDocumentTitle("Opencart Automation Report");
	    sparkReporter.config().setReportName("Opencart Functional Testing");
	    sparkReporter.config().setTheme(Theme.DARK);

	    extent = new ExtentReports();
	    extent.attachReporter(sparkReporter);

	    extent.setSystemInfo("Application", "OpenCart");
	    extent.setSystemInfo("Enviroment", "Demo");
	    extent.setSystemInfo("Tester", "Sekar");
	    extent.setSystemInfo("OS", "Windows 11");
	    extent.setSystemInfo("Browser", "Chrome");
	    extent.setSystemInfo("Project", "TestAutomation");

	    String os = testContext.getCurrentXmlTest().getParameter("os");
	    extent.setSystemInfo("Operating System", os);

	    String browser = testContext.getCurrentXmlTest().getParameter("browser");
	    extent.setSystemInfo("browser", browser);

	    List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();

	    if (!includedGroups.isEmpty()) {
	        extent.setSystemInfo("Included Groups", includedGroups.toString());
	    }
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got successfully executed");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
	    extent.flush();

	    String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
	    File extentReport = new File(pathOfExtentReport);

	    try {
	        Desktop.getDesktop().browse(extentReport.toURI());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	// Send automatic email after test result
	//   try {
	//     URL url = new URL("file///"+System.getProperty("user.dir")+"\\reports\\"+repName);
	//     ImageHtmlEmail email = new ImageHtmlEmail();
	//     email.setHostName("smtp.googlemail.com");
	//     email.setSmtpPort(465);
	//     email.setAuthenticator(new DefaultAuthenticator("sekarembitel@gmail.com", "your-password"));
	//     email.setFrom("sekarembitel@gmail.com");
	//     email.setSSLOnConnect(true);
	//     email.setMsg("Please find attachment");
	//     email.addTo("sekarembitel@gmail.com");
	//     email.setSubject("Extent Report");
	//     email.attach(url, "extent report", "please check report...");
	//     email.send();

	//   } catch (Exception e) {
	//     e.printStackTrace();
	//   }
	// }

}
