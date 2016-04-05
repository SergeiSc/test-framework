package pages.dashboard.leftNavigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.AbstractComponent;

public class MyLibrary {

	protected WebDriver		wd;
	protected WebDriverWait	w;

	/* --- Element locators --- START */

	private By				libraryHeader	= By.xpath("//*[@id='content-container']/div/div[1]/div/h2");
	private By				library			= By.id("t-library");

	/* --- Element locators --- END */

	// Constructor
	public MyLibrary(WebDriver wd, WebDriverWait wait) {
		this.wd = wd;
		this.w = wait;
	}

	public void goTo() {
		wd.findElement(library).click();
		Assert.assertTrue(verifyLibraryPage(), "Library page verification failed");
		System.out.println("Library page PASS");
	}

	public boolean verifyLibraryPage() {
		AbstractComponent a = new AbstractComponent();
		return a.verifyPageText(libraryHeader, "Library");
	}

}
