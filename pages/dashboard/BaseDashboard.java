package pages.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.AbstractComponent;
import pages.dashboard.leftNavigation.Activities;
import pages.dashboard.leftNavigation.Assignments;
import pages.dashboard.leftNavigation.Classes;
import pages.dashboard.leftNavigation.MyLibrary;
import pages.dashboard.leftNavigation.Timetable;

public class BaseDashboard extends AbstractComponent {
	protected static WebDriver		wd;
	protected static WebDriverWait	w;

	private By						upcomingClassesHeader	= By.xpath("//*[@id='content-container']/div[1]/div/ng-include/div[1]/div[1]/h3");
	private By						dashboard				= By.id("t-dashboard");

	// Constructor
	public BaseDashboard(WebDriver wd, WebDriverWait wait) {
		BaseDashboard.wd = wd;
		BaseDashboard.w = wait;
	}

	// Verify dashboard
	public boolean verifyDashboardIsOpened() {
		return verifyPageText(upcomingClassesHeader, "UPCOMING CLASSES");
	}

	public void goTo() {
		wd.findElement(dashboard).click();
		Assert.assertTrue(verifyDashboardIsOpened(), "Dashboard verification failed");
	}

	/* Left navigation buttons */
	
	public Classes classes() {
		return new Classes(wd, w);
	}

	public Assignments assignments() {
		return new Assignments(wd, w);
	}

	public MyLibrary library() {
		return new MyLibrary(wd, w);
	}

	public Timetable timetable() {
		return new Timetable(wd, w);
	}

	public Activities activities() {
		return new Activities(wd, w);
	}

}
