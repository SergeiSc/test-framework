package pages.dashboard.leftNavigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.AbstractComponent;

public class Classes {
	protected WebDriver		wd;
	protected WebDriverWait	w;

	/* --- Element locators --- START */

	private By				classesHeader	= By.xpath("//*[@id='content-container']/div/div[3]/div[1]/h3");
	private By				classes			= By.id("t-classes");

	/* --- Element locators --- END */

	// Constructor
	public Classes(WebDriver wd, WebDriverWait wait) {
		this.wd = wd;
		this.w = wait;
	}

	public void goTo() {
		wd.findElement(classes).click();
		Assert.assertTrue(verifyClassesPage(), "Classes page verification failed");
		System.out.println("Classes page PASS");
	}

	public boolean verifyClassesPage() {
		AbstractComponent a = new AbstractComponent();
		return a.verifyPageText(classesHeader, "Your Classes");
	}

}
