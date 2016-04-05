package pages.dashboard.leftNavigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.AbstractComponent;

public class Timetable {

	protected WebDriver		wd;
	protected WebDriverWait	w;

	/* --- Element locators --- START */

	private By				timetableHeader	= By.xpath("//*[@id='content-container']/div/div[1]/div/h2");
	private By				timetable		= By.id("t-timetable");

	/* --- Element locators --- END */

	// Constructor
	public Timetable(WebDriver wd, WebDriverWait wait) {
		this.wd = wd;
		this.w = wait;
	}

	public void goTo() {
		wd.findElement(timetable).click();
		Assert.assertTrue(verifyTimetablePage(), "Timetable page verification failed");
		System.out.println("Timetable page PASS");
	}

	public boolean verifyTimetablePage() {
		AbstractComponent a = new AbstractComponent();
		return a.verifyPageText(timetableHeader, "Timetable");
	}

}
