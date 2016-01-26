package testSetup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pages.AbstractComponent;

public class TestSetup {
	private static WebDriver		wd;
	private static WebDriverWait	w;

	private int						webDriverWait_seconds	= 15;

	/* BASE SETUP */

	@Parameters({ "browserType", "URL" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String URL) {
		try {
			if (wd == null && w == null) {
				// Set main web driver
				
				setDriver(browserType, URL);

				// Set wait driver
				setWaitDriver(webDriverWait_seconds);

				// Set drivers for AbstractComponent class
				AbstractComponent ac = new AbstractComponent();
				ac.setDrivers(wd, w);
			}
			else {
				System.out.println(">");
			}

		} catch (Exception e) {
			System.out.println("ERROR while initializing browser" + e.getStackTrace());
		}
	}

	/* SET DRIVER */

	private void setDriver(String browserType, String URL) throws InterruptedException {
		switch (browserType) {
			case "chrome":
				wd = initChromeDriver(URL);
				break;
			case "firefox":
				wd = initFirefoxDriver(URL);
				break;
			case "ie":
				wd = initInternetExplorerDriver(URL);
				break;
			default:
				System.out.println("browser : " + browserType
						+ " is invalid, Launching Firefox as browser of choice..");
				wd = initFirefoxDriver(URL);
		}
	}

	/* INITIALIZE FIREFOX DRIVER */

	public static WebDriver initFirefoxDriver(String URL) {
		System.out.println("Launching FireFox browser");
		WebDriver wd = new FirefoxDriver();
		Point p = new Point(550, 50);
		//wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wd.manage().window().setSize(new Dimension(1300, 1025));
		wd.manage().window().setPosition(p);
		wd.navigate().to(URL);
		return wd;
	}

	/* INITIALIZE CHROME DRIVER */

	public static WebDriver initChromeDriver(String URL) throws InterruptedException {
		System.out.println("Launching Chrome browser");
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		WebDriver wd = new ChromeDriver();
		Point p = new Point(550, 50);
		wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wd.manage().window().setSize(new Dimension(1300, 1025));
		wd.manage().window().setPosition(p);
		wd.navigate().to(URL);
		return wd;
	}

	/* INITIALIZE INTERNET EXPLORER DRIVER */

	public static WebDriver initInternetExplorerDriver(String URL) {
		System.setProperty("webdriver.ie.driver", "Drivers/IEDriverServer.exe");
		WebDriver wd = new InternetExplorerDriver();
		wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wd.navigate().to(URL);
		return wd;
	}
	
	/* CLOSE BROWSER */
	
	@AfterSuite
	public void quit() {
		quitDriver();
	}

	/* SET WAIT DRIVER */

	private void setWaitDriver(int i) {
		w = new WebDriverWait(wd, i);
	}

	/* GET DRIVER */

	public static WebDriver getDriver() {
		return wd;
	}

	/* GET WAIT DRIVER */

	public static WebDriverWait getWaitDriver() {
		return w;
	}

	/* QUIT */

	public static void quitDriver() {
		wd.quit();
	}

}
