package tests;

import org.testng.annotations.Test;

import pages.Pages;
import testSetup.TestSetup;

public class LoginAndLogout extends TestSetup {

	@Test
	public void canLogin() {
		Pages.home().login();
	}

	@Test(dependsOnMethods = { "canLogin" })
	public void canLogout() {
		Pages.topNavigation().logout();
	}

}
