package testSetup;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import testSetup.TestSetup;

public class TestListener implements ITestListener {
	WebDriver	driver		= null;
	String		filePath	= "./test-output/SCREENSHOTS/";

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.print("FAIL: " + result.getName() + " test failed");
		String methodName = result.getName().toString().trim();
		String instanceName = result.getInstanceName().toString().trim();
		takeScreenShot(methodName+" - "+instanceName);
	}

	public void takeScreenShot(String methodName) {
		// get the driver
		driver = TestSetup.getDriver();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot with test method name
		try {
			FileUtils.copyFile(scrFile, new File(filePath + methodName + ".png"));
			System.out.println(" - screenshot saved in " + filePath + " \n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
	}

	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}
}