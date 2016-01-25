package tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import pages.Pages;
import testSetup.TestSetup;

public class LoginAndCreateClass extends TestSetup {

	@Test
	public void canLogin() {
		Pages.home().login();
	}

	@Test(dependsOnMethods = { "canLogin" })
	public void canCreateClass() {
		Pages.topNavigation().addNewClass();
	}
	
	@Test(dependsOnMethods = { "canCreateClass" })
	public void canLogout(){
		Pages.topNavigation().logout();
	}

	@AfterSuite
	public void quit() {
		quitDriver();
	}

}
