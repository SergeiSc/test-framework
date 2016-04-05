package pages.dashboard.leftNavigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.AbstractComponent;

public class Activities {

	protected WebDriver		wd;
	protected WebDriverWait	w;

	/* --- Element locators --- START */

	private By				activities			= By.id("t-activities");
	private By				activityFeedHeader	= By.xpath("//*[@id='content-container']/div[1]/div[1]/div/h2");

	/* --- Element locators --- END */

	// Constructor
	public Activities(WebDriver wd, WebDriverWait wait) {
		this.wd = wd;
		this.w = wait;
	}

	public void goTo() {
		wd.findElement(activities).click();
		Assert.assertTrue(verifyActivitiesPage(), "Activities page verification failed");
		System.out.println("Activities page PASS");
	}

	public boolean verifyActivitiesPage() {
		AbstractComponent a = new AbstractComponent();
		return a.verifyPageText(activityFeedHeader, "Activity Feed");
	}

}
