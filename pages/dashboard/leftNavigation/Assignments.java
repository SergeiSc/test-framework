package pages.dashboard.leftNavigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.AbstractComponent;

public class Assignments {

	protected WebDriver		wd;
	protected WebDriverWait	w;

	/* --- Element locators --- START */

	private By				assignmentsHeader	= By.xpath("//*[@id='content-container']/div/div[1]/h2");
	private By				assignments			= By.id("t-assignments");

	/* --- Element locators --- END */

	// Constructor
	public Assignments(WebDriver wd, WebDriverWait wait) {
		this.wd = wd;
		this.w = wait;
	}

	public void goTo() {
		wd.findElement(assignments).click();
		Assert.assertTrue(verifyAssignmentsPage(), "Assignments page verification failed");
		System.out.println("All Assignments page PASS");
	}

	public boolean verifyAssignmentsPage() {
		AbstractComponent a = new AbstractComponent();
		return a.verifyPageText(assignmentsHeader, "All Assignments");
	}

}
