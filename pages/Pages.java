package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.dashboard.BaseDashboard;
import pages.dashboard.topNavigation.TopNavigation;
import testSetup.TestSetup;

public class Pages extends TestSetup {
	protected static WebDriver		wd	= TestSetup.getDriver();
	protected static WebDriverWait	w	= TestSetup.getWaitDriver();

	public Pages() {
		//  	wd = TestSetup.getDriver();
		//		w = getWaitDriver();
	}

	public static HomePage home() {
		return new HomePage(wd, w);
	}

	public static BaseDashboard dashboard() {
		return new BaseDashboard(wd, w);
	}

	public static TopNavigation topNavigation() {
		return new TopNavigation(wd, w);
	}

}
